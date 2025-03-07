# Real-Time Stock Trading Engine

This project implements a real-time stock trading engine that matches buy and sell orders for 1,024 tickers. It supports concurrent order additions and uses lock-free data structures to handle race conditions.

## Features
- `addOrder`: Adds a new order to the order book.
- `matchOrder`: Matches buy and sell orders based on price and quantity.
- Thread-safe implementation using `AtomicReference`.

##Performance
-'addOrder': O(1)
-'matchOrder': O(n), where n is the number of orders.

