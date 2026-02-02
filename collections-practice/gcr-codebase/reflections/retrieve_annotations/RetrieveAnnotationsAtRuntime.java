package retrieve_annotations;

public class RetrieveAnnotationsAtRuntime {

    public static void main(String[] args) {
        Class<?> clazz = UserService.class;

        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Author: " + author.name());
        }
    }
}
