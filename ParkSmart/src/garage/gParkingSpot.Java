package garage;

public class ParkingSpot {
    private final String label;
    private final String description;

    public ParkingSpot(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}