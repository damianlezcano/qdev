package ar.com.q3s.qdev;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

@Path("/")
public class MessageRestService {

	private Map<String,List<String>> files = new HashMap<String,List<String>>();
	
	private Map<String,Map<String,List<String>>> accounts;
	
	public MessageRestService() {
		accounts = new HashMap<String,Map<String,List<String>>>();
		Map<String,List<String>> machines = new HashMap<String, List<String>>();
		List<String> folders = new ArrayList<String>();
		folders.add("folder1");
		folders.add("folder2");
		machines.put("L845", folders);
		accounts.put("lezcano.da@gmail.com", machines);
		
		List<String> fs = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			fs.add(String.format("/home/damian/file%s.txt", i));			
		}
		files.put("folder1", fs);
	}
	
	@GET
	@Path("/keep-alive")
	public Response keepAlive() {
		return Response.status(200).entity("ok!").build();
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.TEXT_HTML)
	public Response method1() {	
        String html = VelocityTemplateFactory.getInstance().getAccount(accounts.keySet());
		return Response.ok(html).build();
	}
	
	@GET
	@Path("/{username}")
	@Consumes(MediaType.TEXT_HTML)
	public Response method2(@PathParam("username") String username) {
        String html = VelocityTemplateFactory.getInstance().getMachine(accounts.get(username).keySet());
		return Response.ok(html).build();
	}

	@GET
	@Path("/{username}/{machine}")
	@Consumes(MediaType.TEXT_HTML)
	public Response method3(@PathParam("username") String username, @PathParam("machine") String machine) {
        String html = VelocityTemplateFactory.getInstance().getFolder(accounts.get(username).get(machine));
		return Response.ok(html).build();
	}
	
	@GET
	@Path("/{username}/{machine}/{folder}")
	@Consumes(MediaType.TEXT_HTML)
	public Response method4(@PathParam("username") String username, @PathParam("machine") String machine, @PathParam("folder") String folder) {
        String html = VelocityTemplateFactory.getInstance().getFile(files.get(folder));
		return Response.ok(html).build();
	}
	
	@GET
	@Path("/{username}/{machine}/{folder}/console")
	@Consumes(MediaType.TEXT_HTML)
	public Response method5(@PathParam("username") String username, @PathParam("machine") String machine, @PathParam("folder") String folder) {
		Template t = VelocityTemplateFactory.getInstance().getConsole();
        StringWriter writer = new StringWriter();
        t.merge( new VelocityContext(), writer );	
		return Response.ok(writer.toString()).build();
	}
}