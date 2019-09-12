package com.ct.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class Demo {

	@GET
	@Produces({"text/plain"}) 
	public String helloWorld() {
		return "hello this is demo";
	}
	
	@GET
	@Produces({"application/xml"})
	public String helloWorldXML() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<hello>\r\n" + 
				"\r\n" + 
				"<message> Hello World</message>\r\n" + 
				"<from> Kamlesh</from>\r\n" + 
				"\r\n" + 
				"</hello>";
	}
	
	@GET
	@Produces({"application/json"}) 
	public String helloWorldJSON() {
		return "{\r\n" + 
				"\r\n" + 
				"	\"message\" : \"hello world\"\r\n" + 
				"	\"from\"    : \"kamlesh\"\r\n" + 
				"\r\n" + 
				"}";
	}
}
