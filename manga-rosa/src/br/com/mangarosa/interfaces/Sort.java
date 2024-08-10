package br.com.mangarosa.interfaces;

import java.util.List;

public interface Sort<T extends Comparable<? super T>> {
    List<T> sort(List<T> dataset);
}
