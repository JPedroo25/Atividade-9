package Task3;

import java.util.Scanner;

// Classe que representa uma ação no histórico de edições (nó na lista encadeada)
class Acao {
    String tipo;  // Tipo da ação ("adicao" ou "remocao")
    String texto; // Texto da ação
    Acao proximo; // Ponteiro para o próximo nó

    public Acao(String tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
        this.proximo = null;
    }
}

// Classe que representa o histórico de edições (lista encadeada de ações)
class HistoricoDeEdicoes {
    private Acao cabeca;  // Cabeça da lista de ações

    public HistoricoDeEdicoes() {
        this.cabeca = null;
    }

    // Adiciona uma nova ação no histórico
    public void adicionarAcao(String tipo, String texto) {
        Acao novaAcao = new Acao(tipo, texto);
        novaAcao.proximo = cabeca;
        cabeca = novaAcao;
    }

    // Desfaz a última ação (undo)
    public String desfazerUltimaAcao() {
        if (cabeca == null) {
            return null; // Nenhuma ação para desfazer
        }

        String textoDesfeito = null;

        if (cabeca.tipo.equals("adicao")) {
            // Desfazendo adição (remove o texto adicionado)
            textoDesfeito = cabeca.texto;
        } else if (cabeca.tipo.equals("remocao")) {
            // Desfazendo remoção (adiciona de volta o texto removido)
            textoDesfeito = cabeca.texto;
        }

        cabeca = cabeca.proximo;  // Remove a ação do histórico

        return textoDesfeito;  // Retorna o texto que foi "desfeito"
    }

    // Exibe o histórico de edições
    public void exibirHistorico() {
        if (cabeca == null) {
            System.out.println("Nenhuma ação realizada.");
        } else {
            Acao atual = cabeca;
            while (atual != null) {
                System.out.println("Ação: " + atual.tipo + " | Texto: " + atual.texto);
                atual = atual.proximo;
            }
        }
    }
}

// Classe principal que gerencia a edição de texto e o controle de reversão
public class Task3 {
    private String textoAtual; // Texto atual da aplicação
    private HistoricoDeEdicoes historico;

    public Task3() {
        textoAtual = "";
        historico = new HistoricoDeEdicoes();
    }

    // Adiciona texto ao conteúdo e registra a ação
    public void adicionarTexto(String texto) {
        textoAtual += texto;
        historico.adicionarAcao("adicao", texto);
        System.out.println("Texto atual: " + textoAtual);
    }

    // Remove o último caractere do conteúdo e registra a ação
    public void removerTexto() {
        if (textoAtual.length() > 0) {
            String textoRemovido = textoAtual.substring(textoAtual.length() - 1);
            textoAtual = textoAtual.substring(0, textoAtual.length() - 1);
            historico.adicionarAcao("remocao", textoRemovido);
            System.out.println("Texto atual: " + textoAtual);
        } else {
            System.out.println("Nada para remover.");
        }
    }

    // Desfaz a última ação
    public void desfazer() {
        String textoDesfeito = historico.desfazerUltimaAcao();
        if (textoDesfeito != null) {
            // Se for uma adição, remove o texto adicionado
            if (textoAtual.endsWith(textoDesfeito)) {
                textoAtual = textoAtual.substring(0, textoAtual.length() - textoDesfeito.length());
            }
            // Se for uma remoção, adiciona o texto de volta
            else {
                textoAtual += textoDesfeito;
            }
            System.out.println("Texto atual após undo: " + textoAtual);
        } else {
            System.out.println("Nenhuma ação para desfazer.");
        }
    }

    // Exibe o texto atual
    public void exibirTexto() {
        System.out.println("Texto atual: " + textoAtual);
    }

    // Exibe o histórico de edições
    public void exibirHistorico() {
        historico.exibirHistorico();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task3 editor = new Task3();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar texto");
            System.out.println("2 - Remover último caractere");
            System.out.println("3 - Desfazer última ação (Undo)");
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
                    editor.adicionarTexto(textoAdicionar);
                    break;

                case 2:
                    editor.removerTexto();
                    break;

                case 3:
                    editor.desfazer();
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
