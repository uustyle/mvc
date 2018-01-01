package filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class FilterServletOutputStream extends ServletOutputStream {
	private OutputStream stream;

	public FilterServletOutputStream(OutputStream stream) {
		this.stream = stream;
	}

	@Override
	public void write(int b) throws IOException  {
		stream.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException  {
		stream.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException  {
		stream.write(b,off,len);
	}

	@Override
	public void close() throws java.io.IOException {
		stream.close();
	}

	@Override
	public void flush() throws IOException {
		stream.flush();
	}

	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}
}