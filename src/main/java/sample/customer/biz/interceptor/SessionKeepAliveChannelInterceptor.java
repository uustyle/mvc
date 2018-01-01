package sample.customer.biz.interceptor;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompEncoder;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

@Component
public class SessionKeepAliveChannelInterceptor extends ChannelInterceptorAdapter {

//	private SessionRepository sessionRepository;

//	@Autowired
//	private StompEncoder stompEncoder;

	private static final Logger logger = LoggerFactory.getLogger(SessionKeepAliveChannelInterceptor.class);

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {


	       MessageHeaders headers = message.getHeaders();
	       SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");

//	       if(StompCommand.MESSAGE.equals(headerAccessor.getCommand())) {
	       if (type == SimpMessageType.MESSAGE) {

		       String simpDestination = ObjectUtils.toString(headers.get("simpDestination"));
		       System.out.println(simpDestination);


		       try {

		    		byte[] payload = (byte[]) message.getPayload();
		    		String aaa = new String(payload, "UTF8");

		    		TextMessage msg2 = new TextMessage(new String(payload, "UTF8"));

		    	} catch (Exception e) {

		    	}


	       }


		Map<String, Object> sessionHeaders = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
		String sessionId = (String) sessionHeaders.get("httpSession.id");
		System.out.println(message.getPayload());

		StompEncoder stompEncoder = new StompEncoder();
		byte[] bytes =stompEncoder.encode((Message<byte[]>) message);

		try {
			TextMessage msg = new TextMessage(new String(bytes, "UTF8"));
			System.out.println(msg.toString());
			System.out.println(msg.getPayload());
			System.out.println(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}


		if (sessionId != null) {
//			Session session = sessionRepository.getSession(sessionId);
//			if (session != null) {
				logger.info("Keeping session with id : " + sessionId + " alive ");
//				sessionRepository.save(session);
//			}

				System.out.println(message.getPayload());

//				Message<String> message1 = (Message<String>) MessageBuilder
//		                .withPayload(message.getPayload())
//		                .build(); //

		}

        MDC.put("client", "1.1.1.1");
		return super.preSend(message, channel);
	}

	/*



//	@Autowired
//	public void setSessionRepository(SessionRepository sessionRepository) {
//		this.sessionRepository = sessionRepository;
//	}


    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

		System.out.println(message.getPayload());

		StompEncoder stompEncoder = new StompEncoder();
		byte[] bytes =stompEncoder.encode( SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders()), (byte[]) message.getPayload());

		try {
			TextMessage msg = new TextMessage(new String(bytes, "UTF8"));

			if (msg != null) {
				System.out.println(msg.toString());
				System.out.println(msg.getPayload());
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}




        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if(accessor != null) {
            StompCommand command = accessor.getCommand();
            if(command != null) {

		        String sessionId = sha.getSessionId();

		        switch(sha.getCommand()) {
		            case CONNECT:
		                logger.debug("STOMP Connect [sessionId: " + sessionId + "]");
		                break;
		            case CONNECTED:
		                logger.debug("STOMP Connected [sessionId: " + sessionId + "]");
		                break;
		            case DISCONNECT:
		                logger.debug("STOMP Disconnect [sessionId: " + sessionId + "]");
		                break;
		            default:
		                break;

		        }
            }
        }
    }

	public void afterSendCompletion(Message<?> message, MessageChannel channel,
			boolean sent, Exception ex) {


		System.out.println(message.getPayload());

		StompEncoder stompEncoder = new StompEncoder();
		byte[] bytes =stompEncoder.encode((Message<byte[]>) message);

		try {
			TextMessage msg = new TextMessage(new String(bytes, "UTF8"));

			if (msg != null) {
				System.out.println(msg.toString());
				System.out.println(msg.getPayload());
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean preReceive(MessageChannel channel) {
		return true;


	}

	public Message<?> postReceive(Message<?> message, MessageChannel channel) {


		System.out.println(message.getPayload());

		StompEncoder stompEncoder = new StompEncoder();
		byte[] bytes =stompEncoder.encode((Message<byte[]>) message);

		try {
			TextMessage msg = new TextMessage(new String(bytes, "UTF8"));

			if (msg != null) {
				System.out.println(msg.toString());
				System.out.println(msg.getPayload());
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return message;



	}


	public void afterReceiveCompletion(Message<?> message,
			MessageChannel channel, Exception ex) {

		System.out.println(message.getPayload());

		StompEncoder stompEncoder = new StompEncoder();
		byte[] bytes =stompEncoder.encode((Message<byte[]>) message);

		try {
			TextMessage msg = new TextMessage(new String(bytes, "UTF8"));

			if (msg != null) {
				System.out.println(msg.toString());
				System.out.println(msg.getPayload());
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
*/


}