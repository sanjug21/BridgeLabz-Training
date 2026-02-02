import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {

    public void RegisterCreator(CreatorStats record) {
        CreatorStats.EngagementBoard.add(record);
    }

    public Dictionary<String, Integer> GetTopPostCounts(List<CreatorStats> records, double likeThreshold) {
        Map<String, Integer> result = new HashMap<>();

        for (CreatorStats creator : records) {
            int count = 0;
            for (double likes : creator.WeeklyLikes) {
                if (likes >= likeThreshold) {
                    count++;
                }
            }

            if (count > 0) {
                result.put(creator.CreatorName, count);
            }
        }

        return new java.util.Hashtable<>(result);
    }

    public double CalculateAverageLikes() {
        double total = 0;
        int count = 0;

        for (CreatorStats creator : CreatorStats.EngagementBoard) {
            for (double likes : creator.WeeklyLikes) {
                total += likes;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return total / count;
    }

    private static void printMenu() {
        System.out.println("1. Register Creator");
        System.out.println("2. Show Top Posts");
        System.out.println("3. Calculate Average Likes");
        System.out.println("4. Exit");
        System.out.println();
        System.out.println("Enter your choice:");
    }

    private static void handleRegister(Program program, Scanner sc) {
        System.out.println("Enter Creator Name:");
        String name = sc.nextLine().trim();

        double[] weeklyLikes = new double[4];
        System.out.println("Enter weekly likes (Week 1 to 4):");
        for (int i = 0; i < 4; i++) {
            String likeInput = sc.nextLine().trim();
            weeklyLikes[i] = Double.parseDouble(likeInput);
        }

        CreatorStats record = new CreatorStats(name, weeklyLikes);
        program.RegisterCreator(record);
        System.out.println("Creator registered successfully");
        System.out.println();
    }

    private static void handleTopPosts(Program program, Scanner sc) {
        System.out.println("Enter like threshold:");
        String thresholdInput = sc.nextLine().trim();
        double likeThreshold = Double.parseDouble(thresholdInput);

        Dictionary<String, Integer> result = program.GetTopPostCounts(CreatorStats.EngagementBoard, likeThreshold);

        if (result.isEmpty()) {
            System.out.println("No top-performing posts this week");
        } else {
            List<String> keys = new ArrayList<>();
            for (java.util.Enumeration<String> e = result.keys(); e.hasMoreElements(); ) {
                keys.add(e.nextElement());
            }
            keys.sort(String::compareTo);

            for (String key : keys) {
                System.out.println(key + " - " + result.get(key));
            }
        }
        System.out.println();
    }

    private static void handleAverage(Program program) {
        double average = program.CalculateAverageLikes();
        if (average == (long) average) {
            System.out.println("Overall average weekly likes: " + (long) average);
        } else {
            System.out.println("Overall average weekly likes: " + average);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Program program = new Program();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();

            String choiceInput = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (choice) {
                case 1:
                    handleRegister(program, sc);
                    break;
                case 2:
                    handleTopPosts(program, sc);
                    break;
                case 3:
                    handleAverage(program);
                    break;
                case 4:
                    System.out.println("Logging off - Keep Creating with StreamBuzz!");
                    sc.close();
                    return;
                default:
                    break;
            }
        }
    }
}
