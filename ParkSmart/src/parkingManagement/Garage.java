package parkingManagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Garage {
	private Integer garageID;
	private String name;
	private String city;
	private String owner;
	private String address;
//	private 
	private String parkingSpot;
	private Integer contractID;
	@JsonProperty("garageOpen")
    private boolean garageOpenStatus;
    private List<Level> levels;

    public Garage() {}
    public Garage(/*Integer garageID,*/ String name, String city, String owner, String address, /*String parkingSpot, Integer contractID,*/ boolean garageOpenStatus, List<Level> levels) {
//        this.garageID = garageID;
        this.name = name;
        this.city = city;
        this.owner = owner;
        this.address = address;
        
//        this.parkingSpot = parkingSpot;
//        this.contractID = contractID;
        this.garageOpenStatus = garageOpenStatus;
        this.levels = levels;
    }
    
    // Getters
    public Integer getGarageID() { return garageID; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getOwner() { return owner; }
    public String getAddress() { return address; }
    public String getParkingSpot() { return parkingSpot; }
    public Integer getContractID() { return contractID; }
    public boolean isGarageOpenStatus() { return garageOpenStatus; }
    public List<Level> getLevels() { return levels; }
    
    
    // Setters
    public void setGarageID(Integer garageID) { this.garageID = garageID; }
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setAddress(String address) { this.address = address; }
    public void setParkingSpot(String parkingSpot) { this.parkingSpot = parkingSpot; }
    public void setContractID(Integer contractID) { this.contractID = contractID; }
    public void setGarageOpenStatus(boolean garageOpenStatus) { this.garageOpenStatus = garageOpenStatus; }
    public void setLevels(List<Level> levels) { this.levels = levels; }  
}