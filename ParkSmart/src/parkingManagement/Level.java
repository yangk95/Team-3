package parkingManagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Level {
	@JsonProperty("levelNumber")
    private int levelIdentifier;
    private List<ParkingSpot> spots;

    public Level() {}
    
    public Level(int levelID, List<ParkingSpot> spots) {
        this.levelIdentifier = levelID;
        this.spots = spots;
    }

    public int getLevelIdentifier() { return levelIdentifier; }
    public List<ParkingSpot> getSpots() { return spots; }
}
