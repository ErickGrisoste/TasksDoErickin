package com.erickin.TasksDoErickin.exceptions;

public class ProjetoErroAoEditarException extends RuntimeException {
    public ProjetoErroAoEditarException(String message) {
        super("Erro ao editar projeto: " + message);
    }
}
