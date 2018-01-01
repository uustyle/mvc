package sample.customer.web.controller.handler;

import java.io.BufferedReader;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import sample.customer.biz.service.ISomeService;
import filter.GenericResponseWrapper;
import filter.RereadableRequestWrapper;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Autowired
	private ISomeService someService;

	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {


		if (handler instanceof HandlerMethod) {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method m = handlerMethod.getMethod();
		logger.info("[SUCCESS CONTROLLER preHandle] {} {}.{}", new Object[] {
				request.getRequestURI(),
				m.getDeclaringClass().getName(), m.getName()});

		BufferedReader body = new BufferedReader(((RereadableRequestWrapper)request).getReader());
		String ret = body.readLine();

		}
		//		HttpSession session = request.getSession(false);
		//
		//
		//		String contextPath = request.getContextPath();
		//	    someService.getMessage("1");


//


//		ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
//				(HttpServletRequest) request);
//		// wrappedRequest.getInputStream().read();
//		String body = IOUtils.toString(wrappedRequest.getReader());


		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {

		if (handler instanceof HandlerMethod) {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method m = handlerMethod.getMethod();
		logger.info("[SUCCESS CONTROLLER afterCompletion] {} {}.{}", new Object[] {
				request.getRequestURI(),
				m.getDeclaringClass().getName(), m.getName()});

		//ラップしたwrapperからサーブレットの処理結果を取得
		byte[] data = ((GenericResponseWrapper)response).getBodyData();
			//OutputStreamに書き込んで応答
		//Shift-JISでString変換
		String result = new String(data, "UTF-8");
		//応答ロギング
		System.out.println(result);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {

		if (handler instanceof HandlerMethod) {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method m = handlerMethod.getMethod();
		logger.info("[SUCCESS CONTROLLER postHandle] {} {}.{}", new Object[] {
				request.getRequestURI(),
				m.getDeclaringClass().getName(), m.getName()});

		}
	}

}
