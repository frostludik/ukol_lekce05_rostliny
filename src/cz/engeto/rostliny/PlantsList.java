package cz.engeto.rostliny;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantsList {
    private List<Plants> plantsList = new ArrayList<>();



    public void addPlant(Plants plant) {
        plantsList.add(plant);
    }

    public void removePlant(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= plantsList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ". You can use index from 0 to " + (plantsList.size() - 1));
        }
        this.plantsList.remove(index);

    }
    public Plants getPlantFromIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= plantsList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ". You can use index from 0 to " + (plantsList.size() - 1));
        }
        return plantsList.get(index);
    }



    public void loadPlantsFromFile(String fileName) throws PlantException {
        int lineCounter = 0;
        plantsList.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                lineCounter++;
                String line = scanner.nextLine();
                String[] parts = line.split(Settings.getDelimiter());
                if (parts.length != 5) throw new PlantException(
                        "Incorrect number of entries at line: " + lineCounter + ": " + line + " !");
                String name = parts[0];
                String notes = parts[1];
                LocalDate planted = LocalDate.parse(parts[4]);
                LocalDate lastWatered = LocalDate.parse(parts[3]);
                int wateringInterval = Integer.parseInt(parts[2]);
                Plants plant = new Plants(name, notes, planted, lastWatered, wateringInterval);
                plantsList.add(plant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("File " + fileName + " not found!\n" + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new PlantException("Error reading number values at line: " + lineCounter + ":\n"
                    + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            throw new PlantException("Error reading value at line: " + lineCounter + ":\n"
                    + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException("Error reading date at line: " + lineCounter + ":\n"
                    + e.getLocalizedMessage());
        }

    }
    public void savePlantsToFile(String outputFileName) throws PlantException {
        String delimiter = Settings.getDelimiter();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)))) {
            for (Plants item : plantsList) {
                writer.println(item.getName() + delimiter
                        + item.getNotes() + delimiter
                        + item.getWateringInterval() + delimiter
                        + item.getLastWatered() + delimiter
                        + item.getPlanted());

            }
        } catch (FileNotFoundException e) {
            throw new PlantException("File "+outputFileName+" not found!\n"
                    + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new PlantException("Output error during writing into file: "+outputFileName
                    +":\n"+ e.getLocalizedMessage());
        }
    }

    public List<Plants> getPlantsList() {
        return new ArrayList<>(plantsList);
    }

    public void setPlantsList(List<Plants> plantsList) {
        this.plantsList = plantsList;
    }

    public List<Plants> getPlants(int i) {
        return new ArrayList<>(plantsList);
    }
}
