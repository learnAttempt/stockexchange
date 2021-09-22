package com.test.stockexchange.entities;

import java.time.LocalTime;

/** entity for system  orders**/
public class Order {

  private final Long id;
  private final LocalTime time;
  private final Stock stock;
  private final OrderType type;
  private final Double price;
  private int quantity;

  public Order(Long id, LocalTime time, Stock stock, OrderType type, Double askingPrice,
      int quantity) {
    this.id = id;
    this.time = time;
    this.stock = stock;
    this.type = type;
    this.price = askingPrice;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public LocalTime getTime() {
    return time;
  }

  public Stock getStock() {
    return stock;
  }

  public OrderType getType() {
    return type;
  }

  public Double getPrice() {
    return price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof Order) && (this.id.equals(((Order) obj).getId()));
  }
}
