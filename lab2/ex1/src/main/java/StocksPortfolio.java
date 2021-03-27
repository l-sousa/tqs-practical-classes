import java.util.ArrayList;

public class StocksPortfolio {

    private String name;
    private IStockMarket iStockMarket;
    private ArrayList<Stock> stocks = new ArrayList<>();

    public IStockMarket getMarketService() {
        return this.iStockMarket;
    }

    public void setMarketService(IStockMarket market) {
        this.iStockMarket = market;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalValue() {
        return this.stocks.stream().mapToDouble(stock -> iStockMarket.getPrice(stock.getName()) * stock.getQuantity()).sum();
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }
}