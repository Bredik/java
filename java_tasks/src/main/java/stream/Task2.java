package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
1. Посчитать стримами среднюю ЗП
2. Вывести троих самых старших сотрудников
 */

public class Task2 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Маша", 32, 55000));
        list.add(new Person("Катя", 39, 28000));
        list.add(new Person("Вова", 28, 135000));
        list.add(new Person("Ваня", 25, 38600));
        list.add(new Person("Саша", 48, 75000));

        // 1.
        double avrS = 0;
        if (!list.isEmpty()) {
            // делаем стрим потоком даблов, по salary и вычисляем среднее
            avrS = list.stream().mapToDouble(el -> el.getSalary()).average().getAsDouble();
            System.out.println(avrS);
        } else System.out.println("Список пустой");

        // 2.
        System.out.println(list.stream().sorted( ((o1, o2) -> o2.getAge() - o1.getAge()))
                .limit(3)
                .map(el -> el.getName())
                .collect(Collectors.joining(", " , "3 самых старших сотрудника: ", ".")));

    }
}

class Person {
    String name;
    int age;
    double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
