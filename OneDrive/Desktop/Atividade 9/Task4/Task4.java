package Task4;

import java.util.Scanner;

// Classe que representa uma ação no histórico de edições (nó na lista duplamente encadeada)
class Acao {
    String texto;     // Texto após a ação
    Acao anterior;    // Ponteiro para o nó anterior (undo)
    Acao proximo;     // Ponteiro para o próximo nó (redo)

    public Acao(String texto) {
        this.texto = texto;
        this.anterior = null;
        this.proximo = null;
    }
}

// Classe que representa o editor de texto com operações de undo e redo
class EditorDeTexto {
    private String textoAtual;   // Texto atual da aplicação
    private Acao primeiro;       // Primeira ação no histórico (ponto de partida)
    private Acao ultimo;         // Última ação no histórico (ponto de fim)
    private Acao posicaoAtual;   // Ação atual (onde estamos para undo/redo)

    public EditorDeTexto() {
        textoAtual = "";  // Inicializa com um texto vazio
        primeiro = null;
        ultimo = null;
        posicaoAtual = null;
    }

    // Adiciona uma nova ação ao histórico
    public void adicionarAcao(String texto) {
        // Se o histórico estiver vazio, a primeira ação é simplesmente o texto
        if (primeiro == null) {
            Acao novaAcao = new Acao(texto);
            primeiro = novaAcao;
            ultimo = novaAcao;
            posicaoAtual = novaAcao;
        } else {
            // Se o histórico já tiver ações, concatenamos o novo texto com o atual
            String textoConcatenado = textoAtual + texto;
            Acao novaAcao = new Acao(textoConcatenado);
            ultimo.proximo = novaAcao;
            novaAcao.anterior = ultimo;
            ultimo = novaAcao;
            posicaoAtual = ultimo;  // Coloca a posição atual na última ação
        }

        textoAtual = textoAtual + texto; // Atualiza o texto atual com a concatenação
        exibirTexto(); // Exibe o texto atualizado no terminal
    }

    // Desfaz a última ação (undo)
    public void undo() {
        if (posicaoAtual == null || posicaoAtual.anterior == null) {
            System.out.println("Não há mais ações para desfazer.");
        } else {
            posicaoAtual = posicaoAtual.anterior;
            textoAtual = posicaoAtual.texto;  // Atualiza o texto atual com o texto da ação anterior
            System.out.println("Texto após undo: " + textoAtual);
        }
    }

    // Refaz a última ação desfeita (redo)
    public void redo() {
        if (posicaoAtual == null || posicaoAtual.proximo == null) {
            System.out.println("Não há mais ações para refazer.");
        } else {
            posicaoAtual = posicaoAtual.proximo;
            textoAtual = posicaoAtual.texto;  // Atualiza o texto atual com o texto da ação refazer
            System.out.println("Texto após redo: " + textoAtual);
        }
    }

    // Exibe o texto atual
    public void exibirTexto() {
        System.out.println("Texto atual: " + textoAtual);
    }

    // Exibe o histórico de edições
    public void exibirHistorico() {
        if (primeiro == null) {
            System.out.println("Nenhuma ação realizada.");
        } else {
            Acao atual = primeiro;
            System.out.println("Histórico de edições:");
            while (atual != null) {
                System.out.println("Texto: " + atual.texto);
                atual = atual.proximo;
            }
        }
    }
}

// Classe principal que gerencia a interação com o usuário
public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EditorDeTexto editor = new EditorDeTexto();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar texto");
            System.out.println("2 - Desfazer última ação (Undo)");
            System.out.println("3 - Refazer última ação (Redo)");
            System.out.println("4 - Exibir texto atual");
            System.out.println("5 - Exibir histórico de edições");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.print("Digite o texto para adicionar: ");
                    String textoAdicionar = scanner.nextLine();
                    editor.adicionarAcao(textoAdicionar);
                    break;

                case 2:
                    editor.undo();
                    break;

                case 3:
                    editor.redo();
                    break;

                case 4:
                    editor.exibirTexto();
                    break;

                case 5:
                    editor.exibirHistorico();
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}




