package parkingManagement;

import java.util.List;

public class ParkingStreet {
	private Integer parkingStreetID;
	private List<ParkingSpot> parkingSpot;
	private Integer contractID;
	private String streetName;

	public ParkingStreet(Integer parkingStreetID, List<ParkingSpot> parkingSpot, Integer contractID, String streetName) {
	    this.parkingStreetID = parkingStreetID;
	    this.parkingSpot = parkingSpot;
	    this.contractID = contractID;
	    this.streetName = streetName;
	}

	// Getters
	public Integer getParkingStreetID() { return parkingStreetID; }
	public List<ParkingSpot> getParkingSpot() { return parkingSpot; }
	public Integer getContractID() { return contractID; }
	public String getStreetName() { return streetName; }

	// Setters
	public void setParkingStreetID(Integer parkingStreetID) { this.parkingStreetID = parkingStreetID; }
	public void setParkingSpot(List<ParkingSpot> parkingSpot) { this.parkingSpot = parkingSpot; }
	public void setContractID(Integer contractID) { this.contractID = contractID; }
	public void setStreetName(String streetName) { this.streetName = streetName; }

}