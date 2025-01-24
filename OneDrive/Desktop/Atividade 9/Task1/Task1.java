package Task1;

import java.util.Scanner;

// Classe que representa um nó na lista encadeada (tarefa)
class Tarefa {
    String descricao;
    boolean concluida;
    Tarefa proximo;

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
        this.proximo = null;
    }
}

// Classe que representa a lista simplesmente encadeada de tarefas
class ListaDeTarefas {
    private Tarefa cabeca;

    public ListaDeTarefas() {
        this.cabeca = null;
    }

    // Adiciona uma nova tarefa na lista
    public void adicionarTarefa(String descricao) {
        Tarefa novaTarefa = new Tarefa(descricao);
        if (cabeca == null) {
            cabeca = novaTarefa;
        } else {
            Tarefa atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novaTarefa;
        }
    }

    // Remove uma tarefa da lista
    public void removerTarefa(String descricao) {
        if (cabeca == null) {
            System.out.println("A lista de tarefas está vazia.");
            return;
        }

        // Caso a tarefa a ser removida seja a primeira
        if (cabeca.descricao.equals(descricao)) {
            cabeca = cabeca.proximo;
            return;
        }

        Tarefa atual = cabeca;
        while (atual.proximo != null && !atual.proximo.descricao.equals(descricao)) {
            atual = atual.proximo;
        }

        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    // Marca uma tarefa como concluída
    public void marcarComoConcluida(String descricao) {
        Tarefa atual = cabeca;
        while (atual != null) {
            if (atual.descricao.equals(descricao)) {
                atual.concluida = true;
                System.out.println("Tarefa marcada como concluída: " + descricao);
                return;
            }
            atual = atual.proximo;
        }
        System.out.println("Tarefa não encontrada.");
    }

    // Exibe todas as tarefas
    public void exibirTarefas() {
        if (cabeca == null) {
            System.out.println("A lista de tarefas está vazia.");
            return;
        }

        Tarefa atual = cabeca;
        while (atual != null) {
            System.out.println(atual.descricao + " - " + (atual.concluida ? "Concluída" : "Pendente"));
            atual = atual.proximo;
        }
    }
}

// Classe principal para interagir com o usuário
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDeTarefas lista = new ListaDeTarefas();
        int opcao;

        do {
            System.out.println("\nGerenciador de Tarefas");
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Remover Tarefa");
            System.out.println("3 - Marcar Tarefa como Concluída");
            System.out.println("4 - Exibir Tarefas");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite a descrição da tarefa: ");
                    String descricaoAdicionar = scanner.nextLine();
                    lista.adicionarTarefa(descricaoAdicionar);
                    break;
                case 2:
                    System.out.print("Digite a descrição da tarefa para remover: ");
                    String descricaoRemover = scanner.nextLine();
                    lista.removerTarefa(descricaoRemover);
                    break;
                case 3:
                    System.out.print("Digite a descrição da tarefa para marcar como concluída: ");
                    String descricaoConcluir = scanner.nextLine();
                    lista.marcarComoConcluida(descricaoConcluir);
                    break;
                case 4:
                    lista.exibirTarefas();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
