package did_you_mean_service;

public class StringComparison {
	/* If difference of two strings lengths 
	 * is 0 or 1, than these strings can be matchable.
	 * */
	int compareLengths(String s1, String s2){
		return Math.abs(s1.length() - s2.length());
	}
	
	boolean compareEquality(String s1, String s2){
		if(s1.compareTo(s2) == 0){
			return true;
		}
		return false;
	}
}
