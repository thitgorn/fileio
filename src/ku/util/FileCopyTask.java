package ku.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import stopwatch.TaskTimer;

/**
 * This class defines code used to create Runnable 'tasks' to test the file copy
 * mehods in FileUtil. The subclasses should define their own run() method to
 * perform the actual task.
 * 
 * See the main method for example of how to use this. It uses the Stopwatch and
 * TaskTimer classes from stopwatch project.
 * 
 * All the file copy methods require an input stream (read from file) and an
 * output stream that writes to a file, so this class defines methods to open a
 * file as an InputStream and open an output file as an OutputStream. Files can
 * be opened via the constructor or setInput(filename) and setOutput(filename)
 * methods.
 * 
 * The method to open an InputStream shows how to use the ClassLoader to find a
 * file on the classpath of this project. The classpath includes files in your
 * project's src/ directory. It is a standard technique for opening resources.
 * 
 * @author
 *
 */
public class FileCopyTask implements Runnable {
	/** The InputStream that data will be read form. */
	protected InputStream in = null;
	/** The OutputStream that data will be written to. */
	protected OutputStream out = null;

	/**
	 * Default constructor doesn't do anything but may be needed for subclasses
	 * that don't invoke parameterized constructor.
	 */
	public FileCopyTask() {
	}

	/**
	 * Initialize a FileCopyTask with names of the input and output files to
	 * use.
	 * 
	 * @param infile
	 *            name of the file to use as input
	 * @param outfile
	 *            name of the file to use as output
	 * @throws RuntimeException
	 *             if either file cannot be opened
	 */
	public FileCopyTask(String infile, String outfile) {
		setInput(infile);
		setOutput(outfile);
	}

	/**
	 * Set the file to use as this object's 'in' attribute (InputStream).
	 * 
	 * @param filename
	 *            is the name of a file to read as input
	 * @throws RuntimeException
	 *             if the filename cannot be opened for input, which usually
	 *             means file not found.
	 */
	public void setInput(String filename) {
		in = null;
		try {
			// If the filename is an absolute path or is in the "current"
			// directory then using FileInputStream should open it.
			in = new FileInputStream(filename);
		} catch (FileNotFoundException fne) {
			// ignore it and try again
		}
		if (in != null)
			return;
		// The ClassLoader knows the application's classpath
		// and can open files that are on the classpath.
		// The filename can have a relative directory to refer to
		// subdirectories of the project source tree.
		ClassLoader loader = this.getClass().getClassLoader();
		in = loader.getResourceAsStream(filename);

		// If loader.getResourceAsStream() cannot create an InputStream
		// then it returns null. (No exception is thrown.)
		// If 'in' is null then throw a RuntimeException
		// so the caller will know that filename could not be opened.

		// TODO If in (InputStream) is null, throw a RuntimeException with a
		// message.
	}

	/**
	 * Specify a filename to use as the OutputStream (out attribute).
	 * 
	 * @param filename
	 *            is the name of the file to write to. If the file already
	 *            exists it will be overwritten.
	 * @throws RuntimeException
	 *             if the filename cannot be opened as an OutputStream.
	 */
	public void setOutput(String filename) {
		try {
			// This is easy. Use FileOutputStream.
			out = new FileOutputStream(filename);
		} catch (FileNotFoundException fne) {
			// rethrow it as an unchecked exception
			throw new RuntimeException("could not open output file " + filename, fne);
		}
	}

	/**
	 * The run() method should be overridden by subclasses to perform a task.
	 */
	public void run() {
		System.out.println("You forgot to override run in subclass.");
	}

	/**
	 * The toString() method should be overridden by subclasses to describe the
	 * task.
	 */
	public String toString() {
		return "Pay attention! You forgot to write toString in subclass.";
	}

	/**
	 * This main method could be in a separate class, for clarity. It uses this
	 * class to create subclasses for each task. It uses Stopwatch and TaskTimer
	 * to execute the task.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final String inputFilename = "Big-Alice-in-Wonderland.txt";

		// Define a FileUtil task to copy a file byte by byte.
		// This is an anonymous class that extends FileUtilTimer.
		// TODO Can you make this code shorter by passing the filenames
		// as parameters to the superclass constructor?
		FileCopyTask task1 = new FileCopyTask() {
			public void run() {
				FileUtil.copy(in, out);
			}

			public String toString() {
				return "Copy a file one byte at a time\n";
			}
		};
		task1.setInput(inputFilename);
		task1.setOutput("/tmp/filecopy1.txt");

		TaskTimer timer = new TaskTimer();
		timer.measureAndPrint(task1); // wasn't that easy?

		final int test1 = 1024;
		final int test2 = 4 * 1024;
		final int test3 = 64 * 1024;

		FileCopyTask task2 = new FileCopyTask() {
			public void run() {
				FileUtil.copy(in, out, test1);
			}

			public String toString() {
				return "Copy a file using a byte array of size 1KB\n";
			}
		};
		task2.setInput(inputFilename);
		task2.setOutput("/tmp/filecopy2.txt");
		timer.measureAndPrint(task2);

		FileCopyTask task3 = new FileCopyTask() {
			public void run() {
				FileUtil.copy(in, out, test2);
			}

			public String toString() {
				return "Copy a file using a byte array of size 4*1024KB\n";
			}
		};
		task3.setInput(inputFilename);
		task3.setOutput("/tmp/filecopy3.txt");
		timer.measureAndPrint(task3);

		FileCopyTask task4 = new FileCopyTask() {
			public void run() {
				FileUtil.copy(in, out, test3);
			}

			public String toString() {
				return "Copy a file using a byte array of size 64*1024KB\n";
			}
		};
		task4.setInput(inputFilename);
		task4.setOutput("/tmp/filecopy4.txt");
		timer.measureAndPrint(task4);

		FileCopyTask task5 = new FileCopyTask() {
			public void run() {
				FileUtil.bcopy(in, out);
			}

			public String toString() {
				return "Copy a file using BufferedReader\n";
			}
		};
		task5.setInput(inputFilename);
		task5.setOutput("/tmp/filecopy5.txt");
		timer.measureAndPrint(task5);

		// for the copy method. Don't write this as a number in the
		// anonymous class! Use a variable from the outer scope (here).
	}

	// class task implements Runnable {
	// int data;
	// String type;
	//
	// task(String type, int data) {
	// this.type = type;
	// this.data = data;
	// }
	//
	// @Override
	// public void run() {
	// if(type)
	// FileUtil.copy(in, out);
	// }
	// }

}