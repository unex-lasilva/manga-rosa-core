import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.model.Radix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe principal para execução do programa de ordenação de pedidos.
 * Esta classe cria uma lista de pedidos com identificadores alfanuméricos aleatórios,
 * utiliza o algoritmo de Radix Sort para ordená-los e exibe os resultados.
 */
public class Main {

    public static void main(String[] args) {
        // Gera uma lista de pedidos com identificadores alfanuméricos aleatórios
        List<Pedido> pedidos = generateRandomPedidos(5);  // Exemplo com 5 pedidos

        // Exibe os pedidos antes da ordenação
        System.out.println("Pedidos antes da ordenação:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        // Cria uma instância do algoritmo de ordenação Radix
        Radix sorter = new Radix();

        // Ordena a lista de pedidos usando o Radix Sort
        List<Pedido> pedidosOrdenados = sorter.sort(pedidos);

        // Exibe os pedidos após a ordenação
        System.out.println("\nPedidos após a ordenação:");
        for (Pedido pedido : pedidosOrdenados) {
            System.out.println(pedido);
        }

        // Exibe o número de comparações realizadas durante a ordenação
        System.out.println("\nNúmero de comparações realizadas: " + sorter.numberComparisons());
    }

    /**
     * Método que gera uma lista de objetos Pedido com identificadores alfanuméricos aleatórios.
     * O identificador gerado contém 13 caracteres, combinando letras maiúsculas e números.
     *
     * @param tamanho O número de pedidos a ser gerado.
     * @return Uma lista de pedidos com identificadores alfanuméricos.
     */
    private static List<Pedido> generateRandomPedidos(int tamanho) {
        List<Pedido> pedidos = new ArrayList<>(tamanho);
        Random random = new Random();
        String alfanumerico = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Conjunto de caracteres alfanuméricos

        // Gera cada pedido com um identificador alfanumérico de 13 caracteres
        for (int i = 0; i < tamanho; i++) {
            char[] id = new char[13];
            for (int j = 0; j < 13; j++) {
                id[j] = alfanumerico.charAt(random.nextInt(alfanumerico.length())); // Gera caracteres aleatórios
            }
            pedidos.add(new Pedido(id)); // Adiciona o pedido à lista
        }

        return pedidos; // Retorna a lista de pedidos gerados
    }
}
