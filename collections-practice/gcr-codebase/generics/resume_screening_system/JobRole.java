import java.util.List;

public abstract class JobRole {
    protected String title;
    protected List<String> requiredKeywords;

    public JobRole(String title, List<String> requiredKeywords) {
        this.title = title;
        this.requiredKeywords = requiredKeywords;
    }

    public String getTitle() {
        return title;
    }

    // Simulating an "AI" scoring mechanism based on keyword matching
    public double calculateRelevanceScore(String candidateSkills) {
        if (candidateSkills == null || candidateSkills.isEmpty())
            return 0.0;

        long matchCount =0;
        for (String keyword : requiredKeywords) {
            if (candidateSkills.toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
            }
        }

        return (double) matchCount / requiredKeywords.size() * 100.0;
    }

    @Override
    public String toString() {
        return title;
    }
}
