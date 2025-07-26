package main;

public class ParkingRecord {
    private String username;
    private String name;
    private String licensePlate;
    private String timeStart;
    private String timeEnd;
    private String ticketID;
    private String spotID;

    // Getters and setters (can be generated with your IDE or written manually)
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
}
