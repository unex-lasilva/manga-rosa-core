package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;
import java.util.List;

public class OrdemAlfanumerica implements Sort<Pedido> {
    private int comparacoes = 0;

    @Override
    public List<Pedido> sort(List<Pedido> pedidos) {
        comparacoes = 0;
        radix(pedidos);
        return pedidos;
    }

    @Override
    public int numberComparisons() {
        return comparacoes;
    }

    // Radix chama o Counting pra ordenar a bagunça dele
    private void radix(List<Pedido> pedidos) {
        for (int index = 12; index >= 0; index--) {
            counting(pedidos, index);
            comparacoes++;
        }
    }

    // Transforma tudo em Int pra poder fazer o Counting funcionar
private int transformaIndex(char ch) {
    return Character.isDigit(ch) ? ch - '0' : ch - 'A' + 10; // Se for digito retorna 0 a 9 , se não 10 a 36
}

    // Metodo Counting com subevento do Radix
    public void counting(List<Pedido> pedidos, int index) {
        int totalCaracteres = 36; // Letras + Numeros
        int tamanhoLista = pedidos.size();// Retorna o numero de elementos,
        int[] contagem = new int[totalCaracteres];
        Pedido[] saida = new Pedido[tamanhoLista];

        for (Pedido pedido : pedidos) {
            int charIndex = transformaIndex(pedido.getId()[index]); // transforma tudo em Int
            contagem[charIndex]++; // Magica do COunting. Lance de verificar a quantidade de vezes que o Caractere
                                   // aparece.
            comparacoes++;
        }

        for (int i = 1; i < totalCaracteres; i++) { // TotalCarc = 36
            contagem[i] += contagem[i - 1];
            comparacoes++;
        }

        // Resultado do Counting
        for (int i = tamanhoLista - 1; i >= 0; i--) {
            Pedido pedido = pedidos.get(i);
            int charIndex = transformaIndex(pedido.getId()[index]);
            saida[--contagem[charIndex]] = pedido;
            

        }

        for (int i = 0; i < tamanhoLista; i++) {
            pedidos.set(i, saida[i]);
            
        }

    }

}
