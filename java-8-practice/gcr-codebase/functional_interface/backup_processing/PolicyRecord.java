package backup_processing;

class PolicyRecord implements Backupable {
    private String policyId;
    private String customerName;
    private double premium;

    public PolicyRecord(String policyId, String customerName, double premium) {
        this.policyId = policyId;
        this.customerName = customerName;
        this.premium = premium;
    }

    @Override
    public String toString() {
        return "Policy[ID=" + policyId + ", Customer=" + customerName + ", Premium=$" + String.format("%.2f", premium) + "]";
    }
}
