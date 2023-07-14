import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


public class Toss {
	public static void main(String[] args) {
		Toss toss = new Toss();
	}
	
	
	class Solution6 {
	    public int solution(int[] prices, int k) {
	        int answer = 0;
	        int profit = 0;
	        int base = prices[0];
	        for (int i = 0; i < k; i++) {
	        	profit += base - prices[1 + i];
	        }
	        answer = profit;
	        
	        for (int i = 1; i < prices.length - k; i++) {
	        	int diff = prices[i - 1] - prices[i];
	        	profit += diff * (k - 1);
	        	profit += prices[i + k];
	        	
	        	if (answer < profit) {
	        		answer = profit;
	        	}
	        }
	        return answer;
	    }
	}
	
	
	
	
	class Solution7 {
		public int solution(int[] schedules) {
	        int answer = 0;
	        int prev = schedules[0];
	        int max = 0;
	        for (int i = 1; i < schedules.length; i++) {
	            int current = schedules[i] + max;
	            
	            if (answer < current) {
	                answer = current;
	            }

	            if (max < prev) {
	                max = prev;
	            }
	            prev = current;
	        }
	        return answer;
	    }	}
	
	
	
	
	
	
	
	
	
	
	
	class Solution3 {
	    public String[] solution(String[] merchantNames) {
	        String[] answer = {};
	        answer = new String[merchantNames.length];
	        //이름 저장
	        //
	        /* 이름에서 공백, 특수문자 제외
	         * 이후 이름 이 길면
	         * 	-그 이름에 이전 이름이 포함되는지 체크
	         * 		-포함되면 이름 업데이트
	         */
	        int i = 0;
	        List<String> original = new ArrayList<String>(10);
	        List<String> names = new ArrayList<String>(10);
	        List<Boolean> hasPunc = new ArrayList<>(10);
	        for (String str : merchantNames) {
	        	boolean punc = containsPunc(str);
	            String newStr = str.replaceAll("\\s", "");
	            if (punc) {
	            	newStr = removePunc(newStr);
	            }
	            if (names.size() == 0) {
	            	original.add(str);
	            	names.add(newStr);
	            	hasPunc.add(punc);
	            	continue;
	            }
	            boolean exists = false;
	            for (int j = 0; j < names.size(); j++) {
	            	if (names.get(j).length() < newStr.length()) { //새로운 이름 길이가 길 경우
	            		if (newStr.contains(names.get(j))) {
	            			original.set(j, str);
	    	            	names.set(j, newStr);
	    	            	exists = true;
	    	            	continue;
	            		}
	            	}
	            	if (names.get(j).length() > newStr.length()) {
	            		if (names.get(j).contains(newStr)) {
	            			exists = true;
	    	            	continue;
	            		}
	            	}
	            	if (names.get(j).length() == newStr.length()) {
	            		if (names.get(j).equals(newStr)) {
	            			if (!hasPunc.get(j) && punc) {
	            				original.set(j, str);
	            				hasPunc.set(j, true);
	            			}
	            			exists = true;
	            			continue;
	            		}
	            	}
	            }
	            if (!exists) {
	            	original.add(str);
	            	names.add(newStr);
	            	hasPunc.add(punc);
	            }
	        }
	        Set<String> set = new HashSet<>(original);
	        answer = new String[set.size()];
	        int j = 0;
	        for (String str : set) {
	        	answer[j] = str;
	        	j++;
	        }
	        return answer;
	    }
	    
	    public boolean containsPunc(String str) {
	    	for (int i = 0; i < str.length(); i++) {
	    		char ch = str.charAt(i);
	    		if (ch == '&' || 
	    			ch == '(' ||
	    			ch == ')' ||
	    			ch == '.' ||
	    			ch == ',' ||
	    			ch == '-') {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    public String removePunc(String str) {
	    	StringBuilder sb = new StringBuilder();
	    	for (int i = 0; i < str.length(); i++) {
	    		char ch = str.charAt(i);
	    		if (ch == '&' || 
		    			ch == '(' ||
		    			ch == ')' ||
		    			ch == '.' ||
		    			ch == ',' ||
		    			ch == '-') {
		    			continue;
		    		}
	    		sb.append(ch);
	    	}
	    	return sb.toString();
	    }
	}
	
	
	
	
	class Solution1 {
	    public int solution(String s, int N) {
	        int answer = -1;
	        //중복되면 종료
	        //n보다 크면 종료
	        int maxIndex = (int) Math.pow(10, N - 1);
	        boolean[] check = new boolean[10];
	        outer:
	        for (int i = 0; i <= s.length() - N; i++) {
	        	int number = 0;
	        	int index = maxIndex;
	        	Arrays.fill(check, false);
	        	check[0] = true;
	        	for (int j = 0; j < N; j++) {
	        		int digit = s.charAt(i + j) - '0';
	        		if (digit > N) {
	        			continue outer;
	        		}
	        		if (check[digit]) {
	        			continue outer;
	        		}
	        		check[digit] = true;
	        		number += digit * index;
	        		index /= 10;
	        	}
	        	if (number > answer) {
	        		answer = number;
	        	}
	        }
	        return answer;
	    }
	}
	public int solution2(int[][] relationships, int target, int limit) {
        int answer = 0;
        int newFriends = 0;
        boolean[][] friend = new boolean[101][101];
        for (int i = 0; i < relationships.length; i++) {
        	friend[relationships[i][0]][relationships[i][1]] = true;
        	friend[relationships[i][1]][relationships[i][0]] = true;
        }
        int[] visited = new int[101];
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(target);
        visited[target] = 1;
        
        while (!queue.isEmpty()) {
        	int current = queue.poll();
        	int currentDepth = visited[current];
        	if (currentDepth > limit) {
        		continue;
        	}
        	for (int i = 1; i < 101; i++) {
        		if (!friend[current][i]) {
        			continue;
        		}
        		if (visited[i] > 0) {
        			continue;
        		}
        		int newDepth = currentDepth + 1;
        		visited[i] = newDepth;
        		queue.add(i);
        		if (newDepth <= 2) {
        			answer += 5;
        		}
        		if (newDepth > 2) {
        			answer += 10;
        			newFriends++;
        		}
        	}
        }
        return answer;
    }
}
