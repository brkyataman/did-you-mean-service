package did_you_mean_service;

public class DistanceCalculator {
	int DamerauLevenshtein(String s1, String s2){
		int[][] matrix = new int[s1.length()+1][s2.length()+1];
		int equalityCost;
		int insertion; //left
		int deletion; //up
		int match_mismatch; //cross
		int substitution;
		int INF = 999999;

		//Initializing matrix first row and first column
		for(int i = 0; i < s1.length()+1; i++){
			matrix[i][0] = i;
		}
		for(int j = 0; j < s2.length()+1; j++){
			matrix[0][j] = j;
		}
		
		for(int i = 1; i < s1.length()+1; i++){
			for(int j = 1; j < s2.length() + 1; j++){
				equalityCost = (s1.charAt(i-1) == s2.charAt(j-1)) ? 0:1;//If chars are same there is no cost value.
				insertion = matrix[i][j-1] + 1;
				deletion = matrix[i-1][j] + 1;
				match_mismatch = matrix[i-1][j-1] + equalityCost;
				substitution = INF;//This value used when conditions for substitution are not provided.
				if(i > 1 && j > 1 && s1.charAt(i-1) == s2.charAt(j-2) && s1.charAt(i-2) == s2.charAt(j-1)){
					substitution = matrix[i-2][j-2] + 1;
				}
				matrix[i][j] = Math.min(Math.min(match_mismatch, deletion), Math.min(insertion, substitution));
			}
		}
		
		return matrix[s1.length()][s2.length()];
	}
}
