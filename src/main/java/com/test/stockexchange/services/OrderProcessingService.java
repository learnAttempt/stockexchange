package com.test.stockexchange.services;

import com.test.stockexchange.entities.Order;
import com.test.stockexchange.entities.TradeTransaction;
import java.util.List;

public interface OrderProcessingService {
  public void addOrders(List<Order> orders);
  public List<TradeTransaction> processOrders();
}
