package Task2;

import java.util.Scanner;

// Classe que representa um nó na lista encadeada (URL)
class NodoURL {
    String url;
    NodoURL proximo;

    public NodoURL(String url) {
        this.url = url;
        this.proximo = null;
    }
}

// Classe que representa o histórico de navegação
class HistoricoDeNavegacao {
    private NodoURL cabeca;
    private int tamanhoMaximo;
    private int tamanhoAtual;

    public HistoricoDeNavegacao(int tamanhoMaximo) {
        this.cabeca = null;
        this.tamanhoMaximo = tamanhoMaximo;
        this.tamanhoAtual = 0;
    }

    // Adiciona uma nova URL ao histórico
    public void adicionarURL(String url) {
        // Cria o novo nó
        NodoURL novaURL = new NodoURL(url);

        // Se a lista estiver vazia, a nova URL se torna a cabeça da lista
        if (cabeca == null) {
            cabeca = novaURL;
        } else {
            // Se já existe pelo menos um nó, adiciona a URL no final da lista
            NodoURL atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novaURL;
        }

        tamanhoAtual++;

        // Se o histórico exceder o tamanho máximo, remove o nó mais antigo
        if (tamanhoAtual > tamanhoMaximo) {
            removerURLMaisAntiga();
        }
    }

    // Remove a URL mais antiga (primeiro nó) se o histórico atingir o limite
    private void removerURLMaisAntiga() {
        if (cabeca != null) {
            cabeca = cabeca.proximo;
            tamanhoAtual--;
        }
    }

    // Exibe todo o histórico de navegação
    public void exibirHistorico() {
        if (cabeca == null) {
            System.out.println("O histórico está vazio.");
        } else {
            NodoURL atual = cabeca;
            while (atual != null) {
                System.out.println(atual.url);
                atual = atual.proximo;
            }
        }
    }
}

// Classe principal para interagir com o usuário
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Definindo o tamanho máximo do histórico de navegação
        System.out.print("Digite o tamanho máximo do histórico: ");
        int tamanhoMaximo = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha após o número

        HistoricoDeNavegacao historico = new HistoricoDeNavegacao(tamanhoMaximo);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar nova URL");
            System.out.println("2 - Exibir histórico de navegação");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite a URL que você visitou: ");
                    String url = scanner.nextLine();
                    historico.adicionarURL(url);
                    System.out.println("URL adicionada com sucesso!");
                    break;

                case 2:
                    System.out.println("Histórico de Navegação:");
                    historico.exibirHistorico();
                    break;

                case 3:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }
}

