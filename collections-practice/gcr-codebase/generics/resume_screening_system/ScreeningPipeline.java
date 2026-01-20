import java.util.List;

public class ScreeningPipeline {

    // Generic Method: Processes a single resume with strict type safety
    public static <T extends JobRole> void processSingleResume(Resume<T> resume) {
        double score = resume.screen();
        String status = score > 50.0 ? "SHORTLISTED" : "REJECTED";

        System.out.printf("Candidate: %-15s | Role: %-18s | Score: %5.1f%% | Status: %s%n",
                resume.getCandidateName(),
                resume.getAppliedRole().getTitle(),
                score,
                status);
    }

    // Wildcard Method: Processes a list of resumes for ANY job role.
    // List<Resume<? extends JobRole>> means a list of Resumes where the role is
    // unknown,
    // but guaranteed to be a JobRole subtype.
    public static void processBatch(List<Resume<? extends JobRole>> batch) {
        System.out.println("\n--- Processing Batch ---");
        for (Resume<? extends JobRole> resume : batch) {
            processSingleResume(resume);
        }
    }

    // Wildcard Method: Analyzing a list of JobRoles directly
    public static void analyzeOpenings(List<? extends JobRole> openRoles) {
        System.out.println("\n--- Analyzing Open Roles ---");
        for (JobRole role : openRoles) {
            // We can read from ? extends JobRole as 'JobRole'
            System.out.println("Open Position: " + role.getTitle());
        }
    }
}
