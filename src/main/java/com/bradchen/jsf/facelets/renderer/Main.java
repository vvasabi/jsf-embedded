package com.bradchen.jsf.facelets.renderer;

import org.apache.myfaces.webapp.StartupServletContextListener;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.faces.webapp.FacesServlet;

public class Main {

	public static void main(String[] args)
			throws Exception {
		ServletContextHandler context = new ServletContextHandler();

		context.setResourceBase(Thread.currentThread().getContextClassLoader()
			.getResource("facelets").toString());
		context.setAttribute("org.apache.myfaces.DYNAMICALLY_ADDED_FACES_SERVLET",
			true);
		context.setInitParameter("facelets.RESOURCE_RESOLVER",
			ClassPathResourceResolver.class.getName());
		context.addEventListener(new StartupServletContextListener());

		FacesServlet servlet = new FacesServlet();
		ServletHolder holder2 = new ServletHolder();
		holder2.setName("Faces Servlet");
		holder2.setServlet(servlet);
		holder2.setInitOrder(2);
		context.getServletHandler().addServletWithMapping(holder2, "*.xhtml");
		context.setContextPath("/");
		context.start();

		MockHttpServletRequest servletRequest
			= new MockHttpServletRequest("GET", "index.xhtml");
		servletRequest.setPathInfo("/index.xhtml");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(servletRequest, response);
		context.stop();
		context.destroy();
		System.out.println(response.getStatus());
		System.out.println(response.getContentAsString());
	}

}
