package org.example.domain;

import java.util.List;
import java.util.ArrayList;

public class JogoPlataforma {
    private String chaveComposta; // Identificador, não é ID pois é uma classe de associação, assim juntando os IDs das duas classes
    private int quantidadeEstoque; // Estoque do Jogo-Plataforma
    private double precoDiario; // Preço Diário
    private List<ItemLocacao> itens; // Lista de Itens de Locação, que funciona como um histórico, mostrando quantas vezes este JogoPlataforma foi Locado
    private Jogo jogo; // Jogo do Jogo-Plataforma
    private Plataforma plataforma; // Plataforma do Jogo-Plataforma

    public JogoPlataforma(Jogo jogo, Plataforma plataforma, int quantidadeEstoque, double precoDiario){ // Construtor que inicializa com o Jogo, Plataforma, estoque e preço diário
        this.chaveComposta = jogo.getId() + "-" + plataforma.getId(); // Aqui ja criando o identificador da classe, uma chave que compõe o ID do Jogo e da Plataforma
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoDiario = precoDiario;
        this.itens = new ArrayList<>();
    }

    // Getters e Setters
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
    public String getChaveComposta(){
        return chaveComposta;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque){
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public void setPrecoDiario(double precoDiario){
        this.precoDiario = precoDiario;
    }

    // Método que é chamado quando feita uma Locação, adicionando o Jogo-Plataforma ao histórico
    public void adicionarHistorico(ItemLocacao item){
        this.itens.add(item);
    }

    // Controladores do estoque
    public void decrementarEstoque(){
            this.quantidadeEstoque--;
    }
    public void incrementarEstoque(){
        this.quantidadeEstoque++;
    }
}
