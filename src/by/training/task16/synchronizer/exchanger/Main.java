package by.training.task16.synchronizer.exchanger;


public class Main {
    public static void main(String[] args){
        Resource resource = new Resource();

        ExampleThread exampleThread1 = new ExampleThread(resource);
        ExampleThread exampleThread2 = new ExampleThread(resource);

        new Thread(exampleThread1).start();
        new Thread(exampleThread2).start();

        try {
            Thread.sleep(10000);
            exampleThread1.stopRunning();
            exampleThread2.stopRunning();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
