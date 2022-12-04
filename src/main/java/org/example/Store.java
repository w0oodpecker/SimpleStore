package org.example;

import java.util.List;

public class Store extends MasterStore{

    private static Store instance = null; //Для хранения инстанса

    private Store() {} //Конструктор, делаем инстанс магазина единственным

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    @Override
    public Store clone(){
        Store store = new Store();
        List<GoodItem> list = this.getItemList().stream().toList();
        store.setItemList(list);
        return store;
    }

}
