package com.huwang;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n;
        String[] new_poker = new String[54];
        Poker poker = new Poker();
        poker.Creat_poker(new_poker);
        poker.Print_poker(new_poker);
        System.out.println("请选择洗牌方式：1.交叉洗牌 2.随机交叉洗牌 3.牌位置换 4.未更新");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        switch (n) {
            case 1:
                poker.Shuffle_poker(new_poker);
                break;
            case 2:
                poker.Shuffle_radom_poker(new_poker);
                break;
            case 3:
                poker.Exchange_poker(new_poker);
                break;
            default:
                System.out.println("你皮？");
                break;
        }
    }
}

class Poker {
    private String[] suit = new String[]{"♠", "♥", "♦", "♣"};
    private String[] rank = new String[]{"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
    private String[] joker = new String[]{"black joker", "red joker"};
    private Random random = new Random();

    void Creat_poker(String[] poker) {
        int k = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                poker[k] = suit[j] + rank[i];
                k++;
            }
        }
        for (int i = 0; i < 2; i++) {
            poker[k] = joker[i];
            k++;
        }
    }

    void Shuffle_poker(String[] poker) {
        String[] b = new String[54];
        for (int j = 0; j < 3; j++) {
            for (int i = 0, k = 0; i < 54; i += 2, k++) {
                b[i] = poker[k];
                b[i + 1] = poker[k + 27];
            }
            System.arraycopy(b, 0, poker, 0, 54);
        }
        Print_poker(poker);
    }

    void Shuffle_radom_poker(String[] poker) {
        String[] b = new String[54];
        for (int l = 0; l < 3; l++) {
            String[] front = Arrays.copyOfRange(poker, 0, 27);
            String[] back = Arrays.copyOfRange(poker, 27, 54);
            int i = 0, j = 0, k = 0;
            while (i < 27 && j < 27) {
                int n = random.nextInt(2);
                if (n == 0) {
                    b[k] = front[i];
                    k++;
                    i++;
                }
                if (n == 1) {
                    b[k] = back[j];
                    k++;
                    j++;
                }
                if (i - j > 3) {
                    b[k] = back[j];
                    k++;
                    j++;
                }
                if (j - i > 3) {
                    b[k] = front[i];
                    k++;
                    i++;
                }
            }
            if (i < 27) {
                for (; i < 27; i++, k++) {
                    b[k] = front[i];
                }
            }
            if (j < 27) {
                for (; j < 27; j++, k++) {
                    b[k] = back[j];
                }
            }
            for (i = 0; i < 54; i++) {
                poker[i] = b[i];
            }
        }
        Print_poker(poker);
    }

    void Exchange_poker(String[] poker) {
        String temp;
        int n;
        for (int i = 53; i > 0; i--) {
            temp = poker[i];
            n=random.nextInt(i);
            poker[i] = poker[n];
            poker[n] = temp;
        }
        Print_poker(poker);
    }

    void Print_poker(String[] poker) {
        int l = 0;
        for (int j = 0; j < 14; j++) {
            for (int i = 0; i < 4 && l < 54; i++) {
                System.out.print(poker[l] + " ");
                l++;
            }
            System.out.println();
        }
    }
}
