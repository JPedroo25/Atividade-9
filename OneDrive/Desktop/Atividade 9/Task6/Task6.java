package Task6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Classe que representa um Cliente
class Cliente {
    String nome;
    int id;

    public Cliente(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente ID: " + id + " - " + nome;
    }
}

// Classe que gerencia a fila de atendimento
class FilaDeAtendimento {
    private Queue<Cliente> fila;  // Fila de clientes

    public FilaDeAtendimento() {
        fila = new LinkedList<>();  // Usando LinkedList para implementar a fila
    }

    // Adiciona um cliente à fila
    public void adicionarCliente(Cliente cliente) {
        fila.offer(cliente);  // Adiciona o cliente no final da fila
        System.out.println(cliente + " entrou na fila.");
    }

    // Chama o próximo cliente (retira da fila)
    public void chamarProximoCliente() {
        Cliente cliente = fila.poll();  // Remove o cliente da frente da fila
        if (cliente == null) {
            System.out.println("Não há clientes na fila para atendimento.");
        } else {
            System.out.println(cliente + " foi chamado para atendimento.");
        }
    }

    // Exibe todos os clientes na fila
    public void exibirFila() {
        if (fila.isEmpty()) {
            System.out.println("A fila está vazia.");
        } else {
            System.out.println("Clientes na fila:");
            for (Cliente cliente : fila) {
                System.out.println(cliente);
            }
        }
    }
}

// Classe principal que gerencia a interação com o usuário
public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaDeAtendimento filaDeAtendimento = new FilaDeAtendimento();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar cliente à fila");
            System.out.println("2 - Chamar o próximo cliente");
            System.out.println("3 - Exibir clientes na fila");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    // Adiciona um cliente à fila
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer de entrada
                    Cliente cliente = new Cliente(nome, id);
                    filaDeAtendimento.adicionarCliente(cliente);
                    break;

                case 2:
                    // Chama o próximo cliente da fila
                    filaDeAtendimento.chamarProximoCliente();
                    break;

                case 3:
                    // Exibe todos os clientes na fila
                    filaDeAtendimento.exibirFila();
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}

