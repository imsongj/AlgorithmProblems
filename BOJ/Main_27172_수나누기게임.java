import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main_27172_수나누기게임 {
    /*
    27172 수 나누기 게임
	정렬
	작은 수부터 곱마다 점수 합
   	*/
	public static final int MAX = 1_000_001;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> numbers = new ArrayList<>();
        int[] score = new int[MAX];
        boolean[] exists = new boolean[MAX];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
        	int number = Integer.parseInt(input[i]);
        	numbers.add(number);
        	exists[number] = true;
        }
        Collections.sort(numbers);
        for (int number : numbers) {
        	int multiple = number * 2;
        	while (multiple < MAX) {
        		if (exists[multiple]) {
        			score[number] += 1;
        			score[multiple] -= 1;
        		}
        		multiple += number;
        	}
        }
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
        	sb.append(score[number]).append(" ");
        }
        System.out.println(sb);
    }
}