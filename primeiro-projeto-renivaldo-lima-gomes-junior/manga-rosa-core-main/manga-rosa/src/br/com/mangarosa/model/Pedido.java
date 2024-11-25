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
        if(id.length == 13) {
            this.id = id;
        } else {
            throw new RuntimeException("The id field must have exactly 13 digits.");
        }
    }

    @Override
    public int compareTo(Pedido other) {
        
        for (int i = 0; i < this.id.length; i++) {
            if (this.id[i] != other.id[i]) {
                return Character.compare(this.id[i], other.id[i]);
            }
        }
        return 0;
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
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.deepEquals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(id);
    }
}