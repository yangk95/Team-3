package main;

import java.awt.*;
import javax.swing.*;

public class Manager {
    private static boolean garageOpen = false;
    private static final int TOTAL_SPOTS = 5;
    private static final JToggleButton[] spotButtons = new JToggleButton[TOTAL_SPOTS];

    // make menu bar
    private static JMenuBar menuBar;
    private static JMenu menu1, menu2;

    // card layout 
    private static CardLayout cardLayout;
    private static JPanel contentPanel;

    // Frame Layout setup
    public static void launch() {
        JFrame frame = new JFrame("Manager Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        // Manager have option to add garage, let's make this as separate menu with Account Management and payment management is sub management of Account
        menuBar = new JMenuBar();
        menu1 = new JMenu("Garage");
        menu2 = new JMenu("Account");

        // create each menu item inside menu. 
        // - garage management (add/remove/undercontrusction)
        // - Garage statistic (display information)
        // - Garage report violate is handling at owner discretion???
        // - Account management (add more employee?)
        // - Account Payment processing (add/remove payment processing)
        JMenuItem garageItem = new JMenuItem("Garage Management");
        garageItem.addActionListener(e -> cardLayout.show(contentPanel, "Garage"));
        menu1.add(garageItem);

        JMenuItem accountItem = new JMenuItem("Account Management");
        accountItem.addActionListener(e -> cardLayout.show(contentPanel, "Account"));
        menu2.add(accountItem);

        // Card Layout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createGaragePanel(), "Garage");
        contentPanel.add(createAccountPanel(), "Account");
    

        menuBar.add(menu1);
        menuBar.add(menu2);


        frame.setJMenuBar(menuBar);
        frame.add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, "Garage");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createGaragePanel() {
        JButton garageButton = new JButton(garageOpen ? "Close Garage" : "Open Garage");
        garageButton.addActionListener(e -> {
            garageOpen = !garageOpen;
            garageButton.setText(garageOpen ? "Close Garage" : "Open Garage");
            System.out.println("Garage is now " + (garageOpen ? "OPEN" : "CLOSED"));
        });

        JPanel topPanel = new JPanel();
        topPanel.add(garageButton);

        JPanel spotPanel = new JPanel(new GridLayout(TOTAL_SPOTS, 1, 5, 5));
        spotPanel.setBorder(BorderFactory.createTitledBorder("Parking Spots"));

        for (int i = 0; i < TOTAL_SPOTS; i++) {
            int index = i + 1;
            JToggleButton spotButton = new JToggleButton("Spot " + index + ": FREE");
            spotButton.addActionListener(ev -> {
                boolean selected = spotButton.isSelected();
                spotButton.setText("Spot " + index + ": " + (selected ? "OCCUPIED" : "FREE"));
            });
            spotButtons[i] = spotButton;
            spotPanel.add(spotButton);
        }

        JPanel garagePanel = new JPanel(new BorderLayout());
        garagePanel.add(topPanel, BorderLayout.NORTH);
        garagePanel.add(spotPanel, BorderLayout.CENTER);

        return garagePanel;
    }

    private static JPanel createAccountPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel (center)
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        // Empty row to keep spacing aligned
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());

        // Button Panel (south)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton resetBtn = new JButton("Reset");
        JLabel messageLabel = new JLabel(" "); // For showing confirmation messages

        buttonPanel.add(messageLabel);
        buttonPanel.add(resetBtn);
        buttonPanel.add(saveBtn);

        // Add action listeners
        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || email.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
                messageLabel.setForeground(Color.RED);
            } else {
                messageLabel.setText("Account info saved!");
                messageLabel.setForeground(new Color(0, 128, 0));
                // Here, you can add code to save data to database or file
            }
        });

        resetBtn.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            messageLabel.setText(" ");
        });

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
}
