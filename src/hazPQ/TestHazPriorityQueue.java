package hazPQ;

public class TestHazPriorityQueue {
	public static void main(String[] args) {
//		HazPriorityQueue<Integer> pq = new HazPriorityQueue<Integer>();
		
//		pq.offer(15);
//		pq.offer(25);
//		pq.offer(10);
//		pq.offer(32);
//		pq.offer(51);
//		pq.offer(85);
//		pq.offer(92);
//		pq.offer(19);
//		
//		System.out.println(pq);
		
		
		
		
		HazPriorityQueue<String> pq = new HazPriorityQueue<String>();
		pq.offer("haz");
		pq.offer("kass");
		pq.offer("bb");
		pq.offer("james");
		pq.offer("bond");
		pq.offer("hello");
		pq.offer("world");
		
		System.out.println(pq);
		
		System.out.println(pq.peek());
		System.out.println(pq.isEmpty());
		System.out.println(pq.poll());
	}
}
