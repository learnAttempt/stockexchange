package com.test.stockexchange.repositories;

import com.test.stockexchange.entities.TradeTransaction;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/** repo to store transactions**/
@Component
public class TransactionRepo implements ITransactionRepo {

  private final List<TradeTransaction> tradeTransactions;

  @Autowired
  public TransactionRepo() {
    this.tradeTransactions=new ArrayList<>();

  }

  @Override
  public List<TradeTransaction> getTrades(){
    return this.tradeTransactions;
  }

  @Override
  public void addTrades(TradeTransaction tradeTransaction){
    this.tradeTransactions.add(tradeTransaction);
  }

}
