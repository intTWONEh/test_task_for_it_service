package org.its.test.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MagicSquare implements Task{
    @Override
    public String getResult(final String conditions) {
        int[] input;

        try {
            input = Arrays.stream(conditions.split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return "Input Error.";
        }

        int[] elements = input.clone();
        int sizeArr = elements.length;
        int lengthRC = (int) Math.sqrt(sizeArr);

        if (!((double) lengthRC == sizeArr / (double) lengthRC)) {
            return "Invalid number of items. Expected n^2 elements, where n is the length of the rows and columns.";
        }

        int[] indexes = new int[sizeArr];

        for (int i = 0; i < sizeArr; i++) {
            indexes[i] = 0;
        }

        int[][] mas = new int[lengthRC][];
        Answer result = new Answer();

        findMagicSquareCoastUnique(elements, result, mas, input);

        int i = 0;
        while (i < sizeArr) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                findMagicSquareCoastUnique(elements, result, mas, input);

                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }

        return result.getAnswer();
    }

    @Override
    public boolean checkConditions(String conditions) {
        int[] input;

        try {
            input = Arrays.stream(conditions.split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return true;
        }

        return false;
    }


    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static void getMas(int[] input, int[][] mas) {
        for (int i = 0; i < mas.length; i++)
            mas[i] = Arrays.copyOfRange(input, i * input.length / mas.length, (i + 1) * input.length / mas.length);
    }

    private static int getSumLine(int[][] mas) {
        int nextSum = 0;
        int oldSum = 0;
        boolean firstStep = true;

        for(int[] line: mas) {
            nextSum = Arrays.stream(line).sum();

            if(firstStep) {
                oldSum = nextSum;
                nextSum = 0;
                firstStep = false;
                continue;
            }

            if(nextSum != oldSum) {
                nextSum = -1;
                return nextSum;
            }
        }

        return nextSum;
    }

    private static void transposition(int[][] mas) {
        for (int i = 0; i < mas.length; i++) {
            for (int j = i + 1; j < mas.length; j++) {
                int temp = mas[i][j];
                mas[i][j] = mas[j][i];
                mas[j][i] = temp;
            }
        }
    }

    private static int getCoast(int[] input, int[] elements) {
        int sum = 0;
        for(int i = 0; i < input.length; ++i) {
            sum += Math.abs(input[i] - elements[i]);
        }

        return sum;
    }

    private static void findMagicSquareCoastUnique(int[] elements, Answer result, int[][] mas, int[] input) {
        int sumRows;
        int sumColumns;
        int coast;

        getMas(elements, mas);
        sumRows = getSumLine(mas);

        if(sumRows != -1) {
            transposition(mas);
            sumColumns = getSumLine(mas);
            transposition(mas);

            if(sumRows == sumColumns) {
                coast = getCoast(input, elements);
                if(coast < result.coast) {
                    result.coast = coast;
                    result.magicSquare = Arrays.toString(elements);
                }
            }
        }
    }

    private static class Answer {
        int coast = Integer.MAX_VALUE;
        String magicSquare = "No result";

        String getAnswer() {
            if(coast == Integer.MAX_VALUE) return magicSquare;
            return magicSquare + " Coast: " + coast;
        }
    }
}