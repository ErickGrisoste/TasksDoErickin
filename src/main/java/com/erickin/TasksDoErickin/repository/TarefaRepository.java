package com.erickin.TasksDoErickin.repository;

import com.erickin.TasksDoErickin.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    List<TarefaModel> findByProjetoId(Long id);

}
