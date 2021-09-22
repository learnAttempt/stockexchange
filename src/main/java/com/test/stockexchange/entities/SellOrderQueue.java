package com.test.stockexchange.entities;

import java.util.SortedSet;
import java.util.TreeSet;
/** entity for storing buy orders**/
public class SellOrderQueue {

  private SortedSet<Order> sellOrders;

  public SellOrderQueue() {

    this.sellOrders = new TreeSet<>(new SellOrderComparator());
  }
  public SortedSet<Order> getSellOrders() {
    return sellOrders;
  }
}
