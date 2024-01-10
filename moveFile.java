import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class moveFile {

	public static void main(String[] args) throws IOException, InterruptedException {
		String sourcedir = "C:\\Users\\User 1\\Downloads\\";
		String dest_dir_audio = "C:\\Users\\User 1\\Downloads\\Audio";
		String dest_dir_image = "C:\\Users\\User 1\\Downloads\\Photos";
		String dest_dir_doc = "C:\\Users\\User 1\\Downloads\\Documents";
		String dest_dir_misc = "C:\\Users\\User 1\\Downloads\\Misc";
		
		File fObj = new File(sourcedir);
			if (fObj.exists() && fObj.isDirectory()) {
				File a[] = fObj.listFiles();
				for (File f: a) {
					String file_path = f.getAbsolutePath();
					String fileType = "";
					int i = file_path.lastIndexOf('.');
					if (i > 0) {
		                fileType = file_path.substring(i + 1);
		            }
					System.out.println(file_path);
					System.out.println(fileType);
					if ((fileType.equals("wav")) || (fileType.equals("mp3"))) {
						System.out.println("Move audio file");
						moveFiles(file_path, dest_dir_audio);
					} else if ((fileType.equals("jpg"))|| (fileType.equals("jpeg")) || (fileType.equals("png"))) {
						System.out.println("Move image file");
						moveFiles(file_path, dest_dir_image);
					} else if ((fileType.equals("docx")) || (fileType.equals("pdf")) || (fileType.equals("exe")) || (fileType.equals("txt"))) {
						System.out.println("Move doc file");
						moveFiles(file_path, dest_dir_doc);
					} else {
						System.out.println("Move misc file");
						moveFiles(file_path, dest_dir_misc);
					}
				}
			}	
			System.out.print("Finished Moving");
	}
	
	private static void moveFiles(String src_path, String dest_path) throws FileNotFoundException, IOException, InterruptedException{
		File srcFile = new File(src_path);
		if (srcFile.exists()) {
			File destFile = new File(dest_path + File.separator + srcFile.getName());
			try (FileInputStream is = new FileInputStream(srcFile); FileOutputStream os = new FileOutputStream(destFile)){
				int len;
				byte[] bytes = new byte[1024];
				while ((len = is.read(bytes)) > 0) {
					os.write(bytes, 0, len);
					Thread.sleep(0);
				}
			} catch (IOException | InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		srcFile.delete();
	}
}