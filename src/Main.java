import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    1918 후위표기식
    우선순위
    (), , /, + , -, 왼쪽에서 오른쪽으로
    (a+bc) + d
    a+bc+d abc+d+
    a+bcd+e abcd+e+
    a+b+c ab+c+ if same priority, pop
                if lower priority, pop
                if higher pririty, add then pop
                if close bracket, pop
    ab+c abc+
    string ab
    q + *
    queue to save operations

    재귀 구현 필요, 괄호 처리 필요
    */
    public static final String A = "";
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String equation = br.readLine();
        System.out.println(getPost(equation));
    }
    
    public static String getPost(String original) {
    	StringBuilder sb = new StringBuilder();
        char prevOp = ' ';
        int index = 0;
        while (index < original.length() - 1) {
        	char current = original.charAt(index);
            String next = original.substring(index + 1, index + 2);
            int nextLength = 2;
            if (next.equals("(")) {
            	String insideBracket = original.substring(index + 2, findCloseBracket(original, index + 1));
            	next = getPost(insideBracket);
            	nextLength = insideBracket.length() + 2 + 1;
            }
            if (current == '(') {
            	String insideBracket = original.substring(index + 1, findCloseBracket(original, index));
            	String post = getPost(insideBracket);
            	nextLength = insideBracket.length() + 2;
            	sb.append(post);
            	index += nextLength;
            	continue;
            }
            if (current >= 'A' && current <= 'Z') {
                sb.append(current);
                index++;
                continue;
            }
            if (prevOp == ' ') {
                prevOp = current;
                sb.append(next);
                index += nextLength;
                continue;
            }
            if (prevOp == '-' || prevOp == '+') {
                if (current == '*' || current == '/') {
                    sb.append(next);
                    sb.append(current);
                    index += nextLength;
                    continue;
                }
                sb.append(prevOp);
                sb.append(next);
                prevOp = current;
                index += nextLength;
                continue;
            }
            sb.append(prevOp);
            sb.append(next);
            prevOp = current;
            index += nextLength;
        }
        sb.append(prevOp);

        return sb.toString();
    }
    
	public static int findCloseBracket(String target, int startIndex) {
		int count = 0;
		for (int i = startIndex; i < target.length(); i++) {
			if (target.charAt(i) == '(') {
				count++;
			}
			if (target.charAt(i) == ')') {
				count--;
				if (count == 0) {
					return i;
				}
			}
		}
		return 0;
	}
		
}