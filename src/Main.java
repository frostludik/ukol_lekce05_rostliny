import cz.engeto.rostliny.PlantException;
import cz.engeto.rostliny.Plants;
import cz.engeto.rostliny.PlantsList;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static cz.engeto.rostliny.Settings.getFilename;
import static cz.engeto.rostliny.Settings.getOutputFilename;

public class Main {
    public static void main(String[] args) throws PlantException {

        // Load list from file:
        PlantsList plantsList1 = new PlantsList();
        try {
            plantsList1.loadPlantsFromFile(getFilename());
        } catch (PlantException e) {
            System.err.println("Error loading plants from file: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getLocalizedMessage());
        }


        System.out.println("\n----List of plants was imported from: " + getFilename() + "----");


        //printing watering info
        System.out.println("\n----Watering info----");
        for (int i = 0; i < plantsList1.getPlantsList().size(); i++) {
            System.out.println(plantsList1.getPlantFromIndex(i).getWateringInfo());
        }

        // Add new plants:
        Plants newPlant1 = new Plants("Bazalka", "v kuchyni", LocalDate.now(), LocalDate.now(), 7);
        Plants newPlant2 = new Plants("Tulipán", "na zahradě", LocalDate.now(), LocalDate.now(), 7);

        plantsList1.addPlant(newPlant1);
        plantsList1.addPlant(newPlant2);


        System.out.println("\n----Two new plants were added to the list----");

        // Remove 1 plant:
        try {
            plantsList1.removePlant(4);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error removing plant: " + e.getLocalizedMessage());
        }

        System.out.println("\n----One plant was removed from the list----");

        // Sort plants by name:
        System.out.println("\n----List of plants sorted by name----");
        List<Plants> sortedByName = plantsList1.getPlantsList();
        Collections.sort(sortedByName);
        for(Plants plant:sortedByName){
            System.out.println(plant);
        }

        //sort plants by last watering date
        System.out.println("\n----List of plants sorted by last watering date----");
        List<Plants> sortedByLastWatering = plantsList1.getPlantsList();
        Collections.sort(sortedByLastWatering, (o1, o2) -> o2.getLastWatered().compareTo(o1.getLastWatered()));
        for(Plants plant:sortedByLastWatering){
            System.out.println(plant);
        }

        // Save list to file:
        try {
            plantsList1.savePlantsToFile(getOutputFilename());
        } catch (PlantException e) {
            System.err.println("Error saving plants to file: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getLocalizedMessage());
        }

        System.out.println("\n----List of plants was saved to: " + getOutputFilename() + "----");
    }
}