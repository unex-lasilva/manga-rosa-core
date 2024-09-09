package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;

import java.util.ArrayList;
import java.util.List;

public class RadixSort implements Sort<Pedido>
{
    private int numberComparisons = 0;

    @Override
    public List<Pedido> sort(List<Pedido> dataset)
    {
        if (dataset == null || dataset.isEmpty())
        {
            return dataset;
        }

        final int ID_LENGTH = 13;// Define o tamanho do ID (que são exatos 13 caracteres)

        List<Pedido> sortedList = new ArrayList<>(dataset);// Clona o dataset (pra não modificar a lista original)

        for (int pos = ID_LENGTH - 1; pos >= 0; pos--)
        {// Realiza a ordenação usando o RadixSort
            countingSortByCharacterPosition(sortedList, pos);
        }

        return sortedList;
    }

    private void countingSortByCharacterPosition(List<Pedido> pedidos, int position)
    {
        final int ALPHABET_SIZE = 36; // 36 porque o alfabeto tem 26 letras e o nosso sistema decimal usa 10 números (0 a 9)
        int[] count = new int[ALPHABET_SIZE];
        Pedido[] output = new Pedido[pedidos.size()];

        for (Pedido pedido : pedidos)
        {// Conta as ocorrências de cada caractere
            char c = pedido.getId()[position];
            int index = getCharIndex(c);
            count[index]++;
        }

        for (int i = 1; i < ALPHABET_SIZE; i++)
        {// Calcula o acumulado da contagem de ocorrências
            count[i] += count[i - 1];
        }

        for (int i = pedidos.size() - 1; i >= 0; i--)
        {// Faz o array de saída
            Pedido pedido = pedidos.get(i);
            char c = pedido.getId()[position];
            int index = getCharIndex(c);
            output[count[index] - 1] = pedido;
            count[index]--;
        }

        for (int i = 0; i < pedidos.size(); i++)
        {// Copia o array ordenado pra lista original
            pedidos.set(i, output[i]);
        }
    }

    private int getCharIndex(char c)
    {
        numberComparisons++;

        if (Character.isDigit(c))
        {
            return c - '0';
        }
        else
        {
            return c - 'A' + 10;
        }
    }

    @Override
    public int numberComparisons()
    {
        return numberComparisons;
    }
}
