package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class User {

    // Total number of parking spots available
    private static final int TOTAL_SPOTS = 5;

    // Array to hold the parking spot buttons
    private static final JButton[] spotButtons = new JButton[TOTAL_SPOTS];

    // Track the selected spot index (default is -1 = none selected yet)
    private static int selectedSpot = -1;

    // Launch the User UI after login
    public static void launch(String username) {
        // Create the window (JFrame)
        JFrame frame = new JFrame("User Parking - ParkSmart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // center on screen

        // Welcome message at the top
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "! Pick a parking spot.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(welcomeLabel, BorderLayout.NORTH);

        // Panel to hold the parking spot buttons
        JPanel spotPanel = new JPanel(new GridLayout(TOTAL_SPOTS, 1, 10, 10));
        spotPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // Create buttons for each parking spot
        for (int i = 0; i < TOTAL_SPOTS; i++) {
            int index = i; // needed for use in lambda
            JButton spotButton = new JButton("Spot " + (index + 1) + ": FREE");
            spotButton.setBackground(Color.GREEN); // green means available

            // When a user clicks on a spot
            spotButton.addActionListener((ActionEvent e) -> {
                // If user already picked a spot, don't allow another
                if (selectedSpot != -1) {
                    JOptionPane.showMessageDialog(frame, "You already picked Spot " + (selectedSpot + 1));
                    return;
                }

                // Save the spot selection
                selectedSpot = index;

                // Update the spot button to show it's taken
                spotButton.setText("Spot " + (index + 1) + ": OCCUPIED");
                spotButton.setBackground(Color.RED); // red means taken
                spotButton.setEnabled(false); // prevent clicking again

                // Show confirmation message
                frame.dispose(); // Close user parking UI
                Payment.launch(username, index); // Go to payment screen
            });

            // Add the button to the array and panel
            spotButtons[i] = spotButton;
            spotPanel.add(spotButton);
        }

        // Add the spot panel to the window
        frame.add(spotPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}