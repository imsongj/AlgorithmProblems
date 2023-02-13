package algorithm.boj;

import java.util.Scanner;

public class Main_17478_S5_재귀함수가뭔가요_전임송 {
	static final String BACKGROUND = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	static final String QUESTION = "\"재귀함수가 뭔가요?\"";
	static final String STORY1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어."; 
	static final String STORY2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static final String STORY3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static final String ANSWER = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static final String ENDING = "라고 답변하였지.";
	static final String LINES = "____";
	
	static int numberOfRecursions;
	static void askQuestion( int count) {
		print(QUESTION, count);
		if (numberOfRecursions > count) {
			print(STORY1, count);
			print(STORY2, count);
			print(STORY3, count);
			askQuestion(count + 1);
		}
		if (numberOfRecursions == count) {
			print(ANSWER, count);
		}
		print(ENDING, count);
	}
	
	static void print(String line, int numberOfLines) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < numberOfLines; i++) {
			stringBuilder.append(LINES);
		}
		stringBuilder.append(line);
		System.out.println(stringBuilder.toString());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		numberOfRecursions = sc.nextInt();
		System.out.println(BACKGROUND);
		askQuestion(0);
		sc.close();
	}
}
