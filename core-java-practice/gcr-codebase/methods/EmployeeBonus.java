public class EmployeeBonus {
    public static void main(String[] args) {
        EmployeeBonus eb=new EmployeeBonus();
        double[][] data=eb.generateData();
        // index 0 -> Salary, index 1 -> Years
        
        double[][] newData=eb.calculateNewSalary(data);
        
        System.out.println("EmpID\tOld Salary\tYears\tNew Salary\tBonus");
        for(int i=0;i<10;i++){
            System.out.println((i+1)+"\t"+String.format("%.2f",data[i][0])+"\t"+(int)data[i][1]+"\t"+String.format("%.2f",newData[i][0])+"\t"+String.format("%.2f",newData[i][1]));
        }
        
        eb.calculateSums(data,newData);
    }

    public double[][] generateData(){
        double[][] data=new double[10][2];
        for(int i=0;i<10;i++){
            data[i][0]=10000+Math.random()*80000; // Salary
            data[i][1]=Math.random()*15; // Years
        }
        return data;
    }

    public double[][] calculateNewSalary(double[][] data){
        double[][] newData=new double[10][2];
        for(int i=0;i<10;i++){
            double salary=data[i][0];
            double years=data[i][1];
            double bonusPercentage=(years>5)?0.05:0.02;
            double bonus=salary*bonusPercentage;
            newData[i][0]=salary+bonus;
            newData[i][1]=bonus;
        }
        return newData;
    }

    public void calculateSums(double[][] oldData,double[][] newData){
        double sumOld=0;
        double sumNew=0;
        double sumBonus=0;
        for(int i=0;i<10;i++){
            sumOld+=oldData[i][0];
            sumNew+=newData[i][0];
            sumBonus+=newData[i][1];
        }
        System.out.println("Total Old Salary: "+String.format("%.2f",sumOld)+", Total New Salary: "+String.format("%.2f",sumNew)+", Total Bonus: "+String.format("%.2f",sumBonus));
    }
}