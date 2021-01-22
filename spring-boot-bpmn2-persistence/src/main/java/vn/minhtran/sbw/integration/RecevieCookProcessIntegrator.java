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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@MessageEndpoint
@Transactional
public class RecevieCookProcessIntegrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecevieCookProcessIntegrator.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = "receiveCookChannel", outputChannel = "servePayChannel")
    public Object start(ObjectNode order) {

        final Map<String, Object> variables = new HashMap<>();

        final Object objectAsMap = this.objectMapper.convertValue(order, Map.class);

        final DocumentContext jsonData = JsonPath.parse(objectAsMap);

        variables.put("order", jsonData.read("$"));
        variables.put("orderId", jsonData.read("$.orderId"));
        runtimeService.startProcessInstanceByKey("Process_0tgbap2", variables);

        LOGGER.info("Done process the receive and cook for customer [{}]", jsonData.read("$.customerName").toString());

        return order;
    }
}
