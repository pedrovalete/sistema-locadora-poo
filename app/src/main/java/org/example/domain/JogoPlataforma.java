package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class JogoPlataforma {
    private int quantidadeEstoque;
    private double precoDiario;
    private List<ItemLocacao> itemLocado;
    private Jogo jogo;

    public JogoPlataforma(Jogo jogo, int quantidadeEstoque, double precoDiario){
        this.jogo = jogo;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoDiario = precoDiario;
        this.itemLocado = new ArrayList<>();
    }
    public Jogo getJogo(){
        return jogo;
    }
    public int getQuantidadeEstoque(){
        return quantidadeEstoque;
    }
    public List<ItemLocacao> getJogos(){
        return itemLocado;
    }
    public double getPrecoDiario(){
        return precoDiario;
    }
    public void adicionarHistorico(ItemLocacao item){
        this.itemLocado.add(item);
    }
}
