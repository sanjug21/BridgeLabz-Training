public class Resume<T extends JobRole> {
    private String candidateName;
    private String candidateSkills;
    private T appliedRole; // The specific role instance

    public Resume(String candidateName, String candidateSkills, T appliedRole) {
        this.candidateName = candidateName;
        this.candidateSkills = candidateSkills;
        this.appliedRole = appliedRole;
    }

    public T getAppliedRole() {
        return appliedRole;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public double screen() {
        // We can safely call methods from JobRole because T extends JobRole
        return appliedRole.calculateRelevanceScore(candidateSkills);
    }
}
