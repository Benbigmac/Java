

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UniversityManagement {

	final static String csvFileNames[]= {"research.csv","researchTopic.csv",
			"student.csv","enrollment.csv","faculty.csv","courses.csv","department.csv"};
	
	public static ArrayList<String[]> dataReader(String filename){
		ArrayList<String[]> table=new ArrayList<String[]>();
		try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
			
				table.add(line.split(","));//takes line and breaks it by commas then adds array to array list
				
			}
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return table;
		
	}

	// for each student retrieve the courses they're enrolled in
	public static void query1() {
		//step 1 read Students table
				ArrayList<String[]> dataReader = dataReader("student.csv");
				ArrayList<String[]> Student_data = new ArrayList<String[]>();
		//step 2 get Student Names and UINs
				int x=0;
				for (String[] object: dataReader) {
				 //skip first line
					if(x!=0) {

						String[] student= {object[0],object[2]};
						Student_data.add(student);
						
					}
					x++;
					
				}
				dataReader = dataReader("enrollment.csv");
				ArrayList<ArrayList<String>> studentcourselist = new ArrayList<ArrayList<String>>();
				
				for (String[] object: Student_data) {
					ArrayList<String> course = new ArrayList<String>();
					for (String[] enrol: dataReader) {
					
					if(object[1].equals(enrol[0])) {
						course.add(enrol[1]);
						
					}
						
				}	
					studentcourselist.add(course);
					}
				dataReader = dataReader("courses.csv");
				int count=0;
				for (ArrayList<String> object: studentcourselist) {
					System.out.print(Student_data.get(count)[0]+" is enrolled in");
					for (String lis: object) {
					
						for (String[] enrol: dataReader) {
							
							if(lis.equals(enrol[0])) {
								System.out.print(" "+enrol[1]);
	
							}
												
					}	
				
				}
					count++;
					System.out.println("");
				}
				
	}
	//What projects is Aishwarya Vijayan working on?  
	public static void query2(String target) {
		
		String email=new String();
		//research: Faculty Email	project id
		//faculty: Name	email	Address	Gender	Age	Salary
		//Project Id	Project Name	Budget
		//faculty.csv "research.csv","researchTopic.csv"
		
		//step 1 read faculty table
		ArrayList<String[]> dataReader = dataReader("faculty.csv");
		// step 2 find person's email
		for (String[] object: dataReader) {
		    if(object[0].equals("Aishwarya Vijayan")) {
		    	email=object[1];
		    }
		}
		String output = target+" is working on these projects: ";
		//step 3 find email in research file
		ArrayList<String> topics= new ArrayList<String>();
		dataReader = dataReader("research.csv");
		for (String[] object: dataReader) {
			
		    if(object[0].equals(email)) {
		    	topics.add(object[1]);
		    	
		    }
		}
		//step 4 find research name
		dataReader = dataReader("researchTopics.csv");
		for (String[] object: dataReader) {
		for (String topic: topics) {
		    if(object[0].equals(topic) ){
		    	output+=(" "+ object[1]+",");

		    }
		}}
		System.out.println(output);
	}
	
	public static void main(String[] args) {
		System.out.println("Bennett Maciorowski");
		System.out.println("Query 1");
		query1();
		System.out.println("Query 2");
		query2("Aishwarya Vijayan");
	}

}
