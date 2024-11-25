//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.interfaces.OrdRadix;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Define os tamanhos das entradas para os testes
        int[] sizes = {10, 100, 1000, 10000, 100000};
        
        // Itera sobre cada tamanho de entrada
        for (int size : sizes) {
            // Gera a lista de pedidos com ids
            List<Pedido> pedidos = generateRandomPedidos(size);

            // Aqui Cria uma instância
            OrdRadix OrdRadix = new OrdRadix();

            // Aqui Ordena a lista de pedidos
            OrdRadix.sort(pedidos);

            // Aqui Obtém o número de comparações realizadas
            int comparisons = OrdRadix.numberComparisons();

            // Aqui Imprime
            System.out.println("Para as entradas de " + size + " foram feitas " + comparisons + " comparações");
        }
    }

    // lista de pedidos com ids
    private static List<Pedido> generateRandomPedidos(int size) {
        List<Pedido> pedidos = new ArrayList<>(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            char[] id = new char[13];
            for (int j = 0; j < 13; j++) {
                int rand = random.nextInt(36); // 10 dígitos e 26 letras 
                if (rand < 10) {
                    id[j] = (char) (rand + '0'); // AQui vai gerar os digitos
                }else {
                    id[j] = (char) (rand - 36 + 'a'); // Aqui para gerar as letras
                }
            }
            pedidos.add(new Pedido(id));
        }

        return pedidos;
    }
}