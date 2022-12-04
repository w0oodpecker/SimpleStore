package org.example;


//Подуровень меню
import java.util.ArrayList;
import java.util.List;

public class MenuLvl {

    private String nameOfLvl; //Наименование подменю
    private List<MenuItem> menuOfLvl; //Список позиций подменю

    MenuLvl(String nameOfLvl){
        this.nameOfLvl = nameOfLvl;
        this.menuOfLvl = new ArrayList<>();
    }


    //Добавление пункта меню
    public void addItem(MenuItem menuItem){
        this.menuOfLvl.add(menuItem);
    }


    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(this.nameOfLvl).append("\n");
        for(int i = 0; i < menuOfLvl.size(); i++){
            output.append(Integer.toString(i)).append(". ");
            output.append(menuOfLvl.get(i).getItemName()).append(" ");
            output.append("\n");
        }
        return output.toString();
    }


    public MenuItem getMenuItem(int index){
        return this.menuOfLvl.get(index);
    }

}
