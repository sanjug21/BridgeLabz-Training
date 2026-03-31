import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResumeShortlistingManager {
    private List<Resume> resumes;
    private Set<String> requiredSkills;

    public ResumeShortlistingManager() {
        this.resumes = new ArrayList<>();
        this.requiredSkills = new HashSet<>();
    }

    public void addRequiredSkill(String skill) {
        requiredSkills.add(skill.toLowerCase());
        System.out.println("Required skill added: " + skill);
    }

    public void addResume(Resume resume) throws InvalidResumeException {
        if (resume.name == null || resume.name.isEmpty()) {
            throw new InvalidResumeException("Invalid resume: Name cannot be empty");
        }

        if (resume.email == null || !resume.email.contains("@")) {
            throw new InvalidResumeException("Invalid resume: Invalid email format");
        }

        if (resume.experience < 0) {
            throw new InvalidResumeException("Invalid resume: Experience cannot be negative");
        }

        if (resume.skills.isEmpty()) {
            throw new InvalidResumeException("Invalid resume: No skills provided");
        }

        resumes.add(resume);
        System.out.println("Resume added: " + resume.name);
    }

    public void rankResumes() {
        for (Resume resume : resumes) {
            resume.calculateMatchScore(requiredSkills);
        }

        resumes.sort(new Comparator<Resume>() {
            @Override
            public int compare(Resume r1, Resume r2) {
                int scoreCompare = Integer.compare(r2.matchScore, r1.matchScore);
                if (scoreCompare != 0) {
                    return scoreCompare;
                }
                return Integer.compare(r2.experience, r1.experience);
            }
        });

        System.out.println("Resumes ranked successfully");
    }

    public void displayShortlistedResumes() {
        System.out.println("\n=======================================");
        System.out.println("      SHORTLISTED RESUMES");
        System.out.println("=======================================");

        if (resumes.isEmpty()) {
            System.out.println("No resumes found.");
            return;
        }

        System.out.println("Required Skills: " + requiredSkills);
        System.out.println("\nRank | Candidate ID | Name | Experience | Match Score");
        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < resumes.size(); i++) {
            Resume r = resumes.get(i);
            System.out.printf("%-4d | %-12s | %-10s | %-10s | %d/%d%n",
                (i + 1), r.candidateId, r.name, 
                r.experience + " yrs", r.matchScore, requiredSkills.size());
        }

        System.out.println("=======================================");
    }

    public void displayResumeDetails(String candidateId) {
        for (Resume r : resumes) {
            if (r.candidateId.equals(candidateId)) {
                System.out.println("\n--- Resume Details ---");
                System.out.println(r.getDetailedInfo());
                return;
            }
        }
        System.out.println("Resume not found: " + candidateId);
    }

    public void displayAllResumes() {
        System.out.println("\n--- All Resumes ---");
        if (resumes.isEmpty()) {
            System.out.println("No resumes found.");
            return;
        }
        for (Resume r : resumes) {
            System.out.println(r);
        }
    }

    public void displayRequiredSkills() {
        System.out.println("\n--- Required Skills ---");
        if (requiredSkills.isEmpty()) {
            System.out.println("No required skills set.");
            return;
        }
        System.out.println(requiredSkills);
    }

    public void displayStatistics() {
        System.out.println("\n--- Statistics ---");
        System.out.println("Total Resumes: " + resumes.size());
        System.out.println("Required Skills: " + requiredSkills.size());

        if (!resumes.isEmpty()) {
            int perfectMatch = 0;
            for (Resume r : resumes) {
                if (r.matchScore == requiredSkills.size()) {
                    perfectMatch++;
                }
            }
            System.out.println("Perfect Match: " + perfectMatch);
        }
    }
}
