package com.erickin.TasksDoErickin.service;

import com.erickin.TasksDoErickin.exceptions.ProjetoErroAoEditarException;
import com.erickin.TasksDoErickin.exceptions.ProjetoNaoEncontradoException;
import com.erickin.TasksDoErickin.model.ProjetoModel;
import com.erickin.TasksDoErickin.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public ProjetoModel criarProjeto(ProjetoModel novoProjeto){
        return projetoRepository.save(novoProjeto);
    }

    public List<ProjetoModel> listarProjetos(){
        return projetoRepository.findAll();
    }

    public ProjetoModel buscarProjetoPorId(Long id){
        return projetoRepository.findById(id).
                orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    public ProjetoModel editarProjeto(Long id, ProjetoModel novoProjeto){
        ProjetoModel projetoEncontrado = buscarProjetoPorId(id);

        //if(!projetoEncontrado.getCriador().equals(novoProjeto.getCriador()))
            //throw new ProjetoErroAoEditarException("Não é possível editar um projeto de outro criador.");

        if(novoProjeto.getDataInicio() != null)
            projetoEncontrado.setDataInicio(novoProjeto.getDataInicio());

        if(novoProjeto.getDataFim() != null)
            projetoEncontrado.setDataFim(novoProjeto.getDataFim());

        if(novoProjeto.getNome() != null)
            projetoEncontrado.setNome(novoProjeto.getNome());

        if(novoProjeto.getDescricao() != null)
            projetoEncontrado.setDescricao(novoProjeto.getDescricao());

        return projetoRepository.save(projetoEncontrado);
    }

    public void deletarProjeto(Long id){

        if(!projetoRepository.existsById(id))
            throw new ProjetoNaoEncontradoException(id);

        projetoRepository.deleteById(id);
    }



}
