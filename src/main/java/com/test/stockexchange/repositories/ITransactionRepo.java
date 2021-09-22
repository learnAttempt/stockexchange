package com.test.stockexchange.repositories;

import com.test.stockexchange.entities.TradeTransaction;
import java.util.List;
import org.springframework.stereotype.Component;

/** can update the transaction **/
@Component
public interface ITransactionRepo {


  public List<TradeTransaction> getTrades();
  public void addTrades(TradeTransaction tradeTransaction);
}
