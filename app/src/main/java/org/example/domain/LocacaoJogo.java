package org.example.domain;

import java.time.LocalDate;
import java.util.List;
import  java.util.ArrayList;

public class LocacaoJogo {
    private int id; // Identificador
    private LocalDate data; // Data da operação
    private double valorTotal; // Valor total da Locação
    private Cliente cliente; // Cliente responsável pela Locação
    private List<ItemLocacao> itens; // Itens presentes na Locação
    private static int idLocacao = 1; // Gerador do ID único

    public LocacaoJogo(Cliente cliente){ // Construtor básico, que se inicia apenas com o objeto Cliente, e inicializa todos os atributos
        this.id = idLocacao++;
        this.data = LocalDate.now();
        this.valorTotal = 0;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    // Getters
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

    public void adicionarItem(JogoPlataforma jogo, int quantidadeDias){ // Usado para adicionar um item na Locação, e recalculando o valor total
        ItemLocacao novoItem = new ItemLocacao(quantidadeDias, this, jogo);

        this.itens.add(novoItem);
        this.calcularValorTotal();
        jogo.adicionarHistorico(novoItem);

    }
    public void removerItem(ItemLocacao item){ // Usado para remover um item na Locação, e recalculando o valor total
        this.itens.remove(item);
        item.getJogoPlataforma().incrementarEstoque();
        this.calcularValorTotal();
    }
    public void listarItens(){ // Método para listar todos os itens presentes na Locação
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
                double subtotal = item.calcularSubtotal();
                System.out.println(String.format("%d. Jogo: %s | Plataforma: %s | Quantidade de Dias: %d | Subtotal: %.2f", i+1, nomeJogo, nomePlataforma, dias, subtotal));
            }
        }
        System.out.println("Valor Total: R$" + String.format("%.2f", this.valorTotal));
    }
    private void calcularValorTotal(){
        double total = 0;
        for(ItemLocacao item : this.itens){
            total += item.calcularSubtotal();
        }
        this.valorTotal = total;
    }

}
