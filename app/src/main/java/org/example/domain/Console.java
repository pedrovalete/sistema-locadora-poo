package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Console {
    private int id;
    private String nome;
    private double precoPorHora;
    private List<AluguelConsole> alugueis;
    private Plataforma plataforma;
    private boolean disponivel;
    private static int idConsole = 1;

    public Console(String nome, Plataforma plataforma, double precoPorHora){
        this.id = idConsole++;
        this.nome = nome;
        this.plataforma = plataforma;
        this.precoPorHora = precoPorHora;
        this.alugueis = new ArrayList<>();
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Plataforma getPlataforma() {
        return plataforma;
    }
    public double getPrecoPorHora() {
        return precoPorHora;
    }
    public List<AluguelConsole> getAlugueis() {
        return alugueis;
    }
    public boolean getDisponibilidade(){
        return disponivel;
    }
    public void alugar(){
        this.disponivel = false;
    }
    public void devolver(){
        this.disponivel = true;
    }
}
