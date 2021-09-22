package com.test.stockexchange.entities;

import java.util.UUID;

/** entity for a trade**/
public class TradeTransaction  {
  private static final long serialVersionUID = 1L;
  private final UUID id;
  private final Order buyOrder;
  private final Order sellOrder;
  private final int quantity;
  private final Double tradingPrice;

  public TradeTransaction( Order buyOrder, Order sellOrder, int quantity,
      Double tradingPrice) {
    this.id =  UUID.randomUUID();;
    this.buyOrder = buyOrder;
    this.sellOrder = sellOrder;
    this.quantity = quantity;
    this.tradingPrice = tradingPrice;
  }

  public UUID getId() {
    return id;
  }

  public Order getBuyOrder() {
    return buyOrder;
  }

  public Order getSellOrder() {
    return sellOrder;
  }

  public int getQuantity() {
    return quantity;
  }

  public Double getTradingPrice() {
    return tradingPrice;
  }


  public String convertToOutput(){
    StringBuilder sb =new StringBuilder();
    sb.append("#");
    sb.append(buyOrder.getId()+" ");
    sb.append(sellOrder.getPrice()+ " ");
    sb.append(quantity+" ");
    sb.append(sellOrder.getId()+" ");
    return sb.toString();

  }

}
