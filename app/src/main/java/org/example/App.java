package org.example;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.example.domain.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    private static Map<Integer, Cliente> clientesCadastrados = new HashMap<>(); // Um HashMap para armazenar todos os Clientes cadastrados no sistema.
    private static Map<Integer, Jogo> jogosCadastrados = new HashMap<>(); // Um HashMap para armazenar todos os Títulos de Jogos cadastrados no sistema.
    private static Map<Integer, Plataforma> plataformasCadastradas = new HashMap<>(); // Um HashMap para armazenar todos as Plataformas cadastradas no sistema.
    private static Map<Integer, Console> consolesDisponiveis = new HashMap<>(); // Um HashMap para armazenar todos os Consoles cadastrados no sistema.
    private static Map<Integer, Acessorio>  acessoriosDisponiveis = new HashMap<>(); // Um HashMap para armazenar todos os Acessórios cadastrados no sistema.
    private static Map<String, JogoPlataforma> estoqueJogos = new HashMap<>(); // Um HashMap para armazenar todos os Jogos-Plataformas (para locações) cadastrados no sistema.
    private static Map<Integer, LocacaoJogo> historicoGeralLocacoes = new HashMap<>(); // Um HashMap para armazenar o histórico geral de Locações da locadora (uso exclusivo do ADM).
    private static Map<Integer, AluguelConsole> historicoGeralAlugueis = new HashMap<>(); // Um HashMap para armazenar o histórico geral de Aluguéis da locadora (uso exclusivo do ADM).

    private static final String SENHA_ADMIN = "senhaadm123"; // Constante estática da senha do ADM.

    public static void preCarregarDados() { // Aqui criei um método ja para fazer um preload de todos os dados necessários.
        System.out.println("Pré-carregando dados iniciais do sistema...");

        // Cadastrar Clientes
        Cliente cliente1 = new Cliente("Bernardo", "bernardo.vieira@email.com", "0101-0101", "senhabernardo");
        Cliente cliente2 = new Cliente("Pedro", "pedro.valete@email.com", "1010-1010", "senhapedro");
        clientesCadastrados.put(cliente1.getId(), cliente1);
        clientesCadastrados.put(cliente2.getId(), cliente2);

        // Cadastrar Jogos e Plataformas
        Jogo jogo1 = new Jogo("CS2");
        Jogo jogo2 = new Jogo("FIFA 25");
        Jogo jogo3 = new Jogo("God of War Ragnarok");
        jogosCadastrados.put(jogo1.getId(), jogo1);
        jogosCadastrados.put(jogo2.getId(), jogo2);
        jogosCadastrados.put(jogo3.getId(), jogo3);

        Plataforma plataforma1 = new Plataforma("PC", "Sistema Windows");
        Plataforma plataforma2 = new Plataforma("Xbox Series X");
        Plataforma plataforma3 = new Plataforma("PlayStation 5", "Edição Padrão com leitor de disco");
        plataformasCadastradas.put(plataforma1.getId(), plataforma1);
        plataformasCadastradas.put(plataforma2.getId(), plataforma2);
        plataformasCadastradas.put(plataforma3.getId(), plataforma3);


        // Conectar Jogos e Plataformas

        // Disponibilizar CS2 para PC
        JogoPlataforma jp1 = new JogoPlataforma(jogo1, plataforma1, 10, 10.50); // Jogo, Plataforma, Estoque e Preço
        estoqueJogos.put(jp1.getChaveComposta(), jp1);

        // Disponibilizar FIFA 25 para Xbox e PS5
        JogoPlataforma jp2 = new JogoPlataforma(jogo2, plataforma2, 8, 18.00);
        JogoPlataforma jp3 = new JogoPlataforma(jogo2, plataforma3, 12, 18.00);
        estoqueJogos.put(jp2.getChaveComposta(), jp2);
        estoqueJogos.put(jp3.getChaveComposta(), jp3);

        // Disponibilizar God of War Ragnarok para PC
        JogoPlataforma jp4 = new JogoPlataforma(jogo3, plataforma1, 20, 20.50);
        estoqueJogos.put(jp4.getChaveComposta(), jp4);

        // Cadastrar Consoles
        Console console1 = new Console(plataforma3.getNome(), plataforma3, 50.0); // Nome (Pegando diretamente da Plataforma), Plataforma, Preço/Hora
        Console console2 = new Console(plataforma3.getNome(), plataforma3, 50.0);
        Console console3 = new Console(plataforma2.getNome(), plataforma2, 45.0);
        consolesDisponiveis.put(console1.getId(), console1);
        consolesDisponiveis.put(console2.getId(), console2);
        consolesDisponiveis.put(console3.getId(), console3);

        // Cadastrar Acessórios
        Acessorio acessorio1 = new Acessorio("Controle DualSense",5, 15.50); // Nome, Estoque, Valor
        Acessorio acessorio2 = new Acessorio("Headset Pulse 3D", 3, 20.00);
        Acessorio acessorio3 = new Acessorio("Controle Xbox", 8, 15.25);
        Acessorio acessorio4 = new Acessorio("Headset Gamer", 10, 18.00);
        acessoriosDisponiveis.put(acessorio1.getId(), acessorio1);
        acessoriosDisponiveis.put(acessorio2.getId(), acessorio2);
        acessoriosDisponiveis.put(acessorio3.getId(), acessorio3);
        acessoriosDisponiveis.put(acessorio4.getId(), acessorio4);


        // Conectando Acessórios com suas respectivas Plataformas

        // Acessórios de PS5
        plataforma3.adicionarAcessorio(acessorio1);
        acessorio1.adicionarPlataforma(plataforma3);
        plataforma3.adicionarAcessorio(acessorio2);
        acessorio2.adicionarPlataforma(plataforma3);

        // Acessórios de Xbox
        plataforma2.adicionarAcessorio(acessorio3);
        acessorio3.adicionarPlataforma(plataforma2);

        // Headset USB é compatível com PS5 e PC
        plataforma1.adicionarAcessorio(acessorio4);
        acessorio4.adicionarPlataforma(plataforma1);
        plataforma3.adicionarAcessorio(acessorio4);
        acessorio4.adicionarPlataforma(plataforma3);

        System.out.println("Dados pré-carregados com sucesso.\n");
    }

    public static void main(String[] args) {
        preCarregarDados();
        Scanner sc = new Scanner(System.in);
        telaInicial(sc);
    }

    public static void telaInicial(Scanner sc){
        System.out.println("\n=================================\n");
        System.out.println("    BEM VINDO(A) À LOCADORA      ");
        System.out.println("\n=================================\n");
        System.out.println("\n1. Administrador");
        System.out.println("2. Cliente");
        System.out.println("0. Sair\n");
        System.out.println("Qual seria a forma de login? \n");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch(escolha){
            case 1:
                loginAdm(sc);
                break;
            case 2:
                loginCliente(sc);
                break;
            case 3:
                return;
            case 0:
                System.out.println("\n Saindo do sistema.");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public static void loginAdm(Scanner sc) { // Fluxo inicial do ADM, fazendo o login.
        System.out.println("\n==============================");
        System.out.println("        LOGIN DO ADM");
        System.out.println("==============================");
        System.out.println();

        System.out.print(" > Digite a sua senha de Administrador: ");
        String senhaADM = sc.nextLine();
        boolean autenticacao = false;
        if(senhaADM.equals(SENHA_ADMIN)){
            autenticacao = true;
        }
        if(!autenticacao){
            System.out.println("\n--------------------------");
            System.out.println("|     Senha incorreta.     |");
            System.out.println("--------------------------");
        }else{
            System.out.println("\n--------------------------------------------");
            System.out.println(" Login realizado. Bem-vindo de volta ADM");
            System.out.println("--------------------------------------------");
            menuAdmin(sc);
        }
    }

    public static void menuAdmin(Scanner sc) { // Fluxo principal do ADM, chamando seus respectivos métodos.
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println("       MENU ADMINISTRADOR      ");
            System.out.println("===============================");
            System.out.println();
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Jogos");
            System.out.println("3. Gerenciar Plataformas");
            System.out.println("4. Gerenciar Locações");
            System.out.println("5. Gerenciar Aluguéis");
            System.out.println("0. Voltar ao Login");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch(escolha){
                case 1:
                    gerenciarCliente(sc);
                    break;
                case 2:
                    gerenciarJogo(sc);
                    break;
                case 3:
                    gerenciarPlataforma(sc);
                    break;
                case 4:
                    gerenciarLocacoes(sc);
                    break;
                case 5:
                    gerenciarAlugueis(sc);
                    break;
                case 0:
                    sair = true;
                    telaInicial(sc);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void loginCliente(Scanner sc) { // Fluxo inicial do Cliente, fazendo o login.
        System.out.println("\n==================================");
        System.out.println("        LOGIN DO CLIENTE");
        System.out.println("==================================");
        System.out.println();

        System.out.print(" > Digite o seu ID de cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        System.out.print("\n > Digite a sua senha: ");
        String senhaDigitada = sc.nextLine();

        Cliente clienteLogin = clientesCadastrados.get(idCliente);

        if (clienteLogin != null && clienteLogin.autenticar(senhaDigitada)) {
            System.out.println("\n-------------------------------------------");
            System.out.println(" Login realizado. Bem-vindo(a), " + clienteLogin.getNome() + ".");
            System.out.println("-------------------------------------------");
            menuCliente(sc, clienteLogin);
        } else {
            System.out.println("\n-------------------------------------------");
            System.out.println("|     ID de cliente ou senha incorreta.     |");
            System.out.println("-------------------------------------------");
        }
    }

    public static void menuCliente(Scanner sc, Cliente clienteLogado) { // Fluxo principal do Cliente, chamando seus respectivos métodos.
        boolean sair = false;
        while(!sair){
            System.out.println("\n=========================");
            System.out.println("       MENU CLIENTE      ");
            System.out.println("=========================");
            System.out.println("1. Nova Locação de Jogos");
            System.out.println("2. Novo Aluguel de Consoles");
            System.out.println("3. Consultar histórico");
            System.out.println("4. Consultar Jogos disponíveis");
            System.out.println("5. Atualizar cadastro");
            System.out.println("0. Voltar ao Login");
            int escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    clienteLogado.novaLocacao(sc, estoqueJogos, historicoGeralLocacoes);
                    break;
                case 2:
                    clienteLogado.novoAluguel(sc, consolesDisponiveis, acessoriosDisponiveis, historicoGeralAlugueis);
                    break;
                case 3:
                    clienteLogado.consultarHistorico();
                    break;
                case 4:
                    listarJogosPlataformas();
                    break;
                case 5:
                    clienteLogado.atualizarCadastro(sc);
                    break;
                case 0:
                    sair = true;
                    telaInicial(sc);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void gerenciarCliente(Scanner sc){ // Fluxo para o ADM gerenciar Clientes, chamando seus respectivos métodos.
        boolean sairCliente = false;
        while(!sairCliente){
            System.out.println("\n--- GERENCIAR CLIENTES ---\n");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atualizar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Remover Cliente");
            System.out.println("0. Voltar ao menu");
            int gerenciarCliente = sc.nextInt();
            sc.nextLine();

            switch (gerenciarCliente) {
                case 1:
                    cadastrarCliente(sc);
                    break;
                case 2:
                    atualizarCliente(sc);
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    removerCliente(sc);
                    break;
                case 0:
                    sairCliente = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void gerenciarJogo(Scanner sc){ // Fluxo para o ADM gerenciar Jogos, chamando seus respectivos métodos.
        boolean sairJogo = false;
        while(!sairJogo){
            System.out.println("\n--- GERENCIAR JOGOS ---\n");
            System.out.println("1. Cadastrar Título de Jogo");
            System.out.println("2. Cadastrar Jogo e Plataforma para Locação");
            System.out.println("3. Atualizar Título de Jogo");
            System.out.println("4. Atualizar Jogo de Locação");
            System.out.println("5. Listar Títulos de Jogos");
            System.out.println("6. Listar Jogos disponíveis para Locação");
            System.out.println("7. Remover Título de Jogo");
            System.out.println("8. Remover Jogo do Catálogo de Locação");
            System.out.println("0. Voltar ao menu");
            int gerenciarJogo = sc.nextInt();
            sc.nextLine();

            switch (gerenciarJogo) {
                case 1:
                    cadastrarJogo(sc);
                    break;
                case 2:
                    cadastrarJogoPlataforma(sc);
                    break;
                case 3:
                    atualizarJogo(sc);
                    break;
                case 4:
                    atualizarJogoPlataforma(sc);
                    break;
                case 5:
                    listarJogos();
                    break;
                case 6:
                    listarJogosPlataformas();
                    break;
                case 7:
                    removerTituloJogo(sc);
                    break;
                case 8:
                    removerJogoLocacao(sc);
                    break;
                case 0:
                    sairJogo = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void gerenciarPlataforma(Scanner sc){ // Fluxo para o ADM gerenciar Plataformas, chamando seus respectivos métodos.
        boolean sairPlataforma = false;
        while(!sairPlataforma){
            System.out.println("\n--- GERENCIAR PLATAFORMAS ---\n");
            System.out.println("1. Cadastrar Plataforma");
            System.out.println("2. Atualizar Plataforma");
            System.out.println("3. Listar Plataformas");
            System.out.println("4. Remover Plataforma");
            System.out.println("5. Cadastrar Console");
            System.out.println("6. Cadastrar Acessório");
            System.out.println("7. Conectar Acessório com Plataforma");
            System.out.println("0. Voltar ao menu");
            int gerenciarPlataforma = sc.nextInt();
            sc.nextLine();

            switch (gerenciarPlataforma) {
                case 1:
                    cadastrarPlataforma(sc);
                    break;
                case 2:
                    atualizarPlataforma(sc);
                    break;
                case 3:
                    listarPlataformas();
                    break;
                case 4:
                    removerPlataforma(sc);
                    break;
                case 5:
                    cadastrarConsole(sc);
                    break;
                case 6:
                    cadastrarAcessorio(sc);
                    break;
                case 7:
                    conectarAcessorioPlataforma(sc);
                    break;
                case 0:
                    sairPlataforma = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void gerenciarLocacoes(Scanner sc){ // Fluxo para o ADM gerenciar Locações, chamando seus respectivos métodos.
        boolean sairLocacoes = false;
        while(!sairLocacoes){
            System.out.println("\n--- GERENCIAR LOCAÇÕES ---\n");
            System.out.println("1. Cadastrar Locação");
            System.out.println("2. Atualizar Locação");
            System.out.println("3. Listar Locações");
            System.out.println("4. Remover Locação");
            System.out.println("0. Voltar ao menu");
            int gerenciarLocacoes = sc.nextInt();
            sc.nextLine();

            switch(gerenciarLocacoes){
                case 1:
                    cadastrarLocacao(sc);
                    break;
                case 2:
                    atualizarLocacao(sc);
                    break;
                case 3:
                    listarLocacoes();
                    break;
                case 4:
                    removerLocacao(sc);
                    break;
                case 0:
                    sairLocacoes = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void gerenciarAlugueis(Scanner sc){ // Fluxo para o ADM gerenciar Aluguéis, chamando seus respectivos métodos.
        boolean sairAlugueis = false;
        while(!sairAlugueis){
            System.out.println("\n--- GERENCIAR ALUGUÉIS ---\n");
            System.out.println("1. Cadastrar Aluguel");
            System.out.println("2. Atualizar Aluguel");
            System.out.println("3. Listar Aluguéis");
            System.out.println("4. Remover Aluguel");
            System.out.println("0. Voltar ao menu");
            int gerenciarAlugueis = sc.nextInt();
            sc.nextLine();

            switch(gerenciarAlugueis){
                case 1:
                    cadastrarAluguel(sc);
                    break;
                case 2:
                    atualizarAluguel(sc);
                    break;
                case 3:
                    listarAlugueis();
                    break;
                case 4:
                    removerAluguel(sc);
                    break;
                case 0:
                    sairAlugueis = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    /// ///////////////////////
    /// /////////////////////////////////////////////////////////////////////
    /// //////////////////////

    public static void cadastrarJogo(Scanner sc) { // Método para o ADM cadastrar um novo Título de Jogo, pedindo apenas o nome.

        System.out.println("\n==============================================");
        System.out.println("    CADASTRO DE NOVO TÍTULO DE JOGO");
        System.out.println("==============================================");
        System.out.println();

        System.out.print(" > Digite o nome do novo jogo: ");
        String nomeJogo = sc.nextLine();
        Jogo novoJogo = new Jogo(nomeJogo);
        jogosCadastrados.put(novoJogo.getId(), novoJogo);

        System.out.println("\n-------------------------------------------");
        System.out.println("| Jogo '" + novoJogo.getNome() + "' cadastrado com o ID " + novoJogo.getId() + ".");
        System.out.println("-------------------------------------------");
    }

    public static void cadastrarPlataforma(Scanner sc) { // Método para o ADM cadastrar uma nova Plataforma, pedindo apenas o nome e descrição (opcional).
        System.out.println("\n======================================");
        System.out.println("    CADASTRO DE NOVA PLATAFORMA");
        System.out.println("======================================");
        System.out.println();
        String nomePlataforma;

        while (true) {
            System.out.print("\n > Digite o nome da nova plataforma: ");
            nomePlataforma = sc.nextLine();

            boolean nomeJaExiste = false;
            for (Plataforma plataforma : plataformasCadastradas.values()) {
                if (plataforma.getNome().equalsIgnoreCase(nomePlataforma)) {
                    nomeJaExiste = true;
                    break;
                }
            }
            if (!nomeJaExiste) {
                break;
            } else {
                System.out.println("\nJá existe uma Plataforma com este nome. Tente novamente.");
            }
        }
        System.out.print("\n > Digite a descrição (opcional, pressione Enter para pular): ");
        String descricaoPlataforma = sc.nextLine();
        Plataforma novaPlataforma;
        if (descricaoPlataforma.isEmpty()) {
            novaPlataforma = new Plataforma(nomePlataforma);
        } else {
            novaPlataforma = new Plataforma(nomePlataforma, descricaoPlataforma);
        }

        plataformasCadastradas.put(novaPlataforma.getId(), novaPlataforma);
        System.out.println("\n--------------------------------------------------------");
        System.out.println(" Plataforma '" + novaPlataforma.getNome() + "' cadastrada com o ID " + novaPlataforma.getId() + ".");
        System.out.println("--------------------------------------------------------");
    }

    public static void cadastrarJogoPlataforma(Scanner sc) { // Aqui é onde o ADM irá fazer a "ponte" entre um Título de Jogo já existente e uma Plataforma também já existente, e assim disponibilizar para Locação.
        // --- CABEÇALHO ---
        System.out.println("\n==================================================");
        System.out.println("    CADASTRAR JOGO-PLATAFORMA");
        System.out.println("================================================");

        if (jogosCadastrados.isEmpty()) {
            System.out.println("\nNenhum título de jogo cadastrado. Cadastre um título primeiro antes de continuar.");
            return;
        }
        System.out.println("Títulos de Jogo existentes:");
        for (Jogo jogo : jogosCadastrados.values()) {
            System.out.println(" ID: " + jogo.getId() + " | Nome: " + jogo.getNome());
        }
        System.out.print("\n > Digite o ID do jogo desejado: ");
        int idJogo = sc.nextInt();
        sc.nextLine();

        if (plataformasCadastradas.isEmpty()) {
            System.out.println("\nNenhuma plataforma cadastrada. Cadastre uma plataforma primeiro antes de continuar.");
            return;
        }
        listarPlataformas();
        System.out.print("\n > Digite o ID da plataforma desejada: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();

        System.out.print(" > Digite o preço diário (ex: 15.50): ");
        double precoDiario = sc.nextDouble();
        System.out.print(" > Digite a quantidade em estoque: ");
        int estoque = sc.nextInt();

        Jogo jogo = jogosCadastrados.get(idJogo);
        Plataforma plataforma = plataformasCadastradas.get(idPlataforma);

        if (jogo != null && plataforma != null) {
            String chaveJogoPlataforma = idJogo + "-" + idPlataforma;

            if (estoqueJogos.containsKey(chaveJogoPlataforma)) {
                System.out.println("\n-------------------------------------------------------------");
                System.out.println("| O jogo '" + jogo.getNome() + "' já está cadastrado para a plataforma '" + plataforma.getNome() + "'. |");
                System.out.println("-------------------------------------------------------------");
            } else {
                JogoPlataforma novoProduto = new JogoPlataforma(jogo, plataforma, estoque, precoDiario);
                estoqueJogos.put(chaveJogoPlataforma, novoProduto);
                System.out.println("\n-------------------------------------------------------------");
                System.out.println("| O jogo '" + jogo.getNome() + "' foi disponibilizado para '" + plataforma.getNome() + "'. |");
                System.out.println("-------------------------------------------------------------");
            }
        } else {
            System.out.println("\n-------------------------------------------");
            System.out.println("| ID de Jogo ou Plataforma inválido.        |");
            System.out.println("-------------------------------------------");
        }
    }

    public static void listarClientes() { // Método simples para listar todos Clientes cadastrados usando o HashMap.
        System.out.println("\n=======================================================");
        System.out.println("        LISTA DE CLIENTES CADASTRADOS");
        System.out.println("=======================================================");

        if (clientesCadastrados.isEmpty()) {
            System.out.println("\nNenhum Cliente cadastrado no sistema.");
        } else {
            for (Cliente cliente : clientesCadastrados.values()) {
                System.out.println("\n ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Telefone: " + cliente.getTelefone());
                System.out.println();
            }
            System.out.println("-------------------------------------------------------");
        }
    }

    public static void listarJogosPlataformas() { // Método simples para listar todos os Jogos-Plataformas disponíveis no sistema, usando o HashMap.
        System.out.println("\n=======================================================================");
        System.out.println("                 ESTOQUE DE JOGOS PARA LOCAÇÃO");
        System.out.println("=======================================================================");

        if (estoqueJogos.isEmpty()) {
            System.out.println("\nNenhum Jogo disponível no estoque.");
        } else {
            for (JogoPlataforma jogo : estoqueJogos.values()) {
                System.out.println("\n Chave: " + jogo.getChaveComposta() + " | Jogo: " + jogo.getJogo().getNome() + " | Plataforma: " + jogo.getPlataforma().getNome() + " | Em estoque: " + jogo.getQuantidadeEstoque() + String.format(" | Preço Diário: %.2f", jogo.getPrecoDiario()));
                System.out.println();
            }
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public static void listarJogos() { // Método simples para listar todos os Títulos de Jogos cadastrados usando o HashMap.
        System.out.println("\n==============================================");
        System.out.println("        LISTA DE TÍTULOS DE JOGOS CADASTRADOS");
        System.out.println("==============================================");

        if (jogosCadastrados.isEmpty()) {
            System.out.println("\nNenhum título de Jogo cadastrado no sistema.");
        } else {
            for (Jogo jogo : jogosCadastrados.values()) {
                System.out.println("\n ID: " + jogo.getId() + " | Nome: " + jogo.getNome());
                System.out.println();
            }
            System.out.println("----------------------------------------------");
        }
    }

    public static void listarPlataformas() { // Método simples para listar todas Plataformas cadastradas usando o HashMap.
        System.out.println("\n====================================================================");
        System.out.println("                 LISTA DE PLATAFORMAS CADASTRADAS");
        System.out.println("====================================================================");

        if (plataformasCadastradas.isEmpty()) {
            System.out.println("\nNenhuma Plataforma cadastrada no sistema.");
        } else {
            for (Plataforma plataforma : plataformasCadastradas.values()) {
                System.out.println("\n ID: " + plataforma.getId() + " | Nome: " + plataforma.getNome());
                System.out.println();
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }

    public static void listarAcessorios() { // Método simples para listar todos Acessórios cadastrados usando o HashMap.
        System.out.println("\n====================================================================");
        System.out.println("                LISTA DE ACESSÓRIOS CADASTRADOS");
        System.out.println("====================================================================");

        if (acessoriosDisponiveis.isEmpty()) {
            System.out.println("\nNenhum Acessório cadastrado até o momento.");
        } else {
            for (Acessorio acessorio : acessoriosDisponiveis.values()) {
                System.out.println("\n ID: " + acessorio.getId() + " | Nome: " + acessorio.getNome() + " | Em estoque: " + acessorio.getEstoque() + " | Valor: " + acessorio.getValor());
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void listarLocacoes() { // Método para listar todas as Locações cadastradas no sistema geral, usando o HashMap.
        System.out.println("\n====================================================================");
        System.out.println("                   HISTÓRICO GERAL DE LOCAÇÕES");
        System.out.println("====================================================================");

        if (historicoGeralLocacoes.isEmpty()) {
            System.out.println("\nNenhuma Locação registrada até o momento.");
        } else {
            for (LocacaoJogo locacao : historicoGeralLocacoes.values()) {
                System.out.println("\nID da Locação: " + locacao.getId());
                System.out.println("Cliente: " + locacao.getCliente().getNome() + " ID: " + locacao.getCliente().getId());
                System.out.println("Data: " + locacao.getData());
                System.out.println("Jogos locados nesta Locação: ");

                List<ItemLocacao> itensLocacao = locacao.getItens();
                for (ItemLocacao item : itensLocacao) {
                    System.out.println(" Jogo: " + item.getJogoPlataforma().getJogo().getNome() + " | Plataforma: " + item.getJogoPlataforma().getPlataforma().getNome());
                }
                System.out.println("....................................................................");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void listarAlugueis() { // Método para listar todos os Aluguéis cadastrados no sistema geral, usando o HashMap.
        System.out.println("\n====================================================================");
        System.out.println("                   HISTÓRICO GERAL DE ALUGUÉIS");
        System.out.println("====================================================================");

        if (historicoGeralAlugueis.isEmpty()) {
            System.out.println("\nNenhum Aluguel registrado até o momento.");
        } else {
            for (AluguelConsole aluguel : historicoGeralAlugueis.values()) {
                System.out.println("\nID do Aluguel: " + aluguel.getId());
                System.out.println("\nCliente: " + aluguel.getCliente().getNome() + " | ID: " + aluguel.getCliente().getId());
                System.out.println("\nData e Hora: " + aluguel.getDataHora());
                System.out.println("\nConsole Alugado: " + aluguel.getConsole().getNome());
                List<Acessorio> acessorios = aluguel.getAcessorios();
                if (acessorios.isEmpty()) {
                    System.out.println("\n  -> Nenhum acessório adicional.");
                } else {
                    System.out.println("\nAcessórios alugados: ");
                    for (Acessorio acessorio : acessorios) {
                        System.out.println(" Acessório: " + acessorio.getNome());
                    }
                }
                System.out.println("\nValor Total: R$" + String.format("%.2f", aluguel.getValorTotal()));
                System.out.println("....................................................................");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void atualizarJogo(Scanner sc) { // Método que usa a ideia dos Setters, para atualizar um Título de Jogo.
        System.out.println("\n====================================================================");
        System.out.println("                        ATUALIZAÇÃO DE JOGO");
        System.out.println("====================================================================");

        if (jogosCadastrados.isEmpty()) {
            System.out.println("\nNenhum jogo cadastrado para atualizar.");
            return;
        }

        System.out.println("\nJogos disponíveis para edição:");
        for (Jogo jogo : jogosCadastrados.values()) {
            System.out.println(" ID: " + jogo.getId() + " | Nome: " + jogo.getNome());
        }
        System.out.println("....................................................................");
        System.out.print("\n> Digite o ID do jogo que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Jogo jogoAtualizar = jogosCadastrados.get(id);

        if (jogoAtualizar != null) {
            System.out.print("> Digite o novo nome (ENTER para manter o atual): ");
            String novoNome = sc.nextLine();
            if (novoNome != null) {
                jogoAtualizar.setNome(novoNome.trim());
            }
            System.out.println("\nJogo atualizado.");

        } else {
            System.out.println("\nID de jogo não encontrado.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void atualizarPlataforma(Scanner sc){ // Método que usa a ideia dos Setters, para atualizar uma Plataforma (nome, e descrição opcional).
        System.out.println("\n====================================================================");
        System.out.println("                     ATUALIZAÇÃO DE PLATAFORMA");
        System.out.println("====================================================================");

        System.out.println("\n--- Atualizar Plataforma ---");
        listarPlataformas();
        System.out.println("\nDigite o ID da Plataforma que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Plataforma plataformaAtualizar = plataformasCadastradas.get(id);

        if(plataformaAtualizar != null){
            System.out.println("\nDigite o novo nome (Enter para pular): ");
            String novoNome = sc.nextLine();
            System.out.println("\nDigite a nova descrição (Enter para pular): ");
            String novaDescricao = sc.nextLine();

            if(!novoNome.isEmpty()){
                plataformaAtualizar.setNome(novoNome);
            }
            if(!novaDescricao.isEmpty()){
                plataformaAtualizar.setDescricao(novaDescricao);
            }
            System.out.println("\nPlataforma atualizada com sucesso.");
        }else{
            System.out.println("\nID não encontrado.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void atualizarJogoPlataforma(Scanner sc){ // Método que usa a ideia dos Setters, para atualizar um Jogo-Plataforma (preço diário e quantidade em estoque).
        System.out.println("\n====================================================================");
        System.out.println("                    ATUALIZAR JOGO PARA ALUGAR");
        System.out.println("====================================================================");

        listarJogosPlataformas();
        System.out.println("\nDigite o ID que deseja atualizar: ");
        String chaveComposta = sc.nextLine();
        JogoPlataforma jogoAtualizar = estoqueJogos.get(chaveComposta);

        if(jogoAtualizar != null){
            System.out.println("\nDigite o novo preço diário (Enter para pular): ");
            String novoPrecoString = sc.nextLine();
            System.out.println("\nDigite a nova quantidade em estoque (Enter para pular): ");
            String novoEstoqueString = sc.nextLine();

            if(!novoPrecoString.isEmpty()){
                double novoPreco = Double.parseDouble(novoPrecoString);
                jogoAtualizar.setPrecoDiario(novoPreco);
            }
            if(!novoEstoqueString.isEmpty()){
                int novoEstoque = Integer.parseInt(novoEstoqueString);
                jogoAtualizar.setQuantidadeEstoque(novoEstoque);
            }
            System.out.println("\nJogo para Alugar atualizado.");
        }else{
            System.out.println("\nID do Jogo não encontrado.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void atualizarCliente(Scanner sc){ // Método que usa a ideia dos Setters, para atualizar um Cliente (nome, email, telefone e senha).
        System.out.println("\n====================================================================");
        System.out.println("                       ATUALIZAÇÃO DE CLIENTE");
        System.out.println("====================================================================");

        listarClientes();
        System.out.println("\nDigite o ID do cliente que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Cliente clienteAtualizar = clientesCadastrados.get(id);

        if(clienteAtualizar != null){
            System.out.println("\nDigite o novo nome (Enter para pular): ");
            String novoNome = sc.nextLine();
            System.out.println("\nDigite o novo e-mail (Enter para pular): ");
            String novoEmail = sc.nextLine();
            System.out.println("\nDigite o novo telefone (Enter para pular): ");
            String novoTelefone = sc.nextLine();
            System.out.println("\nDigite a nova senha (Enter para pular): ");
            String novaSenha = sc.nextLine();

            if(!novoNome.isEmpty()){
                clienteAtualizar.setNome(novoNome);
            }
            if(!novoEmail.isEmpty()){
                clienteAtualizar.setEmail(novoEmail);
            }
            if(!novoTelefone.isEmpty()){
                clienteAtualizar.setTelefone(novoTelefone);
            }
            if(!novaSenha.isEmpty()){
                clienteAtualizar.setSenha(novaSenha);
            }
            System.out.println("\nCliente atualizado com sucesso.");
        }else{
            System.out.println("\nID não encontrado.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void atualizarLocacao(Scanner sc){ // Método que usa a ideia dos Setters, para atualizar uma Locação (podendo adicionar um remover um jogo).
        // Cabeçalho estilizado para a operação
        System.out.println("\n====================================================================");
        System.out.println("                      ATUALIZAÇÃO DE LOCAÇÃO");
        System.out.println("====================================================================");

        listarLocacoes();
        System.out.println("\nDigite o ID da Locação que deseja atualizar: ");
        int idLocacao = sc.nextInt();
        sc.nextLine();
        LocacaoJogo locacaoAtualizar = historicoGeralLocacoes.get(idLocacao);

        if(locacaoAtualizar == null){
            System.out.println("\nID não encontrado.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }
        boolean sair = false;
        while(!sair){
            System.out.println("\nAtualizando a Locação #" + locacaoAtualizar.getId() + " de " + locacaoAtualizar.getCliente().getNome());
            System.out.println("1. Adicionar Jogo");
            System.out.println("2. Remover Jogo");
            System.out.println("0. Concluir Atualização");
            System.out.print("> Escolha uma opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch(escolha){
                case 1:
                    listarJogosPlataformas();
                    System.out.println("\nDigite o ID do Jogo desejado: ");
                    String chaveJogo = sc.nextLine();
                    System.out.println("\nDigite a quantidade de dias da locação: ");
                    int dias = sc.nextInt();
                    sc.nextLine();
                    JogoPlataforma jogoAdicionar = estoqueJogos.get(chaveJogo);
                    if(jogoAdicionar != null){
                        locacaoAtualizar.adicionarItem(jogoAdicionar, dias);
                        System.out.println("\nJogo adicionado à Locação.");
                    }else{
                        System.out.println("\nID não encontrado");
                    }
                    break;
                case 2:
                    locacaoAtualizar.listarItens();
                    System.out.println("\nDigite o número do item que deseja remover: ");
                    int indice = sc.nextInt();
                    sc.nextLine();

                    if(indice > 0 && indice <= locacaoAtualizar.getItens().size()){
                        int indiceRemover = indice - 1;
                        ItemLocacao itemRemovido = locacaoAtualizar.getItens().get(indiceRemover);
                        locacaoAtualizar.removerItem(itemRemovido);

                        System.out.println("\nO item '" + itemRemovido.getJogoPlataforma().getJogo().getNome() + "' foi removido com sucesso.");
                    }else{
                        System.out.println("\nNúmero não foi encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("\nAtualização concluída.");
                    sair = true;
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void atualizarAluguel(Scanner sc){ // Método que usa a ideia dos Setters, para atualizar um Aluguel (podendo adicionar ou remover um acessório, e também alterar a duração do Aluguel).
        System.out.println("\n====================================================================");
        System.out.println("                       ATUALIZAÇÃO DE ALUGUEL");
        System.out.println("====================================================================");

        listarAlugueis();
        System.out.println("\nDigite o ID do Aluguel que deseja atualizar: ");
        int idAluguel = sc.nextInt();
        sc.nextLine();
        AluguelConsole aluguelAtualizar = historicoGeralAlugueis.get(idAluguel);

        if(aluguelAtualizar == null){
            System.out.println("\nID não encontrado.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }
        boolean sair = false;
        while(!sair){
            System.out.println("\nAtualizando o Aluguel #" + aluguelAtualizar.getId() + " de " + aluguelAtualizar.getCliente().getNome());
            System.out.println("1. Adicionar Acessório");
            System.out.println("2. Remover Acessório");
            System.out.println("3. Alterar duração do Aluguel");
            System.out.println("0. Concluir");
            System.out.print("> Escolha uma opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch(escolha){
                case 1:
                    listarAcessorios();
                    System.out.println("\nDigite o ID do Acessório desejado: ");
                    int idAcessorioAdicionar = sc.nextInt();
                    sc.nextLine();
                    Acessorio acessorioAdicionar = acessoriosDisponiveis.get(idAcessorioAdicionar);
                    if(acessorioAdicionar != null){
                        aluguelAtualizar.adicionarAcessorio(acessorioAdicionar);
                        System.out.println("\nAcessório adicionado ao Aluguel.");
                    }else{
                        System.out.println("\nID não encontrado");
                    }
                    break;
                case 2:
                    aluguelAtualizar.listarAcessorios();
                    System.out.println("\nDigite o ID do Acessório que deseja remover: ");
                    int idAcessorioRemover = sc.nextInt();
                    sc.nextLine();
                    Acessorio acessorioRemover = aluguelAtualizar.getAcessorios().remove(idAcessorioRemover);
                    if(acessorioRemover != null){
                        System.out.println("\nO acessório '" + acessorioRemover.getNome() + "' foi removido com sucesso.");
                    }else{
                        System.out.println("\nID não foi encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("\nDigite a nova duração em horas: ");
                    int novaDuracao = sc.nextInt();
                    sc.nextLine();
                    if(novaDuracao > 0) {
                        aluguelAtualizar.setDuracaoHoras(novaDuracao);
                        System.out.println("\nA duração foi alterada com sucesso.");
                    } else {
                        System.out.println("\nDuração inválida.");
                    }
                    break;
                case 0:
                    System.out.println("\nAtualização concluída.");
                    sair = true;
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void removerTituloJogo(Scanner sc){ // Método usado para remover um Título de Jogo.
        System.out.println("\n====================================================================");
        System.out.println("                      REMOVER TÍTULO DE JOGO");
        System.out.println("====================================================================");

        listarJogos();
        System.out.println("\nDigite o ID do Jogo que deseja remover: ");
        int idJogo = sc.nextInt();
        sc.nextLine();
        if(!jogosCadastrados.containsKey(idJogo)){
            System.out.println("\nID não encontrado.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }
        for(JogoPlataforma jogo : estoqueJogos.values()){
            if(jogo.getJogo().getId() == idJogo){
                System.out.println("\nO título não pode ser removido, pois ele ainda está sendo usado para Aluguéis.");
                System.out.println("--------------------------------------------------------------------");
                return;
            }
        }
        Jogo jogoRemover = jogosCadastrados.remove(idJogo);
        if(jogoRemover != null){
            System.out.println("\nO Jogo '" + jogoRemover.getNome() + "' foi removido.");
        }else{
            System.out.println("\nID não encontrado ao tentar remover.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void removerPlataforma(Scanner sc){ // Método usado para remover uma Plataforma (se estiver vinculada em algum Jogo-Plataforma, não pode ser removida).
        System.out.println("\n====================================================================");
        System.out.println("                         REMOVER PLATAFORMA");
        System.out.println("====================================================================");

        listarPlataformas();
        System.out.println("\nDigite o ID da Plataforma que deseja remover: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();

        boolean emUso = false;
        for (JogoPlataforma jp : estoqueJogos.values()) {
            if (jp.getPlataforma().getId() == idPlataforma) {
                emUso = true;
                break;
            }
        }
        if (emUso) {
            System.out.println("\nPlataforma não pode ser removida, pois está em um Jogo disponível para Locação.");
        } else {
            Plataforma plataformaRemovida = plataformasCadastradas.remove(idPlataforma);
            if(plataformaRemovida != null){
                System.out.println("\nA Plataforma '" + plataformaRemovida.getNome() + "' foi removida.");
            }else{
                System.out.println("\nID não encontrado.");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void removerJogoLocacao(Scanner sc){ // Método usado para remover um Jogo-Plataforma do estoque de Locação (se estiver fazendo parte de alguma Locação, não pode ser removido).
        System.out.println("\n====================================================================");
        System.out.println("                      REMOVER JOGO PARA LOCAÇÃO");
        System.out.println("====================================================================");

        listarJogosPlataformas();
        System.out.println("\nDigite o ID do Jogo-Plataforma que deseja remover: ");
        String chaveComposta = sc.nextLine();

        boolean emUso = false;
        for (LocacaoJogo locacao : historicoGeralLocacoes.values()) {
            for (ItemLocacao item : locacao.getItens()) {
                String chaveItem = item.getJogoPlataforma().getJogo().getId() + "-" + item.getJogoPlataforma().getPlataforma().getId();
                if (chaveItem.equals(chaveComposta)) {
                    emUso = true;
                    break;
                }
            }
        }
        if (emUso) {
            System.out.println("\nEste jogo não pode ser removido, pois faz parte de uma Locação existente.");
        } else {
            JogoPlataforma jogoRemovido = estoqueJogos.remove(chaveComposta);
            if(jogoRemovido != null){
                System.out.println("\nO Jogo-Plataforma '" + jogoRemovido.getJogo().getNome() + " (" + jogoRemovido.getPlataforma().getNome() + ")' foi removido.");
            }else{
                System.out.println("\nID não encontrado.");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void removerLocacao(Scanner sc){ // Método usado para remover uma Locação.
        System.out.println("\n====================================================================");
        System.out.println("                          REMOVER LOCAÇÃO");
        System.out.println("====================================================================");

        listarLocacoes();
        System.out.println("\nDigite o ID da Locação que deseja remover: ");
        int idLocacao = sc.nextInt();
        sc.nextLine();
        LocacaoJogo locacaoRemovida = historicoGeralLocacoes.remove(idLocacao);
        if(locacaoRemovida != null){
            System.out.println("\nA Locação #" + locacaoRemovida.getId() + " do cliente '" + locacaoRemovida.getCliente().getNome() + "' foi removida.");
        }else{
            System.out.println("\nID não encontrado.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void removerAluguel(Scanner sc){ // Método usado para remover um Aluguel.
        System.out.println("\n====================================================================");
        System.out.println("                           REMOVER ALUGUEL");
        System.out.println("====================================================================");

        listarAlugueis();
        System.out.println("\nDigite o ID do Aluguel que deseja remover: ");
        int idAluguel = sc.nextInt();
        sc.nextLine();
        AluguelConsole aluguelRemovido = historicoGeralAlugueis.remove(idAluguel);
        if(aluguelRemovido != null){
            System.out.println("\nO Aluguel #" + aluguelRemovido.getId() + " do cliente '" + aluguelRemovido.getCliente().getNome() + "' foi removido.");
        }else{
            System.out.println("\nID não encontrado.");
        }

        System.out.println("--------------------------------------------------------------------");
    }

    public static void removerCliente(Scanner sc){ // Método usado para remover um Cliente (se o cliente estiver com locação/Aluguel ativo, não pode ser removido).
        System.out.println("\n====================================================================");
        System.out.println("                           REMOVER CLIENTE");
        System.out.println("====================================================================");

        listarClientes();
        System.out.println("\nDigite o ID do Cliente que deseja remover: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        boolean emUso = false;
        for (LocacaoJogo locacao : historicoGeralLocacoes.values()) {
            if (locacao.getCliente().getId() == idCliente) {
                emUso = true;
                break;
            }
        }
        if (!emUso) {
            for (AluguelConsole aluguel : historicoGeralAlugueis.values()) {
                if (aluguel.getCliente().getId() == idCliente) {
                    emUso = true;
                    break;
                }
            }
        }
        if (emUso) {
            System.out.println("\nEste cliente não pode ser removido, pois possui Aluguel/Locação ativo.");
        } else {
            Cliente clienteRemovido = clientesCadastrados.remove(idCliente);
            if(clienteRemovido != null){
                System.out.println("\nO Cliente '" + clienteRemovido.getNome() + "' foi removido.");
            }else{
                System.out.println("\nID não encontrado.");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void cadastrarConsole(Scanner sc) { // Método usado para cadastrar um Console, conectando com alguma Plataforma já existente.
        System.out.println("\n====================================================================");
        System.out.println("                        CADASTRO DE CONSOLE");
        System.out.println("====================================================================");

        listarPlataformas();
        System.out.println("\nDigite o ID da plataforma que o console pertence: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();
        System.out.println("\nDigite o nome do console: ");
        String nomeConsole = sc.nextLine();
        System.out.println("\nDigite o preço por hora: ");
        double precoPorHora = sc.nextDouble();
        Plataforma plataformaDoConsole = plataformasCadastradas.get(idPlataforma);
        if (plataformaDoConsole != null) {
            Console novoConsole = new Console(nomeConsole, plataformaDoConsole, precoPorHora);
            consolesDisponiveis.put(novoConsole.getId(), novoConsole);
            System.out.println("\nO console " + nomeConsole + " foi cadastrado com sucesso.");
        }else{
            System.out.println("\nID não encontrado..");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void cadastrarAcessorio(Scanner sc){ // Método usado para cadastrar um Acessório.
        System.out.println("\n====================================================================");
        System.out.println("                       CADASTRO DE ACESSÓRIO");
        System.out.println("====================================================================");

        System.out.println("Digite o nome do novo acessório: ");
        String nomeAcessorio = sc.nextLine();
        System.out.println("Digite o valor do acessório: ");
        double valorAcessorio = sc.nextDouble();
        sc.nextLine();
        System.out.println("Digite a quantidade em estoque: ");
        int estoqueAcessorio = sc.nextInt();
        sc.nextLine();
        Acessorio novoAcessorio = new Acessorio(nomeAcessorio, estoqueAcessorio, valorAcessorio);
        acessoriosDisponiveis.put(novoAcessorio.getId(), novoAcessorio);
        System.out.println("O acessório " + nomeAcessorio + " foi cadastrado com sucesso.");
        System.out.println("--------------------------------------------------------------------");
    }

    public static void conectarAcessorioPlataforma(Scanner sc){ // Método usado para conectar um Acessório já existente, com uma Plataforma também já existente.
        System.out.println("\n====================================================================");
        System.out.println("                COMPATIBILIDADE ACESSÓRIO/PLATAFORMA");
        System.out.println("====================================================================");

        listarAcessorios();
        System.out.println("\nDigite o ID do acessório: ");
        int idAcessorio = sc.nextInt();
        sc.nextLine();
        listarPlataformas();
        System.out.println("\nDigite o ID da plataforma desejada para associar: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();

        Acessorio acessorio = acessoriosDisponiveis.get(idAcessorio);
        Plataforma plataforma = plataformasCadastradas.get(idPlataforma);
        if(acessorio != null && plataforma != null){
            plataforma.adicionarAcessorio(acessorio);
            acessorio.adicionarPlataforma(plataforma);
            System.out.println("\nO acessório '" + acessorio.getNome() + "' agora é compatível com a plataforma '" + plataforma.getNome() + "'");
        }else{
            System.out.println("\nID de acessório ou plataforma inválido.");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void cadastrarCliente(Scanner sc) { // Método simples para cadastrar um novo Cliente.
        System.out.println("\n====================================================================");
        System.out.println("                        CADASTRO DE CLIENTE");
        System.out.println("====================================================================");

        System.out.println("\n Digite o nome do cliente: ");
        String nomeCliente = sc.nextLine();
        System.out.println("\n Digite o email do cliente: ");
        String emailCliente = sc.nextLine();
        System.out.println("\n Digite o telefone do cliente: ");
        String telefoneCliente = sc.nextLine();
        System.out.println("\n Digite a senha do cliente: ");
        String senhaCliente = sc.nextLine();

        Cliente novoCliente = new Cliente(nomeCliente, emailCliente, telefoneCliente, senhaCliente);
        clientesCadastrados.put(novoCliente.getId(), novoCliente);
        System.out.println("\nO cliente " + nomeCliente + " foi cadastrado com sucesso.\n");
        System.out.println("--------------------------------------------------------------------");
    }

    public static void cadastrarLocacao(Scanner sc) { // Método usado para o ADM cadastrar uma nova Locação, escolhendo o Cliente que será o dono dela, e ao final imprimindo um comprovante, mostrando as informações da Locação, usando um formatador do LocalDate e LocalDateTime, para organizar na data brasileira.
        System.out.println("\n====================================================================");
        System.out.println("                        NOVA LOCAÇÃO DE JOGO");
        System.out.println("====================================================================");

        if (clientesCadastrados.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado. Cadastre um cliente primeiro.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }
        if (estoqueJogos.isEmpty()) {
            System.out.println("\nNenhum jogo disponível no estoque para locação.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }

        listarClientes();
        System.out.println("\nDigite o ID do cliente da Locação: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        Cliente clienteLocacao = clientesCadastrados.get(idCliente);

        if (clienteLocacao == null) {
            System.out.println("\nCliente não encontrado.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }

        LocacaoJogo novaLocacao = new LocacaoJogo(clienteLocacao);
        listarJogosPlataformas();
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
            } else if (produtoEscolhido.getQuantidadeEstoque() == 0) {
                System.out.println("\nO jogo '" + produtoEscolhido.getJogo().getNome() + "' está sem estoque.");
            }
        }
        System.out.print("\n> Confirmar a Locação? (S/N): ");
        String confirmacao = sc.nextLine();
        if(confirmacao.equalsIgnoreCase("S")) {
            System.out.println("\n======================================");
            System.out.println("      Comprovante de Locação");
            System.out.println("======================================\n");
            System.out.println("ID da Locação: " + novaLocacao.getId());
            System.out.println("Cliente: " + novaLocacao.getCliente().getNome());
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = novaLocacao.getData().format(formatador);
            System.out.println("Data da Locação: " + dataFormatada);
            System.out.println("\nItens Locados:");

            for (ItemLocacao item : novaLocacao.getItens()) {
                System.out.println("  - " + item.getJogoPlataforma().getJogo().getNome() +
                        " (" + item.getJogoPlataforma().getPlataforma().getNome() +
                        ") por " + item.getQuantidadeDias() + " dias.");
            }

            System.out.println("\nValor Total: R$" + String.format("%.2f", novaLocacao.getValorTotal()));
            clienteLocacao.adicionarLocacao(novaLocacao);
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

    public static void cadastrarAluguel(Scanner sc) { // Método usado para o ADM cadastrar um novo Aluguel, escolhendo o Cliente que será o dono dele, e ao final imprimindo um breve comprovante, mostrando as informações do Aluguel realizado, usando um formatador do LocalDate e LocalDateTime, para organizar na data brasileira.
        System.out.println("\n====================================================================");
        System.out.println("                     NOVO ALUGUEL DE CONSOLE");
        System.out.println("====================================================================");

        if (clientesCadastrados.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado. Cadastre um cliente primeiro.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }
        if (consolesDisponiveis.isEmpty()) {
            System.out.println("\nNenhum console disponível no momento.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }

        listarClientes();
        System.out.println("\nDigite o ID do cliente para o Aluguel: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        Cliente clienteAluguel = clientesCadastrados.get(idCliente);
        if (clienteAluguel == null) {
            System.out.println("\nCliente não encontrado.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }

        System.out.println("\nConsoles disponíveis: ");
        for (Console console : consolesDisponiveis.values()) {
            if (console.getDisponibilidade()) {
                System.out.println("  ID: " + console.getId() + " | Console: " + console.getNome() + String.format(" | Preço por Hora: %.2f", console.getPrecoPorHora()));
            }
        }
        System.out.println("\nDigite o ID do console que gostaria de alugar: ");
        int idConsole = sc.nextInt();
        sc.nextLine();
        Console consoleEscolhido = consolesDisponiveis.get(idConsole);
        if (consoleEscolhido == null || !consoleEscolhido.getDisponibilidade()) {
            System.out.println("\nID de console inválido ou indisponível.");
            System.out.println("--------------------------------------------------------------------");
            return;
        }

        System.out.println("\nDigite a quantidade em horas do aluguel deste console: ");
        int horasDeAluguel = sc.nextInt();
        sc.nextLine();
        consoleEscolhido.alugar();
        AluguelConsole novoAluguel = new AluguelConsole(clienteAluguel, consoleEscolhido, horasDeAluguel);

        System.out.println("\nAcessórios compatíveis com: " + consoleEscolhido.getNome());
        List<Acessorio> acessoriosCompativeis = consoleEscolhido.getPlataforma().getAcessorios();
        if(acessoriosCompativeis.isEmpty()){
            System.out.println("\nNenhum acessório compatível com este console.");
        }else{
            for(Acessorio acessorio : acessoriosCompativeis){
                if(acessorio.getEstoque() > 0){
                    System.out.printf(" ID: %d | Nome: %s | Valor: R$%.2f | Estoque: %d%n\n", acessorio.getId(), acessorio.getNome(), acessorio.getValor(), acessorio.getEstoque());
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

        System.out.print("\n> Confirmar o Aluguel? (S/N): ");
        String confirmacao = sc.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            clienteAluguel.adicionarAluguel(novoAluguel);
            historicoGeralAlugueis.put(novoAluguel.getId(), novoAluguel);

            System.out.println("\n======================================");
            System.out.println("      Comprovante de Aluguel");
            System.out.println("======================================\n");

            System.out.println("\nID do Aluguel: " + novoAluguel.getId());
            System.out.println("\nCliente: " + novoAluguel.getCliente().getNome());
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
            System.out.println("\nData e Hora: " + novoAluguel.getDataHora().format(formatador));
            System.out.println("\nItens Alugados:");
            System.out.println("  - Console: " + novoAluguel.getConsole().getNome() + " (" + novoAluguel.getDuracaoHoras() + " horas)");
            if (!novoAluguel.getAcessorios().isEmpty()) {
                System.out.println("  - Acessórios: ");
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