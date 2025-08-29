package org.javase;

public class LabelBreak {
    public static void main(String[] args) {

        outerLoop:
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 50; j++) {
                if(j % 2 == 1)
                    continue;
                if(j % 15 == 0)
                    break outerLoop;

                System.out.println(j);
            }
        }
    }
}
