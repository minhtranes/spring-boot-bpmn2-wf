package vn.minhtran.sbw.present;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("history")
@Transactional
public class HistoryController {

	@Autowired
	private HistoryService historyService;
	
	@GetMapping("/process")
	public List<HistoricProcessInstance> processDefinitions() {
		List<HistoricProcessInstance> histories = historyService
				.createHistoricProcessInstanceQuery()
				.orderByProcessInstanceStartTime().desc().list();

		return histories;
	}

	@GetMapping("/process/{processDefinitionId}")
	public List<HistoricProcessInstance> history(
			@PathVariable(name = "processDefinitionId", required = true) String processDefinitionId) {
		List<HistoricProcessInstance> histories = historyService
				.createHistoricProcessInstanceQuery().finished()
				.processDefinitionId(processDefinitionId)
				.orderByProcessInstanceDuration().desc().listPage(0, 10);
		histories.forEach(t -> {
			System.out.println(t.getId());
		});

		return histories;
	}
	
	@GetMapping("/process/instance/{processInstanceId}")
	public ProcessInstanceHistoryLog delete(
			@PathVariable(name = "processInstanceId", required = true) String processInstanceId) {
		
		return historyService
			.createProcessInstanceHistoryLogQuery(processInstanceId)
			.includeActivities()
			.singleResult();
	}
}
