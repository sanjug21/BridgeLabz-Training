public class GasStation {
    // 134. Gas Station
    // https://leetcode.com/problems/gas-station/
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        GasStation obj = new GasStation();
        System.out.println(obj.canCompleteCircuit(gas, cost)); // Expected: 3

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        System.out.println(obj.canCompleteCircuit(gas2, cost2)); // Expected: -1
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int startIdx = 0;
        int total = 0;
        int curr = 0;
        for (int i = 0; i < n; i++) {
            int net = gas[i] - cost[i];
            total += net;
            curr += net;
            if (curr < 0) {
                curr = 0;
                startIdx = i + 1;
            }

        }
        if (total >= 0)
            return startIdx;
        return -1;
    }
    
}
