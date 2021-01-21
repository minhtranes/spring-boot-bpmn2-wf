package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@Service
public class CookingService {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(CookingService.class);

    public Object cook(Object order) {
        final DocumentContext jsonData = JsonPath.parse(order);
        LOGGER.info("Cook an order for customer [{}]", jsonData.read("$.customerName").toString());
        
        return order;
    }
}
