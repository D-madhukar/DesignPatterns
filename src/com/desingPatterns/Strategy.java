/**
 * Strategy patterns defines a family of algorithms, encapsulates each one and makes them interchangeable.
 * Strategy lets the algorithm vary independently from clients that use it
 * */

package com.desingPatterns;
import java.util.*;
public class Strategy {
    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1,2,3,4,5);
        //using quicksort
        SortingList sortingList = new SortingList(new QuickSort());
        sortingList.sort(li);

        //using merge sort
        sortingList.set_strategy(new MergeSort());
        sortingList.sort(li);

    }
}

class SortingList {
    SortStrategy _strategy;
    SortingList(SortStrategy _strategy){
        this._strategy = _strategy;
    }
    public <T> void sort(List<T> list){
        this._strategy.sort(list);
    }
    public void set_strategy(SortStrategy _strategy){
        this._strategy = _strategy;
    }
}

//Sorting List
interface  SortStrategy{
    public <T> void sort(List<T> list);
}
class QuickSort implements SortStrategy{
    public <T> void sort(List<T> list) {
        System.out.println("Quick sort");
    }
}
class MergeSort implements SortStrategy{
    public <T> void sort(List<T> list) {
        System.out.println("Merge sort");

    }
}

