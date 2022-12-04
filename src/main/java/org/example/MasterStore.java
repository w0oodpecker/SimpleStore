package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Магазин Мастер класс
public class MasterStore implements Cloneable {

    private final String NOTFOUND = "Ничего не найдено";
    private final String AMOUNTABR = "шт.";
    private final String PRICEABR = "руб.";
    private final String ARTICULETITLE = "Артикул:";
    private final String GODDTITLE = "Наименование:";
    private final String PRICETITLE = "Стоимость:";
    private final String AMOUNTTITLE = "Количество:";
    enum TypeOfSorting {ASCENDING, DESCENDING}

    ; //Тип сортировки
    private List<GoodItem> itemList; //Список позиций

    public MasterStore() { //Конструктор
        itemList = new ArrayList<>();
    } //Позволяем существовать только одному экземпляру магазина


    //Добавление позиции
    public void addItem(String itemNumber, String itemName, int itemCost, int itemAmount) {
        itemList.add(new GoodItem(itemNumber, itemName, itemCost, itemAmount));
    }

    //Удаление позиции
    public void delItem(int index) {
        itemList.remove(index);
    }


    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        if (itemList.size() != 0) {
            for (int i = 0; i < this.itemList.size(); i++) {
                output.append(Integer.toString(i)).append(". ");
                output.append(ARTICULETITLE).append(" ").append(this.itemList.get(i).getItemNumber()).append(" ");
                output.append(GODDTITLE).append(" ").append(this.itemList.get(i).getItemName()).append(" ");
                output.append(PRICETITLE).append(" ").append(this.itemList.get(i).getItemCost()).append(" ").append(PRICEABR);
                output.append(AMOUNTTITLE).append(" ").append(this.itemList.get(i).getItemAmount()).append(" ").append(AMOUNTABR);
                output.append("\n");
            }
        } else {
            output.append(NOTFOUND);
        }
        return output.toString();
    }


    //Сортировка позиций магазина
    public void sortByPrice(TypeOfSorting typeOfSorting) {
        Comparator<GoodItem> comparatorPrice;
        List tmpList;
        if (typeOfSorting == TypeOfSorting.DESCENDING)
            comparatorPrice = (o1, o2) -> (o1.getItemCost() < o2.getItemCost()) ? 1 : ((o1.getItemCost() == o2.getItemCost() ? 0 : -1));
        else {
            comparatorPrice = (o1, o2) -> (o1.getItemCost() < o2.getItemCost()) ? -1 : ((o1.getItemCost() == o2.getItemCost() ? 0 : 1));
        }
        itemList = itemList.stream().sorted(comparatorPrice).toList();
    }


    //Фильтр по диапазону стоимости
    public void filterByCost(int lowLvl, int upperLvl) {
        itemList = itemList.stream().filter(k -> k.getItemCost() >= lowLvl & k.getItemCost() <= upperLvl).toList();
    }

    //Фильтр по подстроке наименования
    public void filterByString(String string) {
        itemList = (itemList.stream().filter(k -> k.getItemName().contains(string))).toList();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public List<GoodItem> getItemList() {
        return this.itemList;
    }

    public void setItemList(List object) {
        this.itemList = object;
    }

}
