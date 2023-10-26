package com.lock.lockpessimista.services;

import com.lock.lockpessimista.models.Pedido;
import com.lock.lockpessimista.models.Produto;
import com.lock.lockpessimista.repositories.PedidoRepository;
import com.lock.lockpessimista.repositories.ProdutoRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;

@Service
public class Servico {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
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

        //Integer quantidadeEstoque = produtoRepository.findQuantidadeEstoqueById(pedido.getProduto().getId());

        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        if (pedido.getQuantidadeItem() > produto.getQuantidadeEstoque()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade em estoque insuficiente");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - pedido.getQuantidadeItem());

        produtoRepository.save(produto);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
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

