package serialization_and_proxy;

import java.lang.reflect.Proxy;

/*
Кеширующий прокси

Есть интерфейс Service c методом doHardWork(). Повторный вызов этого метода с теми же
параметрами должен возвращать рассчитанный результат из кэша.

double r1 = service.doHardWork("work1", 10); //считает результат
double r2 = service.doHardWork("work2", 5);  //считает результат

1.	Указывать с помощью аннотаций, какие методы кешировать и как:
Просчитанный результат хранить в памяти JVM или сериализовывать в файле на диск.
2.	Возможность указывать, какие аргументы метода учитывать при определении уникальности результата,
а какие игнорировать(по умолчанию все аргументы учитываются). Например, должна быть возможность указать,
что doHardWork() должен игнорировать значение второго аргумента, уникальность определяется только по String аргументу.
3.	Если возвращаемый тип это List – возможность указывать максимальное количество элементов в нем.
То есть, если нам возвращается List с size = 1млн, мы можем сказать что в кеше достаточно хранить 100т элементов.

 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Создаем оригинальный объект
        ServiceImpl u1 = new ServiceImpl();

        /*
        Для создания прокси нам нужен ClassLoader (загрузчик классов) оригинального объекта и
        список всех интерфейсов, которые реализует наш оригинальный класс
        */
        //Получаем загрузчик класса у оригинального объекта
        ClassLoader u1_ClassLoader = u1.getClass().getClassLoader();

        //Получаем все интерфейсы, которые реализует оригинальный объект
        Class[] u1_interfaces = u1.getClass().getInterfaces();

        Service proxyU1 = (Service) Proxy.newProxyInstance(u1_ClassLoader,
                u1_interfaces,
                new ServiceInvocationHandler(u1));

        System.out.println("Результат вычислений: " + proxyU1.hardWork("User1", 2));
        System.out.println("-------------------");
        System.out.println("Результат вычислений: " + proxyU1.hardWork("User2", 3));
        System.out.println("-------------------");
        System.out.println("Результат вычислений: " + proxyU1.hardWork("User1", 4));
        System.out.println("-------------------");
        System.out.println("Результат вычислений: " + proxyU1.hardWork("User1", 2));






    }
}
