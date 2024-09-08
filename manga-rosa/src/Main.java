import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.model.RadixSorter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Gera uma lista de pedidos com IDs aleatórios.
        List<Pedido> listPedidos = generateRandomIdList(2);
        System.out.println("Pedidos gerados e não ordenados:");

        // Exibe os pedidos antes da ordenação.
        for (Pedido pedido : listPedidos) {
            System.out.println(pedido);
        }

        Sort<Pedido> sorter = new RadixSorter();

        // Ordena os pedidos e obtém o número total de comparações realizadas.
        listPedidos = sorter.sort(listPedidos);

        System.out.println("\nPedidos ordenados:");
        // Exibe os pedidos após a ordenação.
        for (Pedido pedido : listPedidos) {
            System.out.println(pedido);
        }

        System.out.println("\nTotal de comparações realizadas: " + sorter.numberComparisons());
    }

    // Método para gerar uma lista de IDs de pedidos aleatórios.
    private static List<Pedido> generateRandomIdList(int numberOfIds) {
        List<Pedido> pedidoIdList = new ArrayList<>();
        Random random = new Random();
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Gera o número solicitado de IDs.
        for (int i = 0; i < numberOfIds; i++) {
            char[] id = new char[13]; // ID com 13 caracteres.

            // Gera um ID aleatório com 13 caracteres alfanuméricos.
            for (int j = 0; j < 13; j++) {
                int index = random.nextInt(alphanumericCharacters.length());
                id[j] = alphanumericCharacters.charAt(index);
            }

            pedidoIdList.add(new Pedido(id));
        }

        return pedidoIdList;
    }
}