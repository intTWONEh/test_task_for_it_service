package org.its.test.model;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StringCrossings implements Task{
    @Override
    public String getResult(final String conditions) {
        String[] a, a1, a2;

        try {
            a = conditions.split(";");
            a1 = a[0].split(",");
            a2 = a[1].split(",");
        } catch (Exception e) {
            return "Input Error.";
        }

        Set<String> result = new LinkedHashSet<>();

        for(String strArr1 : a1) {
            for(String strArr2 : a2) {
                if(strArr2.contains(strArr1)) {
                    result.add(strArr1);
                    break;
                }
            }
        }

        return result.stream().map(Object::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public boolean checkConditions(String conditions) {
        String[] a, a1, a2;
        try {
            a = conditions.split(";");
            a1 = a[0].split(",");
            a2 = a[1].split(",");
        } catch (Exception e) {
            return true;
        }

        return false;
    }
}