package parkingManagement;

import java.util.List;

public class Level {
    private final String levelIdentifier;
    private final List<ParkingSpot> spots;

    public Level(String levelID, List<ParkingSpot> spots) {
        this.levelIdentifier = levelID;
        this.spots = spots;
    }

    public String getLevelIdentifier() { return levelIdentifier; }
    public List<ParkingSpot> getSpots() { return spots; }
}
