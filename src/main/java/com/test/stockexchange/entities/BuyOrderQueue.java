package com.test.stockexchange.entities;

import java.util.SortedSet;
import java.util.TreeSet;
/** entity for getting buy orders**/
public class BuyOrderQueue {
  private SortedSet<Order> buyOrders;

  public BuyOrderQueue() {

    this.buyOrders = new TreeSet<>(new BuyOrderComparator());
  }

  public SortedSet<Order> getBuyOrders() {
    return buyOrders;
  }
}
