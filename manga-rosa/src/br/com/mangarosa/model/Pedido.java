package br.com.mangarosa.model;

import java.util.Arrays;

/**
 * Classe que representa um Pedido, com um identificador alfanumérico de 13 caracteres.
 * Implementa a interface Comparable para permitir a comparação entre pedidos, embora
 * o método de comparação possa ser customizado conforme necessário.
 */
public class Pedido implements Comparable<Pedido> {

    // Identificador alfanumérico do pedido
    private char[] id;

    /**
     * Construtor que inicializa o pedido com um identificador fornecido.
     *
     * @param id Identificador alfanumérico do pedido, que deve conter exatamente 13 caracteres.
     */
    public Pedido(char[] id) {
        setId(id); // Chama o método setter para garantir a validação do tamanho do ID
    }

    /**
     * Retorna o identificador alfanumérico do pedido.
     *
     * @return O identificador (char array) do pedido.
     */
    public char[] getId() {
        return id;
    }

    /**
     * Define o identificador do pedido, verificando se ele possui exatamente 13 caracteres.
     * Caso contrário, lança uma exceção.
     *
     * @param id Identificador a ser atribuído.
     */
    public void setId(char[] id) {
        if (id.length == 13) {
            this.id = id; // Atribui o valor ao campo id
        } else {
            throw new RuntimeException("O campo 'id' deve conter exatamente 13 caracteres.");
        }
    }

    /**
     * Retorna a representação em String do objeto Pedido, mostrando o identificador.
     *
     * @return Uma string representando o Pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + Arrays.toString(id) +
                '}';
    }

    /**
     * Verifica se dois objetos Pedido são iguais, comparando seus identificadores.
     *
     * @param o O objeto a ser comparado.
     * @return true se os identificadores forem iguais; false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Arrays.equals(id, pedido.id); // Compara os arrays de caracteres
    }

    /**
     * Gera um código hash para o pedido com base no identificador.
     *
     * @return O código hash do pedido.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(id); // Gera um hash com base
    }

    @Override
    public int compareTo(Pedido o) {
        return 0;
    }
}