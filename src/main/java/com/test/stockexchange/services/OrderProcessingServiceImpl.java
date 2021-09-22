package com.test.stockexchange.services;

import static com.test.stockexchange.entities.OrderType.BUY;
import static com.test.stockexchange.entities.OrderType.SELL;

import com.test.stockexchange.entities.BuyOrderQueue;
import com.test.stockexchange.entities.Order;
import com.test.stockexchange.entities.SellOrderQueue;
import com.test.stockexchange.entities.Stock;
import com.test.stockexchange.entities.TradeTransaction;
import com.test.stockexchange.exception.OrderAddException;
import com.test.stockexchange.repositories.IStockExchangeOrderRepo;
import com.test.stockexchange.repositories.ITransactionRepo;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * main order processing file
 */
@Component
public class OrderProcessingServiceImpl implements OrderProcessingService {
  protected final Log logger = LogFactory.getLog(getClass());


  @Autowired
  private IStockExchangeOrderRepo stockExchangeOrderRepo;
  @Autowired
  private ITransactionRepo transactionRepo;

  /**
   * adds orders to stock exchange repo
   * @param orders
   */
  @Override
  public void addOrders(List<Order> orders) {
    if (orders == null || orders.isEmpty()) {
      return;
    }

    for (Order order : orders) {

      if (order.getStock() == null) {
        throw new OrderAddException("No stocks attached to Order: " + order.getId());
      }

      SortedSet<Order> orderQueue = null;
      if (order.getType() == BUY) {
        BuyOrderQueue buyOrder = stockExchangeOrderRepo.getBuyOrders().get(order.getStock());
        if (buyOrder == null) {
          buyOrder = new BuyOrderQueue();
          stockExchangeOrderRepo.putBuyOrders(order.getStock(), buyOrder);
        }
        orderQueue = buyOrder.getBuyOrders();

      } else if (order.getType() == SELL) {
        SellOrderQueue currentSellOrders =stockExchangeOrderRepo.getSellOrders().get(order.getStock());
        if (currentSellOrders == null) {
          currentSellOrders = new SellOrderQueue();
          stockExchangeOrderRepo.putSellOrders(order.getStock(), currentSellOrders);
        }
        orderQueue = currentSellOrders.getSellOrders();
      }

      if (orderQueue.contains(order)) {
        throw new OrderAddException("Order is possibly duplicated: " + order.getId());
      } else {
        orderQueue.add(order);
        logger.info("Successfully added order for processing");

      }
    }
  }

  /**
   * process and creates trade
   * @return
   */

  public List<TradeTransaction> processOrders(){

    Map<Stock, BuyOrderQueue> buyCollection=stockExchangeOrderRepo.getBuyOrders();
    Map<Stock, SellOrderQueue> currentSellCollection=stockExchangeOrderRepo.getSellOrders();
    buyCollection.forEach((stock, orders) -> {
      SellOrderQueue currentSellOrder = currentSellCollection.get(stock);

      if (currentSellOrder == null) {
        return;
      }
      SortedSet<Order> buyOrderQueue = orders.getBuyOrders();
      SortedSet<Order> currentSellOrderQueue  = currentSellOrder.getSellOrders();
      if (buyOrderQueue == null || buyOrderQueue.isEmpty() || currentSellOrderQueue==null || currentSellOrderQueue.isEmpty()) {
        return;
      }

      buyOrderQueue.stream().filter(order -> (order.getQuantity() > 0)).forEach((buy) -> {
        int i=0;
       for(Order currentSell:currentSellOrderQueue){


        if (currentSell.getQuantity() > 0 && buy.getPrice().compareTo(currentSell.getPrice()) >= 0) {

            int qty = 0;
            if (currentSell.getQuantity() > buy.getQuantity()) {
              qty = buy.getQuantity();
              currentSell.setQuantity(currentSell.getQuantity() - buy.getQuantity());
              buy.setQuantity(0);
              //buy.se
            } else {
              qty = currentSell.getQuantity();
              buy.setQuantity(buy.getQuantity() - currentSell.getQuantity());
              currentSell.setQuantity(0);
            }


            TradeTransaction entry = new TradeTransaction( buy, currentSell,qty, currentSell.getPrice());
            transactionRepo.addTrades(entry);
          }

        }
      });
    });

    return transactionRepo.getTrades();
  }

}
