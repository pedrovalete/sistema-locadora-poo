package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class Acessorio {
    private int id; // Identificador
    private String nome; // Nome do Acessório
    private double valor; // Valor do Acessório
    private int estoque; // Estoque do Acessório
    private List<AluguelConsole> alugueis; // Lista de Aluguéis do Acessório
    private List<Plataforma> plataformas; // Lista de Plataformas que o Acessório pertence
    private static int idAcessorio = 1; // Gerador do ID

    public Acessorio(String nome, int estoque, double valor){ // Construtor que inicializa com os parâmetros nome, estoque e valor do Acessório
        this.id = idAcessorio++;
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
        this.alugueis = new ArrayList<>();
        this.plataformas = new ArrayList<>();
    }

    // Getters
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

    // Método que adiciona uma plataforma que o Acessório pertence à lista de Plataformas
    public void adicionarPlataforma(Plataforma plataforma){
        this.plataformas.add(plataforma);
    }

    // Métodos controladores do estoque do Acessório
    public void decrementarEstoque(){
        this.estoque--;
    }
    public void incrementarEstoque(){
        this.estoque++;
    }
}
