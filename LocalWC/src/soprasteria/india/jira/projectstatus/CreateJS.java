package soprasteria.india.jira.projectstatus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class CreateJS {

	/**
	 * ! Constant declaration for writer
	 */
	private BufferedWriter writer;

	/**
	 * ! The class is inialized.
	 * 
	 * @throws IOException
	 *             - Exception is thrown when method fails
	 */
	public CreateJS(String fileName) throws IOException {

		/**
		 * ! Constant declaration for file
		 */
		File file;

		file = new File(fileName);
		boolean flag = file.exists();
		if (!flag) {
			try {
				flag = file.createNewFile();
			} catch (IOException ioe) {
				String s = file.getParent();
				if (s != null) {
					flag = new File(s).mkdirs();
				}
			}
		}
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ! This method writes the logger into the file.
	 * 
	 * @param info
	 *            - String
	 * @throws IOException
	 *             - Exception is thrown when method fails
	 */
	public void write(String info) throws IOException {
		if (info != null) {
			writer.newLine();
			writer.flush();
			writer.write(info);
			writer.flush();
		}
	}
}