package GUI;

import javax.swing.*;
import java.awt.*;

public class ViewRealTime {
    private JFrame frame;
    private JTextArea jTextArea;
    private JScrollPane scrollPane;

    public ViewRealTime() {
        frame = new JFrame();
        frame.setTitle("REAL-TIME SIMULATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 150, 1100, 300);

        jTextArea = new JTextArea(100, 100);
        jTextArea.setEditable(false);

        scrollPane = new JScrollPane(jTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void updateTextArea(String text) {
        jTextArea.append(text + "\n");
        jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
    }
}