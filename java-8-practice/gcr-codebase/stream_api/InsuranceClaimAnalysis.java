import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class InsuranceClaim {
    String claimType;
    double amount;

    public InsuranceClaim(String claimType, double amount) {
        this.claimType = claimType;
        this.amount = amount;
    }

    public String getClaimType() {
        return claimType;
    }

    public double getAmount() {
        return amount;
    }
}

public class InsuranceClaimAnalysis {

    public static void main(String[] args) {
        List<InsuranceClaim> claims = new ArrayList<>();
        claims.add(new InsuranceClaim("Health", 15000));
        claims.add(new InsuranceClaim("Auto", 8000));
        claims.add(new InsuranceClaim("Health", 22000));
        claims.add(new InsuranceClaim("Home", 35000));
        claims.add(new InsuranceClaim("Auto", 12000));
        claims.add(new InsuranceClaim("Health", 18000));
        claims.add(new InsuranceClaim("Home", 28000));
        claims.add(new InsuranceClaim("Auto", 9500));

        Map<String, Double> averages = claims.stream()
                .collect(Collectors.groupingBy(
                        InsuranceClaim::getClaimType,
                        Collectors.averagingDouble(InsuranceClaim::getAmount)
                ));

        System.out.println("Average Claim Amount by Type:");
        averages.forEach((type, avg) -> 
                System.out.println(type + ": $" + String.format("%.2f", avg)));
    }
}
