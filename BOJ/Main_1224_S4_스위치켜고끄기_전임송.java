import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_1224_S4_스위치켜고끄기_전임송 {
	static BufferedWriter bw;
	static BufferedReader br;
	
	static final int MALE = 1;
	static final int FEMALE = 2;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] switches = new int[n + 1];
		String[] input = br.readLine().split(" ");
		for (int i = 1; i <= n; i++) {
			switches[i] = Integer.parseInt(input[i - 1]);
		}
		
		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int gender = Integer.parseInt(input[0]);
			int number = Integer.parseInt(input[1]);
			
			if (gender == MALE) {
				maleSwitch(switches, number);
				continue;
			}
			femaleSwitch(switches, number);
		}
		
		for (int i = 1; i <= n; i++) {
			bw.append(Integer.toString(switches[i])).append(' ');
			if (i % 20 == 0) {
				bw.append('\n');
				continue;
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void maleSwitch(int[] switches, int number) {
		int length = switches.length - 1;
		int multiple = number;
		while (multiple <= length) {
			switches[multiple] = 1 - switches[multiple];
			multiple += number;
		}
	}
	
	public static void femaleSwitch(int[] switches, int number) throws IOException{
		int length = switches.length - 1;
		switches[number] = 1 - switches[number];
		int i = 1;
		while (number - i >= 1 && number + i <= length) {
			if (switches[number - i] == switches[number + i]) {
				switches[number - i] = 1 - switches[number - i];
				switches[number + i] = 1 - switches[number + i];
				i++;
				continue;
			}
			break;
		}
	}
}