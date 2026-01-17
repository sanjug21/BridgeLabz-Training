package browser_buddy;

// Manages the history of a single tab using a Doubly Linked List
public class Tab {
    String tabName;
    WebPage current; // Pointer to the current page

    public Tab(String tabName) {
        this.tabName = tabName;
        this.current = new WebPage("New Tab"); // Default start page
    }

    // Visit a new page (clears forward history)
    public void visit(String url) {
        WebPage newPage = new WebPage(url);
        newPage.prev = current;
        if (current != null) {
            current.next = newPage;
        }
        current = newPage;
        System.out.println(tabName + " visited: " + url);
    }

    public void back() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println(tabName + " navigated back to: " + current.url);
        } else {
            System.out.println(tabName + ": No history to go back.");
        }
    }

    public void forward() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println(tabName + " navigated forward to: " + current.url);
        } else {
            System.out.println(tabName + ": No forward history.");
        }
    }
    
    public String getCurrentPage() {
        return (current != null) ? current.url : "Empty";
    }

    @Override
    public String toString() {
        return "[" + tabName + " | Current: " + getCurrentPage() + "]";
    }
}