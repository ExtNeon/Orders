import exceptions.EmptyPayListException;
import exceptions.NegativeNumbersInPayListException;
import exceptions.PayListException;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Кирилл on 24.01.2018.
 */
public class Main {

    public static void main(String[] args) {
        try {
            int maxTime = getEnteredIntegerNumber("Введите время: ");
            System.out.println("Заполните массив сумм.");
            System.out.println("Полученная сумма: " + getAmountOfProfitFromOrders(maxTime, getEnteredIntegerArray()) +
                    " за " + maxTime + " времени.");
        } catch (NegativeNumbersInPayListException e) {
            System.err.println("Введены некорректные данные: список сумм оплаты содержит отрицательные числа.");
            System.exit(1);
        } catch (EmptyPayListException e) {
            System.err.println("Введены некорректные данные: список сумм оплаты пуст");
            System.exit(2);
        } catch (PayListException e) {
            e.printStackTrace();
        }
    }

    private static int getAmountOfProfitFromOrders(int maxTime, int[] payList) throws PayListException {
        if (payList.length == 0) {
            throw new EmptyPayListException();
        }
        int[] copyOfPayList = Arrays.copyOf(payList, payList.length);
        Arrays.sort(copyOfPayList);
        if (copyOfPayList[0] < 0) {
            throw new NegativeNumbersInPayListException();
        }
        int sum = 0;
        for (int i = copyOfPayList.length - 1; i >= 0 && --maxTime >= 0; i--) {
            sum += copyOfPayList[i];
        }
        return sum;
    }

    private static int[] getEnteredIntegerArray() {
        Scanner scanner = new Scanner(System.in);
        int arrayLength;
        do {
            System.out.print("Введите размер будущего массива: ");
            if ((arrayLength = scanner.nextInt()) <= 0) {
                System.err.println("Введён некорректный размер массива! Повторите ввод.");
            }
        } while (arrayLength <= 0);
        int[] createdArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            System.out.print("Введите значение элемента №" + i + ": ");
            createdArray[i] = scanner.nextInt();
        }
        System.out.println("Массив успешно заполнен.");
        return createdArray;
    }

    private static int getEnteredIntegerNumber(String message) {
        System.out.print(message);
        return new Scanner(System.in).nextInt();
    }

}
