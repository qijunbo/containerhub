package com.example.docker.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.example.docker.repository.Container;

@Configuration
public class ShellExecutorService {

	@Value("${command}")
	private String commandPath;

	public Container createContainer(String owner) {

		if (execute(commandPath, owner)) {
			return new Container("lims1", 8082);
		} else {
			return null;
		}
	}

	public boolean execute(String... command) {

		ProcessBuilder pb = new ProcessBuilder(command );
		//ProcessBuilder pb = new ProcessBuilder("notepad.exe", "test.txt");
		// pb.command(new String[] { "notepad.exe", "test.txt" });
		try {
			Process process = pb.start();
			InputStream stderr = process.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(line);
			int exitVal = process.waitFor();
			System.out.println(exitVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Map<String, String> env = pb.environment();
//		env.put("VAR1", "myValue");
//		env.remove("OTHERVAR");
//		env.put("VAR2", env.get("VAR1") + "suffix");
//		pb.directory(new File("myDir"));

		return false;
	}

}
