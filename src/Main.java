import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("Введите строку:");
        System.out.println(calc(input.nextLine()));
        input.close();

    }

    public static String calc(String input) throws Exception {

        String [] inputString =  input.split(" ");

        if (inputString.length != 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор, разделенные одним пробелом");
        }
        //делаем проверку на тип числа
        boolean isNumber1 = true;
        boolean isNumber2 = true;

        for (int i=0;i<inputString[0].length() && isNumber1;i++)
        {
            isNumber1 = Character.isDigit(inputString[0].charAt(i));    //нецелые и отрицательные числа не пройдут проверку
        }
        for (int i=0;i<inputString[2].length() && isNumber2;i++)
        {
            isNumber2 = Character.isDigit(inputString[2].charAt(i));    //нецелые и отрицательные числа не пройдут проверку
        }

        if (isNumber1 && isNumber2) {
            //работаем с арабскими числами
            int number1 = Integer.parseInt(inputString[0]); //числа больше 2147483647 сгенерируют NumberFormatException
            int number2 = Integer.parseInt(inputString[2]); //числа больше 2147483647 сгенерируют NumberFormatException
            if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10){
                throw new Exception("допустимы числа от 1 до 10");
            }
            switch (inputString[1]) {
                case "+":
                    return Integer.toString(number1 + number2);
                case "-":
                    return Integer.toString(number1 - number2);
                case "*":
                    return Integer.toString(number1 * number2);
                case "/":
                    return Integer.toString(number1 / number2);
                default:
                    throw new Exception("недопустимая арифметическая операция");
            }
        } else if (!isNumber1 && !isNumber2) {
            //работаем с римскими числами
            int number1 = RomanNumberInput.valueOf(inputString[0]).ordinal() + 1; //если римское число больше 10, то сгенерируется IllegalArgumentException
            int number2 = RomanNumberInput.valueOf(inputString[2]).ordinal() + 1; //если римское число больше 10, то сгенерируется IllegalArgumentException
            switch (inputString[1]) {
                //если результат операции меньше 1, то сгенерируется ArrayIndexOutOfBoundsException
                case "+":
                    return RomanNumberOutput.values()[number1 + number2 - 1].toString();
                case "-":
                    return RomanNumberOutput.values()[number1 - number2 - 1].toString();
                case "*":
                    return RomanNumberOutput.values()[number1 * number2 - 1].toString();
                case "/":
                    return RomanNumberOutput.values()[number1 / number2 - 1].toString();
                default:
                    throw new Exception("недопустимая арифметическая операция");
            }
        }
        else {
            throw new Exception("используются одновременно разные системы счисления / используются нецелые или отрицательные числа");
        }
    }

    enum RomanNumberInput {
        I, II, III, IV, V, VI, VII, VIII, IX, X
    }

    enum RomanNumberOutput {
        I, II, III, IV, V, VI, VII, VIII, IX, X,
        XI, XII, XIII, XIV, XV, XVI, XVII, XVIII, XIX, XX,
        XXI, XXII, XXIII, XXIV, XXV, XXVI, XXVII, XXVIII, XXIX, XXX,
        XXXI, XXXII, XXXIII, XXXIV, XXXV, XXXVI, XXXVII, XXXVIII, XXXIX, XL,
        XLI, XLII, XLIII, XLIV, XLV, XLVI, XLVII, XLVIII, XLIX, L,
        LI, LII, LIII, LIV, LV, LVI, LVII, LVIII, LIX, LX,
        LXI, LXII, LXIII, LXIV, LXV, LXVI, LXVII, LXVIII, LXIX, LXX,
        LXXI, LXXII, LXXIII, LXXIV, LXXV, LXXVI, LXXVII, LXXVIII, LXXIX, LXXX,
        LXXXI, LXXXII, LXXXIII, LXXXIV, LXXXV, LXXXVI, LXXXVII, LXXXVIII, LXXXIX, XC,
        XCI, XCII, XCIII, XCIV, XCV, XCVI, XCVII, XCVIII, XCIX, C
    }
}