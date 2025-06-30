package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Init {

    public static void main(String[] args) {
        JFrame frame = new JFrame("ParkSmart - Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Title section
        JLabel titleLabel = new JLabel("Welcome to ParkSmart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Center section with role buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton userButton = new JButton("Login as User");
        JButton managerButton = new JButton("Login as Manager");

        userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        managerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        userButton.setMaximumSize(new Dimension(200, 40));
        managerButton.setMaximumSize(new Dimension(200, 40));

        userButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            JOptionPane.showMessageDialog(null, "User Page Coming Soon");
            // Replace with: User.launch(); when User class is created
        });

        managerButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            Manager.launch();
        });

        buttonPanel.add(userButton);
        buttonPanel.add(Box.createVerticalStrut(20)); // spacing
        buttonPanel.add(managerButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}