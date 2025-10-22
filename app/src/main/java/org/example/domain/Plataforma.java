package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Plataforma {
    private int id; // Identificador
    private String nome; // Nome
    private String descricao; // Descrição opcional
    private List<Acessorio> acessorios; // Lista de Acessórios que pertencem à Plataforma
    private List<Console> consoles; // Lista de Consoles que pertencem à Plataforma
    private List<JogoPlataforma> jogosPlataforma; // Lista de Jogos-Plataforma que pertencem à Plataforma
    private static int idPlataforma = 1; // Gerador do ID

    public Plataforma(String nome){ // Construtor simples que inicaliza apenas com o nome
        this.id = idPlataforma++;
        this.nome = nome;
        this.acessorios = new ArrayList<>();
        this.consoles = new ArrayList<>();
        this.jogosPlataforma = new ArrayList<>();
    }
    public Plataforma(String nome, String descricao){ // Construtor sobrecarregado, caso o ADM queira criar com uma descrição
        this.id = idPlataforma++;
        this.nome = nome;
        this.descricao = descricao;
        this.acessorios = new ArrayList<>();
        this.consoles = new ArrayList<>();
        this.jogosPlataforma = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public List<Acessorio> getAcessorios() {
        return acessorios;
    }
    public List<Console> getConsoles() {
        return consoles;
    }
    public List<JogoPlataforma> getJogosPlataforma() {
        return jogosPlataforma;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método que adiciona um Acessório à lista de quem pertence esta Plataforma
    public void adicionarAcessorio(Acessorio acessorio){
        this.acessorios.add(acessorio);
    }
}
