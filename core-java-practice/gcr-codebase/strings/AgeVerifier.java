import java.util.Random;

public class AgeVerifier {

    public static void main(String[] args) {
        int numberOfStudents=10;
        System.out.println("Generating and checking ages for "+numberOfStudents+" students.");

        int[] ages=generateRandomAges(numberOfStudents);
        String[][] eligibility=checkEligibility(ages);
        displayEligibilityTable(eligibility);
    }

    public static int[] generateRandomAges(int count) {
        Random random=new Random();
        int[] ages=new int[count];
        for (int i=0;i<count;i++) {
            // Generates a random age (10-99)
            ages[i]=10+random.nextInt(90);
        }
        return ages;
    }

    public static String[][] checkEligibility(int[] ages) {
        String[][] results=new String[ages.length][2];
        for (int i=0;i<ages.length;i++) {
            int age=ages[i];
            results[i][0]=String.valueOf(age);
            // Validate age and check if 18 or above
            if (age<0) {
                results[i][1]="false"; 
                results[i][1]="false"; 
            } else {
                results[i][1]=(age>=18)?"true":"false";
            }
        }
        return results;
    }

    public static void displayEligibilityTable(String[][] data) {
        System.out.println("\nAge | Can Vote?");
        for (String[] row:data) {
            boolean canVote=Boolean.parseBoolean(row[1]);
            System.out.println(row[0] + " | " + canVote);
        }
    }
}