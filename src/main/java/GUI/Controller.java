package GUI;

import BusinessLogic.SimulationManager;
import java.io.IOException;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
        this.view.start(e -> {
            try {
                int simulationTime = Integer.parseInt(view.getSimulationTime());
                int numberOfQueues = Integer.parseInt(view.getNumberOfQueues());
                int numberOfClients = Integer.parseInt(view.getNumberOfClients());
                int minServiceTime = Integer.parseInt(view.getMinServiceTime());
                int maxServiceTime = Integer.parseInt(view.getMaxServiceTime());
                int minArrivalTime = Integer.parseInt(view.getMinArrivalTime());
                int maxArrivalTime = Integer.parseInt(view.getMaxArrivalTime());

                SimulationManager simulationManager = new SimulationManager(simulationTime, numberOfQueues,
                        numberOfClients, minServiceTime, maxServiceTime, minArrivalTime, maxArrivalTime);

                Thread thread = new Thread(simulationManager);
                thread.start();
            } catch (NumberFormatException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}