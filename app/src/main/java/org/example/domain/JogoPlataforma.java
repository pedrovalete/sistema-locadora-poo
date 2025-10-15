package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class JogoPlataforma {
    private int quantidadeEstoque;
    private double precoDiario;
    private List<ItemLocacao> locacoes;

    public JogoPlataforma(int quantidadeEstoque, double precoDiario, LocacaoJogo locacoes){
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoDiario = precoDiario;
        this.locacoes = new ArrayList<>();
    }

    public double getPrecoDiario(){
        return precoDiario;
    }
    public void adicionarHistorico(ItemLocacao item){
        this.locacoes.add(item);
    }
}
