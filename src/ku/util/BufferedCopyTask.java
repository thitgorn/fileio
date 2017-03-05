package ku.util;

/**
 * Copy file by buffer reader.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class BufferedCopyTask extends FileCopyTask {

	/**
	 * construct the task.
	 * 
	 * @param infile
	 *            input file name.
	 * @param outfile
	 *            output file name.
	 */
	public BufferedCopyTask(String infile, String outfile) {
		setInput(infile);
		setOutput(outfile);
	}

	/**
	 * run the task.
	 */
	public void run() {
		FileUtil.bcopy(in, out);
	}

	/**
	 * @return task description
	 */
	public String toString() {
		return "Copy a file using BufferedReader\n";
	}
}
