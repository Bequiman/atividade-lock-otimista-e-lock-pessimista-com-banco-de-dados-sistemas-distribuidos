package com.lock.lockpessimista.controllers;

import com.lock.lockpessimista.models.Produto;
import com.lock.lockpessimista.services.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    Servico servico;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return servico.findAll();
    }

}
