import java.util.Scanner;

class TextState {
    String content;

    TextState(String content) {
        this.content = content;
    }
}



public class TextEditorUndoRedo {
    static Scanner sc = new Scanner(System.in);
    DoubleNode<TextState> head;
    DoubleNode<TextState> tail;
    DoubleNode<TextState> current;
    int size = 0;
    final int MAX_HISTORY = 10;

    public void addState(String text) {
        DoubleNode<TextState> nn = new DoubleNode<TextState>(new TextState(text));

        if (head == null) {
            head = nn;
            tail = nn;
            current = nn;
            size++;
        } else {
            // If we are in the middle of the history (after undo), discard the forward history
            if (current != tail) {
                // Discard nodes after current
                DoubleNode<TextState> temp = current.next;
                while (temp != null) {
                    temp = temp.next;
                    size--;
                }
                // Link new node to current
                current.next = nn;
                nn.prev = current;
                tail = nn;
                current = nn;
                size++;
            } else {
                // Normal append
                tail.next = nn;
                nn.prev = tail;
                tail = nn;
                current = nn;
                size++;
            }
        }

        // Enforce history limit
        if (size > MAX_HISTORY) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
        }
        System.out.println("State added.");
    }

    public void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Cannot Undo. Reached start of history.");
            return;
        }
        current = current.prev;
        System.out.println("Undo successful.");
    }

    public void redo() {
        if (current == null || current.next == null) {
            System.out.println("Cannot Redo. Reached end of history.");
            return;
        }
        current = current.next;
        System.out.println("Redo successful.");
    }

    public void displayCurrentState() {
        if (current == null) {
            System.out.println("Editor is empty.");
        } else {
            System.out.println("Current Text: " + current.data.content);
        }
    }

    public void displayHistory() {
        if (head == null) {
            System.out.println("History is empty.");
            return;
        }
        DoubleNode<TextState> temp = head;
        System.out.println("History States:");
        while (temp != null) {
            String marker = (temp == current) ? " <--- CURRENT" : "";
            System.out.println("[" + temp.data.content + "]" + marker);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        TextEditorUndoRedo editor = new TextEditorUndoRedo();
        System.out.println("==== Text Editor Undo/Redo System ====");
        
        // Initial empty state
        editor.addState(""); 

        while (true) {
            System.out.println("\nCurrent Text: " + (editor.current != null ? editor.current.data.content : ""));
            System.out.println("1. Type/Edit (Add State)");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Full History");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter new text content:");
                    String text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayHistory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}