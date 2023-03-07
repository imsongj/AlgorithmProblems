import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1541 잃어버린 괄호
//1. 마이너스 뒤에 괄호 추가 가능
// 2. 오른쪽으로 이동하면서 마이너스 다음 최고로 증가할때까지 괄호
public class Main_1541_S2_잃어버린괄호_전임송 {
	static final char MINUS = '-';
	static final char PLUS = '+';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int lineLength = line.length();
		//10 - 20 - 30
		int totalSum = 0;
		int partialSum = 0;
		int number = 0;
		int digits = 1;
		for (int i = lineLength - 1; i >= 0; i--) {
			char character = line.charAt(i);
			if (character >= '0' && character <= '9') { //숫자면
				if (character == '0') { //0인 경우
					digits *= 10;
					continue;
				}
				partialSum += (character - '0') * digits;
				digits *= 10;
			}
			if (character == PLUS) {
				number = 0;
				digits = 1;
			}
			if (character == MINUS) {
				partialSum += number;
				totalSum -= partialSum;
				partialSum = 0;
				number = 0;
				digits = 1;
			}
			
		}
		totalSum += partialSum; //마지막 숫자 더하기
		System.out.println(totalSum);
	}
}
