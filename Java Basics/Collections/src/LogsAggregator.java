import java.util.Scanner;
import java.util.TreeMap;


public class LogsAggregator {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int logsCount = scanner.nextInt();
		scanner.nextLine();
		TreeMap<String, TreeMap<String, Integer>> logs = new TreeMap<>();
		for (int i = 0; i < logsCount; i++) {
			String[] values = scanner.nextLine().split(" ");
			String ip = values[0];
			String name = values[1];
			int duration = Integer.parseInt(values[2]);
			
			if (!logs.containsKey(name)) {
				logs.put(name, new TreeMap<>());
			}
			
			if (logs.get(name).containsKey(ip)) {
				duration += logs.get(name).get(ip);
			}
			
			logs.get(name).put(ip, duration);
		}
		
		for (String userName : logs.keySet()) {
			System.out.printf("%s: ", userName);
			
			int sumDuration = 0;
			for (int duration : logs.get(userName).values()) {
				sumDuration += duration;
			}
			System.out.printf("%d [", sumDuration);
			System.out.print(String.join(", ", logs.get(userName).keySet()));
			System.out.print("]\n");
		}
	}

}
