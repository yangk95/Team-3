package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserLogin {

    public static void launch() {
        JFrame frame = new JFrame("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 180);
        frame.setLayout(new GridLayout(4, 2, 10, 10));
        frame.setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JLabel message = new JLabel(" ");
        JButton loginBtn = new JButton("Login");

        // Let any username/password go through
        loginBtn.addActionListener((ActionEvent e) -> {
            String username = userField.getText().trim();

            if (!username.isEmpty()) {
                frame.dispose(); // close login window
                MapView.launch(username); // go to user app page
            } else {
                message.setText("Please enter a username.");
                message.setForeground(Color.RED);
            }
        });

        // Add to frame
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(new JLabel()); // spacer
        frame.add(loginBtn);
        frame.add(message);

        frame.setVisible(true);
    }
}