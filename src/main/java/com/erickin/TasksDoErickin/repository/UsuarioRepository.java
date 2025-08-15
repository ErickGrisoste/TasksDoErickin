package com.erickin.TasksDoErickin.repository;

import com.erickin.TasksDoErickin.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}
