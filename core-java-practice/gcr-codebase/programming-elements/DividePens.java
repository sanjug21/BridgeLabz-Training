public class DividePens {
    public static void main(String[] args) {
        int students=3;
        int pens=12;
        int pensPerStudent=pens/students;
        int remainingPens=pens%students;
        System.out.println("Pen per student is "+pensPerStudent+" and the remaining pen not distributed is "+remainingPens);
    }
}
