/**
 * Abstact factory design pattern provides an interface for creating family of related or dependent objects without specifying their concrete classes
 */
package com.desingPatterns;

public class AbstractFactory {
    public static void main(String[] args) {
        //Windows Components
        UIComponentAbstractFactory factory = new WindowsUIComponentAbstractFactory();
        IAlertBox windowsAlertBox = factory.getAlertBox(); // Here we dont know which instance will come
        IDropdown windowsDropdown = factory.getDropdown();

        //Linux Components
        factory = new LinuxUIComponentAbstractFactory();
        IAlertBox linuxAlertBox = factory.getAlertBox();
        ITextInput linuxTextInput = factory.getTextInput();

    }
}
//Define different components interfaces
interface IAlertBox{}
interface IDropdown{}
interface ITextInput{}

//Implementations of components of Windows family
class WindowsAlertBox implements  IAlertBox {}
class WindowsDropdown implements  IDropdown {}
class WindowsTextInput implements  ITextInput {}

//Implementations of components of Linux family
class LinuxAlertBox implements  IAlertBox {}
class LinuxDropdown implements  IDropdown {}

//Implementations of components of Mac family
class MacAlertBox implements  IAlertBox {}
class MacDropdown implements  IDropdown {}
class MacTextInput implements  ITextInput {}




abstract  class UIComponentAbstractFactory {
    public abstract IAlertBox getAlertBox();
    public abstract IDropdown getDropdown();
    public abstract ITextInput getTextInput();
}

class WindowsUIComponentAbstractFactory extends UIComponentAbstractFactory{
    public IAlertBox getAlertBox(){ return new WindowsAlertBox(); }
    public IDropdown getDropdown(){ return new WindowsDropdown(); }
    public ITextInput getTextInput(){ return new WindowsTextInput(); }
}
class LinuxUIComponentAbstractFactory extends UIComponentAbstractFactory{
    public IAlertBox getAlertBox(){ return new LinuxAlertBox(); }
    public IDropdown getDropdown(){ return new LinuxDropdown(); }
    public ITextInput getTextInput(){ return new WindowsTextInput(); } // LINUX AND WINDOWS uses same TextInput
}
class MacUIComponentAbstractFactory extends UIComponentAbstractFactory{
    public IAlertBox getAlertBox(){ return new MacAlertBox(); }
    public IDropdown getDropdown(){ return new MacDropdown(); }
    public ITextInput getTextInput(){ return new MacTextInput(); }
}

