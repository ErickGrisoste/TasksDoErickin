package com.erickin.TasksDoErickin.exceptions;

public class ProjetoNaoEncontradoException extends RuntimeException {

    public ProjetoNaoEncontradoException(Long id) {
        super("Nenhum projeto encontrado com id: " + id);
    }
}
