package net.morphbank.uuid;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * Create UUIDs v4 and save them in a text file.
 * @author gjimenez
 *
 */
public class CreateUUIDs {

	private String filename = "uuids.csv"; //default filename if not provided
	private int howMany;
	
	/**
	 * 
	 * @param args [howMany, filename]
	 */
	public static void main(String[] args) {
		CreateUUIDs createUUIDs;
		if (args.length == 0) {
			System.err.println("You must specify the numbers of UUIDs you want.");
			System.err.println("java -jar createUUIDs.jar 400 [saveMyUUIDsHere.csv]");
			System.exit(1);
		}
		if (args.length > 1) {
			createUUIDs = new CreateUUIDs(Integer.parseInt(args[0]), args[1]);
		}
		else {
			createUUIDs = new CreateUUIDs(Integer.parseInt(args[0]));
		}
		createUUIDs.run();
				
	}
	
	public CreateUUIDs(int howMany) {
		this.howMany = howMany;
	}
	
	public CreateUUIDs(int howMany, String filename) {
		this.howMany = howMany;
		this.filename = filename;
	}
	
	public void run() {
		BufferedWriter bw = new BufferedWriter(this.getFile(filename));
		for (int i = 0; i < howMany; i++) {
			String uuid = UUID.randomUUID().toString();
			this.writeFile(bw, uuid);
			System.out.println((i+1) + ":" + uuid);
		}
		this.closeBuffer(bw);
	}
	

	private FileWriter getFile(String filename) {
		try {
			return new FileWriter(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void writeFile(BufferedWriter bw, String uuid) {
		try {
			bw.write(uuid + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeBuffer(BufferedWriter bw) {
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
