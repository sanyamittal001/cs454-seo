package pageRank;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GooglePageRankUtil {
	public static String inputPath = "/Users/sanyamittal/Desktop/localstorage/12-03-2016";
	public static int  check = 0;

	public static void main(String[] args) {
		File folder = new File(inputPath);
		File[] listOfFilesArray = folder.listFiles();
		System.out.println("listOfFilesArray lengh : "+listOfFilesArray.length);
		List<File> listOfFiles = new ArrayList<File>();
		
			for(File fileMain : listOfFilesArray){
				if(!fileMain.isDirectory()){
					listOfFiles.add(fileMain);
				}
				check++;
		}

		GooglePageRankUtil pagerankTool = new GooglePageRankUtil();
		for (File file : listOfFiles) {

			String readFilename = file.getName().toString();
			String fileReplace = readFilename.replaceAll(",", "/");
			String filename1 = fileReplace.replaceAll("-", ":");
			String spliedURL[] = filename1.split(".txt");

			System.out.println(pagerankTool.getPageRank(spliedURL[0]));

		}
	}

	public int getPageRank(String pageAddress) {

		String result = "";

		JenkinsHash jenkinsHash = new JenkinsHash();
		long hash = jenkinsHash.hash(("info:" + pageAddress).getBytes());

		// Append a 6 in front of the hashing value.
		String url = "http://toolbarqueries.google.com/tbr?client=navclient-auto&hl=en&" + "ch=6" + hash
				+ "&ie=UTF-8&oe=UTF-8&features=Rank&q=info:" + pageAddress;

		System.out.println("Requesting URL : " + pageAddress);

		try {
			URLConnection conn = new URL(url).openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String input;
			while ((input = br.readLine()) != null) {

				// If response Rank_1:1:2 then PR = 2
				System.out.println(input);

				result = input.substring(input.lastIndexOf(":") + 1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if ("".equals(result)) {
			return 0;
		} else {
			return Integer.valueOf(result);
		}
	}
}