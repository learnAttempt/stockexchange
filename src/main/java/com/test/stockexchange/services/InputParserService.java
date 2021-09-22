package com.test.stockexchange.services;

import com.test.stockexchange.entities.Order;
import com.test.stockexchange.entities.OrderType;
import com.test.stockexchange.entities.Stock;
import com.test.stockexchange.entities.TradeTransaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/** parses the input**/
@Component
public class InputParserService {

  private static String FILE_NAME="output.txt";
  protected final Log logger = LogFactory.getLog(getClass());

  /** i/p: file path
   * parses the file  and create orders
   * @param pathStr
   * @return
   */
  public List<Order> parseFile(String pathStr) {
    Path path = Paths.get(pathStr);
    List<Order> orders = new ArrayList<>();

    try {
      BufferedReader input = Files.newBufferedReader(path);

      String line = null;
      while ((line = input.readLine())!=null ) {
        orders.add(parse(line));
      }
      input.close();
    } catch (DateTimeParseException | NoSuchElementException | NumberFormatException e) {
      logger.info("Invalid input format! Exception: " + e.getMessage());
    } catch (IOException e) {
      logger.info("Failed to get input! Exception: " + e.getMessage());
    }

    return orders;
  }


  public Order parse(String orderLine) {

    String[] orderArr = orderLine.split(" ");
    if (orderArr.length != 6) {
      System.out.println("Input not in correct format");
    }

    String[] orderIdArr = orderArr[0].trim().split("#");

    String timeStr = orderArr[1];
    LocalTime orderTime = LocalTime
        .parse(timeStr, DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()));

    String stockName = orderArr[2].trim();
    Stock stock = new Stock(stockName);

    String typeStr = orderArr[3].trim();
    OrderType type = OrderType.valueOf(typeStr.toUpperCase());

    int quantity = Integer.parseInt(orderArr[5].trim());
    Double price = new Double(orderArr[4].trim());

    return new Order(Long.parseLong(orderIdArr[1]), orderTime, stock, type, price, quantity);
  }


  /**
   * writes the o/p
   * @param transactions
   * @param filepath
   */
  public void writeOutput(List<TradeTransaction> transactions,String filepath){
    String file=filepath+FILE_NAME;
    List<String> transactionOutput =transactions.stream()
        .map(TradeTransaction::convertToOutput)
        .collect(Collectors.toList());

    try {
      Files.write(Paths.get(file), transactionOutput);
    } catch (IOException e) {
      logger.error("Unable to write out names", e);
    }

  }

}
