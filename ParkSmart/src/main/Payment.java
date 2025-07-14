package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Payment {

    public static void launch(String username, int spotNumber) {
        JFrame frame = new JFrame("Payment - ParkSmart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Pay for Spot " + (spotNumber + 1), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        formPanel.add(new JLabel("Card Number:"));
        JTextField cardField = new JTextField();
        formPanel.add(cardField);

        formPanel.add(new JLabel("Expiry Date:"));
        JTextField expiryField = new JTextField();
        formPanel.add(expiryField);

        formPanel.add(new JLabel("CVV:"));
        JTextField cvvField = new JTextField();
        formPanel.add(cvvField);

        JButton payButton = new JButton("Pay");
        JLabel message = new JLabel(" ", SwingConstants.CENTER);

        payButton.addActionListener((ActionEvent e) -> {
            // In real life, you'd validate the inputs and send them to a payment gateway
            String card = cardField.getText().trim();
            String expiry = expiryField.getText().trim();
            String cvv = cvvField.getText().trim();

            if (card.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                message.setText("Please fill out all fields.");
                message.setForeground(Color.RED);
            } else {
                frame.dispose(); // Close payment screen
                JOptionPane.showMessageDialog(null, "Payment successful!");
                // Optional: Return to home screen or finish here
                Init.main(null);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        bottomPanel.add(payButton, BorderLayout.NORTH);
        bottomPanel.add(message, BorderLayout.CENTER);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}