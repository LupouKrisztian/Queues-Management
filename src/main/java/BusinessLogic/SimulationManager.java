package BusinessLogic;

import GUI.ViewRealTime;
import Model.Server;
import Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int numberOfServers;
    public int numberOfClients;
    public int minServiceTime;
    public int maxServiceTime;
    private int minArrivalTime;
    private int maxArrivalTime;

    private Scheduler scheduler;
    private ArrayList<Task> generatedTasks;
    private FileWriter fileWriter;

    private int totalWaitingTime;
    private int maxWaitingTime;
    private int peakHour;
    private int numberOfTasks;
    private int totalServiceTime;

    private ViewRealTime viewRealTime;

    public SimulationManager(int timeLimit, int numberOfServers, int numberOfClients, int minServiceTime, int maxServiceTime,
                             int minArrivalTime, int maxArrivalTime) throws IOException {
        this.timeLimit = timeLimit;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        this.fileWriter = new FileWriter("simulation_log.txt");
        this.generatedTasks = new ArrayList<>();
        generateNRandomTasks(numberOfClients);
        this.totalWaitingTime = 0;
        this.maxWaitingTime = 0;
        this.peakHour = 0;
        this.totalServiceTime = 0;
        this.numberOfTasks = 0;
        this.viewRealTime = new ViewRealTime();
    }

    private void generateNRandomTasks(int numberOfClients) {
        Random random = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = random.nextInt(maxArrivalTime + 1 - minArrivalTime) + minArrivalTime;
            int serviceTime = random.nextInt(maxServiceTime + 1 - minServiceTime) + minServiceTime;
            Task t = new Task(i + 1, arrivalTime, serviceTime);
            System.out.println(t);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks);
    }

    private void writeToFile(int currentTime) {
        String logEntry = "Time: " + currentTime + "\n" + "Waiting clients:";
        for (Task task : generatedTasks) {
            logEntry += " " + task.toString() + ";";
        }
        logEntry += "\n" + scheduler.toString() + "\n";
        System.out.println(logEntry);
        viewRealTime.updateTextArea(logEntry);
        try {
            fileWriter.write(logEntry);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processFirstTask() {
        for (Server server : scheduler.getServers()) {
            if (!server.getTasks().isEmpty()) {
                Task currentTask = server.getTasks().peek();
                currentTask.setServiceTime(currentTask.getServiceTime() - 1);
                if (currentTask.getServiceTime() == 0) {
                    totalWaitingTime += server.getTasks().size();
                    numberOfTasks++;
                    server.getTasks().poll();
                }
            }
        }
    }

    private boolean shouldContinueSimulation() {
        if (generatedTasks.isEmpty()) {
            for (Server server : scheduler.getServers()) {
                if (!server.getTasks().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public boolean isPeakHour() {
        int maxWaiting = 0;
        for (Server server : scheduler.getServers()) {
            for (Task task : server.getTasks()) {
                maxWaiting += task.getServiceTime();
                totalServiceTime += task.getServiceTime();
            }
            if (maxWaiting > maxWaitingTime) {
                maxWaitingTime = maxWaiting;
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        int currentTime = 0;

        while (currentTime < timeLimit && shouldContinueSimulation()) {
            while (!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                generatedTasks.remove(0);
            }

            writeToFile(currentTime);

            if (isPeakHour()) {
                peakHour = currentTime;
            }

            processFirstTask();
            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String summary = "Avg. waiting time: " + String.format("%.2f", (float) totalWaitingTime / numberOfClients) + "\n"
                + "Avg. service time: " + String.format("%.2f", (float) totalServiceTime / numberOfTasks) + "\n"
                + "Peak Hour: " + peakHour;

        try {
            viewRealTime.updateTextArea(summary);
            fileWriter.write(summary);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}