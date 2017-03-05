package ku.util;
/**
 * Copy the file by byte task.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ByteCopyTask extends FileCopyTask {
	
	/**
	 * Construct the task.
	 * @param infile input file name.
	 * @param outfile output file name.
	 */
	public ByteCopyTask(String infile, String outfile) {
		setInput(infile);
		setOutput(outfile);
	}

	/**
	 * run the task.
	 */
	public void run() {
		FileUtil.copy(in, out);
	}

	/**
	 * @return task description.
	 */
	public String toString() {
		return "Copy a file one byte at a time\n";
	}
}
