package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    private static boolean garageOpen = false;
    private static final int TOTAL_SPOTS = 5;
    private static final JToggleButton[] spotButtons = new JToggleButton[TOTAL_SPOTS];

    public static void launch() {
        JFrame frame = new JFrame("Manager Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        JButton garageButton = new JButton("Open Garage");
        garageButton.addActionListener(e -> {
            garageOpen = !garageOpen;
            garageButton.setText(garageOpen ? "Close Garage" : "Open Garage");
            System.out.println("Garage is now " + (garageOpen ? "OPEN" : "CLOSED"));
        });

        JPanel spotPanel = new JPanel();
        spotPanel.setLayout(new GridLayout(TOTAL_SPOTS + 1, 1, 5, 5));
        spotPanel.setBorder(BorderFactory.createTitledBorder("Parking Spots"));

        for (int i = 0; i < TOTAL_SPOTS; i++) {
            int index = i + 1;
            JToggleButton spot = new JToggleButton("Spot " + index + ": FREE");
            spot.addActionListener(new SpotToggleAction(spot, index));
            spotButtons[i] = spot;
            spotPanel.add(spot);
        }

        JPanel topPanel = new JPanel();
        topPanel.add(garageButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(spotPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class SpotToggleAction implements ActionListener {
        private final JToggleButton button;
        private final int index;

        public SpotToggleAction(JToggleButton button, int index) {
            this.button = button;
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean occupied = button.isSelected();
            button.setText("Spot " + index + ": " + (occupied ? "OCCUPIED" : "FREE"));
        }
    }
}
