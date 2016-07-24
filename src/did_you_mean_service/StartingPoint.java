package did_you_mean_service;

import java.util.ArrayList;
import java.util.Scanner;

public class StartingPoint {
	public static void main(String []args){
		//Users search input.
		String searchInput;
		
		//Taking search term from user.
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your search term: ");	
		searchInput = scan.nextLine();	
		searchInput = searchInput.toUpperCase();
		
		//Calling 'dym_service' and returning an ArrayList which contains similar words.
		DidYouMeanService dym_service = new DidYouMeanService();
		ArrayList<String> listOfSimilarWords = dym_service.DidYouMeanList(searchInput);
		
		//If list is empty.
		if(listOfSimilarWords.size() == 0){
			System.out.println("No similar words found..");
			scan.close();
			return;
		}
		
		//If last element of list is equal to search input, it means words.txt includes that input
		if(listOfSimilarWords.get(listOfSimilarWords.size()-1).compareTo(searchInput) == 0){
			System.out.println("Your input found in the list!!");
			scan.close();
			return;
		}
		
		System.out.println("You entered: '" + searchInput + "', but I couldn't find it.");
		System.out.println("Did you mean?");
		for(int i = 0; i < listOfSimilarWords.size(); i++){
			System.out.println(listOfSimilarWords.get(i));
		}
		
		scan.close();
	}
}
