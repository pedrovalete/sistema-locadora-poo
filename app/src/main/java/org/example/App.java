package org.example;

import java.util.Scanner;
import org.example.domain.*;

public class App {
    private static final String SENHA_ADMIN = "senhaadm123";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite a senha se for administrador, ou qualquer outra tecla para cliente: \n");
        String entrada = sc.nextLine();

        if(entrada.equals(SENHA_ADMIN)){
            menuAdmin(sc);
        }else{
            menuCliente(sc);
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

            switch(escolha){
                case 1:
                    System.out.println("\n--- GERENCIAR CLIENTES ---");
                    System.out.println("1. Cadastrar Cliente");
                    System.out.println("2. Atualizar Cliente");
                    System.out.println("3. Listar Clientes");
                    System.out.println("4. Remover Cliente");
                    System.out.println("0. Sair");
                    int gerenciarCliente = sc.nextInt();

                    switch(gerenciarCliente){
                        case 1:

                    }
            }
        }
    }
}
