package com.erickin.TasksDoErickin.service;

import com.erickin.TasksDoErickin.exceptions.UsuarioNaoEcontradoException;
import com.erickin.TasksDoErickin.model.UsuarioModel;
import com.erickin.TasksDoErickin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public UsuarioModel salvarUser(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarUsers(){
        return usuarioRepository.findAll();
    }

    public void deletarUser(Long id){

        if(!usuarioRepository.existsById(id)){
            throw new UsuarioNaoEcontradoException(id);
        }

        usuarioRepository.deleteById(id);
    }

    public UsuarioModel buscarUserPorId(Long id){
        return usuarioRepository.findById(id).
                orElseThrow(() -> new UsuarioNaoEcontradoException(id));
    }

    public UsuarioModel editarUsuario(Long id, UsuarioModel novoUser){

        UsuarioModel usuarioEncontrado = buscarUserPorId(id);

        //if(!usuarioEncontrado.getId().equals(novoUser.getId()))
            //throw new RuntimeException("Erro ao editar, id errado");

        if(novoUser.getEmail() != null)
            usuarioEncontrado.setEmail(novoUser.getEmail());

        if(novoUser.getSenha() != null)
            usuarioEncontrado.setSenha(novoUser.getSenha());

        if(novoUser.getUsername() != null)
            usuarioEncontrado.setUsername(novoUser.getUsername());

        return usuarioRepository.save(usuarioEncontrado);
    }

}
