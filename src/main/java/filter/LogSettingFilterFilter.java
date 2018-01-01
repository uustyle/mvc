package filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;

public class LogSettingFilterFilter implements Filter {

    String CLIENT = "client";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 診断コンテキストを設定します.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
//            MDC.put(CLIENT, servletRequest.getRemoteAddr());
//            System.out.println(servletRequest.getRemoteAddr());
//            filterChain.doFilter(servletRequest, servletResponse);

//    		ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
//    				(HttpServletRequest) servletRequest);
//    		 wrappedRequest.getInputStream().read();
////    		String body = IOUtils.toString(wrappedRequest.getReader());
//    		wrappedRequest.resetInputStream();
//    		filterChain.doFilter(wrappedRequest, servletResponse);

    		RereadableRequestWrapper wrappedRequest = new RereadableRequestWrapper(
    				(HttpServletRequest) servletRequest);
//    		String body = IOUtils.toString(wrappedRequest.getReader());

//    		OutputStream out = servletResponse.getOutputStream();
			GenericResponseWrapper wrapperResponse =
			         new GenericResponseWrapper((HttpServletResponse) servletResponse);

			wrapperResponse.setCharacterEncoding("UTF-8");

    		filterChain.doFilter(wrappedRequest, wrapperResponse);

    		OutputStream out = servletResponse.getOutputStream();
    		byte[] by = wrapperResponse.getData();
    		if (by.length > 0) {
    			int contentLength = 0;
    			out.write(by);
    			contentLength += by.length;
    			servletResponse.setContentLength(contentLength);
    		}

//			//ラップしたwrapperからサーブレットの処理結果を取得
			byte[] data = wrapperResponse.getData();
// 			//OutputStreamに書き込んで応答
			out.write(data);
			out.close();

			//			//Shift-JISでString変換
//			String result = new String(data, "UTF-8");
//			//応答ロギング
//			System.out.println(result);

        } finally {
            MDC.remove(CLIENT);
        }
    }

    @Override
    public void destroy() {

    }


	private static class ResettableStreamHttpServletRequest extends
	HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}


		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}


		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;

			@Override
			public int read() throws IOException {
				return stream.read();
			}

			@Override
			public boolean isFinished() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public void setReadListener(ReadListener paramReadListener) {
				// TODO 自動生成されたメソッド・スタブ
			}
		}
	}
}