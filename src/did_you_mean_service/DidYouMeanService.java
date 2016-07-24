package did_you_mean_service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DidYouMeanService {
	ArrayList<String> DidYouMeanList(String searchInput){
		
		ArrayList<String> listOfSimilarWords = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\words.txt"))){
			
			String currentLine; //Word on the document.
			int lengthDifference; //Length diff. of input and current line.
			StringComparison compare = new StringComparison(); //Going to compare lengths and equality.
			DistanceCalculator calc = new DistanceCalculator(); //Algorithm to calculate distance between two strings.
			int distance;
			
			//Reading document line by line and comparing it with search input.
			while((currentLine = br.readLine()) != null){
				
				//If there is a length difference higher than 1,
				//there is no need to waste time for 'did you mean service'
				lengthDifference = compare.compareLengths(currentLine, searchInput);
					
				if(lengthDifference < 2){//else there is no need to compare two string cause they can't be matchable.	
					if(compare.compareEquality(currentLine, searchInput)){//If both strings are equal, there is no need for 'did you mean service'
						listOfSimilarWords.add(searchInput);//Adding input to list and returning so that program will understand that word has found.
						return listOfSimilarWords;
					}
					else{
						distance = calc.DamerauLevenshtein(searchInput, currentLine);
						if(distance == 1){
							listOfSimilarWords.add(currentLine);				
						}
					}
				}		
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return listOfSimilarWords;
	}
}
