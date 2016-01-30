package br.com.projetoperiodo.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;

@Controller
public class AcessoMonitoria {

	@RequestMapping(value = "exibirMonitoriasCadastradas", method = RequestMethod.GET)
	public ModelAndView exibirMonitoriasCadastradas() {
		System.out.println(1);
		return new ModelAndView("Login");
	}

	@RequestMapping(value = "notificarMonitor", method = RequestMethod.POST)
	public ModelAndView notificarDuvidaAoMonitor(
			@RequestParam(value = "emailNotificador", required = true) String emailNotificador,
			Monitoria monitoria) {

		return null;
	}
}
