import java.util.Arrays;

public class RadixSort {
    // Função para obter o valor máximo do array
    static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    //Função para realizar ordenação de contagem com base no dígito representado por exp
    static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Armazenar contagem de ocorrências em contagem[]
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Altere a contagem[i] para que contenha a posição real deste dígito na saída[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construa a matriz de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copie a matriz de saída para arr[], para que arr[] agora contenha números classificados
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Função principal para classificar arr[] de tamanho n usando Radix Sort
    static void radixSort(int[] arr) {
        int max = getMax(arr);

        // Faça a classificação de contagem para cada dígito. Observe que em vez de passar o número do dígito,
        //exp é passado. exp é 10 ^ i onde i é o número do dígito atual
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    //Função utilitária para imprimir o array
    static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {100,1000,10000,100000};
        radixSort(arr);
        printArray(arr);
    }
}
