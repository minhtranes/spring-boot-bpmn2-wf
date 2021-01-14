package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import vn.minhtran.sbw.model.Order;

@Service
public class CookingService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(CookingService.class);

    public Order cook(String orderId, Order order) {
        LOGGER.info("Cook an order for customer [{}]", order.getCustomerName());
        
        return order;
    }
}
