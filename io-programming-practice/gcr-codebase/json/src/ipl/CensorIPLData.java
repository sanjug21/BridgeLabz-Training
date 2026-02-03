package ipl;

import java.util.*;

public class CensorIPLData {
    
    public static void main(String[] args) throws Exception {
        String jsonPath = "io-programming-practice/gcr-codebase/json/src/ipl/ipl_data.json";
        String csvPath = "io-programming-practice/gcr-codebase/json/src/ipl/ipl_data.csv";
        
        System.out.println("=== IPL Data Censorship Application ===\n");
        
        // Process JSON
        System.out.println("Reading JSON data...");
        List<IPLMatch> jsonMatches = IPLDataUtils.readJSON(jsonPath);
        System.out.println("Read " + jsonMatches.size() + " matches from JSON");
        
        List<IPLMatch> censoredJson = IPLDataUtils.censorData(jsonMatches);
        IPLDataUtils.writeJSON(censoredJson, "io-programming-practice/gcr-codebase/json/src/ipl/ipl_data_censored.json");
        
        // Process CSV
        System.out.println("\nReading CSV data...");
        List<IPLMatch> csvMatches = IPLDataUtils.readCSV(csvPath);
        System.out.println("Read " + csvMatches.size() + " matches from CSV");
        
        List<IPLMatch> censoredCsv = IPLDataUtils.censorData(csvMatches);
        IPLDataUtils.writeCSV(censoredCsv, "io-programming-practice/gcr-codebase/json/src/ipl/ipl_data_censored.csv");
        
        System.out.println("\n Censorship complete!");
        System.out.println("Censorship applied:");
        System.out.println("- Team names: First word + *** (e.g., Mumbai Indians → Mumbai ***)");
        System.out.println("- Player of match: REDACTED");
    }
}
