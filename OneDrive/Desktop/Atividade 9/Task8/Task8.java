package Task8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Classe que representa um processo
class Processo {
    int id;
    String nome;
    int tempoExecucao;

    public Processo(int id, String nome, int tempoExecucao) {
        this.id = id;
        this.nome = nome;
        this.tempoExecucao = tempoExecucao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Tempo de Execução: " + tempoExecucao + " segundos";
    }
}

// Classe que gerencia a fila de processos
class FilaProcessos {
    private Queue<Processo> fila;

    public FilaProcessos() {
        fila = new LinkedList<>();  // Usando LinkedList para implementar a fila
    }

    // Adiciona um processo à fila
    public void adicionarProcesso(Processo processo) {
        fila.offer(processo);  // Adiciona no final da fila
        System.out.println("Processo adicionado à fila: " + processo);
    }

    // Executa (remove) o processo mais antigo da fila
    public void executarProximoProcesso() {
        Processo processo = fila.poll();  // Remove o primeiro processo da fila
        if (processo == null) {
            System.out.println("Não há processos na fila para execução.");
        } else {
            System.out.println("Executando processo: " + processo);
        }
    }

    // Exibe todos os processos na fila
    public void exibirFila() {
        if (fila.isEmpty()) {
            System.out.println("A fila de processos está vazia.");
        } else {
            System.out.println("Processos na fila de execução:");
            for (Processo processo : fila) {
                System.out.println(processo);
            }
        }
    }
}

// Classe principal que simula o escalonamento de processos
public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaProcessos filaDeProcessos = new FilaProcessos();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar processo à fila");
            System.out.println("2 - Executar próximo processo");
            System.out.println("3 - Exibir processos na fila");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    // Adiciona um novo processo à fila
                    System.out.print("Digite o ID do processo: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer
                    System.out.print("Digite o nome do processo: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o tempo de execução do processo (em segundos): ");
                    int tempoExecucao = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    Processo processo = new Processo(id, nome, tempoExecucao);
                    filaDeProcessos.adicionarProcesso(processo);
                    break;

                case 2:
                    // Executa o próximo processo da fila
                    filaDeProcessos.executarProximoProcesso();
                    break;

                case 3:
                    // Exibe todos os processos na fila
                    filaDeProcessos.exibirFila();
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

