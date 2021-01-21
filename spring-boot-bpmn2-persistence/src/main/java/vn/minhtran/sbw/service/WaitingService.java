package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@Service
public class WaitingService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(WaitingService.class);

    public Object serve(Object order) {
        final DocumentContext jsonData = JsonPath.parse(order);
        LOGGER.info(
            "Serve an order for customer [{}]",
            jsonData.read("$.customerName").toString());
        return order;
    }

    public Object receive(Object order) {
        final DocumentContext jsonData = JsonPath.parse(order);
        LOGGER.info(
            "Received an order for customer [{}]",
            jsonData.read("$.customerName").toString());
        return order;
    }
}
