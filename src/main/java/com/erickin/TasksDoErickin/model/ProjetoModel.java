package com.erickin.TasksDoErickin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projetos")
public class ProjetoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TarefaModel> tarefas = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "criador_id", nullable = false, foreignKey = @ForeignKey(name = "fk_projeto_criador"))
    private UsuarioModel criador;

    public ProjetoModel() {}

    public ProjetoModel(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, List<TarefaModel> tarefas, UsuarioModel criador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tarefas = tarefas;
        this.criador = criador;
    }

    public UsuarioModel getCriador() {
        return criador;
    }

    public void setCriador(UsuarioModel criador) {
        this.criador = criador;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<TarefaModel> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaModel> tarefas) {
        this.tarefas = tarefas;
    }
}
