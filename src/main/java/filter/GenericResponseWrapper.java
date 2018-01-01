package filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GenericResponseWrapper extends HttpServletResponseWrapper {
	private ByteArrayOutputStream buff;
	private PrintWriter writer;
	private ServletOutputStream output;

	public GenericResponseWrapper(HttpServletResponse response) {
		super(response);
		this.buff = new ByteArrayOutputStream();
	}

	public byte[] getData() {
		if (this.writer != null) {
			this.writer.close();
		}
		if (this.output != null) {
			try {
				this.output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		byte[] ret = buff.toByteArray();

		this.buff = new ByteArrayOutputStream();
		this.output = null;
		this.writer = null;

		return ret;
	}

	public byte[] getBodyData() {
		return this.buff.toByteArray();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (this.output == null)
			this.output = new FilterServletOutputStream(this.buff);

		return this.output;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (this.writer == null) {
			this.writer = new PrintWriter(
				new OutputStreamWriter(this.getOutputStream(),this.getCharacterEncoding()),
				true
			);
		}
		return this.writer;
	}
}