package Task7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Classe que representa um trabalho de impressão
class TrabalhoImpressao {
    String nomeArquivo;
    int numeroPaginas;

    public TrabalhoImpressao(String nomeArquivo, int numeroPaginas) {
        this.nomeArquivo = nomeArquivo;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "Arquivo: " + nomeArquivo + ", Páginas: " + numeroPaginas;
    }
}

// Classe que gerencia a fila de impressão
class FilaImpressao {
    private Queue<TrabalhoImpressao> fila;

    public FilaImpressao() {
        fila = new LinkedList<>();  // Usando LinkedList para implementar a fila
    }

    // Adiciona um trabalho à fila de impressão
    public void adicionarTrabalho(TrabalhoImpressao trabalho) {
        fila.offer(trabalho);  // Adiciona no final da fila
        System.out.println("Trabalho de impressão adicionado: " + trabalho);
    }

    // Processa (imprime) o próximo trabalho da fila
    public void processarProximoTrabalho() {
        TrabalhoImpressao trabalho = fila.poll();  // Remove o primeiro trabalho da fila
        if (trabalho == null) {
            System.out.println("Não há trabalhos na fila para processar.");
        } else {
            System.out.println("Imprimindo: " + trabalho);
        }
    }

    // Exibe todos os trabalhos na fila
    public void exibirFila() {
        if (fila.isEmpty()) {
            System.out.println("A fila de impressão está vazia.");
        } else {
            System.out.println("Trabalhos na fila de impressão:");
            for (TrabalhoImpressao trabalho : fila) {
                System.out.println(trabalho);
            }
        }
    }
}

// Classe principal que gerencia o sistema de impressão
public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaImpressao filaDeImpressao = new FilaImpressao();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar trabalho de impressão");
            System.out.println("2 - Processar próximo trabalho");
            System.out.println("3 - Exibir fila de impressão");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    // Adiciona um trabalho de impressão à fila
                    System.out.print("Digite o nome do arquivo: ");
                    String nomeArquivo = scanner.nextLine();
                    System.out.print("Digite o número de páginas: ");
                    int numeroPaginas = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer do scanner
                    TrabalhoImpressao trabalho = new TrabalhoImpressao(nomeArquivo, numeroPaginas);
                    filaDeImpressao.adicionarTrabalho(trabalho);
                    break;

                case 2:
                    // Processa (imprime) o próximo trabalho da fila
                    filaDeImpressao.processarProximoTrabalho();
                    break;

                case 3:
                    // Exibe todos os trabalhos na fila
                    filaDeImpressao.exibirFila();
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

