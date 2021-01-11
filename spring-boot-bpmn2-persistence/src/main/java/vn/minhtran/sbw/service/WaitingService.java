package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import vn.minhtran.sbw.model.Order;

@Service
public class WaitingService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(WaitingService.class);

    public void serve(Order order) {

        LOGGER
            .info("Serve an order for customer [{}]", order.getCustomerName());
    }

    public void receive(Order order) {
        LOGGER.info(
            "Received an order for customer [{}]",
            order.getCustomerName());
    }
}
