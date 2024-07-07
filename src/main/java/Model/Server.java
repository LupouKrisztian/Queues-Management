package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod = new AtomicInteger();
    private boolean isActive = true;

    public Server(int maxNOfTasks) {
        tasks = new ArrayBlockingQueue<>(maxNOfTasks);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
        while (isActive) {

            Task currentTask = tasks.peek();

            if (currentTask != null) {
                try {
                    int currentProcessingTime = currentTask.getServiceTime();
                    Thread.sleep(currentProcessingTime * 1000);
                    tasks.poll();
                    waitingPeriod.addAndGet(-currentProcessingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                isActive = false;
            }
        }
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "closed";
        } else {
            StringBuilder result = new StringBuilder();
            for (Task task : tasks) {
                result.append(task.toString()).append("; ");
            }
            return result.toString();
        }
    }
}