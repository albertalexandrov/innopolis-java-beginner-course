package homeworks.homework10;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        filterOddNums(array);
        filterNumsWithOddSumOfDigits(array);
    }

    private static void filterOddNums(int[] array) {
        ByCondition predicate = num -> num % 2 == 0;
        applyPredicate(array, predicate);
    }

    private static void filterNumsWithOddSumOfDigits(int[] array) {
        ByCondition predicate = num -> String.valueOf(num).chars().map(Character::getNumericValue).sum() % 2 == 0;
        applyPredicate(array, predicate);
    }

    private static void applyPredicate(int[] array, ByCondition predicate) {
        int[] filtered = Sequence.filter(array, predicate);
        System.out.println(Arrays.toString(filtered));
    }

}
