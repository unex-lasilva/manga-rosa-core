//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter

import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.model.RadixSort;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static final int CODE_LENGTH = 13;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args)
    {
        List<Pedido> pedidos = new ArrayList<>();// Cria uma lista de pedidos com códigos gerados de forma aleatória

        for (int i = 0; i < 10; i++)
        {
            pedidos.add(new Pedido(gerarCodigoAleatorio().toCharArray()));
        }

        System.out.println("\nAntes da ordenação:\n");// Mostra a lista antes da ordenação

        for (Pedido pedido : pedidos)
        {
            System.out.println(pedido);
        }

        RadixSort radixSort = new RadixSort();// Cria uma instância da classe RadixSort e ordena a lista
        List<Pedido> pedidosOrdenados = radixSort.sort(pedidos);

        System.out.println("\n\nApós a ordenação:\n");// Mostra a lista após ela ser ordenada

        for (Pedido pedido : pedidosOrdenados)
        {
            System.out.println(pedido);
        }

        System.out.println("\n\nNúmero de operações realizadas: " + radixSort.numberComparisons());// Mostra o número de operações realizadas
    }

    private static String gerarCodigoAleatorio()
    {
        StringBuilder codigo = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++)
        {
            int index = RANDOM.nextInt(CHARACTERS.length());
            codigo.append(CHARACTERS.charAt(index));
        }

        return codigo.toString();
    }
}
