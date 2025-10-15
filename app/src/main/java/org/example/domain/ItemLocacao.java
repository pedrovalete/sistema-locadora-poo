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
    public LocacaoJogo getLocacao(){
        return locacao;
    }
    public JogoPlataforma getJogo(){
        return jogo;
    }
    public double getSubTotal(){
        return this.jogo.getPrecoDiario() * this.quantidadeDias;
    }
}
