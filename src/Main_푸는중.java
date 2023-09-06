import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;

public class Main_푸는중 {
    /*
    1202 보석도둑
 	최대한 작은 가방부터 가장 가벼운 보석을 넣는다

   	*/
	public static class Jewel implements Comparable<Jewel> {
		int weight;
		int price;

		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Jewel j) {
			if (this.weight == j.weight) {
				return Integer.compare(this.price, j.price);
			}
			return Integer.compare(this.weight, j.weight);
		}

		@Override
		public String toString() {
			return "Jewel{" +
				"weight=" + weight +
				", price=" + price +
				'}';
		}
	}

	public static Jewel[] jewels;
	public static int[] capacity;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

		jewels = new Jewel[N];
		capacity = new int[K];
		Jewel[] inBag = new Jewel[K];

		PriorityQueue<Jewel> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int w = Integer.parseInt(input[0]);
			int p = Integer.parseInt(input[1]);
			pq.add(new Jewel(w, p));
		}
		for (int i = 0; i < K; i++) {
			int c = Integer.parseInt(br.readLine());
			capacity[i] = c;
		}
		Arrays.sort(capacity);

		int bagIndex = 0;
		Jewel current;
		for (int i = 0; i < K; i++) {
			while (!pq.isEmpty()) {
				current = pq.poll(); //최소 무게부터 가방 전체 조회?
				//보석 들어갈수 있으면 넣고 다시 current 뽑기
				//최소 가방 크기보다 큰 보석은 QUE에 넣지 않음
				if (current.weight <= capacity[i]) {
					if (inBag[i].price <= current.price) {
						pq.add(inBag[i]);
						inBag[i] = current;
					}
				}
			}
		}


		
		
		//무게 작은 가방부터
			//queue에서 poll
				//만약 보석이 가방무게보다 크면 다시 큐에 넣고 다음 가방으로
				//만약 보석이 가방 무게랑 같거나 작으면 가방에 넣기, 원래 가방에 있던 보석 다시 큐에 넣기;
        System.out.println(Arrays.toString(jewels));
    }
}