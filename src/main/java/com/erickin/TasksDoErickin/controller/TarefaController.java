package com.erickin.TasksDoErickin.controller;

import com.erickin.TasksDoErickin.model.TarefaModel;
import com.erickin.TasksDoErickin.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaModel> salvarNovaTarefa(@RequestBody TarefaModel novaTarefa){
        TarefaModel tarefaCriada = tarefaService.criarTarefa(novaTarefa);
        URI location = URI.create("/tarefas/" + tarefaCriada.getId());
        return ResponseEntity.created(location).body(tarefaCriada);
    }

    @GetMapping("/projeto/{idProjeto}")
    public ResponseEntity<List<TarefaModel>> listarTarefaPorProjeto(@PathVariable Long idProjeto){
        return ResponseEntity.ok(tarefaService.listarTarefasPorProjeto(idProjeto));
    }

    @GetMapping("/{idTarefa}")
    public ResponseEntity<TarefaModel> buscarTarefaPorId(@PathVariable Long idTarefa){
        return ResponseEntity.ok(tarefaService.buscarTarefaPorId(idTarefa));
    }

    @PutMapping("/{idTarefa}")
    public ResponseEntity<TarefaModel> editarTarefa(@PathVariable Long idTarefa, @RequestBody TarefaModel novaTarefa){
        return ResponseEntity.ok(tarefaService.editarTarefa(idTarefa, novaTarefa));
    }

    @DeleteMapping("/{idTarefa}")
    public ResponseEntity<HttpStatus> deletarTarefa(@PathVariable Long idTarefa){
        tarefaService.deletarTarefa(idTarefa);
        return ResponseEntity.noContent().build();
    }








}
