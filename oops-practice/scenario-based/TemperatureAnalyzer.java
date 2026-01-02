public class TemperatureAnalyzer {
    // Initialize a 2D array to store temperatures for 7 days and 27 time slots each
    // day
    // first index represents days (0-6),
    // second index represents time slots from 0 to 23 (hours)
    // additional slots for 24, 25, and 26 for min max and average temperatures
    double[][] temperatures;
    public TemperatureAnalyzer() {
        temperatures=new double[7][27];
        generateTemperature();
        analyzeTemperature();
    }

    public  void generateTemperature() {
        // Populate the temperature array with random values between -10 and 40 degrees Celsius
        for (int day=0;day<7;day++) {
            for (int hour=0;hour<24;hour++) {
                temperatures[day][hour]=-10+Math.random()*50; // Random temp between -10 and 40
            }
        }   
    }
    public void analyzeTemperature() {
        for (int day=0;day<7;day++) {
            double minTemp=Double.MAX_VALUE;
            double maxTemp=Double.MIN_VALUE;
            double sumTemp=0;
            for (int hour=0;hour<24;hour++) {
                double temp=temperatures[day][hour];
                if (temp<minTemp) {
                    minTemp=temp;
                }
                if (temp>maxTemp) {
                    maxTemp=temp;
                }
                sumTemp+=temp;
            }
            temperatures[day][24]=minTemp; // Store min temp
            temperatures[day][25]=maxTemp; // Store max temp
            temperatures[day][26]=sumTemp / 24; // Store average temp
        }
    }

    public int[] getHotestAndColdestDays() {
        int hottestDay=-1;
        int coldestDay=-1;
        double highestMaxTemp=Double.MIN_VALUE;
        double lowestMinTemp=Double.MAX_VALUE;

        for (int day=0;day<7;day++) {
            double minTemp=temperatures[day][24];
            double maxTemp=temperatures[day][25];

            if (maxTemp>highestMaxTemp) {
                highestMaxTemp=maxTemp;
                hottestDay=day;
            }
            if (minTemp<lowestMinTemp) {
                lowestMinTemp=minTemp;
                coldestDay=day;
            }
        }
        return new int[]{hottestDay, coldestDay};
    }
    public double[] getDailyAverageTemperature() {
        double average[] = new double[7];
        for (int day=0;day<7;day++) {
            average[day]=temperatures[day][26];
        }
        return average;
    }

    enum Day {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }
    
    public static void main(String[] args) {
        TemperatureAnalyzer analyzer = new TemperatureAnalyzer();
        int[] hotAndColdDays = analyzer.getHotestAndColdestDays();
        System.out.println("Hottest Day: " + Day.values()[hotAndColdDays[0]]);
        System.out.println("Coldest Day: " + Day.values()[hotAndColdDays[1]]);

        double[] dailyAverages = analyzer.getDailyAverageTemperature();
        System.out.println("Daily Average Temperatures:");
        for (int day = 0; day < dailyAverages.length; day++) {
            System.out.printf("%s: %.2f°C%n", Day.values()[day], dailyAverages[day]);
        }

    }
}
