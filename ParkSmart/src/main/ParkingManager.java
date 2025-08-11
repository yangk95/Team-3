package main;

import parkingManagement.Garage;
import parkingManagement.Level;
import parkingManagement.ParkingSpot;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ParkingManager {
	private static List<Garage> garages;
	private static File ticketFile = new File("Ticket.json");
	private static String contract = "resources/contract/Contract1.json";
	private static List<ParkingSpot> spots = new ArrayList<ParkingSpot>();
	
    // Initiate parking contract to instantiate parking spot
    public static void init() {
    	ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = ParkingManager.class.getClassLoader().getResourceAsStream(contract);
            if (is == null) {
                throw new RuntimeException("Parking spots file not found.");
            }
            
            garages = mapper.readValue(is, new TypeReference<List<Garage>>() {});
            
            for (Garage garage : garages) {
            	for (Level level : garage.getLevels()) {
            		for (ParkingSpot spot: level.getSpots()) {
            			spots.add(spot);
            		}
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
//            parkingSpots = new ArrayList<>();
        }
    }
    
    public static List<ParkingSpot> getParkingSpots() {
//    	spots = checkAvailability();
        return spots;
    }
    
    public static List<ParkingSpot> checkAvailability() {
    	try {
	    	ObjectMapper mapper = new ObjectMapper();
//	    	InputStream is = Ticket.class.getClassLoader().getResourceAsStream(ticketFile);
//	        if (is == null ) {
//	            System.out.println("File not found: " + ticketFile);
//	         }
			List<Ticket> allTickets = mapper.readValue(ticketFile, new TypeReference<List<Ticket>>() {});
	    	for (ParkingSpot spot: spots) {
	    		boolean isTaken = false;
//	    		System.out.println(spot.getLabel());
	    		for (Ticket ticket : allTickets){
//	    			System.out.println("Ticket: " + ticket.getSpotID());
//	    			System.out.println("Spot: " + spot.getLabel());
	    			if (ticket.getSpotID().equals(spot.getLabel())) {
	    				isTaken = true;
	    				break;
	    			}
	    		}
	    				spot.setAvailability(!isTaken);
	    			
	    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return spots;
    }
    
    //Mark parking spots unavailable
    public static void markSpotUnavailable(String label) {
        for (ParkingSpot spot : spots) {
            if (spot.getLabel().equals(label)) {
                spot.setAvailability(false);
                break;
            }
        }
    }

    
    public static void markSpotAvailable(String label) {
        for (ParkingSpot spot : spots) {
            if (spot.getLabel().equals(label)) {
                spot.setAvailability(true);
                break;
            }
        }
    }
    
    
}
