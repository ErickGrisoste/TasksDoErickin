package com.erickin.TasksDoErickin.controller;


import com.erickin.TasksDoErickin.model.UsuarioModel;
import com.erickin.TasksDoErickin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> salvarUser(@RequestBody UsuarioModel user){
        UsuarioModel usuarioCriado = usuarioService.salvarUser(user);
        URI location = URI.create("/usuarios/" + usuarioCriado.getId());
        return ResponseEntity.created(location).body(usuarioCriado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> editarUser(@PathVariable Long id, @RequestBody UsuarioModel novoUser){
        return ResponseEntity.ok(usuarioService.editarUsuario(id, novoUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarUser(@PathVariable Long id){
        usuarioService.deletarUser(id);
        return ResponseEntity.noContent().build();
    }


}
