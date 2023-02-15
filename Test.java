import java.util.*;

public class Test {
/*
Задача:
Найти валидные круглые скобки.
Обязательно счетчик и правильные скобки нужно выводить.

    Пример 1:
                   Ввод: "(()"
                   Вывод: 2 - ()
    Пример 2:
                   Ввод: ")()())"
                   Вывод: 4 - ()()
    Пример 3:
                   Ввод: ")(()())"
                   Вывод: 6 - (()())
    Пример 4:
                   Ввод: ")("
                   Вывод: 0

Ниже приведены частные случаи для тестирования, есть, конечно, вопросы ко времени, но благо Java позволяет.
Сделано 15.02 by @ollEWREYllo
*/

    public static void main(String[] args) {
        System.out.println(test("(()") + " " + test("(()").equals("2 - ()"));
        System.out.println(test("(())") + " " + test("(())").equals("4 - (())"));
        System.out.println(test(")()())") + " " + test(")()())").equals("4 - ()()"));
        System.out.println(test(")(()())") + " " + test(")(()())").equals("6 - (()())"));
        System.out.println(test(")(") + " " + test(")(").equals("0 "));
        System.out.println(test("()()()()") + " " + test("()()()()").equals("8 - ()()()()"));
        System.out.println(test("(((())))") + " " + test("(((())))").equals("8 - (((())))"));
    }
    static String test(String text){
        String brackets = text;
        int i = 0;
        StringBuilder builder = new StringBuilder();
        while (text.contains("()")) {
            text = text.replaceFirst("\\(\\)", "");
            i += 2;
            builder.append("()");
        }
        ArrayList<String> list = generateBrackets(i / 2);
        for (String x : list){
            if (brackets.contains(x)) {
                builder = new StringBuilder(x);
            }
        }
        return (i + ((!builder.isEmpty()) ?  " - " : " ") + builder.toString());
    }

    public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count) {
        if (leftRem < 0 || rightRem < leftRem) return;

        if (leftRem == 0 && rightRem == 0) {
            String s = String.copyValueOf(str);
            list.add(s);
        } else {
            if (leftRem > 0) {
                str[count] = '(';
                addParen(list, leftRem - 1, rightRem, str, count + 1);
            }

            if (rightRem > leftRem) {
                str[count] = ')';
                addParen(list, leftRem, rightRem - 1, str, count + 1);
            }
        }
    }
    public static ArrayList<String> generateBrackets(int count) {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

}
