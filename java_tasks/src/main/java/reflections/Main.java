package reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/*
1. Вывести на консоль все методы класса, включая все родительские методы 	 (включая приватные)
2. Вывести все геттеры класса
3. Проверить что все String константы имеют значение = их имени public static final String MONDAY = "MONDAY";
 */

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        Class calc = Class.forName("reflections.CalculatorImpl");

        //1. Вывести на консоль все методы класса, включая все родительские методы (включая приватные)
        Method[] methods = calc.getMethods();
        for (Method m: methods) {
            System.out.println("Name of method = " + m.getName() +
                    ", return type = " + m.getReturnType() +
                    ", parametr types = " +
                    Arrays.toString(m.getParameterTypes()));
        }
        System.out.println("--------------");
        Method [] allMethods = calc.getDeclaredMethods();
        for (Method m: allMethods) {
            System.out.println("Name of method = " + m.getName() +
                    ", return type = " + m.getReturnType() +
                    ", parametr types = " +
                    Arrays.toString(m.getParameterTypes()));
        }

        //2. Вывести все геттеры класса
        System.out.println("--------------");
        Method [] setAndGet = calc.getDeclaredMethods();
        for (Method m: setAndGet) {
            if (m.getName().contains("set") || m.getName().contains("get")) {
                System.out.println("Name of method = " + m.getName() +
                        ", return type = " + m.getReturnType() +
                        ", parametr types = " +
                        Arrays.toString(m.getParameterTypes()));
            }
        }

        // 3. Проверить что все String константы имеют значение = их имени
        System.out.println("---------------------");
        Field[] fields = calc.getFields();
        for (Field f: fields) {
            if (Modifier.isFinal(f.getModifiers()) && (f.getType().getSimpleName().equals("String"))) {
                String value = (String) f.get(calc);
                if (f.getName().equals(value)) {
                    System.out.println("Константа " + f.getName()+ " совпадает с ее значением " + value);
                } else {
                    System.out.println("Константа " + f.getName() + " не совпадает с ее значением." +
                            "Ее значение " + value);
                }
            }
        }

    }
}
