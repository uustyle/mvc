package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class SessionConnectedListener implements ApplicationListener<SessionConnectedEvent> {

    private static final Logger log = LoggerFactory.getLogger(SessionConnectedListener.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        log.info(event.toString());

        // Not sure if it's sending...?
        template.convertAndSend("/topic/login", "New user logged in");
    }

}