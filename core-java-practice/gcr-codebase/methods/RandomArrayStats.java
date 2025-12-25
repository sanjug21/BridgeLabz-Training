public class RandomArrayStats {
    public static void main(String[] args) {
        RandomArrayStats stats=new RandomArrayStats();
        int[] numbers=stats.generate4DigitRandomArray(5);
        
        System.out.print("Numbers are: ");
        for(int num:numbers)System.out.print(num+" ");
        System.out.println();
        
        // gives average, min and max from the array
        double[] results=stats.findAverageMinMax(numbers);
        System.out.println("Average: "+results[0]);
        System.out.println("Min: "+results[1]);
        System.out.println("Max: "+results[2]);
    }

    public int[] generate4DigitRandomArray(int size){
        int[] arr=new int[size];
        for(int i=0;i<size;i++) arr[i]=1000+(int)(Math.random()*9000);
        return arr;
    }

    public double[] findAverageMinMax(int[] numbers){
        double sum=0;
        double min=numbers[0];
        double max=numbers[0];
        for(int num:numbers){
            sum+=num;
            min=Math.min(min,num);
            max=Math.max(max,num);
        }
        return new double[]{sum/numbers.length,min,max};
    }
}