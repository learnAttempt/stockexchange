package com.test.stockexchange.services;

import com.test.stockexchange.entities.Order;
import com.test.stockexchange.entities.TradeTransaction;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * the exchange system class that calls all submodules
 */
@Component
public class StockExchangeService implements CommandLineRunner {

  @Value("${input.path}")
  private String filePath;

  @Value("${output.path}")
  private String outputPath;
  private static final Logger logger = LoggerFactory.getLogger(StockExchangeService.class);

  @Autowired
  private InputParserService parserService;
  @Autowired
  private OrderProcessingService orderProcessingService;

  @Override
  public void run(String... args) throws Exception {

    //logger.info("file path"+filePath);

    executeStockTrading();

  }

  /**
   * executes on application start
   */

  private void executeStockTrading() {
    try {

      List<Order> orders = parserService.parseFile(filePath);
      orderProcessingService.addOrders(orders);
      List<TradeTransaction> transactions = orderProcessingService.processOrders();
      parserService.writeOutput(transactions, outputPath);
    } catch (Exception ex) {
      logger.info("error in processing" + ex.getMessage());
    }


  }
}
