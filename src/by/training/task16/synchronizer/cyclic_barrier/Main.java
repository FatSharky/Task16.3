package by.training.task16.synchronizer.cyclic_barrier;

public class Main {
	public static void main(String[] args) {
		Resource resource = new Resource();

		ExampleThread exampleThread1 = new ExampleThread(resource);
		ExampleThread exampleThread2 = new ExampleThread(resource);
		ExampleThread exampleThread3 = new ExampleThread(resource);
		ExampleThread exampleThread4 = new ExampleThread(resource);
		ExampleThread exampleThread5 = new ExampleThread(resource);

		new Thread(exampleThread1).start();
		new Thread(exampleThread2).start();
		new Thread(exampleThread3).start();
		new Thread(exampleThread4).start();
		new Thread(exampleThread5).start();
	}
}
