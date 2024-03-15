package cz.engeto.rostliny;

public class Settings {
    private static final String FILENAME = "resources/kvetiny.txt";
    private static final String OUTPUTFILENAME = "resources/outputFile.txt";
    private static final String DELIMITER = "\t";

    public static String getFilename() { return FILENAME; }
    public static String getDelimiter() {
        return DELIMITER;
    }
    public static String getOutputFilename() {return OUTPUTFILENAME; }
}
