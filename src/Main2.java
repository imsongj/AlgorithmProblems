import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//1669 멍멍이 쓰다듬기
/*
 *  
 */
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Math.sqrt(21));
        int monkeyHeight = sc.nextInt();
        int dogHeight = sc.nextInt();
        int diff = Math.abs(monkeyHeight - dogHeight);

        if (diff == 0) {
            System.out.println(0);
        } else {
        	int answer = 0;
        	
            if (Math.sqrt(diff)% 1 == 0.0) {
            	answer = (int)Math.sqrt(diff) * 2 - 1;
            } else if ((Math.sqrt(diff) * 10) % 10 > 5.0) {
            	answer = (int)Math.sqrt(diff) * 2 + 1;
            } else if ((Math.sqrt(diff) * 10) % 10 <= 5.0) {
            	answer = (int)Math.sqrt(diff) * 2;
            }
            System.out.println(answer);
        }
        sc.close();
    }
}
