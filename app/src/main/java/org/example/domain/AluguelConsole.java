package org.example.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class AluguelConsole {
    private int id;
    private LocalDateTime dataHora;
    private int duracaoHoras;
    private double valorTotal;
    private Cliente cliente;
    private List<Acessorio> acessorios;
    private Console console;
    private static int idAluguel = 1;

    public  AluguelConsole(Cliente cliente, Console console, int duracaoHoras){
        this.id = idAluguel++;
        this.dataHora = LocalDateTime.now();
        this.duracaoHoras = duracaoHoras;
        this.valorTotal = 0;
        this.cliente = cliente;
        this.acessorios = new ArrayList<>();
        this.console = console;
        this.calcularValorTotal();
    }

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

    public void adicionarAcessorio(Acessorio acessorio){
        this.acessorios.add(acessorio);
        this.calcularValorTotal();
    }
    public void removerAcessorio(Acessorio acessorio){
        this.acessorios.remove(acessorio);
        this.calcularValorTotal();
    }

    public void listarAcessorios(){
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
    private void calcularValorTotal(){
        double total = this.console.getPrecoPorHora() * this.duracaoHoras;
        for(Acessorio acessorio : this.acessorios){
            total += acessorio.getValor();
        }
        this.valorTotal = total;
    }
}
