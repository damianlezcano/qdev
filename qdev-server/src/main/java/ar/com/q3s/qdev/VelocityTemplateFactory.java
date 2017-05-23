package ar.com.q3s.qdev;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityTemplateFactory {

	private static VelocityTemplateFactory instance;
	
	private VelocityEngine ve;
	
	private Template account;
	private Template machine;
	private Template folder;
	private Template file;
	private Template console;
	private Template open;
	
	private VelocityTemplateFactory() {
		ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
	}

	public static VelocityTemplateFactory getInstance(){
		if(instance == null){
			instance = new VelocityTemplateFactory();
		}
		return instance;
	}
	
	public Template getAccount(){
		if(account == null){
			account = ve.getTemplate( "/account.vm" );	
		}
		return account;
	}

	public String getAccount(Object items){
        return merge(getAccount(), "accounts", items);
	}
	
	public Template getMachine(){
		if(machine == null){
			machine = ve.getTemplate( "/machine.vm" );	
		}
		return machine;
	}

	public String getMachine(Object items){
        return merge(getMachine(), "machines", items);
	}
	
	public Template getFolder(){
		if(folder == null){
			folder = ve.getTemplate( "/folder.vm" );	
		}
		return folder;
	}

	public String getFolder(Object items){
        return merge(getFolder(), "folders", items);
	}
	
	public Template getFile(){
		if(file == null){
			file = ve.getTemplate( "/file.vm" );	
		}
		return file;
	}

	public String getFile(Object items){
        return merge(getFile(), "files", items);
	}

	public Template getConsole(){
		if(console == null){
			console = ve.getTemplate( "/console.vm" );	
		}
		return console;
	}

	public Template getOpen(){
		if(open == null){
			open = ve.getTemplate( "/open.vm" );	
		}
		return open;
	}

	public String getOpen(Object items){
        return merge(getOpen(), "content", items);
	}
	
	public String merge(Template t, String key, Object items){
        VelocityContext context = new VelocityContext();
        context.put(key, items);
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
		return writer.toString();
	}
	
}