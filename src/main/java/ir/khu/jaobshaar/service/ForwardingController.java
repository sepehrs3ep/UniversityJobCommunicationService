package ir.khu.jaobshaar.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ForwardingController {

	/**
	 * This is to return index.html for all requests except api requests (Required for single page application)
	 */
	@RequestMapping("/**/{path:[^\\.]+}")
	public String forward(ServletRequest servletRequest) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (request.getRequestURI().startsWith("/api/"))
			throw new Exception();
		return "forward:/";
	}
}