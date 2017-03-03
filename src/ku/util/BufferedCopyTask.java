package ku.util;

public class BufferedCopyTask extends FileCopyTask {
	
	public BufferedCopyTask(String infile, String outfile){
		setInput(infile);
		setOutput(outfile);
	}

	public void run() {
		FileUtil.bcopy(in, out);
	}

	public String toString() {
		return "Copy a file using BufferedReader\n";
	}
}
