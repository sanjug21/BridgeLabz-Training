public class SpringSeason {
    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("Please provide month and day.");
            return;
        }
        int month=Integer.parseInt(args[0]);
        int day=Integer.parseInt(args[1]);
        
        SpringSeason springSeason=new SpringSeason();
        // Check if it is spring season brute force way
        boolean isSpring=springSeason.isSpringSeason(month,day);
        
        if(isSpring)System.out.println("Its a Spring Season");
        else System.out.println("Not a Spring Season");
    }

    public boolean isSpringSeason(int month,int day){
        if(month==3 && day>=20 && day<=31) return true;
        if(month==4 && day>=1 && day<=30) return true;
        if(month==5 && day>=1 && day<=31) return true;
        if(month==6 && day>=1 && day<=20) return true;
        return false;
    }
}