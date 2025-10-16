package org.example.domain;

import java.time.LocalDate;
import java.util.List;
import  java.util.ArrayList;

public class LocacaoJogo {
    private int id;
    private LocalDate data;
    private double valorTotal;
    private Cliente cliente;
    private List<ItemLocacao> itens;
    private static int idLocacao = 1;

    public LocacaoJogo(Cliente cliente){
        this.id = idLocacao++;
        this.data = LocalDate.now();
        this.valorTotal = 0;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<ItemLocacao> getItens() {
        return itens;
    }

    public void adicionarItem(int quantidadeDias, JogoPlataforma jogo){
        ItemLocacao novoItem = new ItemLocacao(quantidadeDias, this, jogo);

        this.itens.add(novoItem);
        this.calcularValorTotal();
        jogo.adicionarHistorico(novoItem);

    }

    private void calcularValorTotal(){
        double total = 0;
        for(ItemLocacao item : this.itens){
            total += item.getSubtotal();
        }
        this.valorTotal = total;
    }
}
