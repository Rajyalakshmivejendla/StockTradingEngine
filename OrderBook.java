import java.util.concurrent.atomic.AtomicReference;

class OrderBook {
    private final AtomicReference<Order> head;

    OrderBook() {
        this.head = new AtomicReference<>(null);
    }

    void addOrder(Order order) {
        while (true) {
            Order currentHead = head.get();
            order.next.set(currentHead); // Set the next order to the current head
            if (head.compareAndSet(currentHead, order)) { // Atomically update the head
                break;
            }
        }
    }

    void matchOrders() {
        Order current = head.get();
        while (current != null) {
            Order nextOrder = current.next.get();
            if (nextOrder != null && current.orderType == Order.OrderType.BUY && nextOrder.orderType == Order.OrderType.SELL &&
                current.tickerSymbol.equals(nextOrder.tickerSymbol) && current.price >= nextOrder.price) {
                int tradeQuantity = Math.min(current.quantity, nextOrder.quantity);
                System.out.println("Matched: " + tradeQuantity + " shares of " + current.tickerSymbol +
                        " at price " + nextOrder.price);

                // Update quantities
                if (current.quantity > tradeQuantity) {
                    current.quantity -= tradeQuantity;
                } else {
                    // Remove the current order if fully matched
                    head.compareAndSet(current, nextOrder.next.get());
                }

                if (nextOrder.quantity > tradeQuantity) {
                    nextOrder.quantity -= tradeQuantity;
                } else {
                    // Remove the next order if fully matched
                    current.next.compareAndSet(nextOrder, nextOrder.next.get());
                }
            }
            current = nextOrder;
        }
    }
}