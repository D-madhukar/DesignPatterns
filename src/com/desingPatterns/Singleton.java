/**
 * Singleton pattern ensures a class has only one instance, and provide a global point of access to it
 */
package com.desingPatterns;

public class Singleton {
    public static void main(String[] args) {
        //verification
        System.out.println(SimpleSingleton.instance() == SimpleSingleton.instance());
    }
}

//Not thread safe
class SimpleSingleton {
    private static SimpleSingleton _instance;
    private SimpleSingleton(){}
    public static SimpleSingleton instance(){
        if(_instance == null){
            _instance = new SimpleSingleton(); //Lazy Instantiation
        }
        return _instance;
    }
}

//Threadsafe singletons with different approaches
//APPROACH-1 , EAGER INSTANTIATION
class EagerThreadSafeSingleton{
    private static EagerThreadSafeSingleton _instance = new EagerThreadSafeSingleton(); //Eager Instantiation;
    private EagerThreadSafeSingleton(){}
    public static EagerThreadSafeSingleton instance(){
        return _instance;
    }
}

//APPROACH-2, SYNCHRONIZATION METHOD
class SynchronizeThreadSafeSingleton{
    private static SynchronizeThreadSafeSingleton _instance = new SynchronizeThreadSafeSingleton(); //Eager Instantiation;
    private SynchronizeThreadSafeSingleton(){}
    public static synchronized  SynchronizeThreadSafeSingleton instance(){
        return _instance;
    }
}

// APPROACH-3, DOUBLE CHECK LOCKING
class DoubleCheckingLockThreadSafeSingleton {
    private static volatile DoubleCheckingLockThreadSafeSingleton _instance; // volatile is important
    private DoubleCheckingLockThreadSafeSingleton(){}
    public static DoubleCheckingLockThreadSafeSingleton instance(){
        if(_instance == null){
            synchronized (DoubleCheckingLockThreadSafeSingleton.class){
                if(_instance == null)
                    _instance = new DoubleCheckingLockThreadSafeSingleton();
            }
        }
        return _instance;
    }
}
