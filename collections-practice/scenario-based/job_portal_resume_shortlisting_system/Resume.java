import java.util.HashSet;
import java.util.Set;

class Resume {
    String candidateId;
    String name;
    String email;
    int experience;
    Set<String> skills;
    int matchScore;

    public Resume(String candidateId, String name, String email, int experience) {
        this.candidateId = candidateId;
        this.name = name;
        this.email = email;
        this.experience = experience;
        this.skills = new HashSet<>();
        this.matchScore = 0;
    }

    public void addSkill(String skill) {
        skills.add(skill.toLowerCase());
    }

    public void calculateMatchScore(Set<String> requiredSkills) {
        matchScore = 0;
        for (String skill : requiredSkills) {
            if (skills.contains(skill.toLowerCase())) {
                matchScore++;
            }
        }
    }

    @Override
    public String toString() {
        return candidateId + " | " + name + " | Experience: " + experience + 
               " years | Skills: " + skills.size() + " | Match Score: " + matchScore;
    }

    public String getDetailedInfo() {
        return candidateId + " | " + name + " | " + email + "\n" +
               "Experience: " + experience + " years\n" +
               "Skills: " + skills + "\n" +
               "Match Score: " + matchScore;
    }
}
