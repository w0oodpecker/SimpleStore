package org.example;

//Пункт меню
public class MenuItem {

    public interface Command {
        boolean execute();}
    private String itemName;
    private Command command;


    MenuItem(String itemName){
        this.itemName = itemName;
        this.command = null;
    }

    MenuItem(String itemName, Command command){
        this.itemName = itemName;
        this.command = command;
    }

    public String getItemName(){
        return this.itemName;
    }

    public Command getCommand(){
        return this.command;
    }

}
