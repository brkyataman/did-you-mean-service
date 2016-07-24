package did_you_mean_service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ServiceTests {

	@Test
	public void testService() {
		DidYouMeanService dym_service = new DidYouMeanService();
		ArrayList<String> results = dym_service.DidYouMeanList("ABACUSS");
		assertNotSame(0,results.size()); //There are some similar words.
		
		results = dym_service.DidYouMeanList("ASDASDASD");
		assertSame(0,results.size()); //List is empty because there is no similarity with that input.
		
		results = dym_service.DidYouMeanList("ABACUS");
		assertSame("ABACUS", results.get(results.size()-1)); //Last element is same because it has found in the txt document.
	}

}
