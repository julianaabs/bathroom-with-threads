
import monitorVersion.Bathroom;
import monitorVersion.Monitor;

public class MainMonitor {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("./main <BATHROOM_CAPACITY>");
		}

		int bathroomCapacity = Integer.parseInt(args[0]);
		Bathroom b = new Bathroom(bathroomCapacity, 'F');
		Monitor m = new Monitor(b);
		m.start();
		
		while(true) {
			continue;
		}

	}
}
