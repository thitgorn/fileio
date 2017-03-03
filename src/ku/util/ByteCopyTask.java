package ku.util;

public class ByteCopyTask extends FileCopyTask {
	
	public ByteCopyTask(String infile, String outfile) {
		setInput(infile);
		setOutput(outfile);
	}

	public void run() {
		FileUtil.copy(in, out);
	}

	public String toString() {
		return "Copy a file one byte at a time\n";
	}
}
