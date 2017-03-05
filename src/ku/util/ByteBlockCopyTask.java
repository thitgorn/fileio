package ku.util;

/**
 * Copy the file by defined size task.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ByteBlockCopyTask extends FileCopyTask {

	// processing size.
	private int block;

	/**
	 * Construct the task.
	 * 
	 * @param block
	 *            byte per round
	 * @param infile
	 *            input file name
	 * @param outfile
	 *            output file name
	 */
	public ByteBlockCopyTask(int block, String infile, String outfile) {
		this.block = block;
		setInput(infile);
		setOutput(outfile);
	}

	/**
	 * run the task.
	 */
	public void run() {
		FileUtil.copy(in, out, block);
	}

	/**
	 * @return task description
	 */
	public String toString() {
		return "Copy a file using a byte array of size " + block / 1024 + "KB\n";
	}
}
