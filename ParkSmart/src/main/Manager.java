package main;
import garage.Garage;

import java.awt.*;
import javax.swing.*;
import garage.*;

public class Manager {
    // private static boolean garageOpen = false;
    // private static final int TOTAL_SPOTS = 5;
    // private static final JToggleButton[] spotButtons = new JToggleButton[TOTAL_SPOTS];
    // private static gGarage activeGarage;

    // make menu bar
    private static JMenuBar menuBar;
    private static JMenu menu1, menu2, menu3;

    // card layout 
    private static CardLayout cardLayout;
    private static JPanel contentPanel;

    // Garage items
    private static List<Garage> garages;

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
        menu3 = new JMenu("Violation");

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

        JMenuItem violationItem = new JMenuItem("Account Management");
        violationItem.addActionListener(e -> cardLayout.show(contentPanel, "Account"));
        menu2.add(violationItem);

        // Card Layout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createGaragePanel(), "Garage");
        contentPanel.add(createAccountPanel(), "Account");
        contentPanel.add(createViolationPanel(), "Violation");
    
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        frame.setJMenuBar(menuBar);
        frame.add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, "Garage");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createGaragePanel() {     
        JPanel garagePanel = new JPanel(new BorderLayout());
        garagePanel.add(buildGarageStatsPanel(), BorderLayout.NORTH);
        garagePanel.add(new JScrollPane(buildLevelsPanel()), BorderLayout.CENTER);
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

    private static Component createViolationPanel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    private static JPanel buildGarageStatsPanel() {
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Garage Overview"));

        JComboBox<String> garageSelector = new JComboBox<>(
            garages.stream().map(Garage::getName).toArray(String[]::new)
        );
        garageSelector.setSelectedItem(activeGarage.getName());

        garageSelector.addActionListener(e -> handleGarageSelection((String) garageSelector.getSelectedItem()));

        int total = countTotalSpots(activeGarage);
        int occupied = countOccupiedSpots(activeGarage);

        topPanel.add(new JLabel("Garage:"));
        topPanel.add(garageSelector);
        topPanel.add(new JLabel("Available: " + (total - occupied) + " / " + total));
        topPanel.add(new JLabel("Charge Rate: $" + CHARGE_RATE + " per spot"));

        return topPanel;
    }

    private static JPanel buildLevelsPanel() {
        JPanel spotPanel = new JPanel();
        spotPanel.setLayout(new BoxLayout(spotPanel, BoxLayout.Y_AXIS));
        spotPanel.setBorder(BorderFactory.createTitledBorder("Levels and Parking Spots"));

        for (gLevel level : activeGarage.getLevels()) {
            JPanel levelPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            levelPanel.setBorder(BorderFactory.createTitledBorder("Level " + level.getLevelNumber()));

            for (ParkingSpot spot : level.getSpots()) {
                levelPanel.add(buildSpotButton(spot));
            }

            spotPanel.add(levelPanel);
        }

        return spotPanel;
    }

    
}

/*
 * parking session begin and end. 
 */
