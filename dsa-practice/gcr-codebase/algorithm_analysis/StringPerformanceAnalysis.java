public class StringPerformanceAnalysis {

    // String: Immutable, O(N^2) when concatenating in a loop
    // Creates a new String object and copies data in every iteration.
    public static void concatString(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a";
        }
         System.out.println(s);
    }

    // StringBuilder: Mutable, Not Thread-Safe, O(N)
    // Modifies the existing buffer, resizing only when necessary.
    public static void concatStringBuilder(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
    }

    // StringBuffer: Mutable, Thread-Safe, O(N)
    // Similar to StringBuilder but methods are synchronized.
    public static void concatStringBuffer(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 100000, 1000000};

        System.out.printf("%-20s %-20s %-20s %-20s%n", "Operations (N)", "String (ms)", "StringBuilder (ms)", "StringBuffer (ms)");
        System.out.println("--------------------------------------------------------------------------------");

        for (int n : datasetSizes) {
            // String Concatenation
            String stringTime;
            // Skip String for N > 20,000 as it becomes too slow (O(N^2))
            if (n > 20000) {
                stringTime = "Unusable";
            } else {
                long start = System.currentTimeMillis();
                concatString(n);
                long end = System.currentTimeMillis();
                stringTime = (end - start) + " ms";
            }

            // StringBuilder Concatenation
            long start = System.currentTimeMillis();
            concatStringBuilder(n);
            long end = System.currentTimeMillis();
            String builderTime = (end - start) + " ms";

            // StringBuffer Concatenation
            start = System.currentTimeMillis();
            concatStringBuffer(n);
            end = System.currentTimeMillis();
            String bufferTime = (end - start) + " ms";

            System.out.printf("%-20d %-20s %-20s %-20s%n", n, stringTime, builderTime, bufferTime);
        }
    }
}