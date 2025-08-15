package com.erickin.TasksDoErickin.service;

import com.erickin.TasksDoErickin.exceptions.TarefaNaoEncontradaException;
import com.erickin.TasksDoErickin.model.ProjetoModel;
import com.erickin.TasksDoErickin.model.TarefaModel;
import com.erickin.TasksDoErickin.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    //Salva uma nova tarefa
    public TarefaModel criarTarefa(TarefaModel novaTarefa){
        return tarefaRepository.save(novaTarefa);
    }

    public TarefaModel buscarTarefaPorId(Long id){
        return tarefaRepository.findById(id).
                orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    //Lista tarefas por projeto
    public List<TarefaModel> listarTarefasPorProjeto(Long idProjeto){
        return tarefaRepository.findByProjetoId(idProjeto);
    }

    //Edita tarefa
    public TarefaModel editarTarefa(Long id, TarefaModel novaTarefa){
        TarefaModel tarefaEncontrada = buscarTarefaPorId(id);

        if(novaTarefa.getNome() != null)
            tarefaEncontrada.setNome(novaTarefa.getNome());

        if(novaTarefa.getDescricao() != null)
            tarefaEncontrada.setDescricao(novaTarefa.getDescricao());

        if(novaTarefa.getProjeto() != null)
            tarefaEncontrada.setProjeto(novaTarefa.getProjeto());

        if(novaTarefa.getStatus() != null)
            tarefaEncontrada.setStatus(novaTarefa.getStatus());

        tarefaEncontrada.setResponsavel(novaTarefa.getResponsavel());

        return tarefaRepository.save(tarefaEncontrada);
    }

    //Deleta tarefa
    public void deletarTarefa(Long idTarefa){
        TarefaModel tarefa = buscarTarefaPorId(idTarefa);

        ProjetoModel projeto = tarefa.getProjeto();
        projeto.getTarefas().remove(tarefa);

        tarefaRepository.deleteById(idTarefa);
    }









}
