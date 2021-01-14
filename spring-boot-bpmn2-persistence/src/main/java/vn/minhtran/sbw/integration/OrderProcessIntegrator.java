package vn.minhtran.sbw.integration;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.transaction.annotation.Transactional;

import vn.minhtran.sbw.model.Order;

@MessageEndpoint
@Transactional
public class OrderProcessIntegrator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderProcessIntegrator.class);

	@Autowired
	private RuntimeService runtimeService;

	@ServiceActivator(inputChannel = "orderChannel", outputChannel = "finishedOrderChannel")
	public Order start(Order order) {

		final Map<String, Object> variables = new HashMap<>();
		{
			variables.put("order", order);
			variables.put("orderId", order.getOrderId());
		}
		runtimeService.startProcessInstanceByKey("Process_0tgbap2", variables);

		LOGGER.info("Done process the order for customer [{}]",
				order.getCustomerName());

		return order;
	}
}
