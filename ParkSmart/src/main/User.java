package main;

import javax.swing.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class User {

    // Total number of parking spots available
    private static final int TOTAL_SPOTS = 5;

    // Array to hold the parking spot buttons
    private static final JButton[] spotButtons = new JButton[TOTAL_SPOTS];

    // Track the selected spot index (default is -1 = none selected yet)
    private static int selectedSpot = -1;
    
    private static String ticketFile = "resourse/database/Ticket.json";
//    private static boolean userBooking = false;
    
//    // once found user booking
//    private static String licensePlate = null;
//    private static String timeStart = null;
//    private static String timeEnd = null;
//    private static String ticketID = null;
//    private static String spotID = null;

    // Launch the User UI after login
    public static void launch(String username) {
        // Create the window (JFrame)
        JFrame frame = new JFrame("User Parking - ParkSmart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 768);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // center on screen

//     // gather user information
//        identifyUser(username);
        
        // Welcome message at the top
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "! Pick a parking spot.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(welcomeLabel, BorderLayout.NORTH);
        
        JPanel userContentPanel = new JPanel();
        userContentPanel.add(userDisplay(username), "user");
        frame.add(userContentPanel, BorderLayout.CENTER);
        
/** removing this from Sprint 3
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
        
    */    
        
        
        frame.setVisible(true);
    }

//	private static void identifyUser(String username) {
//	    String inputUsername = username;
//
//	    ObjectMapper mapper = new ObjectMapper();
//	    try {	    	
//            InputStream is = ParkingRecord.class.getClassLoader().getResourceAsStream(userFile);
//            if (is == null) {
//                System.out.println("File not found: " + userFile);
//                return;
//            }
//            
//            List<ParkingRecord> records = mapper.readValue(is, new TypeReference<List<ParkingRecord>>() {});
//            
//	        boolean found = false;
//	        for (ParkingRecord record : records) {
//	            if (record.getUsername().equalsIgnoreCase(inputUsername)) {
//	                found = true;
//	                System.out.println("Found");
//	                userBooking = true;
//	                licensePlate = record.getLicensePlate();
//	                timeStart = record.getTimeStart();
//	                timeEnd = record.getTimeEnd();
//	                ticketID = record.getTicketID();
//	                spotID = record.getSpotID();
//	                break;
//	            }
//	        }//
//	        if (!found) {
//	            System.out.println("User not found.");
//	        }
//
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

	private static Component userDisplay(String username) {
		JPanel userDisplay = new JPanel(new BorderLayout());
		userDisplay.add(currentBooking(username), BorderLayout.NORTH);
		userDisplay.add(searchBooking(), BorderLayout.CENTER);
		return userDisplay;
	}

	private static Component searchBooking() {
		JPanel searchBooking = new JPanel();
		searchBooking.setBorder(BorderFactory.createTitledBorder("Search for Parking Spot"));
		return searchBooking;
	}

	private static Component currentBooking(String username) {
		JPanel currentBooking = new JPanel();
		currentBooking.setLayout(new BoxLayout(currentBooking, BoxLayout.Y_AXIS));
		currentBooking.setBorder(BorderFactory.createTitledBorder("Current Parking Spot / Reservation"));
		
		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(ticketFile.getAbsolutePath());
		try {
			InputStream is = Ticket.class.getClassLoader().getResourceAsStream(ticketFile);
	        if (is == null ) {
	            System.out.println("File not found: " + ticketFile);
	         }
			
			List<Ticket> allTickets = mapper.readValue(is, new TypeReference<List<Ticket>>() {});
			boolean hasBooking = false;
			for (Ticket ticket : allTickets) {
				
				if (ticket.getUsername().equalsIgnoreCase(username)) {
					hasBooking = true;
					System.out.println("Found");

			        currentBooking.add(new JLabel("----"));
			        currentBooking.add(new JLabel("License Plate: " + ticket.getLicensePlate()));
			        currentBooking.add(new JLabel("Start Time: " + ticket.getTimeStart()));
			        currentBooking.add(new JLabel("End Time: " + ticket.getTimeEnd()));
			        currentBooking.add(new JLabel("Ticket ID: " + ticket.getTicketID()));
			        currentBooking.add(new JLabel("Spot ID: " + ticket.getSpotID()));
				    
				} 
			}
			if (!hasBooking) {
				
				currentBooking.add(new JLabel("You don't have any booking or reservation at this time."));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		return currentBooking;
	}
    
}