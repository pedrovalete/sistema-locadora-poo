package org.example;

import java.time.format.SignStyle;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import org.example.domain.*;

public class App {
    private static Map<Integer, Cliente> clientesCadastrados = new HashMap<>();
    private static Map<Integer, Jogo> jogosCadastrados = new HashMap<>();
    private static Map<Integer, Plataforma> plataformasCadastradas = new HashMap<>();
    private static Map<Integer, Console> consolesDisponiveis = new HashMap<>();
    private static Map<Integer, Acessorio>  acessoriosDisponiveis = new HashMap<>();
    private static Map<String, JogoPlataforma> estoqueJogos = new HashMap<>();

    private static final String SENHA_ADMIN = "senhaadm123";

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Pedro", "abc@abc.com", "9999-9999", "abc123");
        clientesCadastrados.put(cliente.getId(), cliente);
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite a senha se for administrador, ou qualquer outra tecla para cliente: \n");
        String entrada = sc.nextLine();

        if (entrada.equals(SENHA_ADMIN)) {
            menuAdmin(sc);
        } else {
            loginCliente(sc);
        }
    }

    public static void menuAdmin(Scanner sc) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- MENU ADMINISTRADOR ---\n");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Jogos");
            System.out.println("3. Gerenciar Plataformas");
            System.out.println("4. Gerenciar Locações");
            System.out.println("5. Gerenciar Aluguéis");
            System.out.println("6. Gerenciar Estoque");
            System.out.println("0. Sair");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    gerenciarCliente(sc);
                    break;
                case 2:
                    boolean sairJogo = false;
                    while (!sairJogo) {
                        System.out.println("\n--- GERENCIAR JOGOS ---\n");
                        System.out.println("1. Cadastrar Jogo");
                        System.out.println("2. Atualizar Jogo");
                        System.out.println("3. Listar Jogos");
                        System.out.println("4. Remover Jogo");
                        System.out.println("0. Voltar ao menu");
                        int gerenciarJogo = sc.nextInt();
                        sc.nextLine();

                        switch (gerenciarJogo) {
                            case 1:
                                cadastrarJogo(sc);
                                break;
                            case 0:
                                sairJogo = true;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean sairPlataforma = false;
                    while (!sairPlataforma) {
                        System.out.println("\n--- GERENCIAR PLATAFORMAS ---\n");
                        System.out.println("1. Cadastrar Plataforma");
                        System.out.println("2. Atualizar Plataforma");
                        System.out.println("3. Listar plataforma");
                        System.out.println("4. Remover Platforma");
                        System.out.println("0. Voltar ao menu");
                        int gerenciarPlataforma = sc.nextInt();
                        sc.nextLine();

                        switch (gerenciarPlataforma) {
                            case 1:
                                cadastrarPlataforma(sc);
                                break;
                            case 0:
                                sairPlataforma = true;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void loginCliente(Scanner sc) {
        System.out.println("\n--- LOGIN DO CLIENTE ---\n");

        System.out.println("Digite o seu ID de cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite sua senha: ");
        String senhaDigitada = sc.nextLine();

        Cliente clienteLogin = clientesCadastrados.get(idCliente);

        if (clienteLogin != null && clienteLogin.autenticar(senhaDigitada)) {
            System.out.println("\nLogin realizado. Bem vindo, " + clienteLogin.getNome());
            menuCliente(sc, clienteLogin);
        } else {
            System.out.println("\nID de cliente ou senha incorreta. Tente novamente.");
        }
    }

    public static void menuCliente(Scanner sc, Cliente clienteLogado) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- MENU CLIENTE ---\n");
            System.out.println("1. Locação de Jogos");
            System.out.println("2. Alugar Consoles");
            System.out.println("3. Consultar histórico");
            System.out.println("4. Consultar jogos disponíveis");
            System.out.println("5. Atualizar cadastro");
            System.out.println("0. Sair");
            int escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 0:
                    sair = true;
                    break;
                case 5:
                    clienteLogado.atualizarCadastro(sc);
            }
        }
    }

    public static void gerenciarCliente(Scanner sc){
        boolean sairCliente = false;
        while (!sairCliente) {
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
                case 0:
                    sairCliente = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void cadastrarJogo(Scanner sc) {
        System.out.println("\n--- Cadastro de Jogo ---");
        System.out.println(" Digite o nome do jogo: ");
        String nomeJogo = sc.nextLine();
        Jogo novoJogo = new Jogo(nomeJogo);
        jogosCadastrados.put(novoJogo.getId(), novoJogo);
        System.out.println("\nJogo '" + novoJogo.getNome() + "' cadastrado com sucesso.");
    }

    public static void cadastrarPlataforma(Scanner sc) {
        System.out.println("\n--- Cadastro de Plataforma ---");
        System.out.println(" Digite o nome da plataforma: ");
        String nomePlataforma = sc.nextLine();
        boolean comparacaoNomes = false;
        for (Plataforma plataforma : plataformasCadastradas.values()) {
            if (plataforma.getNome().equalsIgnoreCase(nomePlataforma)) {
                comparacaoNomes = true;
                break;
            }
        }
        if (comparacaoNomes == true) {
            System.out.println("\nPlataforma já existente. Tente novamente.");
        } else {
            System.out.println(" Informe uma descrição (opcional, presisione Enter para pular): ");
            String descricaoPlataforma = sc.nextLine();

            Plataforma novaPlataforma;
            if (descricaoPlataforma.isEmpty()) {
                novaPlataforma = new Plataforma(nomePlataforma);
            } else {
                novaPlataforma = new Plataforma(nomePlataforma, descricaoPlataforma);
            }

            plataformasCadastradas.put(novaPlataforma.getId(), novaPlataforma);
            System.out.println("Plataforma '" + novaPlataforma.getNome() + "' cadastrada com sucesso.");
        }
    }

    public static void cadastrarJogoPlataforma(Scanner sc) {
        System.out.println("\n--- Cadastro de Jogo e Plataforma ---");
        if (jogosCadastrados.isEmpty()) {
            System.out.println(" Nenhum jogo cadastrado até o momento.");
        } else {
            System.out.println(" Jogos presentes no sistema: ");
            for (Integer id : jogosCadastrados.keySet()) {
                Jogo jogo = jogosCadastrados.get(id);

                System.out.println("  ID: " + id + " | Nome: " + jogo.getNome());
            }
        }
        System.out.println("\nDigite o ID do jogo desejado: ");
        int idJogo = sc.nextInt();
        sc.nextLine();
        if (plataformasCadastradas.isEmpty()) {
            System.out.println(" Nenhuma plataforma cadastrada até o momento.");
        } else {
            System.out.println(" Plataformas presentes no sistema: ");
            for (Integer id : plataformasCadastradas.keySet()) {
                Plataforma plataforma = plataformasCadastradas.get(id);

                System.out.println("  ID: " + id + " | Plataforma: " + plataforma.getNome());
            }
        }
        System.out.println("\nDigite o ID da plataforma desejada: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o preço diário: ");
        double precoDiario = sc.nextDouble();

        System.out.println("Digite a quantidade do estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Jogo jogo = jogosCadastrados.get(idJogo);
        Plataforma plataforma = plataformasCadastradas.get(idPlataforma);
        if (jogo != null & plataforma != null) {
            String chaveJogoPlataforma = idJogo + "-" + idPlataforma;

            if (estoqueJogos.containsKey(chaveJogoPlataforma)) {
                System.out.println(" Este jogo já está cadastrado com esta plataforma no sistema.");
                return;
            }
            JogoPlataforma novoProduto = new JogoPlataforma(jogo, plataforma, estoque, precoDiario);
            estoqueJogos.put(chaveJogoPlataforma, novoProduto);
            System.out.println("O jogo " + jogo.getNome() + "foi cadastrado na plataforma " + plataforma.getNome());
        } else {
            System.out.println(" ID informado para jogo ou plataforma não existe.");
        }
    }

    public static void cadastrarConsole(Scanner sc) {
        System.out.println("\n--- Cadastro de Console ---");
        System.out.println("Plataformas disponíveis: ");
        for (Integer id : plataformasCadastradas.keySet()) {
            Plataforma plataforma = plataformasCadastradas.get(id);
            System.out.println(" ID: " + id + " | Plataforma: " + plataforma.getNome());
        }
        System.out.println("Digite o ID da plataforma que o console pertence: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do console: ");
        String nomeConsole = sc.nextLine();
        System.out.println("Digite o preço por hora: ");
        double precoPorHora = sc.nextDouble();
        Plataforma plataformaDoConsole = plataformasCadastradas.get(idPlataforma);
        if (plataformaDoConsole != null) {
            Console novoConsole = new Console(nomeConsole, plataformaDoConsole, precoPorHora);
            consolesDisponiveis.put(novoConsole.getId(), novoConsole);
            System.out.println("o console " + nomeConsole + " foi cadastrado com sucesso.");
        }else{
            System.out.println("A plataforma não foi encontrada.");
        }
    }

    public static void cadastrarAcessorio(Scanner sc){
        System.out.println("\n--- Cadastro de Acessório ---");
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
    }

    public static void conectarAcessorioPlataforma(Scanner sc){
        System.out.println("\n--- Compatibilidade do Acessório e Plataforma ---");
        System.out.println("Acessórios disponíveis: ");
        for(Acessorio acessorio : acessoriosDisponiveis.values()){
            System.out.println(" ID: " + acessorio.getId() + " | Nome: " + acessorio.getNome() + " | Em estoque: " + acessorio.getEstoque());
        }
        System.out.println("Digite o ID do acessório: ");
        int idAcessorio = sc.nextInt();
        sc.nextLine();

        System.out.println("\nPlataformas disponíveis: ");
        for(Plataforma plataforma : plataformasCadastradas.values()){
            System.out.println(" ID: " + plataforma.getId() + " | Nome: " + plataforma.getNome());
        }
        System.out.println("Digite o ID da plataforma desejada para associar: ");
        int idPlataforma = sc.nextInt();
        sc.nextLine();

        Acessorio acessorio = acessoriosDisponiveis.get(idAcessorio);
        Plataforma plataforma = plataformasCadastradas.get(idPlataforma);
        if(acessorio != null && plataforma != null){
            plataforma.adicionarAcessorio(acessorio);
            acessorio.adicionarPlataforma(plataforma);
            System.out.println("O acessório '" + acessorio.getNome() + "' é compatível com a plataforma '" + plataforma.getNome() + "'");
        }else{
            System.out.println("ID de acessório ou plataforma inválido.");
        }
    }

    public static void cadastrarCliente(Scanner sc) {
        System.out.println("\n--- Cadastro de Cliente ---\n");
        System.out.println(" Digite o nome do cliente: ");
        String nomeCliente = sc.nextLine();
        System.out.println(" Digite o email do cliente: ");
        String emailCliente = sc.nextLine();
        System.out.println(" Digite o telefone do cliente: ");
        String telefoneCliente = sc.nextLine();
        System.out.println(" Digite a senha do cliente: ");
        String senhaCliente = sc.nextLine();

        Cliente novoCliente = new Cliente(nomeCliente, emailCliente, telefoneCliente, senhaCliente);
        clientesCadastrados.put(novoCliente.getId(), novoCliente);
        System.out.println("\nO cliente " + nomeCliente + " foi cadastrado com sucesso.\n");
    }

    public static void cadastrarLocacao(Scanner sc, Cliente clienteLogado) {
        System.out.println("\n--- Nova Locação de Jogos ---\n");
        LocacaoJogo novaLocacao = new LocacaoJogo(clienteLogado);
        System.out.println(" Jogos disponíveis: ");
        if (estoqueJogos.isEmpty()) {
            System.out.println("  Nenhum jogo disponível no momento.");
        } else {
            for (String id : estoqueJogos.keySet()) {
                JogoPlataforma jogoPlataforma = estoqueJogos.get(id);
                String nomeJogo = jogoPlataforma.getJogo().getNome();
                String nomePlataforma = jogoPlataforma.getPlataforma().getNome();
                double precoDiario = jogoPlataforma.getPrecoDiario();
                int estoque = jogoPlataforma.getQuantidadeEstoque();

                System.out.println("   ID: " + id + " | Jogo: " + nomeJogo + " | Plataforma: " + nomePlataforma + " | Preço Diário: " + precoDiario + " | Em estoque: " + estoque);
            }
            System.out.println("Quantos jogos você deseja locar? ");
            int quantidadeJogos = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < quantidadeJogos; i++) {
                System.out.println(" Digite o ID do jogo e plataforma desejada: ");
                String idProduto = sc.nextLine();
                JogoPlataforma produtoEscolhido = estoqueJogos.get(idProduto);

                if (produtoEscolhido != null && produtoEscolhido.getQuantidadeEstoque() > 0) {
                    System.out.println("Digite a quantidade de dias de locação para este jogo: ");
                    int diasDeLocacao = sc.nextInt();
                    sc.nextLine();
                    novaLocacao.adicionarItem(produtoEscolhido, diasDeLocacao);
                    produtoEscolhido.decrementarEstoque();
                } else if (produtoEscolhido == null) {
                    System.out.println("ID de locação inválido.");
                } else if (produtoEscolhido.getQuantidadeEstoque() == 0) {
                    System.out.println("O jogo '" + produtoEscolhido.getJogo().getNome() + "' está sem estoque.");
                }
            }
        }
        System.out.println("\n--- Comprovante da Locação ---");
        System.out.println("ID da Locação: " + novaLocacao.getId());
        System.out.println("Cliente: " + novaLocacao.getCliente().getNome());
        System.out.println("Data: " + novaLocacao.getData());
        System.out.println("Valor Total: R$" + String.format(".2f", novaLocacao.getValorTotal()));

        clienteLogado.adicionarLocacao(novaLocacao);
    }

    public static void cadastrarAluguel(Scanner sc, Cliente clienteLogado) {
        System.out.println("\n--- Novo Aluguel de Consoles e Acessórios ---\n");
        System.out.println(" Consoles disponíveis: ");
        if (consolesDisponiveis.isEmpty()) {
            System.out.println("  Nenhum console disponível no momento.");
            return;
        } else {
            for (Console console : consolesDisponiveis.values()) {
                if (console.getDisponibilidade()) {
                    int id = console.getId();
                    String nomeConsole = console.getNome();
                    double precoPorHora = console.getPrecoPorHora();
                    System.out.println("   ID: " + id + " | Console: " + nomeConsole + " | Preço Diário: " + precoPorHora);
                }
            }
            System.out.println("Digite o ID do console que gostaria de alugar: ");
            int idConsole = sc.nextInt();
            sc.nextLine();
            Console consoleEscolhido = consolesDisponiveis.get(idConsole);
            if (consoleEscolhido != null && consoleEscolhido.getDisponibilidade()) {
                System.out.println("Digite a quantidade em horas do aluguel deste console: ");
                int horasDeAluguel = sc.nextInt();
                sc.nextLine();
                AluguelConsole novoAluguel = new AluguelConsole(clienteLogado, consoleEscolhido, horasDeAluguel);
                clienteLogado.adicionarAluguel(novoAluguel);
                consoleEscolhido.alugar();
            } else{
                System.out.println("ID de aluguel inválido.");
            }
            System.out.println("\nAcessórios Disponíveis: ");
            for(Acessorio acessorio : acessoriosDisponiveis.values()){
                
            }
            System.out.println("\n--- Comprovante da Locação ---");
            System.out.println("ID da Locação: " + novaLocacao.getId());
            System.out.println("Cliente: " + novaLocacao.getCliente().getNome());
            System.out.println("Data: " + novaLocacao.getData());
            System.out.println("Valor Total: R$" + String.format(".2f", novaLocacao.getValorTotal()));

            clienteLogado.adicionarLocacao(novaLocacao);
        }
    }
}
