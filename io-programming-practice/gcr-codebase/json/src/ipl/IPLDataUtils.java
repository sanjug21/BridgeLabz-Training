package ipl;

import java.io.*;
import java.util.*;
import org.json.*;

public class IPLDataUtils {
    
    // Censor team name - keep first word, mask rest with ***
    public static String censorTeamName(String teamName) {
        String[] parts = teamName.split(" ");
        if (parts.length > 1) {
            return parts[0] + " ***";
        }
        return teamName;
    }
    
    // Redact player name
    public static String redactPlayer(String playerName) {
        return "REDACTED";
    }
    
    // Read JSON file
    public static List<IPLMatch> readJSON(String filePath) throws Exception {
        List<IPLMatch> matches = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        
        JSONArray array = new JSONArray(sb.toString());
        
        for (int i = 0; i < array.length(); i++) {
            JSONObject match = array.getJSONObject(i);
            int match_id = match.getInt("match_id");
            String team1 = match.getString("team1");
            String team2 = match.getString("team2");
            
            JSONObject score = match.getJSONObject("score");
            int score1 = score.getInt(team1);
            int score2 = score.getInt(team2);
            
            String winner = match.getString("winner");
            String player_of_match = match.getString("player_of_match");
            
            matches.add(new IPLMatch(match_id, team1, team2, score1, score2, winner, player_of_match));
        }
        return matches;
    }
    
    // Read CSV file
    public static List<IPLMatch> readCSV(String filePath) throws Exception {
        List<IPLMatch> matches = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine(); // Skip header
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int match_id = Integer.parseInt(parts[0]);
            String team1 = parts[1];
            String team2 = parts[2];
            int score1 = Integer.parseInt(parts[3]);
            int score2 = Integer.parseInt(parts[4]);
            String winner = parts[5];
            String player_of_match = parts[6];
            
            matches.add(new IPLMatch(match_id, team1, team2, score1, score2, winner, player_of_match));
        }
        reader.close();
        return matches;
    }
    
    // Apply censorship
    public static List<IPLMatch> censorData(List<IPLMatch> matches) {
        List<IPLMatch> censored = new ArrayList<>();
        for (IPLMatch match : matches) {
            censored.add(new IPLMatch(
                match.match_id,
                censorTeamName(match.team1),
                censorTeamName(match.team2),
                match.score1,
                match.score2,
                censorTeamName(match.winner),
                redactPlayer(match.player_of_match)
            ));
        }
        return censored;
    }
    
    // Write censored JSON
    public static void writeJSON(List<IPLMatch> matches, String filePath) throws Exception {
        JSONArray array = new JSONArray();
        
        for (IPLMatch match : matches) {
            JSONObject obj = new JSONObject();
            obj.put("match_id", match.match_id);
            obj.put("team1", match.team1);
            obj.put("team2", match.team2);
            
            JSONObject score = new JSONObject();
            score.put(match.team1, match.score1);
            score.put(match.team2, match.score2);
            obj.put("score", score);
            
            obj.put("winner", match.winner);
            obj.put("player_of_match", match.player_of_match);
            
            array.put(obj);
        }
        
        FileWriter writer = new FileWriter(filePath);
        writer.write(array.toString(2));
        writer.close();
        System.out.println("Censored JSON written to: " + filePath);
    }
    
    // Write censored CSV
    public static void writeCSV(List<IPLMatch> matches, String filePath) throws Exception {
        FileWriter writer = new FileWriter(filePath);
        writer.write("match_id,team1,team2,score_team1,score_team2,winner,player_of_match\n");
        
        for (IPLMatch match : matches) {
            writer.write(match.match_id + "," + 
                        match.team1 + "," + 
                        match.team2 + "," + 
                        match.score1 + "," + 
                        match.score2 + "," + 
                        match.winner + "," + 
                        match.player_of_match + "\n");
        }
        writer.close();
        System.out.println("Censored CSV written to: " + filePath);
    }
}
