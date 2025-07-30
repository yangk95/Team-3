package main;

import javax.swing.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import parkingManagement.ParkingSpot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    // Total number of parking spots available
    private static final int TOTAL_SPOTS = 5;

    // Array to hold the parking spot buttons
    private static final JButton[] spotButtons = new JButton[TOTAL_SPOTS];

    // Track the selected spot index (default is -1 = none selected yet)
    private static int selectedSpot = -1;
    
    private static File ticketFile1 = new File("Ticket.json");
    private static File ticketFile2 = new File ("Tickets.json");
//    private static boolean userBooking = false;
    
    private static JPanel currentBookingPanel;
    private static JPanel searchBookingPanel;
    private static JPanel userContentPanel;
    
    // Launch the User UI after login
    public static void launch(String username) {
        // Create the window (JFrame)
        JFrame frame = new JFrame("User Parking - ParkSmart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 768);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // center on screen
        
        // Welcome message at the top
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "! Pick a parking spot.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(welcomeLabel, BorderLayout.NORTH);
        
        userContentPanel = new JPanel();
        userContentPanel.setLayout(new BorderLayout());
        userContentPanel.add(userDisplay(username), BorderLayout.CENTER);
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
        
        // Refresh panels()
        refreshPanels(username);
        frame.setVisible(true);
    }

	private static  Component userDisplay(String username) {
		    JPanel userDisplay = new JPanel();
		    userDisplay.setLayout(new BorderLayout());

		    // Add current booking info at top
		    userDisplay.add(currentBooking(username), BorderLayout.NORTH);

		    // Add search booking buttons in the center
		    userDisplay.add(searchBooking(username), BorderLayout.CENTER);
		    
		    return userDisplay;
		}
		
	private static  JPanel searchBooking(String username) {
		JPanel searchBooking = new JPanel();
		searchBooking.setBorder(BorderFactory.createTitledBorder("Search for Parking Spot"));
		
		List<ParkingSpot> spots = ParkingManager.getParkingSpots();
		spots = ParkingManager.checkAvailability();
		for(ParkingSpot spot : spots) {
			System.out.println(spot.getLabel() + " is " + spot.isAvailability());
			if(spot.isAvailability()) {
//				for (int i = 0; i < spots.size(); i++) {
//					int index = i;
					JButton spotButton = new JButton("Spot " + spot.getLabel() + ": FREE");
		            spotButton.setBackground(Color.GREEN); // green means available
		            
		            spotButton.addActionListener(null);
		            
		            spotButton.addActionListener((ActionEvent e) -> {
		                // If user already picked a spot, don't allow another
		                if (selectedSpot != -1) {
		                    JOptionPane.showMessageDialog(searchBooking, "You already picked Spot " + (selectedSpot + 1));
		                    return;
		                }
		                spot.setAvailability(false);

		                // Save the spot selection
//		                selectedSpot = index;

		                // Update the spot button to show it's taken
//		                spotButton.setText("Spot " + (index + 1) + ": OCCUPIED");
//		                spotButton.setBackground(Color.RED); // red means taken
//		                spotButton.setEnabled(false); // prevent clicking again

		                // Show confirmation message
//		                frame.dispose(); // Close user parking UI
		                Payment.paymentTicket(username, spot); // Go to payment screen
		            });
		            searchBooking.add(spotButton);
//				}	            
	            
			}	
			
		}
		
		
		return searchBooking;
	}

	private static JPanel currentBooking(String username) {
		JPanel bookingPanel = new JPanel();
		bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
		bookingPanel.setBorder(BorderFactory.createTitledBorder("Your Parking Reservations"));
		
		ObjectMapper mapper = new ObjectMapper();
		List<Ticket> allTickets = new ArrayList<>();
		
		
		long currentTime = System.currentTimeMillis();
//		System.out.println(ticketFile.getAbsolutePath());
		
		//Define a date formatter
		SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

		boolean hasCurrent = false;
		boolean hasPrevious = false;
		
		long startTime;
		long endTime;
		
		
		// Temporary panels to hold each group
        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(new BoxLayout(currentPanel, BoxLayout.Y_AXIS));

        JPanel previousPanel = new JPanel();
        previousPanel.setLayout(new BoxLayout(previousPanel, BoxLayout.Y_AXIS));
		
        
        try {
//			InputStream is = new FileInputStream(ticketFile);
//	        if (is == null ) {
//	            System.out.println("File not found: " + ticketFile);
//	         }
			if (ticketFile1.exists()) {
			    allTickets.addAll(mapper.readValue(ticketFile1, new TypeReference<List<Ticket>>() {}));
			}

			if (ticketFile2.exists()) {
			    allTickets.addAll(mapper.readValue(ticketFile2, new TypeReference<List<Ticket>>() {}));
			}

			System.out.println("Total loaded tickets: " + allTickets.size());
	        
        
		// Now process allTickets
		//for (Ticket t : allTickets) {
		    //System.out.println("Loaded ticket: " + t.getUsername() + " - " + t.getTicketID());
		//}
		
		
	
	        
	        for (Ticket ticket : allTickets) {
                if (ticket.getUsername() != null && ticket.getUsername().equalsIgnoreCase(username)) {
                    try {
                        startTime = Long.parseLong(ticket.getTimeStart());
                        endTime = Long.parseLong(ticket.getTimeEnd());

                        String readableStart = displayFormat.format(new Date(startTime));
                        String readableEnd = displayFormat.format(new Date(endTime));

                        JPanel ticketPanel = new JPanel();
                        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
                        ticketPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                        ticketPanel.add(new JLabel("License Plate: " + ticket.getLicensePlate()));
                        ticketPanel.add(new JLabel("Start Time: " + readableStart));
                        ticketPanel.add(new JLabel("End Time: " + readableEnd));
                        ticketPanel.add(new JLabel("Ticket ID: " + ticket.getTicketID()));
                        ticketPanel.add(new JLabel("Spot ID: " + ticket.getSpotID()));

                        if (endTime > currentTime) {
                            hasCurrent = true;
                            currentPanel.add(ticketPanel);
                        } else {
                            hasPrevious = true;
                            previousPanel.add(ticketPanel);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            if (hasCurrent) {
            	JPanel currentWrapper = new JPanel(new BorderLayout());
                currentWrapper.setBorder(BorderFactory.createTitledBorder("---- Current Reservations ----"));
                currentWrapper.add(currentPanel, BorderLayout.CENTER);
                bookingPanel.add(currentWrapper);
            }
            if (hasPrevious) {
            	JPanel previousWrapper = new JPanel(new BorderLayout());
                previousWrapper.setBorder(BorderFactory.createTitledBorder("---- Previous Reservations ----"));
                previousWrapper.add(previousPanel, BorderLayout.CENTER);
                bookingPanel.add(previousWrapper);
            }
            if (!hasCurrent && !hasPrevious) {
                bookingPanel.add(new JLabel("You don't have any booking or reservation at this time."));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookingPanel;
    }
	
	
	public static void refreshPanels(String username) {
		try {
			if (currentBookingPanel != null) {
	            userContentPanel.remove(currentBookingPanel);
	        }
	        if (searchBookingPanel != null) {
	            userContentPanel.remove(searchBookingPanel);
	        }

	        currentBookingPanel = currentBooking(username);
	        searchBookingPanel = searchBooking(username);

	        userContentPanel.add(currentBookingPanel, BorderLayout.NORTH);
	        userContentPanel.add(searchBookingPanel, BorderLayout.CENTER);
	        userContentPanel.revalidate();
	        userContentPanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
}