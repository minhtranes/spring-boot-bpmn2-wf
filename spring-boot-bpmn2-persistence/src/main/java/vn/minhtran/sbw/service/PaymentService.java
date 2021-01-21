package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(PaymentService.class);

    public Object pay(Object order) {
        final DocumentContext jsonData = JsonPath.parse(order);
        LOGGER.info("Pay an order for customer [{}]", jsonData.read("$.customerName").toString());
        jsonData.set("$.payed", true);
        
        return order;
    }
}
