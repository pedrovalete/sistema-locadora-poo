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

    public void adicionarItem(JogoPlataforma jogo, int quantidadeDias){
        ItemLocacao novoItem = new ItemLocacao(quantidadeDias, this, jogo);

        this.itens.add(novoItem);
        this.calcularValorTotal();
        jogo.adicionarHistorico(novoItem);

    }
    public void removerItem(ItemLocacao item){
        this.itens.remove(item);
        item.getJogoPlataforma().incrementarEstoque();
        this.calcularValorTotal();
    }
    public void listarItens(){
        System.out.println("\n--- Itens da Locação #" + this.id);
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("Data: " + this.data);

        if(this.itens.isEmpty()){
            System.out.println("A Locação ainda não possui itens.");
        }else{
            System.out.println("Itens: ");
            for(int i = 0; i<this.itens.size(); i++){
                ItemLocacao item = this.itens.get(i);
                String nomeJogo = item.getJogoPlataforma().getJogo().getNome();
                String nomePlataforma = item.getJogoPlataforma().getPlataforma().getNome();
                int dias = item.getQuantidadeDias();
                double subtotal = item.getSubtotal();
                System.out.println(String.format("%d. Jogo: %s | Plataforma: %s | Quantidade de Dias: %d | Subtotal: %.2f", i+1, nomeJogo, nomePlataforma, dias, subtotal));
            }
        }
        System.out.println("Valor Total: R$" + String.format("%.2f", this.valorTotal));
    }
    private void calcularValorTotal(){
        double total = 0;
        for(ItemLocacao item : this.itens){
            total += item.getSubtotal();
        }
        this.valorTotal = total;
    }

}
