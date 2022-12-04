package org.example;


import java.math.BigDecimal;
import java.util.Comparator;

//Номенклатурная еденица
public class GoodItem implements Cloneable {

    private String itemNumber; //Код позиции
    private String itemName; //Наименование позиции
    private int itemCost; //Стоимость позиции
    private int itemAmount; //Количество позиций

    GoodItem(String itemNumber, String itemName, int itemCost, int itemAmount) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemAmount = itemAmount;
    }

    public String getItemNumber() {
        return this.itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCost() {
        return itemCost;
    }

    public int getItemAmount() {
        return itemAmount;
    }


    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    @Override
    public String toString() {
        return this.getItemNumber() + " " +
                this.getItemName() + " " +
                this.getItemCost() + " " +
                this.getItemAmount() + " ";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}



