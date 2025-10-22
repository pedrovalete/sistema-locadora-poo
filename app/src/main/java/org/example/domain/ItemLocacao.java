package org.example.domain;

public class ItemLocacao {
    private int quantidadeDias; // Quantidade de dias que o item sera Locado
    private LocacaoJogo locacao; // Locação que ele pertence
    private JogoPlataforma jogo; // Jogo-Plataforma que o pertence

    public ItemLocacao(int quantidadeDias, LocacaoJogo locacao, JogoPlataforma jogo){ // Construtor que inicializa com os dias, a Locação e o Jogo-Plataforma
        this.quantidadeDias = quantidadeDias;
        this.locacao = locacao;
        this.jogo = jogo;
    }

    // Getters
    public int getQuantidadeDias() {
        return quantidadeDias;
    }
    public LocacaoJogo getLocacaoJogo(){
        return locacao;
    }
    public JogoPlataforma getJogoPlataforma(){
        return jogo;
    }

    // Método que calcula um valor subTotal, que seria apenas do Jogo, sem contar os Acessórios
    public double calcularSubtotal(){
        return this.jogo.getPrecoDiario() * this.quantidadeDias;
    }

}
