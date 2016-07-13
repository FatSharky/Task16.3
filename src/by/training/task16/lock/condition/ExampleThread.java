package by.training.task16.lock.condition;


public class ExampleThread implements Runnable {
    private volatile boolean isRunning = true;

    private Resource resource;
    private int operationNumber;

    public ExampleThread(Resource resource, int operationNumber){
        this.resource = resource;
        this.operationNumber = operationNumber;
    }

    @Override
    public void run() {
        switch (operationNumber){
            case 1:
                while (isRunning){
                    resource.operation1();
                }
                break;
            case 2:
                while (isRunning){
                    resource.operation2();
                }
                break;
        }
    }

    public void stopRunning(){
        isRunning = false;
    }
}
