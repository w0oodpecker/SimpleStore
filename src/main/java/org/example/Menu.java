package org.example;

import java.util.*;

//Пользовательское меню
public class Menu {

    MenuLvl currentLvlMenu; //Для хранения положения в меню
    Store store; //Для объекта магазина
    Store workStore; //Для объекта клона магазина в целях манипуляций: фильтр и пр
    Basket basket; //Для объекта корзины покупателя

    enum AddDelType {ADDGOOD, DELGOOD}

    //COMMONMESSAGES
    private final String WRONGALARM = "Что-то пошло не так, попробуйте еще раз :)";
    private final String CATALOGUPDATED = "Каталог обновлен!";
    private final String UNDERCONSTRUCTION = "Извините, мы еще разрабатываем этот раздел";
    private final String CHOOSEMENUITEM = "Введите номер пункта меню и нажмите Enter: ";
    private final String CHOOSEGOODITEM = "Введите номер товара и нажмите Enter: ";

    //SETFILTERNAME
    private final String ENTERSTFORSEARCHING = "Введите строку для поиска: ";

    //Уровень Главное
    private final String DESCRMAINMENU = "Крутой магазин:";
    private final String CATALOG = "Каталог";
    private final String BASKET = "Корзина";
    private final String EXIT = "Выход";
    private MenuLvl mainMenu; //Главное меню


    //Уровень Товары
    private final String DESCRGODDSMENU = "Каталог:";
    private final String SHOWGOODS = "Показать товары";
    private final String UPDATEGOODS = "Обновить каталог";
    private final String ADDTOBASKET = "Добавить товар в корзину";
    private final String SORTDESCPRICE = "Сортировать по убыванию стоимости";
    private final String SORTASCPRICE = "Сортировать по возрастанию стоимости";
    private final String FILTERS = "Фильтры";
    private final String UPTOMAIN = "Наверх";
    private MenuLvl goodsMenu;


    //Уровень Фильтры
    private final String DESCRFILTERMENU = "Фильтры:";
    private final String SETFILTERPRICE = "Фильтр по стоимости";
    private final String SETFILTERNAME = "Фильтр по наименованию";
    private final String RESETFILTER = "Сброс фильтров и сортировки";
    private final String UPTOGOODS = "Наверх";
    private MenuLvl filterMenu;

    //Уровень Корзина
    private final String DESCRBASKETMENU = "Корзина:";
    private final String SHOWBASKET = "Посмотреть корзину";
    private final String DELLITEM = "Удалить товар из корзины";
    private final String ORDER = "Оформить заказ";
    //private final String UPTOMAIN = "Наверх";
    private MenuLvl basketMenu;

    //SETFILTERPRICE
    private final String SETFILTERPRICERANGE = "Установите диапазон стоимости товара";
    private final String SETFILTERPRICELOW = "Введите нижнюю границу: ";
    private final String SETFILTERPRICEUPPER = "Введите верхнюю границу: ";


    Menu(Store store) throws CloneNotSupportedException {
        //Инициализация объекта меню
        this.store = store;
        this.workStore = store.clone();
        this.basket = new Basket();

        mainMenu = new MenuLvl(DESCRMAINMENU); //Создаем объект главного меню
        goodsMenu = new MenuLvl(DESCRGODDSMENU); //Создаем объект меню каталога
        filterMenu = new MenuLvl(DESCRFILTERMENU); //Создаем объект меню фильтр
        basketMenu = new MenuLvl(DESCRBASKETMENU); //Создаем объект меню корзины

        //Наполняем mainMenu
        mainMenu.addItem(new MenuItem(CATALOG, () -> printMenu(goodsMenu))); //Вызов меню каталог
        mainMenu.addItem(new MenuItem(BASKET, () -> printMenu(basketMenu))); //Вызов меню корзина
        mainMenu.addItem(new MenuItem(EXIT, () -> exit())); //Выход из программы

        //Наполняем goodsMenu
        goodsMenu.addItem(new MenuItem(SHOWGOODS, () -> showGoods(workStore, DESCRGODDSMENU)));//Показать список товаров
        goodsMenu.addItem(new MenuItem(UPDATEGOODS, () -> {
            try {
                workStore = updateGoods(store);
                return true;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        })); //Обновить каталог
        goodsMenu.addItem(new MenuItem(ADDTOBASKET, () -> addOrDel(basket, workStore, AddDelType.ADDGOOD)));
        goodsMenu.addItem(new MenuItem(SORTDESCPRICE, () -> sortDescGoodsByPrice(workStore))); //Сортировка по убыванию стоимости
        goodsMenu.addItem(new MenuItem(SORTASCPRICE, () -> sortAscGoodsByPrice(workStore))); //Сортировка по возрастанию стоимости
        goodsMenu.addItem(new MenuItem(FILTERS, () -> printMenu(filterMenu))); //Вызов меню фильтр
        goodsMenu.addItem(new MenuItem(UPTOMAIN, () -> printMenu(mainMenu))); //Возврат в главное меню

        //Наполняем filterMenu
        filterMenu.addItem(new MenuItem(SETFILTERPRICE, () -> setFilterGoodsPrice(workStore)));
        filterMenu.addItem(new MenuItem(SETFILTERNAME, () -> setFilterGoodsName(workStore)));
        filterMenu.addItem(new MenuItem(RESETFILTER, () -> {
            try {
                workStore = updateGoods(store);
                return true;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }));
        filterMenu.addItem(new MenuItem(UPTOGOODS, () -> printMenu(goodsMenu))); //Возврат в главное меню

        //Наполняем basketMenu
        basketMenu.addItem(new MenuItem(SHOWBASKET, () -> showGoods(basket, DESCRBASKETMENU)));
        basketMenu.addItem(new MenuItem(DELLITEM, () -> addOrDel(basket, workStore, AddDelType.DELGOOD)));
        basketMenu.addItem(new MenuItem(ORDER, () -> underconstractionAlarm()));
        basketMenu.addItem(new MenuItem(UPTOMAIN, () -> printMenu(mainMenu))); //Возврат в главное меню
        this.currentLvlMenu = mainMenu;
    }


    //Вввод на консоль подменю
    public boolean printMenu(MenuLvl menuLvl) {
        System.out.println(menuLvl.toString());
        System.out.println(CHOOSEMENUITEM);
        this.currentLvlMenu = menuLvl;
        return true;
    }

    //EXIT
    public boolean exit() {
        return false;
    }

    //SHOWGOODS
    public boolean showGoods(MasterStore object, String message) {
        System.out.println(message);
        System.out.println(object.toString());
        System.out.println(currentLvlMenu.toString());
        System.out.println(CHOOSEMENUITEM);
        return true;
    }

    //SORTDESC
    public boolean sortDescGoodsByPrice(MasterStore object) {
        object.sortByPrice(Store.TypeOfSorting.DESCENDING);
        System.out.println(currentLvlMenu.toString());
        return true;
    }

    //SORTDESC
    public boolean sortAscGoodsByPrice(MasterStore object) {
        object.sortByPrice(Store.TypeOfSorting.ASCENDING);
        System.out.println(currentLvlMenu.toString());
        return true;
    }

    //SETFILTERPRICE - по стоимости
    public boolean setFilterGoodsPrice(MasterStore object) {
        int lowLvl = 0; //Нижняя граница
        int upperLvl = 0; //Верхняя граница
        boolean flagOfEnding = true;

        while (flagOfEnding) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(SETFILTERPRICERANGE);
                System.out.print(SETFILTERPRICELOW);
                lowLvl = scanner.nextInt();
                System.out.print(SETFILTERPRICEUPPER);
                upperLvl = scanner.nextInt();
                System.out.println();
                flagOfEnding = false;
            } catch (InputMismatchException exc) {
                System.out.println(WRONGALARM);
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(WRONGALARM);
            }
        }
        object.filterByCost(lowLvl, upperLvl);
        System.out.println(currentLvlMenu.toString());
        return true;
    }

    //SETFILTERNAME - по стоимости
    public boolean setFilterGoodsName(MasterStore object) {
        String string; //Подстрока поиска
        Scanner scanner = new Scanner(System.in);
        System.out.print(ENTERSTFORSEARCHING);
        string = scanner.nextLine();
        System.out.print("");
        object.filterByString(string);
        System.out.println(currentLvlMenu.toString());
        return true;
    }

    //UPDATEGOODS
    public Store updateGoods(Store objectSource) throws CloneNotSupportedException {
        Store objectTarget = objectSource.clone();
        System.out.println(CATALOGUPDATED);
        System.out.println(currentLvlMenu.toString());
        return objectTarget;
    }

    //UNDERCONSTRACTION
    public boolean underconstractionAlarm() {
        System.out.println(UNDERCONSTRUCTION);
        System.out.println(currentLvlMenu.toString());
        return true;
    }

    //ADDTOBASKET
    public boolean addOrDel(Basket basket, Store store, AddDelType basketAddDel) {

        int goodItemNumber = 0;
        boolean flagOfEnding = true;
        while (flagOfEnding) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(CHOOSEGOODITEM);
                goodItemNumber = scanner.nextInt();
                flagOfEnding = false;
            } catch (InputMismatchException exc) {
                System.out.println(WRONGALARM);
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(WRONGALARM);
            }
        }

        if (basketAddDel == AddDelType.ADDGOOD) {
            basket.addItem(
                    store.getItemList().get(goodItemNumber).getItemNumber(),
                    store.getItemList().get(goodItemNumber).getItemName(),
                    store.getItemList().get(goodItemNumber).getItemCost(),
                    1);
        } else {
            basket.delItem(goodItemNumber);
        }
        showGoods(basket, DESCRBASKETMENU);
        return true;
    }


}
