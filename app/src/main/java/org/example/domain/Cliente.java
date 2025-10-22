package org.example.domain;

import org.example.App;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

public class Cliente {
    private int id; // Identificador da Classe
    private String nome; // Nome do Cliente
    private String email; // Email do Cliente
    private String telefone; // Telefone do Cliente
    private String senha; // Senha do Cliente
    private List<AluguelConsole> alugueis; // Uma lista dos Aluguéis realizados pelo Cliente
    private List<LocacaoJogo> locacoes; // Uma lista das Locações realizadas pelo Cliente
    private static int idCliente = 1; // Constante estática, usada para gerar um ID único a cada novo Cliente

    public Cliente(String nome, String email, String telefone, String senha){ // Construtor da classe, inicializando as ArrayLists de Locações e Aluguéis
        this.id = idCliente++; // Aqui é gerado o ID único de cada Cliente
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;

        this.locacoes = new ArrayList<>();
        this.alugueis = new ArrayList<>();
    }

    // Getters e Setters
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
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean autenticar(String senha){ // Método simples para retornar um valor booleano da autenticação da senha do Cliente
        return this.senha.equals(senha);
    }

    public void consultarHistorico(){ // Aqui o Cliente consulta seu histórico de Locações e Aluguéis, percorrendo as duas listas explicadas acima, e usando um formatador do LocalDate e LocalDateTime, para organizar na data brasileira
        System.out.println("\n===================================================");
        System.out.println("\n    Histórico de Transações de " + this.getNome());
        System.out.println("\n===================================================");

        System.out.println("\n==========================");
        System.out.println("\nLocações de Jogos: ");
        if(this.locacoes.isEmpty()){
            System.out.println("\nNenhuma locação de jogo encontrada.");
        } else{
            for(LocacaoJogo locacao : this.locacoes){
                System.out.println("\n---------------------------------------");
                System.out.println("\n ID da Locação: " + locacao.getId());
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = locacao.getData().format(formatador);
                System.out.println(" Data da Locação: " + dataFormatada);
                System.out.println("\n Itens Alugados:");

                for (ItemLocacao item : locacao.getItens()) {
                    String nomeDoJogo = item.getJogoPlataforma().getJogo().getNome();
                    int dias = item.getQuantidadeDias();
                    double subtotal = item.calcularSubtotal();

                    System.out.println("  - " + nomeDoJogo + " por " + dias + " dias (Subtotal: R$" + subtotal + ")");
                }
                System.out.println(String.format("\n Valor Total da Locação: R$%.2f", locacao.getValorTotal()));
                System.out.println("\n---------------------------------------");
            }
            }
        System.out.println("\n==========================");
        System.out.println("\nAluguéis de Consoles: ");{
            if(this.alugueis.isEmpty()){
                System.out.println("\nNenhum aluguel de console encontrado.");
                System.out.println("\n---------------------------------------");
            }else{
                for(AluguelConsole aluguel : this.alugueis){
                    System.out.println("\n------------------------------------");
                    System.out.println("\n ID do Aluguel: " + aluguel.getId());
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
                    System.out.println("\nData e Hora: " + aluguel.getDataHora().format(formatador));
                    System.out.println("\n Console: " + aluguel.getConsole().getNome());

                    if(!aluguel.getAcessorios().isEmpty()){
                        System.out.println("\n Acessórios incluídos no aluguel: ");
                        for(Acessorio acessorio : aluguel.getAcessorios()){
                            System.out.println("  - " + acessorio.getNome());
                        }
                    }
                    System.out.println("\n Valor total do Aluguel: R$" + aluguel.getValorTotal());
                    System.out.println("\n---------------------------------------");
                }
            }
        }
        System.out.println("\n--- Fim do Histórico ---");
        System.out.println();
    }

    public void atualizarCadastro(Scanner sc){ // Usando a lógica do ".isEmpty()", o Cliente pode atualizar apenas alguns dados do seu cadastro
        System.out.println("\nDigite o novo nome (Enter para pular): ");
        String novoNome = sc.nextLine();
        System.out.println("\nDigite o novo email (Enter para pular): ");
        String novoEmail = sc.nextLine();
        System.out.println("\nDigite o novo telefone (Enter para pular): ");
        String novoTelefone = sc.nextLine();
        System.out.println("\nDigite a nova senha (Enter para pular): ");
        String novaSenha = sc.nextLine();

        if(!novoNome.isEmpty()){
            this.nome = novoNome;
        }
        if(!novoEmail.isEmpty()){
            this.email = novoEmail;
        }
        if(!novoTelefone.isEmpty()){
            this.telefone = novoTelefone;
        }
        if(!novaSenha.isEmpty()){
            this.senha = novaSenha;
        }
        System.out.println("\nCadastro atualizado com sucesso, " + this.nome + ".");
    }
    public void adicionarLocacao(LocacaoJogo novaLocacao){ // Método que adiciona a nova Locação realizada na lista de Locações do Cliente.
        this.locacoes.add(novaLocacao);
    }
    public void adicionarAluguel(AluguelConsole novoAluguel){ // Método que adiciona o novo Aluguel realizado na lista de Aluguéis do Cliente.
        this.alugueis.add(novoAluguel);
    }

    public void novaLocacao(Scanner sc, Map<String, JogoPlataforma> estoqueJogos, Map<Integer, LocacaoJogo> historicoGeralLocacoes) { // Com este método aqui, o Cliente realiza uma nova Locação, informando todas as informações necessárias, e no final recebendo um comprovante, e a Locação sendo armazenada na lista de Locações do Cliente, e no HashMap de Locações da Locadora
        System.out.println("\n====================================================================");                                 // Estes parâmetros são chamados lá na App, no menuCliente, que servem para receber o Scanner obviamente, mas também os HashMaps que armazenam o estoque de Jogos-Plataforma e o histórico geral de Locações.
        System.out.println("                        NOVA LOCAÇÃO DE JOGO");
        System.out.println("====================================================================");

        if (estoqueJogos.isEmpty()) {
            System.out.println("\nNenhum jogo disponível no estoque para locação.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }
        LocacaoJogo novaLocacao = new LocacaoJogo(this);

        App.listarJogosPlataformas();

        System.out.println("\n Quantos jogos você deseja locar? ");
        int quantidadeJogos = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < quantidadeJogos; i++) {
            System.out.println("\n Digite a chave do Jogo e Plataforma desejada: ");
            String idChave = sc.nextLine();
            JogoPlataforma produtoEscolhido = estoqueJogos.get(idChave);

            if (produtoEscolhido != null && produtoEscolhido.getQuantidadeEstoque() > 0) {
                System.out.println("\n Digite a quantidade de dias de locação para este jogo: ");
                int diasDeLocacao = sc.nextInt();
                sc.nextLine();
                novaLocacao.adicionarItem(produtoEscolhido, diasDeLocacao);
                produtoEscolhido.decrementarEstoque();
            } else if (produtoEscolhido == null) {
                System.out.println("\nID de Locação inválido.");
            } else {
                System.out.println("\nO jogo '" + produtoEscolhido.getJogo().getNome() + "' está sem estoque.");
            }
        }

        System.out.print("\n> " + this.getNome() + ", confirmar a locação? (S/N): ");
        String confirmacao = sc.nextLine();
        if(confirmacao.equalsIgnoreCase("S")) {
            System.out.println("\n======================================");
            System.out.println("      Comprovante de Locação");
            System.out.println("======================================\n");
            System.out.println("ID da Locação: " + novaLocacao.getId());
            System.out.println("\nCliente: " + novaLocacao.getCliente().getNome());
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = novaLocacao.getData().format(formatador);
            System.out.println("\nData da Locação: " + dataFormatada);
            System.out.println("\nItens Locados:");

            for (ItemLocacao item : novaLocacao.getItens()) {
                System.out.println("  - " + item.getJogoPlataforma().getJogo().getNome() +
                        " (" + item.getJogoPlataforma().getPlataforma().getNome() +
                        ") por " + item.getQuantidadeDias() + " dias.");
            }

            System.out.println("\nValor Total: R$" + String.format("%.2f", novaLocacao.getValorTotal()));

            this.adicionarLocacao(novaLocacao);
            historicoGeralLocacoes.put(novaLocacao.getId(), novaLocacao);
        }else{
            System.out.println("\nLocação cancelada pelo usuário.");
            for (ItemLocacao item : novaLocacao.getItens()) {
                item.getJogoPlataforma().incrementarEstoque();
            }
            System.out.println("Estoque restaurado.");
        }
        System.out.println("------------------------------------------------------");
    }

    public void novoAluguel(Scanner sc, Map<Integer, Console> consolesDisponiveis, Map<Integer, Acessorio> acessoriosDisponiveis, Map<Integer, AluguelConsole> historicoGeralAlugueis) { // Com este método aqui, o Cliente realiza uma novo Aluguel, informando todas as informações necessárias, e no final recebendo um comprovante, e o Aluguel sendo armazenado na lista de Aluguéis do Cliente, e no HashMap de Aluguéis da Locadora.
        System.out.println("\n====================================================================");                                                                                    // Estes parâmetros são chamados lá na App, no menuCliente, que servem para receber os HashMaps que armazenam os Consoles disponíveis, os Acessórios disponíveis e o histórico geral de Locações.
        System.out.println("                     NOVO ALUGUEL DE CONSOLE");
        System.out.println("====================================================================");

        if (consolesDisponiveis.isEmpty()) {
            System.out.println("\nNenhum console disponível no momento.");
            System.out.println("---------------------------------------------------");
            return;
        }

        System.out.println("\nConsoles disponíveis para aluguel: ");
        for (Console console : consolesDisponiveis.values()) {
            if (console.getDisponibilidade()) {
                System.out.println("  ID: " + console.getId() + " | Console: " + console.getNome() + String.format(" | Preço por Hora: R$%.2f", console.getPrecoPorHora()));
            }
        }
        System.out.println("\nDigite o ID do console que gostaria de alugar: ");
        int idConsole = sc.nextInt();
        sc.nextLine();
        Console consoleEscolhido = consolesDisponiveis.get(idConsole);
        if (consoleEscolhido == null || !consoleEscolhido.getDisponibilidade()) {
            System.out.println("\nID de console inválido ou indisponível.");
            System.out.println("-------------------------------------------------------");
            return;
        }

        System.out.println("\nDigite a quantidade em horas do aluguel deste console: ");
        int horasDeAluguel = sc.nextInt();
        sc.nextLine();
        consoleEscolhido.alugar();
        AluguelConsole novoAluguel = new AluguelConsole(this, consoleEscolhido, horasDeAluguel);

        List<Acessorio> acessoriosCompativeis = consoleEscolhido.getPlataforma().getAcessorios();
        if(acessoriosCompativeis.isEmpty()){
            System.out.println("\nNenhum acessório compatível com este console.");
        } else {
            System.out.println("\nAcessórios compatíveis com: " + consoleEscolhido.getNome());
            for(Acessorio acessorio : acessoriosCompativeis){
                if(acessorio.getEstoque() > 0){
                    System.out.println(String.format(" ID: %d | Nome: %s | Valor: R$%.2f | Estoque: %d", acessorio.getId(), acessorio.getNome(), acessorio.getValor(), acessorio.getEstoque()));
                }
            }
            System.out.println("\nDigite quantos acessórios gostaria de alugar (0 para nenhum): ");
            int quantidadeAcessorios = sc.nextInt();
            sc.nextLine();
            for(int i = 0; i < quantidadeAcessorios; i++){
                System.out.println("\nDigite o ID do acessório " + (i+1) + ":");
                int idAcessorio = sc.nextInt();
                sc.nextLine();
                Acessorio acessorioEscolhido = acessoriosDisponiveis.get(idAcessorio);
                if (acessorioEscolhido != null && acessorioEscolhido.getEstoque() > 0){
                    novoAluguel.adicionarAcessorio(acessorioEscolhido);
                    acessorioEscolhido.decrementarEstoque();
                } else {
                    System.out.println("\nID de acessório inválido, sem estoque ou incompatível.");
                }
            }
        }
        System.out.print("\n> " + this.getNome() + ", confirmar o aluguel? (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            this.adicionarAluguel(novoAluguel);
            historicoGeralAlugueis.put(novoAluguel.getId(), novoAluguel);

            System.out.println("\n======================================");
            System.out.println("      Comprovante de Aluguel");
            System.out.println("======================================");

            System.out.println("\nID do Aluguel: " + novoAluguel.getId());
            System.out.println("\nCliente: " + novoAluguel.getCliente().getNome());
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
            System.out.println("\nData e Hora do Aluguel: " + novoAluguel.getDataHora().format(formatador));
            System.out.println("\nItens Alugados:");
            System.out.println("  - Console: " + novoAluguel.getConsole().getNome() + " (" + novoAluguel.getDuracaoHoras() + " horas)");
            if (!novoAluguel.getAcessorios().isEmpty()) {
                System.out.println("\n  - Acessórios: ");
                for (Acessorio acessorio : novoAluguel.getAcessorios()) {
                    System.out.println("    - " + acessorio.getNome());
                }
            }
            System.out.println("\nValor Total: R$" + String.format("%.2f", novoAluguel.getValorTotal()));
        } else {
            System.out.println("\nAluguel cancelado pelo usuário.");
            consoleEscolhido.devolver();
            for(Acessorio acessorio : novoAluguel.getAcessorios()){
                acessorio.incrementarEstoque();
            }
            System.out.println("Estoque restaurado.");
        }
        System.out.println("------------------------------------------------------");
    }
}
