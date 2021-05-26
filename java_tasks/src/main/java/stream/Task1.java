package stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Узнать какое слово встречается чаще всего
 */

public class Task1 {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("мама");
        words.add("мыла");
        words.add("раму");
        words.add("мама");
        words.add("мыла");
        words.add("крокодила");
        words.add("мама");
        words.add("подметала");
        words.add("пол");
        words.add("котик");

        //Вариант 1
        Map<String, Long> wordToCountMap = words
                .stream()
                // Делаем сборку, группируем по уникальным элементам и считаем их, в итоге получается мапа
                // Map<String, Long>
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String word = wordToCountMap.entrySet().stream()
                // Ищем максимум по значению и получаем ключ
                .max(Map.Entry.comparingByValue()).get().getKey();

        Long count = wordToCountMap.entrySet().stream()
                // Ищем максимум по значению и получаем значение
                .max(Map.Entry.comparingByValue()).get().getValue();

        System.out.println(word + " - " + count);

        //Вариант 2, почти такой же
        String res = words
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                // Вызываем компаратор, где сравниваем лонги у значения мапы
                .entrySet().stream().max(Comparator.comparingLong(el -> el.getValue()))
                // Далее, когда нашли максимальное, получаем ключ, ключ и есть наше слово
                .get().getKey();
    }
}
