/*
 * Class: ServePaymentProcessIntigrator
 *
 * Created on Jan 21, 2021
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package vn.minhtran.sbw.integration;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@MessageEndpoint
public class ServePaymentProcessIntegrator {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ServePaymentProcessIntegrator.class);

    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = "servePayChannel", outputChannel = "finishedServePayChannel")
    public Object start(ObjectNode order) {

        final Map<String, Object> variables = new HashMap<>();
        
        final Object objectAsMap = this.objectMapper.convertValue(order, Map.class);
        
        
        final DocumentContext jsonData = JsonPath.parse(objectAsMap);
        
        variables.put("order", jsonData.read("$"));
        variables.put("orderId", jsonData.read("$.orderId"));
        runtimeService.startProcessInstanceByKey("serve_payment", variables);

        LOGGER.info(
            "Done process the serve and pay for customer [{}]",
            jsonData.read("$.customerName").toString());

        return order;
    }
    
}
