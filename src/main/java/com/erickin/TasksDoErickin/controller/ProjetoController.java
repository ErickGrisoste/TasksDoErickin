package com.erickin.TasksDoErickin.controller;

import com.erickin.TasksDoErickin.model.ProjetoModel;
import com.erickin.TasksDoErickin.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoModel> criarProjeto(@RequestBody ProjetoModel projeto){
        ProjetoModel projetoCriado = projetoService.criarProjeto(projeto);
        URI location = URI.create("/projetos/" + projetoCriado.getId());
        return ResponseEntity.created(location).body(projetoCriado);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoModel>> listarProjetos(){
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoModel> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(projetoService.buscarProjetoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoModel> editarProjeto(@PathVariable Long id, @RequestBody ProjetoModel novoProjeto){
        return ResponseEntity.ok(projetoService.editarProjeto(id, novoProjeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarProjeto(@PathVariable Long id){
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }


}
