package org.example.domain;

public class ItemLocacao {
    private int quantidadeDias;
    private LocacaoJogo locacao;
    private JogoPlataforma jogo;

    public ItemLocacao(int quantidadeDias, LocacaoJogo locacao, JogoPlataforma jogo){
        this.quantidadeDias = quantidadeDias;
        this.locacao = locacao;
        this.jogo = jogo;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }
    public LocacaoJogo getLocacaoJogo(){
        return locacao;
    }
    public JogoPlataforma getJogoPlataforma(){
        return jogo;
    }
    public double getSubtotal(){
        return this.jogo.getPrecoDiario() * this.quantidadeDias;
    }
}
