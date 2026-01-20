
public class ResearchCourse extends CourseType {
    private String researchArea;

    public ResearchCourse(String name, String researchArea) {
        super(name);
        this.researchArea = researchArea;
    }

    @Override
    public String getEvaluationType() {
        return "Research-Based (Area: " + researchArea + ")";
    }
}
