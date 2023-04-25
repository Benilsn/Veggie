package br.com.veggierecipes.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<String> transformToList(List<String> listToMap) {

        String[] lines = listToMap.get(0).split("\n");
        List<String> mappedList = Arrays.asList(lines).stream()
                .map(i -> i + ";")
                .collect(Collectors.toList());

        return mappedList;
    }

}
