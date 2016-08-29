package sbctester;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SoapLoggingHandler implements SOAPHandler<SOAPMessageContext>{

	@Override
	public void close(MessageContext c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext c) {
		try {
			System.out.println("SOAP Fault ... ");
			logMessage(c);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext c) {
		boolean isRequest = ((Boolean) c.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();
		
		try {
			if (isRequest) {
				System.out.println("SOAP Message: ");
				logMessage(c);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void logMessage(SOAPMessageContext c) {
		SOAPMessage msg = c.getMessage();
		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			msg.writeTo(bout);
			System.out.println(bout.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (bout != null) { 
				try { bout.close(); } 
				catch (IOException e) {  } 
			}
		}
	}

}
