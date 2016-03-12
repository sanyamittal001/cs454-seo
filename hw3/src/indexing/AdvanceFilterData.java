package indexing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AdvanceFilterData {

	public static Set<String> stopWordsSet = new LinkedHashSet<String>();
	public static Set<String> dictionarySet = new LinkedHashSet<String>();
	public static Set<String> filterDataSpecialCharSet = new LinkedHashSet<String>();
	
	
	public static String outputPath = "/Users/sanyamittal/Desktop/localstorage/12-03-2016";
	public static String inputPath = "/Users/sanyamittal/Desktop/localstorage/12-03-2016/jsoupData";
	public static String stopWord = "/Users/sanyamittal/Desktop/localstorage/dictionary/stopWords.dat";
	public static String filterDataSpecialChar = "/Users/sanyamittal/Desktop/localstorage/dictionary/filterData";
	public static String mostTerm = "";
	
	
	public static void main(String[] args) throws Exception {

		JsoupData.jsoupStart(outputPath);
		advancefilter();
		System.out.println("********************** indexing with instinct ranking done **********************");
	}

	private static void advancefilter() throws Exception {

		fillStopWordsSet();
		filterDataSpecialChar();
		
		File folder = new File(inputPath);
		File[] listOfFiles = folder.listFiles();
		System.out.println("file size : >> "+listOfFiles.length);
		
		System.out.println(".................Indexing folder creating.................");
		
		File indexDirectory = new File(outputPath+"/Indexing");
		indexDirectory.mkdir();
		System.out.println(".................Indexing folder created.................");

		for (int v = 0; v < listOfFiles.length; v++) {
		  File file = listOfFiles[v];
		  
			if (file.isFile()) {
				BufferedWriter write = new BufferedWriter(
						new FileWriter(indexDirectory +"/"+"Index " + v + " " + file.getName()));
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(listOfFiles[v].toString()), "UTF-8"));
				String readLine = "";
				while ((readLine = br.readLine()) != null) {

					if (readLine.isEmpty()) {
						continue;
					}

					String line[] = readLine.split(" ");
					String allData = "";

					List<String> listWords = new ArrayList<>();

					for (int a = 0; a < line.length; a++) {
						allData = line[a];
						if (allData.matches("[0-9]")) {
							continue;
						}
						for (String string : filterDataSpecialCharSet) {
							allData = allData.replace(string.toLowerCase().toString(), "");
						}
						if (allData.matches(".*[^0-9^].*") && !allData.toString().matches("\\?")) {
							allData = allData.replaceAll("[^\\x20-\\x7e]", "");
							listWords.add(allData.trim());
						}
					}
					count(listWords, write);
				}
				br.close();
				write.close();
			}
		 } 
	}

	private static void count(List<String> listWords2, BufferedWriter write) throws IOException {


		if (!listWords2.isEmpty()) {
			Map<String, Integer> hashMap = new TreeMap<String, Integer>(Collections.reverseOrder());
			
			for (String string : listWords2) {
				
				if (!string.isEmpty() && !stopWordsSet.contains(string) && !string.contains("//?")
						&& !string.contains("1") && !string.contains("2") && !string.contains("3") && !string.contains("4") 
						&& !string.contains("5") && !string.contains("6") && !string.contains("7") && !string.contains("8")
						&& !string.contains("9") && !string.contains("0")) {
					string = string.toLowerCase();
					if (hashMap.containsKey(string)) {
						int current_count = hashMap.get(string);
						current_count++;
						hashMap.replace(string, current_count);
					} 
					
					else
						hashMap.put(string, 1);
				}
			}
				hashMap = sortByComparator(hashMap);
				
				for (String string1 : hashMap.keySet()) {
					int count1 = hashMap.get(string1);
					write.write(string1 + "\t" + count1 + "\n");
				}
		}
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {
		List list = new LinkedList<>(unsortMap.entrySet());
		
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	

	private static void fillStopWordsSet() throws IOException {

		FileReader fr = new FileReader(stopWord);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			stopWordsSet.add(line.toLowerCase().trim());
		}
		System.out.println("stopwordsSet > "+stopWordsSet.size());

		br.close();
	}
	
	private static void filterDataSpecialChar()  throws IOException {
		
		FileReader fr1 = new FileReader(filterDataSpecialChar);
		BufferedReader br = new BufferedReader(fr1);
		String line;
		while ((line = br.readLine()) != null) {
			filterDataSpecialCharSet.add(line.toLowerCase().trim());
		}
		System.out.println("filterDataSpecialCharSet > "+filterDataSpecialCharSet.size());
		br.close();
	}
}
