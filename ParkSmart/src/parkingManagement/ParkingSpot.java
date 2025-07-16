package parkingManagement;

public class ParkingSpot {
    private String spotID;
    private String description;
    private boolean availability;
    private String ticketID;
    private Integer garageID;
    private Integer streetID;
    private String spaceType;
    private String parkingDirection;
    private Integer rate;
    private String city;

    public ParkingSpot(String sID, 
    		String description, 
    		boolean avail, 
    		String ticketID, 
    		Integer gID, 
    		Integer stID, 
    		String sType, 
    		String pDirection,
    		Integer rate,
    		String city) {
    	this.spotID = sID;
    	this.description = description;
    	this.availability = avail;
    	this.ticketID = ticketID;
    	this.garageID = gID;
    	this.streetID = stID;
    	this.spaceType = sType;
    	this.parkingDirection = pDirection;
    	this.rate = rate;
    	this.city = city;
    }
    
    // Getters
    public String getSpotID() { return spotID; }
    public String getDescription() { return description; }
    public boolean isAvailability() { return availability; }
    public String getTicketID() { return ticketID; }
    public Integer getGarageID() { return garageID; }
    public Integer getStreetID() { return streetID; }
    public String getSpaceType() { return spaceType; }
    public String getParkingDirection() { return parkingDirection; }
    public Integer getRate() { return rate; }
    public String getCity() { return city; }
    
    // Setters
    public void setSpotID(String spotID) { this.spotID = spotID; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailability(boolean availability) { this.availability = availability; }
    public void setTicketID(String ticketID) { this.ticketID = ticketID; }
    public void setGarageID(Integer garageID) { this.garageID = garageID; }
    public void setStreetID(Integer streetID) { this.streetID = streetID; }
    public void setSpaceType(String spaceType) { this.spaceType = spaceType; }
    public void setParkingDirection(String parkingDirection) { this.parkingDirection = parkingDirection; }
    public void setRate(Integer rate) { this.rate = rate; }   
    public void setCity(String city) { this.city = city; }
}