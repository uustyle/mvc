package sample.customer.biz.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class SessionKeepAliveChannelInterceptor extends ChannelInterceptorAdapter {

//	private SessionRepository sessionRepository;

	private static final Logger logger = LoggerFactory.getLogger(SessionKeepAliveChannelInterceptor.class);

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		Map<String, Object> sessionHeaders = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
		String sessionId = (String) sessionHeaders.get("httpSession.id");
		if (sessionId != null) {
//			Session session = sessionRepository.getSession(sessionId);
//			if (session != null) {
				logger.info("Keeping session with id : " + sessionId + " alive ");
//				sessionRepository.save(session);
//			}
		}

        MDC.put("client", "1.1.1.1");
		return super.preSend(message, channel);
	}

//	@Autowired
//	public void setSessionRepository(SessionRepository sessionRepository) {
//		this.sessionRepository = sessionRepository;
//	}
}