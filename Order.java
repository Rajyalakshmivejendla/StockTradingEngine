import java.util.concurrent.atomic.AtomicReference;

class Order {
    enum OrderType { BUY, SELL }

    final OrderType orderType;
    final String tickerSymbol;
    int quantity; // Quantity can change during matching
    final double price;
    final AtomicReference<Order> next;

    Order(OrderType orderType, String tickerSymbol, int quantity, double price) {
        this.orderType = orderType;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.next = new AtomicReference<>(null); // Initialize AtomicReference
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderType=" + orderType +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}