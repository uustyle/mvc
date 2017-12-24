package sample.customer.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.customer.biz.domain.Greeting;
import sample.customer.biz.domain.HelloMessage;
@Controller
public class MessageController {

	private static Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello1")
    @SendTo("/topic/greetings")
    @ResponseBody
    public Greeting greeting(SimpMessageHeaderAccessor ha, HelloMessage message, MessageHeaders messageHeaders) throws Exception {
    	System.out.println(ha.getSessionAttributes().get("CLIENT"));
    	System.out.println(ha.getSessionAttributes().get("HTTPSESSIONID"));

    	logger.info("start-------------");
		logger.info("greeting");
		logger.info("end-------------");

    	logger.info(messageHeaders.toString());

    	logger.info("end-------------");

    	//現在日時取得
        Calendar c = Calendar.getInstance();

        //フォーマットを指定
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(sdf.format(c.getTime()));
         return new Greeting("Hello, " + message.getName() + "!");
   }

    @SubscribeMapping("/hello2")
    public void sendMessage(String message) {
        simpMessagingTemplate.convertAndSend("/topic/greetings2/", message);
    }

    @MessageMapping("/hello3")
    @SendTo("/topic/greetings2")
    @ResponseBody
    public Greeting exceptionTest(String message) throws Exception {
    	if (message.equals("error")){
    		throw new NullPointerException("System error occurred.");
    	}

    	return new Greeting("Hello, " + message + "!");
   }

    // 例外ハンドリング用のメソッドを追加する
    @MessageExceptionHandler
    @SendToUser("/queue/errors") // 送信者のみを宛先にする
    public MessagingError handleException(Throwable exception) {
        MessagingError error = new MessagingError();
        error.setMessage(exception.getMessage());
        return error;
    }

}
