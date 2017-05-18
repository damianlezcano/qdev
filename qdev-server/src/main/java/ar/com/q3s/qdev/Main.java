package ar.com.q3s.qdev;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;

public class Main {

	public static final String FORMAT_NEW = "Map map%s = new HashMap();";
	public static final String FORMAT_PUT = "map%s.put(\"%s\",\"%s\");";
	public static final String FORMAT_PUT_INNER_MAP = "map%s.put(\"map%s\",map%s);";
	
	public static void main(String[] args) {
	      TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
	      tjws.setPort(8080);
	      tjws.start();
	      tjws.getDeployment().getRegistry().addPerRequestResource(MessageRestService.class);
	}
	
}