package com.test.stockexchange.entities;

import java.util.Comparator;

/** entity for comparing buy orders**/
public class BuyOrderComparator implements Comparator<Order> {
  @Override
  public int compare(Order a, Order b) {
    if (a.getId().equals(b.getId())) {
      return 0;
    }

    int timeDiff=a.getTime().compareTo(b.getTime());
    if (timeDiff == 0) {
      return Double.compare(a.getPrice(),b.getPrice());
    }
    return timeDiff;
  }
}
