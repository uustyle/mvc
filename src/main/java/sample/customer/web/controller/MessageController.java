package sample.customer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.customer.biz.domain.Greeting;
import sample.customer.biz.domain.HelloMessage;

@Controller
public class MessageController {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello1")
    @SendTo("/topic/greetings")
    @ResponseBody
    public Greeting greeting(HelloMessage message) throws Exception {
         return new Greeting("Hello, " + message.getName() + "!");
   }

    @SubscribeMapping("/hello2")
    public void sendMessage(String message) {
        simpMessagingTemplate.convertAndSend("/topic/greetings2/", message);
    }


}
