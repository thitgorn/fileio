package ku.util;

public class ByteBlockCopyTask extends FileCopyTask {

	private int block;

	public ByteBlockCopyTask(int block, String infile, String outfile) {
		this.block = block;
		setInput(infile);
		setOutput(outfile);
	}

	public void run() {
		FileUtil.copy(in, out, block);
	}

	public String toString() {
		return "Copy a file using a byte array of size " + block/1024 + "KB\n";
	}
}
