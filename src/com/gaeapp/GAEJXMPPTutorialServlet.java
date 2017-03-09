package com.gaeapp;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.mail.MailService.Message;
import com.google.appengine.api.xmpp.*;

@SuppressWarnings("serial")
public class GAEJXMPPTutorialServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world"); 
		doPost(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException
	{

		//get the incoming message from the Request object i.e. req
		// interpret it and compose a response
		//send the response message back
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		com.google.appengine.api.xmpp.Message msg = xmpp.parseMessage(req);
		JID fromJid = msg.getFromJid();
		String body = msg.getBody();
		com.google.appengine.api.xmpp.Message replyMessage = new MessageBuilder()
				.withRecipientJids()
				.withBody("TEXT_TO_SEND_TO_RECIPIENT")
				.build();

		//Compose the Message Object i.e. replyMessage
		if (xmpp.getPresence(fromJid).isAvailable()) {
			SendResponse status = xmpp.sendMessage(replyMessage);
			//Take appropriate action based on status SUCCESS or FAIL i.e. log an
			//error, update database, etc
		}
	}
}
