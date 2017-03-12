package sample.customer.biz.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // WebSocketのメッセージブローカーのBean定義を有効化する
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer { // AbstractWebSocketMessageBrokerConfigurerを継承しWebSocket関連のBean定義をカスタマイズする

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("endPoint").withSockJS(); // WebSocketのエンドポイント (接続時に指定するエンドポイント)を指定
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // アプリケーション(Controller)でハンドリングするエンドポイントのプレフィックス
        registry.enableSimpleBroker("/topic"); // Topic(Pub-Sub)とQueue(P2P)を有効化 >>> メッセージブローカーがハンドリングする
    }

}