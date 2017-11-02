
import monitor.Bathroom;
import monitor.Monitor;

public class Main {

	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("./main <BATHROOM_CAPACITY>");
		}
		int bathroomCapacity = Integer.parseInt(args[1]);
		Bathroom b = new Bathroom(bathroomCapacity, 'F');
		Monitor m = new Monitor(b);
		m.start();
		
		while(true) {
			continue;
		}
	}

}
