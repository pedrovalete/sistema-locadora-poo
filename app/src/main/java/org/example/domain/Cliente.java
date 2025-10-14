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

    public Cliente(int id, String nome, String email, String telefone, String senha){
        this.id = id;
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

    public void atualizarCadastro(String nome, String email, String telefone, String senha){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
    
    public boolean autenticar(String senha){
        return this.senha.equals(senha);
    }
}
