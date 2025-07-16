package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Init {

    public static void main(String[] args) {
        JFrame frame = new JFrame("ParkSmart - Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to ParkSmart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

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
            UserLogin.launch(); // Launches user login screen
        });

        managerButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            Manager.launch();
        });

        buttonPanel.add(userButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(managerButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
}