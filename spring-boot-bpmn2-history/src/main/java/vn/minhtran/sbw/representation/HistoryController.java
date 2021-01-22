package vn.minhtran.sbw.representation;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class HistoryController.
 */
@RestController
@RequestMapping("history")
@Transactional
public class HistoryController {

	@Autowired
	private HistoryService historyService;
	
	/**
	 * Process definitions.
	 *
	 * @return the list
	 */
	@GetMapping("/process")
	public List<HistoricProcessInstance> processDefinitions() {
	    return historyService
				.createHistoricProcessInstanceQuery()
				.orderByProcessInstanceStartTime().desc().list();
	}

    /**
     * History.
     *
     * @param processDefinitionId the process definition id
     * @return the list
     */
    @GetMapping("/process/{processDefinitionId}")
    public List<HistoricProcessInstance> history(
        @PathVariable(name = "processDefinitionId", required = true) String processDefinitionId) {

        return historyService.createHistoricProcessInstanceQuery().finished()
            .processDefinitionId(processDefinitionId)
            .orderByProcessInstanceDuration().desc().listPage(0, 10);
    }

	/**
	 * Delete.
	 *
	 * @param processInstanceId the process instance id
	 * @return the process instance history log
	 */
	@GetMapping("/process/instance/{processInstanceId}")
	public ProcessInstanceHistoryLog delete(
			@PathVariable(name = "processInstanceId", required = true) String processInstanceId) {
		
		return historyService
			.createProcessInstanceHistoryLogQuery(processInstanceId)
			.includeActivities()
			.singleResult();
	}
	
    /**
     * History by order id.
     *
     * @param orderId the order id
     * @return the list
     */
    @GetMapping("/process/orderId/{orderId}")
    public List<HistoricDetail> historyByOrderId(
        @PathVariable(name = "orderId", required = true) String orderId) {

        return this.historyService.createNativeHistoricDetailQuery().sql(
            "select * from act_hi_detail where text_::jsonb ->> 'orderId' = #{orderId} and name_ = 'order'")
            .parameter("orderId", orderId).list();
    }
}
