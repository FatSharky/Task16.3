package by.training.task16.synchronizer.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;


public class ExampleThread implements Runnable {
    private Resource resource;

    public ExampleThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            resource.operation1();
            resource.operation2();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
