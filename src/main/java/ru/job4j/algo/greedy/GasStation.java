package ru.job4j.algo.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas4Fill = 0;
        int totalGas4Spend = 0;
        int currentGasInTank = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas4Fill += gas[i];
            totalGas4Spend += cost[i];
            currentGasInTank += gas[i] - cost[i];

            if (currentGasInTank < 0) {
                startStation = i + 1;
                currentGasInTank = 0;
            }
        }

        return totalGas4Fill >= totalGas4Spend ? startStation : -1;
    }

    public static void main(String[] args) {
        GasStation gs = new GasStation();

        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println(gs.canCompleteCircuit(gas1, cost1));

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println(gs.canCompleteCircuit(gas2, cost2));
    }
}