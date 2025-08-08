package main;


import javax.swing.*;
import java.awt.*;

public class MapView {

    public static void launch(String username) {
        JFrame frame = new JFrame("Detecting Location...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Finding Parking Near You", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Load mock map image
        ImageIcon mapIcon = new ImageIcon(MapView.class.getResource("/resources/images/mock_map.jpg"));
        Image originalMap = mapIcon.getImage();

        //Image Size
        int imgWidth = mapIcon.getIconWidth();
        int imgHeight = mapIcon.getIconHeight();
        int targetWidth = 300;
        int targetHeight = (imgHeight * targetWidth) / imgWidth;

        Image scaledMap = originalMap.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        JLabel mapLabel = new JLabel(new ImageIcon(scaledMap));
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(mapLabel, BorderLayout.CENTER);

        // Use My Location button
        JButton locationButton = new JButton("Use My Location");
        locationButton.setPreferredSize(new Dimension(160, 40));
        locationButton.addActionListener(e -> {
            frame.dispose();
            showLoadingThenLaunchUser(username);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(locationButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showLoadingThenLaunchUser(String username) {
        JDialog loadingDialog = new JDialog((Frame) null, "Loading", true);
        loadingDialog.setSize(300, 120);
        loadingDialog.setLocationRelativeTo(null);
        loadingDialog.setLayout(new BorderLayout());

        JLabel loadingLabel = new JLabel("Searching for available parking spots...", SwingConstants.CENTER);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        loadingDialog.add(loadingLabel, BorderLayout.NORTH);
        loadingDialog.add(progressBar, BorderLayout.CENTER);

        //Loading bar
        SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

        //Loading bar transition to User UI
        new Thread(() -> {
            try {
                Thread.sleep(2000); // simulate loading
            } catch (InterruptedException ignored) {}

            //Close loading dialog and launch User UI
            SwingUtilities.invokeLater(() -> {
                loadingDialog.dispose(); 
                User.launch(username);
            });
        }).start();
    }
}
