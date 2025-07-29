package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import parkingManagement.ParkingSpot;

public class Ticket {
    private String username;
    private String name;
    private String licensePlate;
    private String timeStart;
    private String timeEnd;
    private String ticketID;
    private String spotID;
    
//    private int duration;
//    private long timestamp;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getTimeStart() { return timeStart; }
    public void setTimeStart(String timeStart) { this.timeStart = timeStart; }

    public String getTimeEnd() { return timeEnd; }
    public void setTimeEnd(String timeEnd) { this.timeEnd = timeEnd; }

    public String getTicketID() { return ticketID; }
    public void setTicketID(String ticketID) { this.ticketID = ticketID; }

    public String getSpotID() { return spotID; }
    public void setSpotID(String spotID) { this.spotID = spotID; }
    
    public static void generateTicket(String name, String plate, int duration, ParkingSpot selectedSpot) {
    	Ticket newTicket = new Ticket();
    	newTicket.setName(name);
    	newTicket.setLicensePlate(plate);
//    	newTicket.setDuration(duration);
    	newTicket.setSpotID(selectedSpot.getLabel());  // assuming you passed this into the form
    	long startTime = System.currentTimeMillis();
    	long durationMillis = duration * 60L * 60 * 1000; // if duration is in hours
    	long endTime = startTime + durationMillis;

    	newTicket.setTimeStart(String.valueOf(startTime));
    	newTicket.setTimeEnd(String.valueOf(endTime));

    	
    	try {
    	    ObjectMapper mapper = new ObjectMapper();
    	    File ticketFile = new File("Tickets.json");

    	    List<Ticket> tickets = new ArrayList<>();

    	    // Only read if file exists
    	    if (ticketFile.exists()) {
    	        tickets = mapper.readValue(ticketFile, new TypeReference<List<Ticket>>() {});
    	    }

    	    tickets.add(newTicket);  // Add the new ticket

    	    // Write back to JSON
    	    mapper.writerWithDefaultPrettyPrinter().writeValue(ticketFile, tickets);

    	} catch (IOException ex) {
    	    ex.printStackTrace();
    	    JOptionPane.showMessageDialog(null, "Error saving ticket!", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
//    public int getDuration() { return duration; }
//    public void setDuration(int duration) { this.duration = duration; }
//
//    public long getTimestamp() { return timestamp; }
//    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}

