package sbctester;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class DebugSoapHandlerResolver implements HandlerResolver {
	List<Handler> handlerList = new ArrayList<Handler>();
	
	@Override
	public List<Handler> getHandlerChain(PortInfo arg0) {
		if (handlerList.isEmpty()) {
			handlerList.add(new SoapLoggingHandler());
		}
		return handlerList;
	}

}
