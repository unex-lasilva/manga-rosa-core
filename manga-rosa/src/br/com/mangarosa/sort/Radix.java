package br.com.mangarosa.sort;

import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.Pedido;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Radix implements Sort<Pedido> {

    private int numberOfComparisons;

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        // ID de 13 caracteres
        int maxLength = 13;

        List<Pedido> sortedList = new ArrayList<>(dataset);

        // Radix Sort por cada posição de caractere, começando do menos significativo
        for (int position = maxLength - 1; position >= 0; position--) {
            countingSortByCharacterPosition(sortedList, position);
        }

        return sortedList;
    }

    private void countingSortByCharacterPosition(List<Pedido> list, int position) {
        // Contagem de caracteres (base 62: 0-9, A-Z, a-z)
        int[] count = new int[62];
        List<Pedido> output = new ArrayList<>(list.size());
    
        // Inicializa o array de contagem
        Arrays.fill(count, 0);
    
        // Contagem das ocorrências dos caracteres na posição especificada
        for (Pedido pedido : list) {
            char c = pedido.getId()[position];
            int index = getIndex(c);
            count[index]++;
            numberOfComparisons++; // Contador de operações de contagem
        }
    
        // Alterar o array de contagem para que cada elemento seja a posição final do caractere
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
            numberOfComparisons++; // Contador de operações de atualização
        }
    
        // Inicializar a lista de saída com a mesma capacidade da lista de entrada
        for (int i = 0; i < list.size(); i++) {
            output.add(null); // Preenche com nulos inicialmente
        }
    
        // Construa o array de saída
        for (int i = list.size() - 1; i >= 0; i--) {
            Pedido pedido = list.get(i);
            char c = pedido.getId()[position];
            int index = getIndex(c);
            int pos = count[index] - 1;
            output.set(pos, pedido);
            count[index]--;
            numberOfComparisons++; // Contador de operações de adição na lista de saída
        }
    
        // Copia a lista de saída para a lista original
        for (int i = 0; i < list.size(); i++) {
            list.set(i, output.get(i));
        }
    }
    
    

    private int getIndex(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        } else if (Character.isUpperCase(c)) {
            return c - 'A' + 10;
        } else if (Character.isLowerCase(c)) {
            return c - 'a' + 36;
        }
        throw new IllegalArgumentException("Invalid character in ID.");
    }

    @Override
    public int numberComparisons() {
        return numberOfComparisons;
    }
}
