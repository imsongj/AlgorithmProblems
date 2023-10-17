import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	/*
	2023 KAKAO BLIND RECRUITMENT
	개인정보 수집 유효기간

	 */
	public static void main(String[] args) throws IOException {
		Solution test = new Solution();
		Solution.Date date = test.new Date("2021.05.19");
		date.addMonth(100);
		System.out.println(date);
	}

}

class Solution {
	//달 + 3, 일 - 1,
	class Date {
		int year;
		int month;
		int day;

		public Date(String date) {
			String[] input = date.split("\\.");
			year = Integer.parseInt(input[0]);
			month = Integer.parseInt(input[1]);
			day = Integer.parseInt(input[2]);
		}
		public void addMonth(int count) {
			month += count;
			while (month > 12) {
				year++;
				month -= 12;
			}
			day--;
			if (day <= 0 ) {
				month--;
				day += 28;
			}
			if (month <= 0) {
				year--;
				month += 12;
			}
		}
		public boolean isTodayOver(Date today) {
			if (today.year > year) {
				return true;
			}
			if (today.year == year && today.month > month) {
				return true;
			}
			if (today.year == year && today.month == month && today.day > day) {
				return true;
			}
			return false;
		}
		@Override
		public String toString() {
			return String.format("%04d.%02d.%02d", year, month, day);
		}
	}
	Map<String, Integer> types = new HashMap<>();
	public int[] solution(String today, String[] terms, String[] privacies) {
		List<Integer> result = new ArrayList<>();
		int[] answer = {};
		for (String term : terms) {
			String[] input = term.split(" ");
			types.put(input[0], Integer.parseInt(input[1]));
		}
		Date todayDate = new Date(today);
		for (int i = 0; i < privacies.length; i++) {
			String[] input = privacies[i].split(" ");
			Date date = new Date(input[0]);
			date.addMonth(types.get(input[1]));
			if (date.isTodayOver(todayDate)) {
				result.add(i + 1);
			}
		}
		Collections.sort(result);
		answer = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		return answer;
	}
}
