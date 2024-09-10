package br.com.mangarosa.model;

import java.util.Arrays;
import java.util.Objects;

public class Pedido implements Comparable<Pedido> {

    private char[] id;

    public Pedido(char[] id){
        setId(id);
    }

    public char[] getId() {
        return id;
    }

    public void setId(char[] id) {
        if(id.length != 13)
            throw new RuntimeException("The id field must have exactly 13 digits.");
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + Arrays.toString(id) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.deepEquals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(id);
    }

    @Override
    public int compareTo(Pedido o) {
        return 0;
    }
}
