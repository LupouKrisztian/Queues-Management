package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Server> servers;
    private int maxNumbersServers;
    private int maxTasksPerServer;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.maxNumbersServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<>(maxNoServers);

        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            Thread thread = new Thread(server);
            thread.start();
            servers.add(server);
        }
    }

    public void dispatchTask(Task t){
        Server bestServer = null;
        int shortestWait = Integer.MAX_VALUE;

        for (Server server : servers) {
            int waitTime = server.getWaitingPeriod().intValue();
            if (waitTime < shortestWait) {
                shortestWait = waitTime;
                bestServer = server;
            }
        }

        if (bestServer != null) {
            bestServer.addTask(t);
        }
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int serverIndex = 1;

        for (Server server : servers) {
            result.append("Server ").append(serverIndex).append(": ").append(server).append("\n");
            serverIndex++;
        }

        return result.toString();
    }
}