import java.io.File;  // import the File class
import java.io.FileNotFoundException;  // import this class to handle errors
import java.util.Scanner; // import the Scanner class to read text files

// 5. Concurrent File Search
public class FileSearch implements Runnable{
	private String keyword; // the searched keyword 
	private String filename; // the directory of the file which is going to be searched

	// The run method of the thread
	@Override
	public void run() {
		String lines = ""; // lines that contain the keyword
		int count_lines = 1; // counter to know which line has the keyword
		try {
			File file = new File(filename); // get the file by filename
		    Scanner scan = new Scanner(file); // read the file
		    while (scan.hasNextLine()) {
		        String current_line = scan.nextLine();
		        // if current read line contains the keyword, then add the line number to the lines
		        if (current_line.contains(keyword)) lines += ", "+ count_lines; 
		        count_lines++; // increment the counter to track the line number
		    }
		    System.out.println("Keyword \""+ keyword +"\" found in "+ filename +" at lines:" + lines.substring(1));
			
		} catch (FileNotFoundException e) { //
		      System.out.println("File cannot be found: " + filename);
		}
	}
	
	// Constructor to define keyword and filename
	public FileSearch(String keyword,String filename) {
		this.keyword = keyword; // set the keyword
		this.filename = filename; // set the filename
	}
	
	
	public static void main(String[] args) {
		
		// Create FileSearch objects with the keyword and the filenames
		FileSearch fs1 = new FileSearch("thread","file1.txt");
		FileSearch fs2 = new FileSearch("thread","file2.txt");
		
		// Create threads
		Thread t1 = new Thread(fs1);
		Thread t2 = new Thread(fs2);

		// Start the threads
		t1.start();
		t2.start();
	}

}
