package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel contentPanel;

    private JTextField textSimulationTime;
    private JTextField textNumberOfClients;
    private JTextField textNumberOfQueues;
    private JTextField textMinArrivalTime;
    private JTextField textMaxArrivalTime;
    private JTextField textMinServiceTime;
    private JTextField textMaxServiceTime;

    private JLabel labelSimulationTime;
    private JLabel labelNumberOfClients;
    private JLabel labelNumberOfQueues;
    private JLabel labelMinArrivalTime;
    private JLabel labelMaxArrivalTime;
    private JLabel labelMinServiceTime;
    private JLabel labelMaxServiceTime;

    private JButton btnStart;

    public View() {
        setTitle("QUEUES MANAGEMENT APPLICATION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 150, 450, 350);
        setResizable(false);

        contentPanel = new JPanel();
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        labelSimulationTime = new JLabel();
        labelSimulationTime.setHorizontalAlignment(SwingConstants.RIGHT);
        labelSimulationTime.setBounds(10, 20, 120, 26);
        labelSimulationTime.setText("Simulation Time:");

        labelNumberOfClients = new JLabel();
        labelNumberOfClients.setHorizontalAlignment(SwingConstants.RIGHT);
        labelNumberOfClients.setBounds(10, 60, 120, 26);
        labelNumberOfClients.setText("Number of Clients:");

        labelNumberOfQueues = new JLabel();
        labelNumberOfQueues.setHorizontalAlignment(SwingConstants.RIGHT);
        labelNumberOfQueues.setBounds(10, 100, 120, 26);
        labelNumberOfQueues.setText("Number of Queues:");

        labelMinArrivalTime = new JLabel();
        labelMinArrivalTime.setHorizontalAlignment(SwingConstants.RIGHT);
        labelMinArrivalTime.setBounds(10, 140, 120, 26);
        labelMinArrivalTime.setText("Min Arrival Time:");

        labelMaxArrivalTime = new JLabel();
        labelMaxArrivalTime.setHorizontalAlignment(SwingConstants.RIGHT);
        labelMaxArrivalTime.setBounds(10, 180, 120, 26);
        labelMaxArrivalTime.setText("Max Arrival Time:");

        labelMinServiceTime = new JLabel();
        labelMinServiceTime.setHorizontalAlignment(SwingConstants.RIGHT);
        labelMinServiceTime.setBounds(10, 220, 120, 26);
        labelMinServiceTime.setText("Min Service Time:");

        labelMaxServiceTime = new JLabel();
        labelMaxServiceTime.setHorizontalAlignment(SwingConstants.RIGHT);
        labelMaxServiceTime.setBounds(10, 260, 120, 26);
        labelMaxServiceTime.setText("Max Service Time:");

        textSimulationTime = new JTextField();
        textSimulationTime.setBounds(140, 20, 150, 26);

        textNumberOfClients = new JTextField();
        textNumberOfClients.setBounds(140, 60, 150, 26);

        textNumberOfQueues = new JTextField();
        textNumberOfQueues.setBounds(140, 100, 150, 26);

        textMinArrivalTime = new JTextField();
        textMinArrivalTime.setBounds(140, 140, 150, 26);

        textMaxArrivalTime = new JTextField();
        textMaxArrivalTime.setBounds(140, 180, 150, 26);

        textMinServiceTime = new JTextField();
        textMinServiceTime.setBounds(140, 220, 150, 26);

        textMaxServiceTime = new JTextField();
        textMaxServiceTime.setBounds(140, 260, 150, 26);

        btnStart = new JButton();
        btnStart.setBounds(320, 210, 100, 30);
        btnStart.setText("START");
        btnStart.setForeground(Color.WHITE);
        btnStart.setBackground(Color.RED);

        contentPanel.add(labelNumberOfClients);
        contentPanel.add(labelNumberOfQueues);
        contentPanel.add(labelSimulationTime);
        contentPanel.add(labelMinArrivalTime);
        contentPanel.add(labelMaxArrivalTime);
        contentPanel.add(labelMinServiceTime);
        contentPanel.add(labelMaxServiceTime);

        contentPanel.add(textNumberOfClients);
        contentPanel.add(textNumberOfQueues);
        contentPanel.add(textSimulationTime);
        contentPanel.add(textMinArrivalTime);
        contentPanel.add(textMaxArrivalTime);
        contentPanel.add(textMinServiceTime);
        contentPanel.add(textMaxServiceTime);

        contentPanel.add(btnStart);
    }

    public void start(ActionListener e) {
        btnStart.addActionListener(e);
    }

    public String getSimulationTime() { return textSimulationTime.getText(); }

    public String getNumberOfClients() {
        return textNumberOfClients.getText();
    }

    public String getNumberOfQueues() {
        return textNumberOfQueues.getText();
    }

    public String getMinArrivalTime() {
        return textMinArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return textMaxArrivalTime.getText();
    }

    public String getMinServiceTime() {
        return textMinServiceTime.getText();
    }

    public String getMaxServiceTime() {
        return textMaxServiceTime.getText();
    }
}