import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main_2467_용액 {
    /*
    2467 용액
	양 끝에서 포인터 시작
	오른쪽 포인터를 0과 가까워지지 않을 때 까지 이동
	0 보다 작으면 왼쪽 포인터를 이동
	-1 0 1 2 3
   	*/
	public static final int MAX = 1_000_001;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
        	int number = Integer.parseInt(input[i]);
        	score[i] = number;
        }
        
        int right = N - 1;
        int left = 0;
        
        int minRight = score[right];
        int minLeft = score[left];
        int minScore = Math.abs(minRight + minLeft);
        
        while (left < right) {
        	int newScore = score[right] + score[left];
        	
        	if (Math.abs(newScore) <= minScore) {
        		minRight = score[right];
        		minLeft = score[left];
        		minScore = Math.abs(newScore);
        	}
        	if (newScore < 0) {
        		left++;
        		continue;
        	}
        	right--;
        }
        System.out.println(minLeft + " " + minRight );
    }
}