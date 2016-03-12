***** START_UP APPROACH AND WORK INITIALIZATION *******

Folder structure : 

		(FOlder)System storage (user need to create) 
			-(FOlder)date folder (it will generated automatically)
				NOTE: below file will be generate automatically.
				crawledfiles1.txt
				crawledfies2.txt
				..
				..
				..
				crawledfien.txt
				FOLDER(jsonFiles)  (it will generated automatically)
				FOLDER(jsoupFiles)	(it will generated automatically)
				FOLDER(Indexing)	(it will generated automatically)
			
			-(folder)directory(user need to create)
				filterData
				stopWords.dat
			NOte:this files are must be present before you start AdvanceFilterData (indexing)
			
			-(folder)lucene(user need to create)
				(folder)index (user need to create)
					some files which i shared to you


STEP-1 : 

	Package Name : main
		file name : mainController.java 
			changes: change the path of rootFolder on line number 60 or nearby 60
			
			RUN : mainConteroller.java
			
Step : 2

	Pacakge Name : indexing
		file name : AdvanceFilterData.java
			changes : change the path inputpath  , output path according to above generated date path
					as well as  create a directory name as "dictionary" as mention above in folder structure
					and change path according to that
			
			RUN : AdvanceFilterData.java
			
Step : 3

	Pacakge Name : pageRank
		file name : GooglePageRankUtil.java
			changes : change intput path till the DATE folder which you ll be having after step 1
			
		RUN : AdvanceFilterData.java

Step : 4
	
	Pacakge Name : webInterface
		file name : LuceneTester.java
			changes : change indexdir path till the lucene/index
					  chage datadir till DATE/Indexing folder according to aboev folder structure 
				
			Run : queryPage.jsp  (run on server)

			query : example : university
						max page :  on how many pages you would like to search the term "university"

****** END *******

Runnable instructions step by step:
1. java -jar crawler.jar -d <depth> -u <url> -e
2. java -jar data_dump.jar
3. run AdvanceFilterData.java from eclipse
4. run queryPage.jsp  (run on server) using apache tomcat in eclipse


- Thank you (Sanya Mittal)

	
		
					
					
