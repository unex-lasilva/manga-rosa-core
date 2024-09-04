import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.sort.Radix;
import br.com.mangarosa.sort.RadixSortTest;

public class Main {

    public static void main(String[] args) {
        // Rodar testes com diferentes quantidades de pedidos
        int[] testSizes = {10, 100, 1000, 10000, 100000};
        
        for (int size : testSizes) {
            RadixSortTest.runTest(size);
        }
    }
}
