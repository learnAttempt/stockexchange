package com.test.stockexchange.entities;

import java.util.Comparator;
/** entity for comparing sell orders**/
public class SellOrderComparator implements Comparator<Order> {
  
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
