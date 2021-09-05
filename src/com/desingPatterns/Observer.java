/**
 * Observer pattern defines a one-to-many dependency between objects so that when one object changes state,
 * all of its dependents are notified and updated automatically.
 */
package com.desingPatterns;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        Publisher publisher = new ArticlePublisher();
        Customer1 customer1 = new Customer1("Madhu");
        Customer1 customer2 = new Customer1("Ramki");
        publisher.subscribe(customer1);
        publisher.subscribe(customer2);

        //Add new article will notify both customers
        publisher.addArticle("article1");

        //Madhu unsubscribe
        publisher.unSubscribe(customer1);
        publisher.addArticle("article2");
    }
}

interface Subscriber {
    void update(Publisher publisher);
}
interface  Publisher {
    void subscribe(Subscriber subscriber);
    void unSubscribe(Subscriber subscriber);
    void notifySubscribers();
    String getLatestArticle();
    void addArticle(String article);
}
class ArticlePublisher implements  Publisher{
    List<Subscriber> subscriberList;
    String latestArticle;
    ArticlePublisher(){
        this.subscriberList = new ArrayList<>();
    }
    @Override
    public void subscribe(Subscriber subscriber) {
        this.subscriberList.add(subscriber);
    }

    @Override
    public void unSubscribe(Subscriber subscriber) {
        this.subscriberList.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for(Subscriber subscriber :subscriberList){
            subscriber.update(this);
        }
    }
    public void addArticle(String article){
        this.latestArticle = article;
        notifySubscribers();
    }
    public String getLatestArticle(){
        return this.latestArticle;
    }
}

class  Customer1 implements  Subscriber {
    String name;
    Customer1(String name){
         this.name = name;
    }
    @Override
    public void update(Publisher publisher) {
        System.out.print(this.name+" customer notified  ");
        System.out.println("Latest article " + publisher.getLatestArticle());
    }
}





