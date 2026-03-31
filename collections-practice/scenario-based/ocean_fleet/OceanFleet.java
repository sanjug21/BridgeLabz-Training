import java.util.List;
import java.util.Scanner;

public class OceanFleet {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VesselUtil vesselUtil = new VesselUtil();

        System.out.println("Enter the number of vessels to be added");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter vessel details");
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                i--;
                continue;
            }
            String[] parts = line.split(":");
            String vesselId = parts[0];
            String vesselName = parts[1];
            double averageSpeed = Double.parseDouble(parts[2]);
            String vesselType = parts[3];

            Vessel vessel = new Vessel(vesselId, vesselName, averageSpeed, vesselType);
            vesselUtil.addVesselPerformance(vessel);
        }

        System.out.println("Enter the Vessel Id to check speed");
        String checkId = sc.nextLine().trim();
        Vessel found = vesselUtil.getVesselById(checkId);
        if (found == null) {
            System.out.println("Vessel Id " + checkId + " not found");
        } else {
            System.out.println(formatVessel(found));
        }

        System.out.println("High performance vessels are");
        List<Vessel> highPerformance = vesselUtil.getHighPerformanceVessels();
        for (Vessel vessel : highPerformance) {
            System.out.println(formatVessel(vessel));
        }

        sc.close();
    }

    private static String formatVessel(Vessel vessel) {
        return vessel.getVesselId() + " | " + vessel.getVesselName() + " | "
                + vessel.getVesselType() + " | " + vessel.getAverageSpeed() + " knots";
    }
}
