package br.com.mangarosa.interfaces;

import br.com.mangarosa.model.Pedido;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdRadix implements Sort<Pedido> {
    private int operacoes = 0;

     // Obtém o comprimento do ID do primeiro pedido e ordena os pedidos com base em cada dígito do id
    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        int length = dataset.get(0).getId().length;

        for (int digito = length - 1; digito >= 0; digito--) {
            dataset = countingSortByCharacter(dataset, digito);
        }

        return dataset;
    }

    @Override
    public int numberComparisons() {
        return operacoes;
    }
    
    private List<Pedido> countingSortByCharacter(List<Pedido> dataset, int digito) {
        int n = dataset.size();
        List<Pedido> output = new ArrayList<>(n);
    // Inicializa a lista de saída usando null
        for (int i = 0; i < n; i++) {
            output.add(null);
        }
        //  contagem para poder armazenar
        int[] count = new int[256];
        Arrays.fill(count, 0);
        // conta a frequencia e vai incrementar p
        for (Pedido pedido : dataset) {
            char c = pedido.getId()[digito];
            count[c]++;
            operacoes++; 
        }
        // Vai incrementa por cada atualização
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
            operacoes++; 
        }
        // Aqui por cada movimentação, ja tomei burnout então...
        for (int i = n - 1; i >= 0; i--) {
            char c = dataset.get(i).getId()[digito];
            output.set(count[c] - 1, dataset.get(i));
            count[c]--;
            operacoes++; 
        }

        return output;
    }
}
