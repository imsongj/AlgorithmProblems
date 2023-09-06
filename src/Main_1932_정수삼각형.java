import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1932_정수삼각형 {
	/*
	 * 1932 정수 삼각형
	 * DP
	 * 위 인덱스는 아래 인덱스 i, i - 1()
	 *       1
	 *      1 2
	 *     1 2 3
	 *    1 2 3 4 
	 *   1 2 3 4 5
	 *   
	 *   0 1
	 *   0 1 2
	 *   0 1 2 3
	 *   0 1 2 3 4
	 *   0 1 2 3 4 5 
	 *   
	*/
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input;
        int[][] triangle = new int[N][N + 1];
        int[][] result = new int[N][N + 1];
		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < i; j++) {
				int number = Integer.parseInt(input[j]);
				triangle[i - 1][j + 1] = number;
				result[i - 1][j + 1] = number;
			}
			//System.out.println(Arrays.toString(triangle[i - 1]));
		}
		for (int i = N - 1; i > 0; i--) {
			for (int j = 1; j <= N; j++) {
				triangle[i][j] = result[i][j];
			}
			for (int j = 1; j <= N; j++) {
				result[i - 1][j] = triangle[i][j] + triangle[i - 1][j];
				int sum = triangle[i][j] + triangle[i - 1][j - 1];
				if (sum > result[i - 1][j - 1]) {
					result[i - 1][j - 1] = sum;
				}
			}
			
		}
		System.out.println(result[0][1]);
    }
}