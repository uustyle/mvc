package sample.customer.biz.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompDecoder;
import org.springframework.messaging.simp.stomp.StompEncoder;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.socket.TextMessage;

@Component
public class SessionKeepAlive2ChannelInterceptor extends ChannelInterceptorAdapter {

//	private SessionRepository sessionRepository;

//	@Autowired
//	private StompEncoder stompEncoder;

	private static final Logger logger = LoggerFactory.getLogger(SessionKeepAlive2ChannelInterceptor.class);



	  @Override
	    public Message<?> preSend(Message<?> message, MessageChannel channel) {
	       StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

			Map<String, Object> sessionHeaders = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());


	       headerAccessor.setHeader("priority", "5");
	       headerAccessor.setHeader("tree", "alpha");
	       MessageHeaders headers = message.getHeaders();
	       SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
	       String simpDestination = ObjectUtils.toString(headers.get("simpDestination"));

//	       if(StompCommand.MESSAGE.equals(headerAccessor.getCommand())) {
	       if (type == SimpMessageType.MESSAGE) {
//	               Map<String, Object> map = headerAccessor.toMap();
//	               map.put("key1", "value1");
//	               Map nativeHeaders = new HashMap();
//	               nativeHeaders.put("hello", Collections.singletonList("world"));
//	               map.put(NativeMessageHeaderAccessor.NATIVE_HEADERS, nativeHeaders);
//	              GenericMessage msg = new GenericMessage(message.getPayload(), map);
//	               System.out.println("==> " + msg);
//               return msg;

	    	   StompHeaderAccessor headers2 = StompHeaderAccessor.wrap(message);
	    	   String msg = headers2.getMessage();

	       		StompEncoder stompEncoder = new StompEncoder();

	       		Map<String, Object> headers3 = message.getHeaders();

System.out.println(message.getPayload().toString());
//				byte[] byteArray = new byte[fldDto.getLength()];
//			    buf.get(byteArray);
//				value = new String(byteArray);


//	    		byte[] bytes =stompEncoder.encode((Message<byte[]>) message);
//	    		byte[] bytes =stompEncoder.encode( SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders()), (byte[]) message.getPayload());

try {

	byte[] payload = (byte[]) message.getPayload();
	String aaa = new String(payload, "UTF8");

	TextMessage msg2 = new TextMessage(new String(payload, "UTF8"));

} catch (Exception e) {

}




//	    		ByteArrayOutputStream baos = new ByteArrayOutputStream(
//						128 + payload.length);
//				DataOutputStream output = new DataOutputStream(baos);
//				if (SimpMessageType.HEARTBEAT.equals(SimpMessageHeaderAccessor
//						.getMessageType(headers))) {
//					if (this.logger.isTraceEnabled()) {
//						this.logger.trace("Encoding heartbeat");
//					}
//					output.write(StompDecoder.HEARTBEAT_PAYLOAD);
//				} else {
//					StompCommand command = StompHeaderAccessor.getCommand(headers);
//					Assert.notNull(command, "Missing STOMP command: " + headers);
//					output.write(command.toString().getBytes(
//							StompDecoder.UTF8_CHARSET));
//					output.write(10);
//					writeHeaders(command, headers, message.getPayload(), output);
//					output.write(10);
//					writeBody(payload, output);
//					output.write(0);
//				}


//	    		try {
//	    			TextMessage msg2 = new TextMessage(new String(bytes, "UTF8"));
//	    			System.out.println(msg2.toString());
//	    			System.out.println(msg2.getPayload());
//	    			System.out.println(msg2);
//
//	    		} catch (Exception e) {
//	    			e.printStackTrace();
//	    		}

	            return message;

	       }
	       else {
	            return message;
	       }
	    }


	  /*

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
*/

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




}