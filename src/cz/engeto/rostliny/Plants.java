package cz.engeto.rostliny;

import java.time.LocalDate;

public class Plants implements Comparable<Plants>{
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate lastWatered;
    private int wateringInterval;


    public Plants(String name, String notes, LocalDate planted, LocalDate lastWatered, int wateringInterval) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setLastWatered(lastWatered);
        setWateringInterval(wateringInterval);
    }

    public Plants(String name, LocalDate planted, int wateringInterval) throws PlantException {
        this(name, "", planted, LocalDate.now(), wateringInterval);
    }

    public Plants(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }


    public String getWateringInfo() {
        LocalDate nextWatering = lastWatered.plusDays(wateringInterval);
        return "Plant: " + name + ", last watered on " + lastWatered + ", recommended next watering on " + nextWatering;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }
    public int getWateringInterval() {
        return wateringInterval;
    }

    public void setLastWatered(LocalDate lastWatered) throws PlantException{
        if (lastWatered.isBefore(planted)) {
            throw new PlantException("Last watering date cannot be before the planting date! Planted on: " + planted + "!");
        }
        this.lastWatered = lastWatered;
    }

    public void setWateringInterval(int wateringInterval) throws PlantException{
        if (wateringInterval <= 0) {
            throw new PlantException("Interval must be greater than zero! Given interval: " + wateringInterval + "!");
        }
        this.wateringInterval = wateringInterval;
    }

    @Override
    public int compareTo(Plants plant) {
        return this.name.compareTo(plant.getName());
    }
    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", planted=" + planted +
                ", lastWatered=" + lastWatered +
                ", wateringInterval=" + wateringInterval +
                '}';
    }
}
