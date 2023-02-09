import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Account implements Comparable<Account> {
	private int order;
	private int age;
	private String name;
	
	public Account(int order, int age, String name) {
		super();
		this.order = order;
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(Account o) {
		if (this.age > o.age) {
			return 1;
		}
		if (this.age == o.age && this.order > o.order) {
			return 1;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s", age, name);
	}
	
	
}
public class Main_10814 {
	static BufferedWriter bw;
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		Account[] accounts = new Account[n];
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int age = Integer.parseInt(input[0]);
			accounts[i] = new Account(i, age, input[1]);
		}
		Arrays.sort(accounts);
		
		for (Account account : accounts) {
			bw.append(account.toString()).append('\n');
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}
