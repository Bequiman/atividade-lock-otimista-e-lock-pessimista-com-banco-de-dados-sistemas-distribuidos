package com.lock.lockpessimista.controllers;

import com.lock.lockpessimista.models.Pedido;
import com.lock.lockpessimista.services.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private Servico servico;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pedido pedido){
        return servico.save(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAllOrders() {
        return servico.findAllOrders();
    }


}


