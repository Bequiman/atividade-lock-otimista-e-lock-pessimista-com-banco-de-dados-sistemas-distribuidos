package com.iftm.demo.controllers;

import com.iftm.demo.models.Pedido;
import com.iftm.demo.models.Produto;
import com.iftm.demo.services.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


