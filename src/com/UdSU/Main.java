package com.UdSU;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Мероприятие> меропрития = new HashSet<Мероприятие>();
        меропрития.add(new Мероприятие("Огнетушитель", 3, 8));
        меропрития.add(new Мероприятие("Вытяжка", 3, 8));
        меропрития.add(new Мероприятие("Пеносброс", 15.1, 43));
        меропрития.add(new Мероприятие("Штатный пожарник", 2, 30));
        меропрития.add(new Мероприятие("Пожарная сирена", 1, 10, true));

        Организация организация = new Организация("Гамбринус", 1000, меропрития);
        организация.setActionsOptimized(Расчетчик.getActionsOpt(организация));

        for (Организация.ActionAndCount ac : организация.getActionsOptimized()) {
            System.out.println(ac.мероприятие.getName() + " " + ac.количество);
        }
        System.out.println("Успешность набора = " + организация.getActionsOptimizedValue());

        Расчетчик.printList();
    }
}
