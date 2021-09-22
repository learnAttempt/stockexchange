package com.test.stockexchange;

import com.test.stockexchange.entities.Order;
import com.test.stockexchange.repositories.IStockExchangeOrderRepo;
import com.test.stockexchange.repositories.ITransactionRepo;
import com.test.stockexchange.services.InputParserService;
import com.test.stockexchange.services.OrderProcessingService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
class StockExchangeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private OrderProcessingService orderProcessingService;

	@Mock
  private InputParserService inputParserService;

	@Mock
  private IStockExchangeOrderRepo stockExchangeOrderRepo;

	@Mock
  private ITransactionRepo transactionRepo;

	List<Order> list=new ArrayList<Order>();

	@Test
	void contextLoads() {

	}

	@Before
  public void setup(){
    String l1="#1 09:45 BAC sell 240.12 100";
    list.add(inputParserService.parse(l1));
    l1="#2 09:46 BAC sell 237.45 90";
    list.add(inputParserService.parse(l1));
    l1="#3 09:47 BAC buy 238.10 110";
    list.add(inputParserService.parse(l1));
  }

  @Test
	public void processOrders(){

	 /* orderProcessingService.addOrders(list);
    List<TradeTransaction> t=orderProcessingService.processOrders();
    assertEquals(t.size(), 1);
    assertEquals(t.get(0).getQuantity(),90);
    assertEquals(String.valueOf(t.get(0).getTradingPrice()),"237.45");*/
  }

}
