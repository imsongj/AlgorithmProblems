import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0;
		int weightSum = 0;
		Queue<Integer> bridge = new LinkedList<>();
		for (int i = 0; i < truck_weights.length; i++) {
			while (true) {
				if (bridge.isEmpty()) {
					bridge.add(truck_weights[i]);
					weightSum += truck_weights[i];
					time++;
					break;
				}
				if (bridge.size() < bridge_length) {
					if (weightSum + truck_weights[i] <= weight) {
						bridge.add(truck_weights[i]);
						weightSum += truck_weights[i];
						time++;
						break;
					}
					bridge.add(0);
					time++;
					continue;
				}
				if (bridge.size() == bridge_length) {
					weightSum -= bridge.poll();
				}
			}
		}
		return time + bridge_length;
	}
}