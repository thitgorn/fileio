package ku.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;

public class FileUtil {
	static void copy(InputStream in, OutputStream out) {
		try {
			int copyOfIn;
			while ((copyOfIn = in.read()) >= 0) {
				out.write(copyOfIn);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	static void copy(InputStream in, OutputStream out, int blokcsize) {
		try {
			int blocksize = blokcsize;
			byte[] buffer = new byte[blocksize];
			int count;
			while ((count = in.read(buffer)) >= 0) {
				out.write(buffer, 0, count);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	static void bcopy(InputStream in, OutputStream out) {
		Reader reader = new InputStreamReader(in);
		BufferedReader input = new BufferedReader(reader);
		PrintWriter writer = new PrintWriter(out);
		String buffer;
		try {
			while (!((buffer = input.readLine()) == null)) {
				writer.write(buffer);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}
