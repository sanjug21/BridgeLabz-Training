package browser_buddy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BrowserBuddy {
    private List<Tab> activeTabs;
    private Stack<Tab> closedTabs; // Stack to store closed tabs for restoration
    private int activeTabIndex;

    public BrowserBuddy() {
        activeTabs = new ArrayList<>();
        closedTabs = new Stack<>();
        // Start with one default tab
        activeTabs.add(new Tab("Tab 1"));
        activeTabIndex = 0;
    }

    public void openNewTab(String name) {
        activeTabs.add(new Tab(name));
        activeTabIndex = activeTabs.size() - 1; // Switch focus to new tab
        System.out.println("Opened " + name);
    }

    public void closeCurrentTab() {
        if (activeTabs.isEmpty()) {
            System.out.println("No tabs to close.");
            return;
        }
        Tab removed = activeTabs.remove(activeTabIndex);
        closedTabs.push(removed); // Push to stack
        System.out.println("Closed " + removed.tabName);
        
        if (!activeTabs.isEmpty()) {
            // Adjust index to the last available tab
            if (activeTabIndex >= activeTabs.size()) {
                activeTabIndex = activeTabs.size() - 1;
            }
        } else {
            activeTabIndex = -1;
        }
    }

    public void restoreClosedTab() {
        if (closedTabs.isEmpty()) {
            System.out.println("No closed tabs to restore.");
            return;
        }
        Tab restored = closedTabs.pop(); // Pop from stack
        activeTabs.add(restored);
        activeTabIndex = activeTabs.size() - 1;
        System.out.println("Restored " + restored.tabName);
    }

    public void switchTab(int index) {
        if (index >= 0 && index < activeTabs.size()) {
            activeTabIndex = index;
            System.out.println("Switched to " + activeTabs.get(activeTabIndex).tabName);
        } else {
            System.out.println("Invalid tab index.");
        }
    }

    public Tab getCurrentTab() {
        if (activeTabIndex >= 0 && activeTabIndex < activeTabs.size()) {
            return activeTabs.get(activeTabIndex);
        }
        return null;
    }
    
    public void displayState() {
        System.out.println("\n--- Browser State ---");
        System.out.println("Active Tabs:");
        for (int i = 0; i < activeTabs.size(); i++) {
            String marker = (i == activeTabIndex) ? " > " : "   ";
            System.out.println(marker + i + ": " + activeTabs.get(i));
        }
        System.out.println("Closed Tabs History (Stack Size): " + closedTabs.size());
        System.out.println("---------------------");
    }

    public static void main(String[] args) {
        BrowserBuddy browser = new BrowserBuddy();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Visit URL");
            System.out.println("2. Back");
            System.out.println("3. Forward");
            System.out.println("4. Open New Tab");
            System.out.println("5. Close Current Tab");
            System.out.println("6. Restore Closed Tab");
            System.out.println("7. Switch Tab");
            System.out.println("8. Display State");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            Tab currentTab = browser.getCurrentTab();

            switch (choice) {
                case 1:
                    if (currentTab != null) {
                        System.out.print("Enter URL: ");
                        String url = scanner.nextLine();
                        currentTab.visit(url);
                    } else {
                        System.out.println("No active tab. Open a new one.");
                    }
                    break;
                case 2:
                    if (currentTab != null) currentTab.back();
                    break;
                case 3:
                    if (currentTab != null) currentTab.forward();
                    break;
                case 4:
                    System.out.print("Enter Tab Name: ");
                    String name = scanner.nextLine();
                    browser.openNewTab(name);
                    break;
                case 5:
                    browser.closeCurrentTab();
                    break;
                case 6:
                    browser.restoreClosedTab();
                    break;
                case 7:
                    System.out.print("Enter Tab Index: ");
                    try {
                        int idx = Integer.parseInt(scanner.nextLine());
                        browser.switchTab(idx);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                    break;
                case 8:
                    browser.displayState();
                    break;
                case 0:
                    System.out.println("Exiting BrowserBuddy.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        scanner.close();
    }
}