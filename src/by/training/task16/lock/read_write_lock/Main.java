package by.training.task16.lock.read_write_lock;

public class Main {
	public static void main(String[] args) {
		Resource resource = new Resource();

		ExampleThread readThread1 = new ExampleThread(resource, true);
		ExampleThread readThread2 = new ExampleThread(resource, true);
		ExampleThread readThread3 = new ExampleThread(resource, true);
		ExampleThread writeThread = new ExampleThread(resource, false);

		new Thread(readThread1).start();
		new Thread(readThread2).start();
		new Thread(readThread3).start();
		new Thread(writeThread).start();

		try {
			Thread.sleep(10000);
			readThread1.stopRunning();
			readThread2.stopRunning();
			readThread3.stopRunning();
			writeThread.stopRunning();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
