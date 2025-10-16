package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Jogo {
    private int id;
    private String nome;
    private List<JogoPlataforma> jogoPlataformas;
    private static int idJogo = 1;

    public Jogo(String nome){
        this.id = idJogo++;
        this.nome = nome;
        this.jogoPlataformas = new ArrayList<>();
    }
    public String getNome(){
        return nome;
    }
    public int getId(){
        return id;
    }
    public List<JogoPlataforma> getJogoPlataformas(){
        return jogoPlataformas;
    }
}
