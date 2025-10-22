package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Console {
    private int id; // Identificador
    private String nome; // Nome do Console
    private double precoPorHora; // Preço por hora do Console
    private List<AluguelConsole> alugueis; // Lista de Aluguéis do Console
    private Plataforma plataforma; // Plataforma que o Console pertence
    private boolean disponivel; // Condição de disponibilidade do Console
    private static int idConsole = 1; // Gerador do ID

    public Console(String nome, Plataforma plataforma, double precoPorHora){ // Construtor que inializa com os parâmetros do nome, Plataforma que o console pertence, e o seu preço por hora
        this.id = idConsole++;
        this.nome = nome;
        this.plataforma = plataforma;
        this.precoPorHora = precoPorHora;
        this.alugueis = new ArrayList<>();
        this.disponivel = true;
    }

    // Getters
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

    // Métodos que alteram a disponibilidade do Console, quando alugado, alugar() é chamado, mas quando cancelado, devolver() é chamado
    public void alugar(){
        this.disponivel = false;
    }
    public void devolver(){
        this.disponivel = true;
    }
}
