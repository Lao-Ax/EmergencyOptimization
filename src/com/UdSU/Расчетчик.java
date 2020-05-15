package com.UdSU;

import java.util.ArrayList;
import java.util.Set;

/**
 * Класс, представляющий алгоритм решения. Характеризуется наборами инвариантных и переменных мероприятий, итоговым
 * оптимизированным набором и переменным набором для счета и сравнения.
 */
public abstract class Расчетчик {
    private static ArrayList<Мероприятие> constants = new ArrayList<Мероприятие>(); // инвариантные мероприятия и средства
    private static ArrayList<Мероприятие> actions = new ArrayList<Мероприятие>(); // управляемые мероприятия и средства
    private static ArrayList<Организация.ActionAndCount> actionsOpt = new ArrayList<Организация.ActionAndCount>(); // итоговый набор

    private static void setConstants(Организация орг){
        Set<Мероприятие> temp = орг.getActionsAvailable();
        for (Мероприятие м : temp) {
            if (м.isConstant()) constants.add(м);
        }
    }

    private static void setActions(Организация орг){
        Set<Мероприятие> temp = орг.getActionsAvailable();
        for (Мероприятие м : temp) {
            if (!м.isConstant()) actions.add(м);
        }
    }

    public static ArrayList<Организация.ActionAndCount> getActionsOpt(Организация организация){
        F(организация);
        return actionsOpt;
    }

    private static int N;
    private static int W;
    private static double maxValue;                        // наилучшая успешность решения на текущем шаге
    private static int[] best, now;                        // наилучшее, текущее решения

    private static void F(Организация организация) {
        setActions(организация);                // устанавливаем управляемые мероприятия
        setConstants(организация);              // устанавилваем инвариантные мероприятия
        N = actions.size()-1;                   // устанавливаем кол-во управляемых мер.
        W = (int) Math.round(организация.getBudget());        // устанавливаем макс. стоимость.
        best = new int[actions.size()];
        now = new int[actions.size()];

        for (Мероприятие м : constants) {       // идем по инвариантным мерам,
            maxValue += м.getValue();           // увеличивая общую успешность и
            W -= м.getPrice();                  // уменьшая доступный бюджет, если они чего-то стоят.
            actionsOpt.add(new Организация.ActionAndCount(м, 1)); // заносим инвариантные меры в итоговый набор
        }

        solve(0, W, maxValue);

        for (int i = 0; i < best.length; i++) {
            if (best[i] >0 ) {
                actionsOpt.add(new Организация.ActionAndCount(actions.get(i), best[i]));
            }
        }
    }

    public static ArrayList<int[]> list = new ArrayList<int[]>();
    public static void printList() {
        if (list.size() == 0) return;
        System.out.println("\n"+ "Альтернативные наборы с такой же успешностью:");
        for (int[] k : list) {
            double d = 0.;
            for (int i = 0; i < k.length; i++) {
                if (k[i] > 0) {
                    d += actions.get(i).getValue()*k[i];
                }
            }
            for (Мероприятие m : constants) {
                d += m.getValue();
            }

            if (Double.compare(d, maxValue) == 0) {
                System.out.println();
                for (Мероприятие m : constants) {
                    System.out.println(m.getName() + " 1");
                }
                for (int i = 0; i < k.length; i++) {
                    if (k[i] > 0) {
                        System.out.println(actions.get(i).getName() + " " + k[i]);
                    }
                }
                System.out.println("Успешность набора = " + d);
            }
        }
    }

    private static void solve(int k, int w, double p){ // k - порядковый номер группы предметов; // w - деньги бюджета; // p - успешность текущего решения
        if ((k > N) && (Double.compare(p, maxValue) >= 0)) {
            if (Double.compare(p, maxValue) == 0) {
                for (int i : now) if (i > 0) {
                    list.add(now.clone());
                    break;
                }
            }
            else {
                list.clear();
                best = now.clone();
                maxValue = p;
            }

        } else if (k <= N) {
            for (int i = 0; i <= (int) (w / actions.get(k).getPrice()); i++) { // счетчик: сколько мер k-ого типа можно взять, до пустого бюждета
                now[k] = i;
                solve(k+1, w - i * (int) actions.get(k).getPrice(), p + i * actions.get(k).getValue());
            }
        }
    }
}
