import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.OrdemAlfanumerica;
import br.com.mangarosa.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criar alguns pedidos de exemplo
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(new char[]{'A', '1', '2', 'B', '5', '7', 'C', 'D', 'E', '4', '3', 'F', 'G'}));
        pedidos.add(new Pedido(new char[]{'Z', '9', '8', 'X', '7', '6', 'A', 'B', 'C', '5', '4', '3', '2'}));
        pedidos.add(new Pedido(new char[]{'Y', '3', '4', 'X', 'A', '1', '8', '7', 'M', 'N', 'P', 'O', 'Q'}));
        pedidos.add(new Pedido(new char[]{'W', '5', '4', 'H', 'I', 'J', '2', '1', 'K', 'L', 'M', 'N', 'O'}));
        pedidos.add(new Pedido(new char[]{'M', 'N', 'O', '6', '7', '5', 'P', 'Q', 'R', '1', '2', '3', '4'}));
        pedidos.add(new Pedido(new char[]{'B', 'A', 'C', '9', '8', '2', 'T', 'Y', 'U', '3', '1', '7', '5'}));
        pedidos.add(new Pedido(new char[]{'P', 'Q', 'R', '3', '2', '1', 'A', 'B', 'C', '4', '5', '7', '8'}));
        pedidos.add(new Pedido(new char[]{'Y', 'K', 'J', 'M', 'N', 'O', '9', '8', '2', '6', '5', '4', '3'}));
        pedidos.add(new Pedido(new char[]{'Y', 'Y', 'Z', '2', '1', '0', 'A', 'B', 'C', '8', '7', '5', '4'}));
        pedidos.add(new Pedido(new char[]{'Y', 'W', 'X', '4', '3', '2', '1', 'A', 'B', 'C', '9', '8', '6'}));
        
        System.out.println("Pedidos antes da ordenação:");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
        
        Sort<Pedido> ordenacao = new OrdemAlfanumerica();
        ordenacao.sort(pedidos);
        

        System.out.println("\nPedidos após a ordenação:");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
        
        
        System.out.println("\nNúmero de comparações: " + ordenacao.numberComparisons());
    }
} 
