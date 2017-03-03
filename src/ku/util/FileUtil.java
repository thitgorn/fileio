package ku.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
				out.write(buffer, count, blocksize);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	static void bcopy(InputStream in, OutputStream out) {

	}
}
