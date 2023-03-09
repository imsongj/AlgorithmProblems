import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//9252 LCS2
//2차원배열 
//모든 알파벳 비교
// 1. 같으면 그 전

public class Main {	
	static long K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Long.parseLong(br.readLine());
		System.out.println(getNumber(K));
	}
	public static long getNumber(long length) {
		if (length == 1) { //1번째 숫자
			return 0;
		}
		if (length == 2) { //2번째 숫자
			return 1;
		}
		long log = log2(length);
		long power = (long) Math.pow(2, log); //주어진 수랑 최대한 가까운 2의 거듭제곱 값 찾기
		if (power == length) { //2의 거듭제곱
			if (log % 2 == 1) {
				return 1;
			}
			return 0;
		}
		long number = getNumber(length - power); //주어진 수 - 2의 거듭제곱 값 재귀; 앞 반의 몇번째 숫자인지
		if (number == 0) { //숫자 변환
			return 1;
		}
		if (number == 1) {
			return 0;
		}
		return number;
	}
	
	public static long log2(long N)
    {
        long result = (long)(Math.log(N) / Math.log(2));
 
        return result;
    }
}