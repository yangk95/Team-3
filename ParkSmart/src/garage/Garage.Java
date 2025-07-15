package garage;

import java.util.List;

public class Garage {
    private String name;
    private String city;
    private String owner;
    private boolean garageOpenStatus;
    private List<Level> levels;

    // public Garage() {
    //     // 
    // }

    public Garage(String name, String city, String owner, boolean garageOpen, List<Level> levels) {
        this.name = name;
        this.city = city;
        this.owner = owner;
        this.garageOpenStatus = garageOpen;
        this.levels = levels;
    }   

    public String getName() { return name; }
    public String getCity() { return city; }
    public String getOwner() { return owner; }
    public boolean getGarageOpenStatus() { return garageOpenStatus; }
    public List<Level> getLevels() { return levels; }
}
