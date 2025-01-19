package org.example.utils;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertArray {
    public static <T> Object[][] convertTo2DArray(List<T> entities, Function<T, List<? extends Serializable>> mapper) {
        List<List<? extends Serializable>> result = entities.stream()
                .map(mapper)
                .collect(Collectors.toList());

        return result.stream()
                .map(l -> l.toArray(new Object[0]))
                .toArray(Object[][]::new);
    }
}
