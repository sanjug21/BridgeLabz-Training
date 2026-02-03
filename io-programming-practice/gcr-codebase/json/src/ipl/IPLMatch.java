package ipl;

public class IPLMatch {
    public int match_id;
    public String team1;
    public String team2;
    public int score1;
    public int score2;
    public String winner;
    public String player_of_match;
    
    public IPLMatch(int match_id, String team1, String team2, int score1, int score2, 
                    String winner, String player_of_match) {
        this.match_id = match_id;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.winner = winner;
        this.player_of_match = player_of_match;
    }
}
