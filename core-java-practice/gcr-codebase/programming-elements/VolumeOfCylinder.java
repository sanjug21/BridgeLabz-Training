import java.util.Scanner;

class VolumeOfCylinder{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int h=sc.nextInt();
        int r=sc.nextInt();
        int v=(22*r*r*h)/7;
        System.out.println(v);
    }
}