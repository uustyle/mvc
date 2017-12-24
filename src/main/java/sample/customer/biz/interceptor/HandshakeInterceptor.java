package sample.customer.biz.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
            ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");

		if ((request instanceof ServletServerHttpRequest)) {
			ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
			HttpSession session =
			serverRequest.getServletRequest().getSession(
					isCreateSession());

			Enumeration<String> names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				System.out.println("name:" + session.getAttribute(name));
//				if ((isCopyAllAttributes())
//						|| (getAttributeNames().contains(name))) {
//					attributes.put(name, session.getAttribute(name));
//				}
			}


			if (session != null) {
				attributes.put("HTTPSESSIONID", session.getId());
			}

		}

		if (request instanceof ServletServerHttpRequest) {
	        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
	        String ipAddress = servletRequest.getServletRequest().getHeader("X-FORWARDED-FOR");
	        if (ipAddress == null) {
	            ipAddress = servletRequest.getServletRequest().getRemoteAddr();
	        }
	        attributes.put("client", ipAddress);
	    }
//	    return true;
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }


    @Override
    public void afterHandshake(ServerHttpRequest request,
            ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception ex) {
        System.out.println("After Handshake");

        super.afterHandshake(request, response, wsHandler, ex);
    }

}