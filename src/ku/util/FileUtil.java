package ku.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	static void copy(InputStream in, OutputStream out) {
		try {
			int copyOfIn;
			while ((copyOfIn = in.read()) < 0) {
				out.write(copyOfIn);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void copy(InputStream in, OutputStream out, int blokcsize) {

	}

	static void bcopy(InputStream in, OutputStream out) {

	}
}
