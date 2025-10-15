package org.example.domain;

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

    public void atualizarCadastro(String novoNome, String novoEmail, String novoTelefone, String novaSenha){
        this.nome = novoNome;
        this.email = novoEmail;
        this.telefone = novoTelefone;
        this.senha = novaSenha;
        System.out.println("Cadastro atualizado com sucesso para: " + this.nome);
    }

    public boolean autenticar(String senha){
        return this.senha.equals(senha);
    }
}
