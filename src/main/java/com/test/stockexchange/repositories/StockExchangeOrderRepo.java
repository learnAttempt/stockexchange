package com.test.stockexchange.repositories;

import com.test.stockexchange.entities.BuyOrderQueue;
import com.test.stockexchange.entities.SellOrderQueue;
import com.test.stockexchange.entities.Stock;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** repo to store orders**/
@Component
public class StockExchangeOrderRepo implements IStockExchangeOrderRepo {

  private final HashMap<Stock, BuyOrderQueue> buyCollection;
  private final HashMap<Stock, SellOrderQueue> sellCollection;

  @Autowired
  public StockExchangeOrderRepo() {
    this.buyCollection = new HashMap<>();
    this.sellCollection = new HashMap<>();
  }

  @Override
  public HashMap<Stock, BuyOrderQueue> getBuyOrders() {
    return this.buyCollection;
  }

  @Override
  public HashMap<Stock, SellOrderQueue> getSellOrders() {
    return this.sellCollection;
  }

  @Override
  public void putBuyOrders(Stock stock,BuyOrderQueue buyOrderQueue) {
     this.buyCollection.putIfAbsent(stock,buyOrderQueue);
  }

  @Override
  public void putSellOrders(Stock stock,SellOrderQueue sellOrderQueue) {
     this.sellCollection.putIfAbsent(stock,sellOrderQueue);
  }
}
