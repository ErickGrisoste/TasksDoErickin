package com.erickin.TasksDoErickin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tarefas")
public class TarefaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "responsavel_id", foreignKey = @ForeignKey(name = "fk_tarefa_responsavel"))
    private UsuarioModel responsavel;
    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tarefa_projeto"))
    @JsonBackReference
    private ProjetoModel projeto;


    public TarefaModel() {}

    public TarefaModel(Long id, String nome, String descricao, StatusEnum status, UsuarioModel responsavel, ProjetoModel projeto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.responsavel = responsavel;
        this.projeto = projeto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public UsuarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public ProjetoModel getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoModel projeto) {
        this.projeto = projeto;
    }
}
