package webdriver;

import java.io.File;
import java.io.IOException;

public abstract class Create_file {

	public static void main(String[] args) {
		String sourceFolder = System.getProperty("user.dir");
		String fileName = "createFile.txt";
		// tạo đường dẫn bằng File.separator
		File file = new File(sourceFolder +File.separator + fileName);

		try {
			if (file.createNewFile()) {
				System.out.println("Tạo file thành công!");
			} else {
				System.out.println("Tạo file thất bại!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
