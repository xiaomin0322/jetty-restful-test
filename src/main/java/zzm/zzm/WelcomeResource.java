package zzm.zzm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/welcome")
@Produces(WelcomeResource.PREFERRED_APPLICATION_JSON)    
public class WelcomeResource {
	
	static final String PREFERRED_APPLICATION_JSON = "application/json;qs=2";
	
	static final String[] p = new String[]{PREFERRED_APPLICATION_JSON};

	@GET
	//@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Welcome to Jersey world";
	}

	    @GET  
	    @Path("/1")  
	    public String test1(){  
		return "{\"a\":\"1\"}";
	}

}
