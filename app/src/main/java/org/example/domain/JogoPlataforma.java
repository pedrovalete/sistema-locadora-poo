package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class JogoPlataforma {
    private int quantidadeEstoque;
    private double precoDiario;
    private List<ItemLocacao> itens;
    private Jogo jogo;
    private Plataforma plataforma;

    public JogoPlataforma(Jogo jogo, Plataforma plataforma, int quantidadeEstoque, double precoDiario){
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoDiario = precoDiario;
        this.itens = new ArrayList<>();
    }
    public Jogo getJogo(){
        return jogo;
    }
    public Plataforma getPlataforma(){
        return plataforma;
    }
    public int getQuantidadeEstoque(){
        return quantidadeEstoque;
    }
    public List<ItemLocacao> getItens(){
        return itens;
    }
    public double getPrecoDiario(){
        return precoDiario;
    }
    public void adicionarHistorico(ItemLocacao item){
        this.itens.add(item);
    }
}
