import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	/*
	practice
	달리기경주
	 */
	public static void main(String[] args) throws IOException {
		Solution test = new Solution();

	}

}

class Solution {
	static Map<String, Integer> names = new HashMap<>();
	public String[] solution(String[] players, String[] callings) {
		String[] answer = {};

		int i = 0;
		for (String player : players) {
			names.put(player, i++);
		}
		for (String calling : callings) {
			swap(players, calling);
			System.out.println(Arrays.toString(players));
		}
		return players;
	}
	public void swap(String[] players, String player) {
		int index = names.get(player);
		String tmp = players[index - 1];
		players[index - 1] = players[index];
		players[index] = tmp;
		names.put(players[index], index);
		names.put(players[index - 1], index - 1);
	}
}
