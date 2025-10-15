package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Plataforma {
    private int id;
    private String nome;
    private String descricao;
    private List<Acessorio> acessorios;
    private List<Console> consoles;
    private List<Jogo> jogos;
    private static int idPlataforma = 1;

    public Plataforma(String nome){
        this.id = idPlataforma++;
        this.nome = nome;
        this.acessorios = new ArrayList<>();
        this.consoles = new ArrayList<>();
        this.jogos = new ArrayList<>();
    }
    public Plataforma(String nome, String descricao){
        this.id = idPlataforma++;
        this.nome = nome;
        this.descricao = descricao;
        this.acessorios = new ArrayList<>();
        this.consoles = new ArrayList<>();
        this.jogos = new ArrayList<>();
    }
}
