package org.example.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class AluguelConsole {
    private int id; // Identificador
    private LocalDateTime dataHora; // Data e Hora da operação
    private int duracaoHoras; // Duração do Aluguel
    private double valorTotal; // Valor total
    private Cliente cliente; // Cliente responsável
    private List<Acessorio> acessorios; // Lista de acessórios presentes no Aluguel
    private Console console; // Console do Aluguel
    private static int idAluguel = 1; // Gerador do ID

    public  AluguelConsole(Cliente cliente, Console console, int duracaoHoras){ // Construtor para inicializar com os parâmetros do Cliente, o Console, e a duração em horas.
        this.id = idAluguel++;
        this.dataHora = LocalDateTime.now();
        this.duracaoHoras = duracaoHoras;
        this.valorTotal = 0;
        this.cliente = cliente;
        this.acessorios = new ArrayList<>();
        this.console = console;
        this.calcularValorTotal();
    }

    // Getters e Setters
    public int getId(){
        return id;
    }
    public LocalDateTime getDataHora(){
        return dataHora;
    }
    public int getDuracaoHoras(){
        return duracaoHoras;
    }
    public double getValorTotal(){
        return valorTotal;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public List<Acessorio> getAcessorios(){
        return acessorios;
    }
    public Console getConsole(){
        return console;
    }
    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    // Adicionar e remover Acessórios do Aluguel, e recalculando o valor total.
    public void adicionarAcessorio(Acessorio acessorio){
        this.acessorios.add(acessorio);
        this.calcularValorTotal();
    }
    public void removerAcessorio(Acessorio acessorio){
        this.acessorios.remove(acessorio);
        this.calcularValorTotal();
    }

    public void listarAcessorios(){ // Método para listar todos os Acessórios presentes no Aluguel.
        System.out.println("\n--- Acessórios do Aluguel #" + this.id);
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("Console: " + this.console.getNome());
        System.out.println("Data e Hora: " + this.dataHora);

        if(this.acessorios.isEmpty()){
            System.out.println("O Aluguel ainda não possui Acessórios.");
        }else{
            System.out.println("Acessórios: ");
            for(Acessorio acessorio : acessorios){
                String nomeAcessorio = acessorio.getNome();
                double valorAcessorio = acessorio.getValor();
                System.out.println(" ID: " + acessorio.getId() + " | Nome: " + acessorio.getNome() + " | Valor: " + acessorio.getValor());
            }
        }
        System.out.println("Valor Total: R$" + String.format("%.2f", this.valorTotal));
    }
    private void calcularValorTotal(){ // Método para calcular o valor total do Aluguel, baseando no valor do console por hora, duração do Aluguel, e preço fixo dos Acessórios.
        double total = this.console.getPrecoPorHora() * this.duracaoHoras;
        for(Acessorio acessorio : this.acessorios){
            total += acessorio.getValor();
        }
        this.valorTotal = total;
    }
}
