package com.test.stockexchange.repositories;

import com.test.stockexchange.entities.BuyOrderQueue;
import com.test.stockexchange.entities.SellOrderQueue;
import com.test.stockexchange.entities.Stock;
import java.util.Map;
import org.springframework.stereotype.Component;

/** interface for accessing of orders **/
@Component
public interface IStockExchangeOrderRepo {
  public Map<Stock, BuyOrderQueue> getBuyOrders();

  public Map<Stock, SellOrderQueue> getSellOrders();

  public void putBuyOrders(Stock stock,BuyOrderQueue buyOrderQueue);

  public void putSellOrders(Stock stock,SellOrderQueue sellOrderQueue);
}
