package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Jogo {
    private int id; // Identificador
    private String nome; // Nome
    private List<JogoPlataforma> jogoPlataformas; // Lista de Jogos-Plataforma que pertencem ao Jogo
    private static int idJogo = 1; // Gerador do ID

    public Jogo(String nome){ // Construtor simples que se inicaliza apenas com o nome
        this.id = idJogo++;
        this.nome = nome;
        this.jogoPlataformas = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome(){
        return nome;
    }
    public int getId(){
        return id;
    }
    public List<JogoPlataforma> getJogoPlataformas(){
        return jogoPlataformas;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
