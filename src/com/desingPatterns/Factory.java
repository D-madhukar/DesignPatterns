/**
 * Factory method pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate.
 * Factory method lets a class defer instantiation to subclasses
 */
package com.desingPatterns;

public class Factory {
    public static void main(String[] args) {
        //Approach 1
//        IButton button = UIButtonFactory.getButton(OSType.WINDOWS);
//        IButton button = UIButtonFactory.getButton(OSType.LINUX);
//        IButton button = UIButtonFactory.getButton(OSType.MAC);

        //get windows button from windows factory
        UIComponentFactory factory = new WindowsButtonFactory();
        IButton button = factory.getButton();
        //Get linux button
        factory = new LinuxButtonFactory();
        button = factory.getButton();
    }
}

interface IButton { // Product
}
class WindowsButton implements IButton{}
class LinuxButton implements  IButton {}
class MacButton implements  IButton{}

//Approach -1 : one way of doing this , but its not flexible to override
//enum OSType {
//    WINDOWS,LINUX, MAC
//}
//class UIButtonFactory {
//    public static IButton getButton(OSType osType){
//        IButton button = null;
//        switch (osType){
//            case OSType.WINDOWS: {button = new WindowsButton(); break;};
//            case OSType.LINUX: {button = new LinuxButton();break};
//            default: button = new MacButton();
//        }
//        return button;
//    }
//}

abstract  class UIComponentFactory {
    public abstract IButton getButton();
}
class WindowsButtonFactory extends  UIComponentFactory {
    public IButton getButton(){
        return new WindowsButton();
    }
}
class LinuxButtonFactory extends  UIComponentFactory {
    public IButton getButton(){
        return new LinuxButton();
    }
}
class MacButtonFactory extends  UIComponentFactory {
    public IButton getButton(){
        return new MacButton();
    }
}