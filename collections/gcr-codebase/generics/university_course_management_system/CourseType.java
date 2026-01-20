
public abstract class CourseType {
    private String name;

    public CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getEvaluationType();
}
