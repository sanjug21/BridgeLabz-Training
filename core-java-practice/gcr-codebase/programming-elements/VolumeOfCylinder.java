import java.util.Scanner;

class VolumeOfCylinder{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int height=sc.nextInt();
        int radius=sc.nextInt();
        double VolumeOfCylinder=(22*radius*radius*height)/7;
        System.out.println(VolumeOfCylinder);
        sc.close();
    }
}