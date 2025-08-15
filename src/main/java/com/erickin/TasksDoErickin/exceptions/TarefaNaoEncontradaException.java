package com.erickin.TasksDoErickin.exceptions;

public class TarefaNaoEncontradaException extends RuntimeException {
    public TarefaNaoEncontradaException(Long id) {
        super("Nenhuma tarefa encontrada com id: " + id);
    }
}
