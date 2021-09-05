/**
 * Decorator pattern attaches additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 */
package com.desingPatterns;

import java.io.*;

public class Decorator {
    public static void main(String[] args) throws IOException {
        Component comp = new Decorator1(new ConcreteComponent());
        System.out.println(comp.getDescription());

        comp = new Decorator1(new Decorator2(new ConcreteComponent()));
        System.out.println(comp.getDescription());


        //using FilterInputSteam, real world example;
        InputStream in = new UpperCaseStream(new StringBufferInputStream("madhukar"));
        int ch = in.read();
        while (ch != -1) {
            System.out.print((char) (ch));
            ch = in.read();
        }
    }
}
class UpperCaseStream extends FilterInputStream {
    public UpperCaseStream(InputStream wrapper) {
        super(wrapper);
    }
    public int read() throws IOException {
        int data = super.read();
        if(data!=-1){
            if(data>=97 && data <=122){
                return 65 + (data-97);
            }
        }
        return data;
    }
}

abstract class Component  {
    abstract public String getDescription();
}
class ConcreteComponent extends  Component {
    public String getDescription(){
        return " Concrete1 ";
    }
}
abstract class BaseDecorator extends Component {
    Component component;
    BaseDecorator(Component component){
        this.component = component;
    }
    public String getDescription(){
        return this.component.getDescription();
    };
}
class Decorator1 extends BaseDecorator {
    Decorator1(Component component){
        super(component);
    }
    public String getDescription(){
        String wrappedObjVal = this.component.getDescription();
        return wrappedObjVal + "-->"+"Decorator1 ";
    }
}
class Decorator2 extends BaseDecorator {
    Decorator2(Component component){
        super(component);
    }
    public String getDescription(){
        String wrappedObjVal = this.component.getDescription();
        return wrappedObjVal + "-->"+"Decorator2 ";
    }
}