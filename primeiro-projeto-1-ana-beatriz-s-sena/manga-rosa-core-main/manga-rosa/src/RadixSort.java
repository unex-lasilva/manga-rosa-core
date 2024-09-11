import java.util.Arrays;

public class RadixSort {

    // Contador de comparações
    private static int comparaConta = 0;

    // Counting Sort sendo usado para contar e organizar os caracteres
    public static void countingSort(String[] arr, int posicao) {
        int n = arr.length;
        String[] saida = new String[n];
        int[] count = new int[256];  // conta todos os caracteres padrão da tabela ASCII

        // Inicializa o array de contagem
        Arrays.fill(count, 0);

        // Conta a ocorrência de cada caractere na posição 'posicao'
        for (int i = 0; i < n; i++) {
            int charIndex = arr[i].charAt(posicao);
            count[charIndex]++;
            comparaConta++;  // Contando cada operação de acesso ao array arr
        } // complexidade O(n)

        // Atualiza count[i] para que ele contenha a posição correta de um caractere na saída
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
            
        } // complexidade O(1), pois o loop é constante

        // Constrói o array de saída
        for (int i = n - 1; i >= 0; i--) {
            int charIndex = arr[i].charAt(posicao);
            saida[count[charIndex] - 1] = arr[i];
            count[charIndex]--;
            
        } // complexidade O(n)

        // Copia o array de saída para arr[], de forma que arr[] contenha os elementos ordenados por esta posição
        for (int i = 0; i < n; i++) {
            arr[i] = saida[i];
            
        } // complexidade O(n)
    }

    // Função principal do Radix Sort
    public static void radixSort(String[] arr) {
        int maxLength = 13;  // Todas as entradas têm 13 caracteres

        // Aplica o Counting Sort para cada posição, do último caractere para o primeiro (posição 12 a 0)
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            countingSort(arr, pos);
        }
    }

    // Função para obter o número de comparações
    public static int getcomparaConta() {
        return comparaConta;
    }

    public static void main(String[] args) {
        // Exemplo de entrada com strings alfanuméricas de 13 caracteres
        String[] arr = {
            "B1C3F4A5G8J6L",
            "A9B3C7D2E5F4G",
            "Z1X2Y3W4V5U6T",
            "M5N4O3P2Q1R9S",
            "J9K8L7M6N5O4P",
            "T4U3V2W1X9Y8Z"
        };

        System.out.println("Array original:");
        System.out.println(Arrays.toString(arr));

        // Chama a função radixSort
        radixSort(arr);

        System.out.println("\nArray ordenado:");
        System.out.println(Arrays.toString(arr));

        // Exibe o número de comparações realizadas
        System.out.println("\nNúmero total de comparações: " + getcomparaConta());
    }
}
