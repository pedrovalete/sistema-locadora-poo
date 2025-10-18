package org.example.domain;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    private List<AluguelConsole> alugueis;
    private List<LocacaoJogo> locacoes;
    private static int idCliente = 1;

    public Cliente(String nome, String email, String telefone, String senha){
        this.id = idCliente++;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;

        this.locacoes = new ArrayList<>();
        this.alugueis = new ArrayList<>();
    }

    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public String getTelefone(){
        return telefone;
    }
    public String getSenha(){
        return senha;
    }

    public boolean autenticar(String senha){
        return this.senha.equals(senha);
    }
    public void consultarHistorico(){
        System.out.println("\n--- Histórico de Transações de " + this.getNome() + "---");

        System.out.println("\nLocações de Jogos: ");
        if(this.locacoes.isEmpty()){
            System.out.println("\nNenhuma locação de jogo encontrada.");
        } else{
            for(LocacaoJogo locacao : this.locacoes){
                System.out.println("\n------------------------------------");
                System.out.println(" ID da Locação: " + locacao.getId());
                System.out.println(" Data: " + locacao.getData());
                System.out.println(" Itens Alugados:");

                for (ItemLocacao item : locacao.getItens()) {
                    String nomeDoJogo = item.getJogoPlataforma().getJogo().getNome();
                    int dias = item.getQuantidadeDias();
                    double subtotal = item.getSubtotal();

                    System.out.println("  - " + nomeDoJogo + " por " + dias + " dias (Subtotal: R$" + subtotal + ")");
                }
                System.out.println("   Valor Total da Locação: R$" + locacao.getValorTotal());
            }
            }
        System.out.println("\nAluguéis de Consoles: ");{
            if(this.alugueis.isEmpty()){
                System.out.println("\nNenhum aluguel de console encontrado.");
            }else{
                for(AluguelConsole aluguel : this.alugueis){
                    System.out.println("\n------------------------------------");
                    System.out.println(" ID do Alguel: " + aluguel.getId());
                    System.out.println(" Data e horário: " + aluguel.getDataHora());
                    System.out.println(" Console: " + aluguel.getConsole().getNome());

                    if(!aluguel.getAcessorios().isEmpty()){
                        System.out.println(" Acessórios incluídos no aluguel: ");
                        for(Acessorio acessorio : aluguel.getAcessorios()){
                            System.out.println("  - " + acessorio.getNome());
                        }
                    }
                    System.out.println(" Valor total do Aluguel: R$" + aluguel.getValorTotal());
                }
            }
        }
        System.out.println("\n--- Fim do Histórico ---");
    }

    public void atualizarCadastro(Scanner sc){
        System.out.println("\nDigite o novo nome: ");
        String novoNome = sc.nextLine();
        System.out.println("\nDigite o novo email: ");
        String novoEmail = sc.nextLine();
        System.out.println("\nDigite o novo telefone: ");
        String novoTelefone = sc.nextLine();
        System.out.println("\nDigite a nova senha: ");
        String novaSenha = sc.nextLine();

        this.nome = novoNome;
        this.email = novoEmail;
        this.telefone = novoTelefone;
        this.senha = novaSenha;
        System.out.println("\nCadastro atualizado com sucesso " + this.nome);
    }

}
