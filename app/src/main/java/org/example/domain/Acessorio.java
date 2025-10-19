package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Acessorio {
    private int id;
    private String nome;
    private double valor;
    private int estoque;
    private List<AluguelConsole> alugueis;
    private List<Plataforma> plataformas;
    private static int idAcessorio = 1;

    public Acessorio(String nome, int estoque, double valor){
        this.id = idAcessorio++;
        this.nome = nome;
        this.valor = valor;
        this.alugueis = new ArrayList<>();
        this.plataformas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public double getValor() {
        return valor;
    }
    public int getEstoque(){
        return estoque;
    }
    public List<AluguelConsole> getAlugueis() {
        return alugueis;
    }
    public List<Plataforma> getPlataformas() {
        return plataformas;
    }
    public void decrementarEstoque(){
        this.estoque--;
    }
}
