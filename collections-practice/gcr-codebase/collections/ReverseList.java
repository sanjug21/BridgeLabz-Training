import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class ReverseList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input List Size
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        // 2. Input Elements
        List<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList=new LinkedList<>();
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            int val=sc.nextInt();
            arrayList.add(val);
            linkedList.add(val);
        }

        System.out.println("Original List: " + arrayList);

        // reverse array list
        // it will reverse both the arraylist as well as linked list
        reverseList(arrayList);
        System.out.println("Reversed Array List: " + arrayList);


       
        sc.close();

    }
    public static void reverseList(List<Integer> list) {
        if (list == null || list.size() <= 1) return;

        ListIterator<Integer> forward = list.listIterator();
        ListIterator<Integer> backward = list.listIterator(list.size());

        for (int i = 0; i < list.size() / 2; i++) {
            Integer temp = forward.next();
            forward.set(backward.previous());
            backward.set(temp);
        }
    }
   



    
}