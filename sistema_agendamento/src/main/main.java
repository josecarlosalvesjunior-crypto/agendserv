package main;

import dao.clientedao;
import dao.prestadordao;
import dao.servicodao;
import dao.relatoriodao;
import entidades.cliente;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        clientedao clienteDAO = new clientedao();
        prestadordao prestadorDAO = new prestadordao();
        servicodao servicoDAO = new servicodao();
        relatoriodao relatorioDAO = new relatoriodao();

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE AGENDAMENTO =====");
            System.out.println("1 - Inserir Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("5 - Listar Prestadores");
            System.out.println("6 - Listar Serviços");
            System.out.println("7 - Relatório Completo (JOIN)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {

                case 1:

                    cliente cliente = new cliente();

                    System.out.print("Nome: ");
                    cliente.setNomeCliente(sc.nextLine());

                    System.out.print("CPF: ");
                    cliente.setCpfCliente(sc.nextLine());

                    System.out.print("Email: ");
                    cliente.setEmailCliente(sc.nextLine());

                    System.out.print("Telefone: ");
                    cliente.setTelefoneCliente(sc.nextLine());

                    clienteDAO.inserir(cliente);

                    break;

                case 2:

                    clienteDAO.listar();

                    break;

                case 3:

                    cliente clienteAtualizar = new cliente();

                    System.out.print("Código: ");
                    clienteAtualizar.setCodCliente(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Novo Nome: ");
                    clienteAtualizar.setNomeCliente(sc.nextLine());

                    System.out.print("Novo Email: ");
                    clienteAtualizar.setEmailCliente(sc.nextLine());

                    System.out.print("Novo Telefone: ");
                    clienteAtualizar.setTelefoneCliente(sc.nextLine());

                    clienteDAO.atualizar(clienteAtualizar);

                    break;

                case 4:

                    System.out.print("Código do cliente: ");
                    int codigoExcluir = sc.nextInt();

                    clienteDAO.excluir(codigoExcluir);

                    break;

                case 5:

                    prestadorDAO.listar();

                    break;

                case 6:

                    servicoDAO.listar();

                    break;

                case 7:

                    relatorioDAO.relatorioCompleto();

                    break;

                case 0:

                    System.out.println("Sistema encerrado.");
                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while(opcao != 0);

        sc.close();
    }
}