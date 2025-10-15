package org.example;

import java.util.Scanner;
import java.util.Map;
import org.example.domain.*;

public class App {
    private static Map<Integer, Cliente> clientesCadastrados;

    private static final String SENHA_ADMIN = "senhaadm123";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite a senha se for administrador, ou qualquer outra tecla para cliente: \n");
        String entrada = sc.nextLine();

        if(entrada.equals(SENHA_ADMIN)){
            menuAdmin(sc);
        }else{
            loginCliente(sc);
        }
    }
    public static void menuAdmin(Scanner sc){
        boolean sair = false;
        while(!sair){
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Jogos");
            System.out.println("3. Gerenciar Plataformas");
            System.out.println("4. Gerenciar Locações");
            System.out.println("5. Gerenciar Aluguéis");
            System.out.println("0. Sair");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch(escolha){
                case 1:
                    System.out.println("\n--- GERENCIAR CLIENTES ---");
                    System.out.println("1. Cadastrar Cliente");
                    System.out.println("2. Atualizar Cliente");
                    System.out.println("3. Listar Clientes");
                    System.out.println("4. Remover Cliente");
                    System.out.println("0. Sair");
                    int gerenciarCliente = sc.nextInt();
                    sc.nextLine();

                    switch(gerenciarCliente){
                        case 1:

                    }
            }
        }
    }
    public static void loginCliente(Scanner sc){
        System.out.println("\n--- LOGIN DO CLIENTE ---");

        System.out.println("Digite o seu ID de cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite sua senha: ");
        String senhaDigitada = sc.nextLine();

        Cliente clienteLogin = clientesCadastrados.get(idCliente);

        if(clienteLogin != null && clienteLogin.autenticar(senhaDigitada)){
            System.out.println("\nLogin realizado. Bem vindo, " + clienteLogin.getNome());
            menuCliente(sc, clienteLogin);
        }else{
            System.out.println("\nAconteceu um erro. ID de cliente ou senha incorretos. Tente novamente.");
        }
    }

    public static void menuCliente(Scanner sc, Cliente clienteLogado) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Locação de Jogos");
            System.out.println("2. Alugar Consoles");
            System.out.println("3. Consultar histórico");
            System.out.println("4. Consultar jogos disponíveis");
            System.out.println("5. Atualizar cadastro");
            System.out.println("0. Sair");
        }
    }
}
