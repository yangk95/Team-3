package main;

import javax.swing.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import parkingManagement.ParkingSpot;

import java.awt.*;
import java.awt.event.ActionEvent;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Scanner;
import java.util.List;
import java.io.File;
//import java.io.FileInputStream;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    // Total number of parking spots available
    private static final int TOTAL_SPOTS = 5;

    // Array to hold the parking spot buttons
    private static final JButton[] spotButtons = new JButton[TOTAL_SPOTS];

    // Track the selected spot index (default is -1 = none selected yet)
    private static String selectedSpot = null;
    
    private static File ticketFile1 = new File("Ticket.json");
    private static File ticketFile2 = new File ("Tickets.json");
//    private static boolean userBooking = false;
    
    //private static JPanel currentBookingPanel;
    //private static JPanel searchBookingPanel;
    private static JPanel userContentPanel;
    
    private static String currentView = "parking";
    
    // Launch the User UI after login
    public static void launch(String username) {
        // Create the window (JFrame)
        JFrame frame = new JFrame("User Parking - ParkSmart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 768);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // center on screen
        
        // Welcome message at the top
        JLabel welcomeLabel = new JLabel(username + ", pick a parking spot!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(welcomeLabel, BorderLayout.NORTH);
        
        userContentPanel = new JPanel();
        userContentPanel.setLayout(new BorderLayout());
        //userContentPanel.add(userDisplay(username), BorderLayout.CENTER);
        //userContentPanel.add(searchBooking(username), BorderLayout.CENTER);
        frame.add(userContentPanel, BorderLayout.CENTER);
        
        
        
        
        JPanel buttonPanel = new JPanel();
        JButton currentBtn = new JButton("Current Reservations");
        JButton backToParkingBtn = new JButton("Back to Parking");
        buttonPanel.add(currentBtn);
        buttonPanel.add(backToParkingBtn);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        userContentPanel.add(searchBooking(username), BorderLayout.CENTER);
        currentView = "parking";

     // Button: Show current reservations
        currentBtn.addActionListener(e -> {
            if (!"current".equals(currentView)) {
                userContentPanel.removeAll();
                userContentPanel.add(currentBooking(username), BorderLayout.CENTER);
                userContentPanel.revalidate();
                userContentPanel.repaint();
                currentView = "current";
            }
        });

        // Button: Back to parking
        backToParkingBtn.addActionListener(e -> {
            if (!"parking".equals(currentView)) {
                userContentPanel.removeAll();
                userContentPanel.add(searchBooking(username), BorderLayout.CENTER);
                userContentPanel.revalidate();
                userContentPanel.repaint();
                currentView = "parking";
            }
        });

        frame.setVisible(true);
    }

    /*
	private static  Component userDisplay(String username) {
		    JPanel userDisplay = new JPanel();
		    userDisplay.setLayout(new BorderLayout());

		    // Add current booking info at top
		    userDisplay.add(currentBooking(username), BorderLayout.NORTH);

		    // Add search booking buttons in the center
		    userDisplay.add(searchBooking(username), BorderLayout.CENTER);
		    
		    return userDisplay;
		}
		*/
    
    
		
	private static  JPanel searchBooking(String username) {
		JPanel searchBooking = new JPanel();
		searchBooking.setBorder(BorderFactory.createTitledBorder("Search for Parking Spot"));
		
		List<ParkingSpot> spots = ParkingManager.getParkingSpots();
		spots = ParkingManager.checkAvailability();
		
		for(ParkingSpot spot : spots) {
			System.out.println(spot.getLabel() + " is " + spot.isAvailability());
			
			//if(spot.isAvailability()) {
//				for (int i = 0; i < spots.size(); i++) {
//					int index = i;
					JButton spotButton = new JButton("Spot " + spot.getLabel() + (spot.isAvailability() ? ": FREE" : "OCCUPIED"));
					spotButton.setEnabled(spot.isAvailability());
		            spotButton.setBackground(spot.isAvailability() ? Color.GREEN : Color.GRAY); // green means available
		            
		            //spotButton.addActionListener(null);
		            if(spot.isAvailability()) {
		            spotButton.addActionListener((ActionEvent e) -> {
		                // If user already picked a spot, don't allow another
		                if (selectedSpot != null) {
		                    JOptionPane.showMessageDialog(searchBooking, "You already selected spot: " + (selectedSpot));
		                    return;
		                }
		                
		                selectedSpot = spot.getLabel();
		                
		                //Mark as unavailable
		                spot.setAvailability(false);


		                //Update button label and color BEFORE refresh 
		                spotButton.setText("Spot " + spot.getLabel() + ": OCCUPIED");
		                spotButton.setBackground(Color.GRAY); //gray means taken
		                spotButton.setEnabled(false); // prevent clicking again

		                // Show confirmation message
//		                frame.dispose(); // Close user parking UI
		                Payment.paymentTicket(username, spot); // Go to payment screen
		                //refreshParkingUI(username);
		            });
		            searchBooking.add(spotButton);
//				}	            
	            
			}	
			
		}
		
		
		return searchBooking;
	}

	/*
	//Refresh Parking Spots
	private static void refreshParkingUI(String username) {
	    userContentPanel.removeAll();
	    userContentPanel.add(searchBooking(username), BorderLayout.CENTER);
	    userContentPanel.revalidate();
	    userContentPanel.repaint();
	    currentView = "parking";
	}
	*/
	
	
	private static JPanel currentBooking(String username) {
		JPanel bookingPanel = new JPanel();
		bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
		bookingPanel.setBorder(BorderFactory.createTitledBorder("Your Current Reservations"));
		
		ObjectMapper mapper = new ObjectMapper();
		List<Ticket> allTickets = new ArrayList<>();
		
		
		long currentTime = System.currentTimeMillis();
		
		//Define a date formatter
		SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		/*
		boolean hasCurrent = false;
		boolean hasPrevious = false;
		
		long startTime;
		long endTime;
		
		
		// Temporary panels to hold each group
        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(new BoxLayout(currentPanel, BoxLayout.Y_AXIS));

        JPanel previousPanel = new JPanel();
        previousPanel.setLayout(new BoxLayout(previousPanel, BoxLayout.Y_AXIS));
		*/
        
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
	        
        
			
			//DEBUGGING
	//System.out.println("Loaded tickets: " + allTickets.size());
		
		
	        for (Ticket ticket : allTickets) {
                if (ticket.getUsername() != null && ticket.getUsername().equalsIgnoreCase(username)) {
                    
                	long startTime; 
                	long endTime;
                	
                	try {
                        // Try parsing as long first
                        startTime = Long.parseLong(ticket.getTimeStart());
                        endTime = Long.parseLong(ticket.getTimeEnd());
                    } catch (NumberFormatException e) {
                        startTime = isoFormat.parse(ticket.getTimeStart()).getTime();
                        endTime = isoFormat.parse(ticket.getTimeEnd()).getTime();
                    }
                    
                    if (endTime > currentTime) {
                        JPanel ticketPanel = new JPanel();
                        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
                        ticketPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                        ticketPanel.add(new JLabel("License Plate: " + ticket.getLicensePlate()));
                        ticketPanel.add(new JLabel("Start Time: " + displayFormat.format(new Date(startTime))));
                        ticketPanel.add(new JLabel("End Time: " + displayFormat.format(new Date(endTime))));
                        ticketPanel.add(new JLabel("Ticket ID: " + ticket.getTicketID()));
                        ticketPanel.add(new JLabel("Spot ID: " + ticket.getSpotID()));
                        
                        
                        JButton cancelBtn = new JButton("Cancel Reservation");
                        cancelBtn.addActionListener(e -> {
                            int confirm = JOptionPane.showConfirmDialog(null, "Cancel this reservation?", "Confirm", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                            	cancelReservation(ticket);
                                userContentPanel.removeAll();
                                userContentPanel.add(currentBooking(username), BorderLayout.CENTER);
                                userContentPanel.revalidate();
                                userContentPanel.repaint();
                            }
                        });
                        ticketPanel.add(cancelBtn);

                        bookingPanel.add(ticketPanel, 0);
                    }
                }
            }

            if (bookingPanel.getComponentCount() == 0) {
                bookingPanel.add(new JLabel("You don’t have any current reservations."));
            }

        } catch (Exception e) {
            e.printStackTrace();
            bookingPanel.add(new JLabel("Error loading reservations."));
        }

        return bookingPanel;
    }
	
	
	 private static void cancelReservation(Ticket ticketToRemove) {
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            List<Ticket> allTickets = new ArrayList<>();

	            if (ticketFile1.exists()) {
	                allTickets = mapper.readValue(ticketFile1, new TypeReference<List<Ticket>>() {});
	            }

	            allTickets.removeIf(ticket -> ticket.getTicketID().equals(ticketToRemove.getTicketID()));
	            mapper.writerWithDefaultPrettyPrinter().writeValue(ticketFile1, allTickets);

	            ParkingManager.markSpotAvailable(ticketToRemove.getSpotID());

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
                        /*
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
    */
	
	/*
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
    */
    
}