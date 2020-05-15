package com.UdSU;

import java.util.ArrayList;
import java.util.Set;

/**
 * Класс, представляющий собой компанию (орагнизацию), для которой осуществляется расчет. Она характеризуется названием,
 * доступным бюджетом, множеством доступных в ее распоряжении мероприятий и средств, а так же конкретным наилучшим набором
 * (определится после решения).
 */
public class Организация {
    private String name;
    private double budget;
    private Set<Мероприятие> actionsAvailable;
    private ArrayList<ActionAndCount> actionsOptimized;

    public Организация(String name, double budget, Set<Мероприятие> actionsAvailable) {
        this.name = name;
        this.budget = budget;
        this.actionsAvailable = actionsAvailable;
        this.actionsOptimized = new ArrayList<ActionAndCount>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Set<Мероприятие> getActionsAvailable() {
        return actionsAvailable;
    }

    public void setActionsAvailable(Set<Мероприятие> actionsAvailable) {
        this.actionsAvailable = actionsAvailable;
    }

    public ArrayList<ActionAndCount> getActionsOptimized() {
        return actionsOptimized;
    }

    public void setActionsOptimized(ArrayList<ActionAndCount> actionsOptimized) {
        this.actionsOptimized = actionsOptimized;
    }

    public double getActionsOptimizedValue(){
        double d = 0.;
        for (ActionAndCount ac : actionsOptimized) {
            d += ac.мероприятие.getValue()*ac.количество;
        }
        return d;
    }

    public static class ActionAndCount{
        Мероприятие мероприятие;
        int количество;

        public ActionAndCount(Мероприятие мероприятие, int количество) {
            this.мероприятие = мероприятие;
            this.количество = количество;
        }
    }
}
