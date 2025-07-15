package garage;

import java.util.List;

public class Level {
    private final int levelNumber;
    private final List<ParkingSpot> spots;

    public Level(int levelNumber, List<ParkingSpot> spots) {
        this.levelNumber = levelNumber;
        this.spots = spots;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }
}
