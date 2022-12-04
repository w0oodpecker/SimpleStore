package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        //Текстовые сообщения
        final String ENDSTRING = "Вы завершили работу программы";
        final String WRONGALARM = "Что-то пошло не так. Попробуйте еще раз :)";

        //Инициализация магазина
        Store store = Store.getInstance();
        store.addItem("0070", "Тапочки м. 42 р.", 1002, 5);
        store.addItem("0002", "Туфли ж. 39 р.", 1500, 1);
        store.addItem("0098", "Майка м. XL", 1353, 10);
        store.addItem("0013", "Рюкзак 60L", 5050, 3);
        store.addItem("0004", "Рюкзак 500L", 4055, 8);
        store.addItem("0105", "Ручка шариковая Parker черная", 500, 100);
        store.addItem("0105", "Ручка шариковая Big синия", 500, 100);


        //Инициализация меню
        Menu menu = new Menu(store);
        boolean flagOfEnding = true; //Флаг для выхода из программы

        menu.printMenu(menu.currentLvlMenu);

        while (flagOfEnding) {
            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                flagOfEnding = menu.currentLvlMenu.getMenuItem(input).getCommand().execute();
            } catch (InputMismatchException exc) {
                System.out.println(WRONGALARM);
                menu.printMenu(menu.currentLvlMenu);
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(WRONGALARM);
                menu.printMenu(menu.currentLvlMenu);
            }
        }
        System.out.println(ENDSTRING);
    }
}