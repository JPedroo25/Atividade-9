package Task5;

import java.util.Scanner;

// Classe que representa uma carta
class Carta {
    String valor;  // Exemplo: "Ás", "2", "Rei", etc.
    String naipe;  // Exemplo: "Copas", "Espadas", "Ouros", "Paus"

    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}

// Classe que representa um nó na lista duplamente encadeada (cada nó é uma carta)
class No {
    Carta carta;
    No anterior;  // Ponteiro para o nó anterior
    No proximo;   // Ponteiro para o próximo nó

    public No(Carta carta) {
        this.carta = carta;
        this.anterior = null;
        this.proximo = null;
    }
}

// Classe que representa a mão do jogador (lista duplamente encadeada)
class Mao {
    No primeiro;  // Ponteiro para o primeiro nó da lista
    No ultimo;    // Ponteiro para o último nó da lista

    public Mao() {
        this.primeiro = null;
        this.ultimo = null;
    }

    // Adiciona uma carta no final da mão (lista)
    public void adicionarCarta(Carta carta) {
        No novoNo = new No(carta);

        if (primeiro == null) {
            // Se a lista estiver vazia, a carta é o primeiro e último nó
            primeiro = novoNo;
            ultimo = novoNo;
        } else {
            // Adiciona no final da lista
            ultimo.proximo = novoNo;
            novoNo.anterior = ultimo;
            ultimo = novoNo;
        }
        System.out.println("Carta " + carta + " adicionada à mão.");
    }

    // Remove a carta no início da mão
    public void removerCarta() {
        if (primeiro == null) {
            System.out.println("Não há cartas na mão.");
            return;
        }

        System.out.println("Carta removida: " + primeiro.carta);
        primeiro = primeiro.proximo;
        if (primeiro != null) {
            primeiro.anterior = null;
        } else {
            ultimo = null;  // Se não há mais cartas, o último nó também deve ser null
        }
    }

    // Reorganiza a mão, movendo a carta do início para o final
    public void reorganizarCarta() {
        if (primeiro == null || primeiro.proximo == null) {
            System.out.println("Não há cartas para reorganizar.");
            return;
        }

        Carta cartaReorganizada = primeiro.carta;
        removerCarta();  // Remove a primeira carta
        adicionarCarta(cartaReorganizada);  // Adiciona a carta novamente no final
    }

    // Exibe todas as cartas da mão
    public void exibirMao() {
        if (primeiro == null) {
            System.out.println("A mão está vazia.");
            return;
        }

        System.out.println("Cartas na mão:");
        No atual = primeiro;
        while (atual != null) {
            System.out.println(atual.carta);
            atual = atual.proximo;
        }
    }
}

// Classe principal que gerencia o jogo
public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mao mao = new Mao();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar carta");
            System.out.println("2 - Remover carta");
            System.out.println("3 - Reorganizar cartas");
            System.out.println("4 - Exibir cartas na mão");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    // Adiciona uma carta à mão
                    System.out.print("Digite o valor da carta: ");
                    String valor = scanner.nextLine();
                    System.out.print("Digite o naipe da carta: ");
                    String naipe = scanner.nextLine();
                    Carta carta = new Carta(valor, naipe);
                    mao.adicionarCarta(carta);
                    break;

                case 2:
                    // Remove uma carta da mão
                    mao.removerCarta();
                    break;

                case 3:
                    // Reorganiza a mão, movendo a primeira carta para o final
                    mao.reorganizarCarta();
                    break;

                case 4:
                    // Exibe todas as cartas na mão
                    mao.exibirMao();
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

