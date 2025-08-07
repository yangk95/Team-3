package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Init {

    public static void main(String[] args) {
    	ParkingManager.init();
    	
        JFrame frame = new JFrame("ParkSmart - Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Welcome Page
        JLabel titleLabel = new JLabel("Welcome to ParkSmart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(titleLabel, BorderLayout.NORTH);
        
        //Load Logo
        ImageIcon logoIcon = new ImageIcon(Init.class.getResource("/resources/images/project_logo.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        
        //Logo is clickable --> redirects user to login screen
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();          // Close welcome window
                UserLogin.launch();       // Open login screen
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Show hand icon
            }
        });

        frame.add(logoLabel, BorderLayout.CENTER);
        
        

        /*
        //User Login below Logo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        JButton userButton = new JButton("Login as User");
        //JButton managerButton = new JButton("Login as Manager");

        userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //managerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        userButton.setMaximumSize(new Dimension(200, 40));
        //managerButton.setMaximumSize(new Dimension(200, 40));

        userButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            UserLogin.launch(); // Launches user login screen
        });
        */

        /*
        managerButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            Manager.launch();
        });
        */
        
        
        /*
        buttonPanel.add(userButton);
        //buttonPanel.add(Box.createVerticalStrut(20));
        //buttonPanel.add(managerButton);

        //frame.add(logoLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        */
        
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }   
}