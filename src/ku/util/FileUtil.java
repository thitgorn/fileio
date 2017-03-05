package ku.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Utility class for helping copy task.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class FileUtil {

	/**
	 * copy byte by byte from a file to file.
	 * 
	 * @param in
	 *            InputStream
	 * @param out
	 *            OutputStream
	 */
	static void copy(InputStream in, OutputStream out) {
		try {
			int copyOfIn;
			while ((copyOfIn = in.read()) >= 0) {
				out.write(copyOfIn);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
		closeInAndOut(in, out);
	}

	/**
	 * copy the file by defining byte per round from a file to file.
	 * 
	 * @param in
	 *            InputStream
	 * @param out
	 *            OutputStream
	 * @param blokcsize
	 *            byte per round
	 */
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
		closeInAndOut(in, out);
	}

	/**
	 * copy using Buffer Reader , this copies by line from a file to file.
	 * 
	 * @param in
	 *            InputStream
	 * @param out
	 *            OutputStreams
	 */
	static void bcopy(InputStream in, OutputStream out) {
		Reader reader = new InputStreamReader(in);
		BufferedReader input = new BufferedReader(reader);
		PrintWriter writer = new PrintWriter(out);
		String buffer;
		try {
			while (!((buffer = input.readLine()) == null)) {
				writer.println(buffer);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
		try {
			input.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
		closeInAndOut(in, out);
	}

	/**
	 * close the input and output stream. just for avoiding duplicate code.
	 */
	private static void closeInAndOut(InputStream in, OutputStream out) {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}
