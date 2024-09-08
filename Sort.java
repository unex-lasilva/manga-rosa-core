package Projeto1;

import java.util.List;

public interface Sort<T extends Comparable<? super T>> {
    List<T> sort(List<T> dataset);

    int numberComparisons();
}