import java.util.*;

public class OTPGenerator {
    public static void main(String[] args) {
        OTPGenerator gen=new OTPGenerator();
        int[] otps=new int[10];
        
        for(int i=0;i<10;i++){
            otps[i]=gen.generateOTP();
        }
        
        System.out.println("Generated OTPs: "+Arrays.toString(otps));
        boolean unique=gen.checkUnique(otps);
        if(unique)System.out.println("All OTPs are unique.");
        else System.out.println("OTPs contain duplicates.");
    }

    public int generateOTP(){
        return 100000+(int)(Math.random()*900000);
    }

    public boolean checkUnique(int[] otps){
        for(int i=0;i<otps.length;i++){
            for(int j=i+1;j<otps.length;j++){
                if(otps[i]==otps[j])return false;
            }
        }
        return true;
    }
}