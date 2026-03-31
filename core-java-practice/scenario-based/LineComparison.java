import java.util.Scanner;

class LineComparison {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("Welcome to Line Comparison Computation Program");
        System.out.println("Choose the Use Case you want to run:");
        System.out.println("1. Calculate Length of a Line");
        System.out.println("2. Check Equality of Two Lines");
        System.out.println("3. Compare Two Lines");
        int useCase = sc.nextInt();
        switch (useCase) {
            case 1:
                lengthOfLine();
                break;
            case 2:
                equalLines();
                break;
            case 3:
                lengthComparison();
                break;
            default:
                System.out.println("Invalid Use Case");
        }            

        sc.close();
    }
    public static void lengthOfLine(){
        // UC-1: As a fan of geometry, I want to model a line based on a point
        // consisting of (x, y)
        // co-ordinates using the Cartesian system,So that I can calculate its length
        System.out.println("UC-1: Calculate Length of a Line");
        System.out.println("Enter x1 and y1 coordinates of first point: ");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        System.out.println("Enter x2 and y2 coordinates of second point: ");
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        double lengthOfLine = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        System.out.println("Length of the line is: " + lengthOfLine);

    }
    public static void equalLines(){
        // Uc-2: As a fan of geometry, I want to check equality of two lines based on
        // the end points,
        // So that I know when two lines are equal. - Using Java equals
        System.out.println("UC-2: Check Equality of Two Lines");
        System.out.println("Enter x1 and y1 coordinates of first point of Line 1: ");
        int L1X1 = sc.nextInt();
        int L1Y1 = sc.nextInt();
        System.out.println("Enter x2 and y2 coordinates of second point of Line 1: ");
        int L1X2 = sc.nextInt();
        int L1Y2 = sc.nextInt();
        double lengthOfLine1 = Math.sqrt(Math.pow((L1X2 - L1X1), 2) + Math.pow((L1Y2 - L1Y1), 2));

        System.out.println("Enter x1 and y1 coordinates of first point of Line 2: ");
        int L2X1 = sc.nextInt();
        int L2Y1 = sc.nextInt();
        System.out.println("Enter x2 and y2 coordinates of second point of Line 2: ");
        int L2X2 = sc.nextInt();
        int L2Y2 = sc.nextInt();
        double lengthOfLine2 = Math.sqrt(Math.pow((L2X2 - L2X1), 2) + Math.pow((L2Y2 - L2Y1), 2));
        int result = Double.compare(lengthOfLine1, lengthOfLine2);
        if (result == 0) {
            System.out.println("Lines are equal");
        } else {
            System.out.println("Lines are not equal");
        }

    }

    public static void lengthComparison(){
        // UC-3: As a fan of geometry, I want to compare two lines based on the end
        // points, So that I know one line is
        // greater than equal, greater or less than the other line. - Using Java
        // compareTo method to compare 2 Lengths
        System.out.println("UC-3: Compare Two Lines");
        System.out.println("Enter x1 and y1 coordinates of first point of Line 1: ");
        int L1X1 = sc.nextInt();
        int L1Y1 = sc.nextInt();
        System.out.println("Enter x2 and y2 coordinates of second point of Line 1: ");
        int L1X2 = sc.nextInt();
        int L1Y2 = sc.nextInt();
        double lengthOfLine1 = Math.sqrt(Math.pow((L1X2 - L1X1), 2) + Math.pow((L1Y2 - L1Y1), 2));

        System.out.println("Enter x1 and y1 coordinates of first point of Line 2: ");
        int L2X1 = sc.nextInt();
        int L2Y1 = sc.nextInt();
        System.out.println("Enter x2 and y2 coordinates of second point of Line 2:");
        int L2X2 = sc.nextInt();
        int L2Y2 = sc.nextInt();
        double lengthOfLine2 = Math.sqrt(Math.pow((L2X2 - L2X1), 2) + Math.pow((L2Y2 - L2Y1), 2));

        int result = Double.compare(lengthOfLine1, lengthOfLine2);
        if (result == 0) {
            System.out.println("Lines are equal");
        } else if (result > 0) {
            System.out.println("Line 1 is greater than Line 2");
        } else {
            System.out.println("Line 2 is greater than Line 1");
        }
    }

}