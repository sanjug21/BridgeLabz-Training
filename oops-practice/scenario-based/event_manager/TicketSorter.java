package event_manager;

import java.util.Collections;
import java.util.List;

// Implements Quick Sort for sorting tickets by price
public class TicketSorter {
    
    public void quickSort(List<Ticket> tickets, int low, int high) {
        if (low < high) {
            int pi = partition(tickets, low, high);
            quickSort(tickets, low, pi - 1);
            quickSort(tickets, pi + 1, high);
        }
    }

    private int partition(List<Ticket> tickets, int low, int high) {
        double pivot = tickets.get(high).getPrice();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (tickets.get(j).getPrice() <= pivot) {
                i++;
                Collections.swap(tickets, i, j);
            }
        }
        Collections.swap(tickets, i + 1, high);
        return i + 1;
    }
}