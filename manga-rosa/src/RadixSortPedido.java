import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RadixSortPedido {

    private static final int TAMANHO_CODIGO = 13; // tamanho do código do pedido
    private static final int ASCII_RANGE = 256; // limite de caracteres

    public static void sort(List<Pedido> pedidos) { 
        for (int pos = TAMANHO_CODIGO - 1; pos >= 0; pos--) { //organização por posição
            radixSortByCharacter(pedidos, pos);
        }
    }

    private static void radixSortByCharacter(List<Pedido> pedidos, int pos) {
        int n = pedidos.size();
        Pedido[] output = new Pedido[n];
        int[] count = new int[ASCII_RANGE]; // lista para contar quantas vezes cada letra ou número aparece na posição

        pedidos.forEach(p -> count[p.getCodigo().charAt(pos)]++); // contador de quantas vezes cada letra ou número aparece em cada posição.

        for (int i = 1; i < ASCII_RANGE; i++) count[i] += count[i - 1]; //posição correta de cada letra

        for (int i = n - 1; i >= 0; i--) { //coloca pedido na ordem
            Pedido pedido = pedidos.get(i);
            output[--count[pedido.getCodigo().charAt(pos)]] = pedido;
        }

        for (int i = 0; i < n; i++) pedidos.set(i, output[i]); //copiando os pedidos organizados de volta para a lista principal
    }

    public static void main(String[] args) { //entradas do usuario
        try (Scanner scanner = new Scanner(System.in)) {
            List<Pedido> pedidos = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                System.out.println("Digite o código com 13 caracteres: ");
                String codigo = scanner.nextLine();

                    // Valida se o código tem 13 caracteres alfanuméricos
                while (!isValidCode(codigo)) {
                    System.out.println("O código deve ter 13 caracteres alfanuméricos.");
                    System.out.println("Digite o código com 13 caracteres: ");
                    codigo = scanner.nextLine();
                }

                System.out.println("Digite a descrição do pedido:");
                String descricao = scanner.nextLine();

                pedidos.add(new Pedido(codigo, descricao));
            }

            // ordenação lista de pedidos
            sort(pedidos);

            // exibe pedidos depois de ordenado
            System.out.println("Pedidos ordenados:");
            pedidos.forEach(System.out::println);
        }
    }

    private static boolean isValidCode(String codigo) {
        return codigo.length() == TAMANHO_CODIGO && codigo.matches("[a-zA-Z0-9]+"); //verificando se o código está correto
    }
}


