import java.util.Random;

public class StockTradingEngine {
    private static final String[] TICKERS = new String[1024];
    private static final Random random = new Random();

    static {
        for (int i = 0; i < TICKERS.length; i++) {
            TICKERS[i] = "TICKER" + i;
        }
    }

    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();

        for (int i = 0; i < 1000; i++) {
            String ticker = TICKERS[random.nextInt(TICKERS.length)];
            Order.OrderType orderType = random.nextBoolean() ? Order.OrderType.BUY : Order.OrderType.SELL;
            int quantity = random.nextInt(100) + 1;
            double price = 100 + (200 - 100) * random.nextDouble();

            Order order = new Order(orderType, ticker, quantity, price);
            orderBook.addOrder(order);

            orderBook.matchOrders();

            try {
                Thread.sleep(10); // Simulate some delay between orders
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}