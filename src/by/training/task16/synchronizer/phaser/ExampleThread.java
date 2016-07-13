package by.training.task16.synchronizer.phaser;

public class ExampleThread implements Runnable {
	private volatile boolean isRunning = true;

	private Resource resource;

	public ExampleThread(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				resource.useResource();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRunning() {
		isRunning = false;
	}
}
