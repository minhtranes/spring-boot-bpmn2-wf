package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import vn.minhtran.sbw.model.Order;

@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(PaymentService.class);

    public Order pay(Order order) {
        LOGGER.info("Pay an order for customer [{}]", order.getCustomerName());
        order.setPayed(true);
        
        return order;
    }
}
