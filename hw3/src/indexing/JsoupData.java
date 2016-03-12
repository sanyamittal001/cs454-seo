package indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupData {

	public static int check=0;

		public static void jsoupStart(String outputPath) throws MalformedURLException , IOException  {
			
			File folder = new File(outputPath);
			File[] listOfFilesArray = folder.listFiles();
			System.out.println("listOfFilesArray lengh : "+listOfFilesArray.length);
			List<File> listOfFiles = new ArrayList<File>();
			
				for(File fileMain : listOfFilesArray){
					if(!fileMain.isDirectory()){
						listOfFiles.add(fileMain);
					}
					check++;
			}
			File JsoupDirectory = new File(outputPath+"/jsoupData");
			JsoupDirectory.mkdir();
			System.out.println("ssssssss : > "+listOfFiles.size());
			
			int i =1;
			for (File string: listOfFiles) {

				File currentFile = string;
				String fileName = JsoupDirectory+"/"+"Jsoup "+i+" "+currentFile.getName();
				
				File file = new File(fileName);
					if (!file.exists()) {
						file.createNewFile();
					}else{
						file.delete();
						file.createNewFile();
					}
					
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(string.toString()), "UTF8"));
					String inputLine,s1="";
					while ((inputLine = br.readLine()) != null) {
						s1 = s1+inputLine;
					}
					Document doc = Jsoup.parse(s1);
					Element document = doc.body();
					FileUtils.writeStringToFile(file,document.text(),true);
					br.close();
					i++;
				}
		}
}