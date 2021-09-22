package com.test.stockexchange.entities;

public enum  OrderType {
  BUY("buy"),
  SELL("sell");

  private String value;

  OrderType(String value) {
    this.value = value;
  }
}
