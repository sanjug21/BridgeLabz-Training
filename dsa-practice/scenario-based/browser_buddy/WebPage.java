package browser_buddy;

public class WebPage {
    String url;
    WebPage prev;
    WebPage next;

    public WebPage(String url) {
        this.url = url;
        this.prev = null;
        this.next = null;
    }
}