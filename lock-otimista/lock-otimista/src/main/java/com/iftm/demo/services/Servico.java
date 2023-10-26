package com.iftm.demo.services;

import com.iftm.demo.models.Pedido;
import com.iftm.demo.models.Produto;
import com.iftm.demo.repositories.PedidoRepository;
import com.iftm.demo.repositories.ProdutoRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Servico {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Transactional
    public ResponseEntity<?> save(Pedido pedido) {
        if (pedido.getQuantidadeItem() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido não especificado");
        }

        if (pedido.getProduto() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não especificado");
        }

        if (pedido.getQuantidadeItem() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade de item inválida");
        }

        //Produto produto = produtoRepository.findById(pedido.getProduto().getId()).orElse(null);

        Produto produto = produtoRepository.findAndLockForWrite(pedido.getProduto().getId());

        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        if (pedido.getQuantidadeItem() > produto.getQuantidadeEstoque()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade em estoque insuficiente");
        }

        pedido.getProduto().setCodigoVersionamento(produto.getCodigoVersionamento());

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - pedido.getQuantidadeItem());

        produtoRepository.save(produto);

        var dbPedido = pedidoRepository.save(pedido);

        //return ResponseEntity.ok(pedidoRepository.save(pedido));

        return ResponseEntity.ok(dbPedido);
    }

    public ResponseEntity<List<Produto>> findAll() {
        var dbProduto = produtoRepository.findAll();

        if(dbProduto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dbProduto);
    }

    public ResponseEntity<List<Pedido>> findAllOrders() {
        var dbPedido = pedidoRepository.findAll();

        if(dbPedido.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dbPedido);
    }
}



