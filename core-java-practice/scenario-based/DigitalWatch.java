public class DigitalWatch {
    public static void main(String[] args) {
        System.out.println("Digital Watch Simulation");

        // Loop for hours
        for(int h=0;h<24;h++){
            // Loop for minutes
            for(int m=0;m<60;m++){
                System.out.printf("%02d:%02d%n",h,m);

                // Stop at 13:00 manually (simulate power cut)
                if(h==13 && m==0){
                    System.out.println("Power Cut! Watch stopped.");
                    break;
                }
            }
            // Breaks outer at 13 hrs
            if(h==13){
                break;
            }
        }
    }
}