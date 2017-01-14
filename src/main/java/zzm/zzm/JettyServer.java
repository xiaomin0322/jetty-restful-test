package zzm.zzm;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyServer {

	private void start() throws Exception {
		int port = 8080;
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder sh = new ServletHolder(ServletContainer.class);
		sh.setInitParameter(
				"com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig");
		sh.setInitParameter("com.sun.jersey.config.property.packages",
				"zzm.zzm");
		context.addServlet(sh, "/*");


		context.addFilter(new FilterHolder(new ResponseFilter()), "/*", EnumSet.allOf(DispatcherType.class));
		
		server.start();
	}

	public void stop() throws Exception {

	}

	public static void main(String[] args) throws Exception {
		JettyServer server = new JettyServer();
		server.start();
	}
}