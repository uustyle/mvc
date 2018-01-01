package filter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GenericResponseWrapper2 extends HttpServletResponseWrapper {
	private ByteArrayOutputStream output;
	private int contentLength;
	private String contentType;

	public GenericResponseWrapper2(HttpServletResponse response) {
		super(response);
		output=new ByteArrayOutputStream();
	}

	public byte[] getData() {
		return output.toByteArray();
	}

	@Override
	public ServletOutputStream getOutputStream() {

		final DataOutputStream stream = new DataOutputStream(output);
		ServletOutputStream servletOutputStream = new ServletOutputStream() {

			@Override
			public boolean isReady() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public void setWriteListener(WriteListener paramWriteListener) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void write(int b) throws IOException  {
				stream.write(b);
			}

		};
		return servletOutputStream;

	}

	@Override
	public PrintWriter getWriter() throws IOException{
//		return new PrintWriter(getOutputStream(),true);
		return new PrintWriter(
				new OutputStreamWriter(getOutputStream(),this.getCharacterEncoding()),
				true
			);
	}

	public void setContentLength(int length) {
		this.contentLength = length;
		super.setContentLength(length);
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentType(String type) {
		this.contentType = type;
		super.setContentType(type);
	}

	public String getContentType() {
		return contentType;
	}
}