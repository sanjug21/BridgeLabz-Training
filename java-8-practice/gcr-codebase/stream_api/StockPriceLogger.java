import java.util.ArrayList;
import java.util.List;

class StockPrice {
    String symbol;
    double price;

    public StockPrice(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

public class StockPriceLogger {

    public static void main(String[] args) {
        List<StockPrice> stocks = new ArrayList<>();
        stocks.add(new StockPrice("AAPL", 175.43));
        stocks.add(new StockPrice("GOOGL", 142.89));
        stocks.add(new StockPrice("MSFT", 378.91));
        stocks.add(new StockPrice("AMZN", 151.22));
        stocks.add(new StockPrice("TSLA", 238.45));

        System.out.println("Live Stock Price Feed:");
        stocks.forEach(stock -> System.out.println(stock.getSymbol() + ": $" + stock.getPrice()));
    }
}
