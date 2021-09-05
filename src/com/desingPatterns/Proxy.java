package com.desingPatterns;
public class Proxy {
    public static void main(String[] args) {
        //Basic Proxy type
        Subject subject1 = new SubjectProxy(new SubjectImp());
        subject1.display();

        System.out.println("----------------------------------------------");
        //VirtualProxy
        VirtualProxySubject subject2 = new VirtualSubjectProxy();//Original Object is not yet create
        subject2.display();//first time it creates original object;
        subject2.display();
        System.out.println("-----------------------------------------------");
        //CachingProxy
        CachingSubject subject3 = new CachingSubjectProxy();
        subject3.fetchData();
        subject3.fetchData();//use cached data
        subject3.fetchData();//use cached data
        ((CachingSubjectProxy) subject3).clearCache();
        subject3.fetchData();//fetch data again
        System.out.println("-------------------------------------------------");

    }
}
//Basic type
interface  Subject {
    void display();
}
class SubjectImp implements Subject{
    public void display(){
        System.out.println("Subject Implementation");
    }
}
class SubjectProxy implements  Subject {
    Subject originalsubject;
    SubjectProxy(Subject subject){
        this.originalsubject = subject;
    }
    public void display(){
        System.out.println("Subject proxy");
        this.originalsubject.display();
    }
}

//Virtual proxy, Lazy Initialisation
interface VirtualProxySubject {
    void display();
}
class VirtualProxySubjectImp implements VirtualProxySubject {
    VirtualProxySubjectImp(){
        System.out.println(this.getClass().getSimpleName() +": constructing Heavy Operation.....");
    }
    public void display(){
        System.out.println(this.getClass().getSimpleName() + ": display");
    }
}
class VirtualSubjectProxy implements  VirtualProxySubject{
    VirtualProxySubject subject;
    VirtualSubjectProxy(){ }
    public void display(){
        if(subject == null) {
            System.out.println(this.getClass().getSimpleName() + ": Lazy Initialisation");//Happens only once
            subject = new VirtualProxySubjectImp();//Lazy Intialization
        }
        System.out.println(this.getClass().getSimpleName() +": display");
        subject.display();
    }
}

//Caching Proxy
interface CachingSubject {
    public String fetchData();
}
class CachingSubjectImp implements CachingSubject {
    public String fetchData(){
        System.out.println(this.getClass().getSimpleName() +": Fetching data from DB/Network");
        return "ABC";//Returning hardcoded value
    }
}
class CachingSubjectProxy implements CachingSubject {
    CachingSubject subject = new CachingSubjectImp();
    String data;
    boolean invaliate = true;
    public String fetchData(){
        if(data == null || invaliate) {
            System.out.println("Fetch data");
            data = subject.fetchData();
            invaliate = false;
        }else{
            System.out.println("Using cached data");
        }
        return data;
    }
    public void clearCache(){
        invaliate = true;
        System.out.println("Cache is invalidated");
    }
}

//Protection Proxy