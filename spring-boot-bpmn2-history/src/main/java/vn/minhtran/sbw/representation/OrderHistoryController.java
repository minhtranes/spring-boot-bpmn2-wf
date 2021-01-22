package vn.minhtran.sbw.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderHistoryController.
 */
@RestController
@RequestMapping("/history/orders")
public class OrderHistoryController {

	/** The history service. */
	@Autowired
	private HistoryService historyService;

	/** The history controller. */
	@Autowired
	private HistoryController historyController;

	/**
	 * Delete.
	 *
	 * @param variableName the variable name
	 * @param variableValue the variable value
	 * @return the list
	 */
	@GetMapping("/{variableName}/{variableValue}")
	public List<ProcessInstanceHistoryLog> delete(
			@PathVariable(name = "variableName", required = true) String variableName,
			@PathVariable(name = "variableValue", required = true) String variableValue) {

		List<HistoricVariableInstance> list = historyService
				.createHistoricVariableInstanceQuery()
				.variableValueEquals(variableName, variableValue).list();
		if (list == null) {
			return new ArrayList<>();
		}

		List<String> processInstanceIds = list.stream()
				.map(HistoricVariableInstance::getProcessInstanceId)
				.collect(Collectors.toList());

		final List<ProcessInstanceHistoryLog> ret = new ArrayList<>();
		processInstanceIds.forEach(i -> {
			ret.add(historyController.delete(i));
		});

		return ret;
	}
}
