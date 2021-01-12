package vn.minhtran.sbw.present;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("history")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@GetMapping("/process/{processDefinitionId}")
	public List<HistoricProcessInstance> history(
			@PathVariable(name = "processDefinitionId", required = true) String processDefinitionId) {
		return historyService.createHistoricProcessInstanceQuery()
				.finished()
				.processDefinitionId(processDefinitionId)
				.orderByProcessInstanceDuration().desc().listPage(0, 10);
	}
}
