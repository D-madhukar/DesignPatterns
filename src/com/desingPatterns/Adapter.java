package com.desingPatterns;

import java.util.*;
import java.util.Iterator;

public class Adapter {
    public static void main(String[] args) {
        //Client using targetInterface
        TargetInterface target = new TargetInterfaceToIncompatableInterfaceAdapter(new IncompatableInterfaceImp());
        target.display();
        System.out.println("----------------------IteratorToEnumerate Adapter -----------------");
        //Using enumeration on ArrayList object
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        enumerate(new IteratorToEnumerationAdapter<>(list.iterator()));

        System.out.println("----------------------EnumerateToIterator Adapter -----------------");
        Vector<Integer> vector = new Vector<>();
        vector.add(1);vector.add(2);
        vector.add(3);vector.add(4);
        iterate(new EnumerationToIteratorAdapter<>(vector.elements()));

    }
    public static <T> void enumerate(Enumeration<T> itr){ // This doesnot know it is receiving Adapter or not
        while(itr.hasMoreElements()){
            System.out.print(itr.nextElement()+" ");
        }
        System.out.println();
    }
    public static <T> void iterate(Iterator<T> itr){
        while(itr.hasNext()){
            System.out.print(itr.next()+" ");
        }
        System.out.println();
    }
}

//Sample example
interface  TargetInterface {
    void display();
}
class TargetInterfaceToIncompatableInterfaceAdapter implements TargetInterface {
    IncompatableInterface adaptee;
    public TargetInterfaceToIncompatableInterfaceAdapter(IncompatableInterface adaptee){
        this.adaptee = adaptee;
    }
    public void display(){
        System.out.println("Adapter");
        this.adaptee.print();
    }
}
interface IncompatableInterface {
    void print();
}
class IncompatableInterfaceImp implements IncompatableInterface {
    public  void print(){
        System.out.println("Incompatible interface method");
    }
}


//Real world Example-1
class IteratorToEnumerationAdapter<T> implements Enumeration<T>{
    Iterator<T> iterator ;
    IteratorToEnumerationAdapter(Iterator<T> iterator){
        this.iterator = iterator;
    }
    @Override
    public boolean hasMoreElements() {
        return this.iterator.hasNext();
    }

    @Override
    public T nextElement() {
        return this.iterator.next();
    }
}
//can do reverse also
class EnumerationToIteratorAdapter<T> implements Iterator<T>{
    Enumeration<T> enumeration;
    EnumerationToIteratorAdapter(Enumeration<T> enumeration){
        this.enumeration = enumeration;
    }
    @Override
    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return this.enumeration.nextElement();
    }

}