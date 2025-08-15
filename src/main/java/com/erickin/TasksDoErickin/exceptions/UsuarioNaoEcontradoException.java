package com.erickin.TasksDoErickin.exceptions;

public class UsuarioNaoEcontradoException extends RuntimeException {

    public UsuarioNaoEcontradoException(Long id) {
        super("Nenhum usuário encontrado com o id: " + id);
    }
}
