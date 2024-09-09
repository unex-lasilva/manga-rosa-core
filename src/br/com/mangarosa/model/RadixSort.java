package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;
import java.util.ArrayList;
import java.util.List;

public class RadixSort implements Sort<Pedido> {
    
    private int countComparacoes;

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        countComparacoes = 0;

        Pedido[] arr = dataset.toArray(new Pedido[0]); 

        radix_sort(arr); 

        List<Pedido> listaOrdenada = new ArrayList<>();
        for (Pedido p : arr) { 
            listaOrdenada.add(p); 
        }

        return listaOrdenada; 
    }
    //Metodo para executar o radix sort chamando o metodo counting sort
    private void radix_sort(Pedido[] arr) { 
        for (int i = 12; i >= 0; i--) {
            countingSort(arr, i); 
    }
    }

    private void countingSort(Pedido[] arr, int pos) {
        int range = 256; // define uma variavel range com tamanho de 256. 1 operação
        int[] contador = new int[range]; // Inicializa o array contator com tamanho fixo. 1 operação
        Pedido[] saida = new Pedido[arr.length]; // Inicializa um array de saida de pedidos n vezes

        //Preenchimento do array contador e incremento no numero de contagens de comparações: 3n operações
        for (Pedido pedido : arr) { // n 
            char c = pedido.getId()[pos];
            contador[c]++; 
            countComparacoes++; 
        }

        //Acumulo do array contador: 3n operações + 256 sendo nosso range de operações fixo
        for (int i = 1; i < contador.length; i++) { // 256
            contador[i] += contador[i - 1]; // 3n
        }

        //Reorganização dos elementos no array de saida: 4n operações
        for (int j = arr.length - 1; j >= 0; j--) { 
            Pedido pedido = arr[j]; 
            char c = pedido.getId()[pos]; 
            saida[contador[c] - 1] = pedido; 
            contador[c]--; 
        }

        //Cópia dos elementos de volta para o arr: n operações 
        System.arraycopy(saida, 0, arr, 0, arr.length); 
    }

    //Metodo que retorna a contagem de comparações
    @Override
    public int numberComparisons() {
        return countComparacoes;
    }
}
