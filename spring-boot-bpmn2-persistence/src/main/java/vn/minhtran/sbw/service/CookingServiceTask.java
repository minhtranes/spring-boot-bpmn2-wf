package vn.minhtran.sbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@Service
public class CookingServiceTask {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(CookingServiceTask.class);

    public Object cook(Object order) {
        final DocumentContext jsonData = JsonPath.parse(order);
        LOGGER.info(
            "Cook an order for customer [{}] - status [{}]",
            jsonData.read("$.customerName").toString(),
            jsonData.read("$.payed"));

        return order;
    }
}
