package algorithm.boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main_2023_G5_신기한소수_전임송 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	
	public static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void makePrime(int count, int primeNumber) throws IOException {
		if (count == n) {
			if (isPrime(primeNumber)) {
				bw.append(Integer.toString(primeNumber)).append('\n');
			}
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (i != 2 && i % 2 == 0) {
				continue; 
			}
			int newPrimeNumber = primeNumber * 10 + i;
			if (isPrime(newPrimeNumber)) {
				makePrime(count + 1, newPrimeNumber);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		makePrime(0, 0);
		bw.flush();
		br.close();
		bw.close();
	}	
}