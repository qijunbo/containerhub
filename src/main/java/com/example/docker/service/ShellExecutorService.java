package com.example.docker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.example.docker.repository.Container;

@Configuration
public class ShellExecutorService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Value("${docker.create}")
	private String createContainer;

	public Container createContainer(String owner) {

		if (execute(createContainer, owner).getErrorcode() == 0) {
			return getPort(owner);
		} else {
			return null;
		}
	}

	@Value("${docker.getport}")
	private String getport;

	public Container getPort(String owner) {
		ShellExecutorService.ShellResult result = execute(getport, owner);

		if (LOG.isDebugEnabled()) {
			LOG.debug("==== shell script executing result====");
			LOG.debug(result.toString());
		}

		if (result.getErrorcode() == 0) {
			return new Container(owner, owner, Integer.parseInt(result.getMessage().trim()));
		} else {
			return null;
		}
	}

	public ShellResult execute(String... command) {
		int exitVal = -1;
		StringBuffer message = new StringBuffer();

		ProcessBuilder pb = new ProcessBuilder(command);
		// ProcessBuilder pb = new ProcessBuilder("notepad.exe", "test.txt");
		// pb.command(new String[] { "notepad.exe", "test.txt" });
		try {
			Process process = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null)
				message.append(line);
			exitVal = process.waitFor();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// Map<String, String> env = pb.environment();
		// env.put("VAR1", "myValue");
		// env.remove("OTHERVAR");
		// env.put("VAR2", env.get("VAR1") + "suffix");
		// pb.directory(new File("myDir"));

		return new ShellExecutorService.ShellResult(exitVal, message.toString());
	}

	static class ShellResult {

		int errorcode;

		String message;

		public ShellResult(int errorcode, String message) {
			super();
			this.errorcode = errorcode;
			this.message = message;
		}

		public int getErrorcode() {
			return errorcode;
		}

		public String getMessage() {
			return message;
		}

		@Override
		public String toString() {

			return String.format("ShellExecutorService.ShellResult[errorcode=%d, message='%s' ]", errorcode, message);
		}

	}

}
