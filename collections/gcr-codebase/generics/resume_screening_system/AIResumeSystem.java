import java.util.ArrayList;
import java.util.List;

public class AIResumeSystem {
    public static void main(String[] args) {
        // Create specific roles
        SoftwareEngineer seRole = new SoftwareEngineer();
        DataScientist dsRole = new DataScientist();
        ProductManager pmRole = new ProductManager();

        // Create Resumes (Type Safe)
        Resume<SoftwareEngineer> r1 = new Resume<>(
                "Alice Dev",
                "I know Java, Spring, and Algorithms.",
                seRole);

        Resume<DataScientist> r2 = new Resume<>(
                "Bob Data",
                "Expert in Python and Machine Learning.",
                dsRole);

        Resume<ProductManager> r3 = new Resume<>(
                "Charlie PM",
                "Good at Agile and Roadmaps.",
                pmRole);

        Resume<SoftwareEngineer> r4 = new Resume<>(
                "Dave Junior",
                "I know HTML and CSS.",
                seRole);

        // 1. Process individually (Generic Method)
        ScreeningPipeline.processSingleResume(r1);

        // 2. Process Batch (Wildcard Usage)
        // We need a list that can hold Resume<SE>, Resume<DS>, etc.
        List<Resume<? extends JobRole>> resumeBatch = new ArrayList<>();
        resumeBatch.add(r1);
        resumeBatch.add(r2);
        resumeBatch.add(r3);
        resumeBatch.add(r4);

        ScreeningPipeline.processBatch(resumeBatch);

        // 3. Analyze Roles (Wildcard Usage)
        List<JobRole> roles = List.of(seRole, dsRole, pmRole);
        ScreeningPipeline.analyzeOpenings(roles);
    }
}
