package webInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import controller.QueryTermModel;

public class LuceneTester {
	
   String indexDir = "C:/crawlerDB/lucene/Index";
   String dataDir = "C:/crawlerDB/indexing";
   Indexer indexer;
   Searcher searcher;

   /*public static void main(String[] args) {*/
   public static List start(QueryTermModel queryTermModel) {
	  List ls = new ArrayList();
      LuceneTester tester;
      try {
         tester = new LuceneTester();
         /*tester.createIndex();*/
         if(queryTermModel.getQueryTerm1() == null)
         {
        	 System.out.println("IF tester : >> ");
        	 ls = tester.search(queryTermModel.getQueryTerm0());
         }

         else{
        	 System.out.println("elseIF tester : >> ");
        	List ls0 = tester.search(queryTermModel.getQueryTerm0());
        	ls.addAll(ls0);
        	List ls1  = tester.search(queryTermModel.getQueryTerm1());
        	ls.addAll(ls1);
         }
     	System.out.println("lucent tester : >> "+ls.size());
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return ls;
   }

  /* private void createIndex() throws IOException{
      indexer = new Indexer(indexDir);
      int numIndexed;
      long startTime = System.currentTimeMillis();	
      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
      long endTime = System.currentTimeMillis();
      indexer.close();
      System.out.println(numIndexed+" File indexed, time taken: "
         +(endTime-startTime)+" ms");		
   }*/

	private List search(String searchQuery) throws IOException, ParseException {
		List ls = new ArrayList();

		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
		Document doc = null;
		String a[] ;
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			doc = searcher.getDocument(scoreDoc);
			System.out.println("File   >>>		" + doc.get(IndexConstant.FILE_PATH));
			a = doc.toString().split("filename:");
			a = a[1].split(">");
			ls.add(a[0].toString());
		}
		System.out.println("search on lucene : >> "+ls.size());
		searcher.close();
		return ls;
	}
}