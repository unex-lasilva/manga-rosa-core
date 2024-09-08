package br.com.mangarosa.model;

import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.interfaces.Sort;
import java.util.ArrayList;
import java.util.List;

public class Radix implements Sort<Pedido> {
    private int comparisonCount = 0;

    // Ordena os IDs
    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        // A ordem dos caracteres será da direita para a esquerda
        for (int posicao = 12; posicao >= 0; posicao--) {
            // Aplica o Counting Sort no caractere na posição atual
            countingSort(dataset, posicao);
        }
        return dataset;
    }

    // Retorna o contador de comparações
    @Override
    public int numberComparisons() {
        return comparisonCount;
    }

    // O Counting sort, que vai ordenar os caracteres que estão sendo analisados
    private void countingSort(List<Pedido> dataset, int posicao) {
        int n = dataset.size();
        List<Pedido> saida = new ArrayList<>(n);
        int[] count = new int[256]; // 256 pois é equivalente ao valor da tabela ASCII

        // Inicializa a lista de saída
        for (int i = 0; i < n; i++) {
            saida.add(null);
        }

        // Conta a ocorrência dos caracteres atuais
        for (Pedido pedido : dataset) {   
            count[pedido.getId()[posicao]]++;  
            comparisonCount++;   //Conta as comparações  
        }

        // Atualiza a posição do caractere
        for (int i = 1; i < 256; i++) { 
            count[i] += count[i - 1]; 
        }

        // Constroi o array de saída
        for (int i = n - 1; i >= 0; i--) {
            Pedido pedido = dataset.get(i); 
            char currentChar = pedido.getId()[posicao]; 
            saida.set(count[currentChar] - 1, pedido); 
            count[currentChar]--;     
        }

        // Atualiza o dataset com a nova ordem encontrada
        for (int i = 0; i < n; i++) { 
            dataset.set(i, saida.get(i)); 

        }
     
    }
}

