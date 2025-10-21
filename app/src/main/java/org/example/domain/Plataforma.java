package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Plataforma {
    private int id;
    private String nome;
    private String descricao;
    private List<Acessorio> acessorios;
    private List<Console> consoles;
    private List<JogoPlataforma> jogosPlataforma;
    private static int idPlataforma = 1;

    public Plataforma(String nome){
        this.id = idPlataforma++;
        this.nome = nome;
        this.acessorios = new ArrayList<>();
        this.consoles = new ArrayList<>();
        this.jogosPlataforma = new ArrayList<>();
    }
    public Plataforma(String nome, String descricao){
        this.id = idPlataforma++;
        this.nome = nome;
        this.descricao = descricao;
        this.acessorios = new ArrayList<>();
        this.consoles = new ArrayList<>();
        this.jogosPlataforma = new ArrayList<>();
    }

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

    public void adicionarAcessorio(Acessorio acessorio){
        this.acessorios.add(acessorio);
    }
}
