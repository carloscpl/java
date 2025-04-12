package com.carlos.spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.spring_boot.model.Pessoa;


@RestController
public class Controller {
    
    @GetMapping("/{nome}")
    public String boasvindas(@PathVariable String nome) {
        return "Seja bem vindo (a) " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }
    
    
}

