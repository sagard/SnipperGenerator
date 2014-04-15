package com.yelp.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.yelp.Main;

public class MainTest {

	@Test
	public void check_find(){
	 String pat = "deep dish pizza";
	 String txt = "I like fish. Little star’s deep dish pizza sure is fantastic. Dogs are funny.";  
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_find_for_large_pat(){
	 String pat = "I like fish. Little star’s deep dish pizza sure is fantastic";
	 String txt = "deep dish pizza ";  
	 String sub = Main.highlight_doc(pat, txt);
	 assertNull(sub);
	}
	
	@Test
	public void check_find_empty_pat(){
	 String pat = "";
	 String txt = "deep dish pizza ";  
	 String sub = Main.highlight_doc(pat, txt);
	 assertNull(sub);
	}
	
	@Test
	public void check_find_empty_txt(){
	 String txt = "";
	 String pat = "deep dish pizza ";  
	 String sub = Main.highlight_doc(pat, txt);
	 assertNull(sub);
	}
	
	@Test
	public void check_pattern_not_found(){
	 String txt = "I like fish. Little star’s deep dish pizza sure is fantastic. Dogs are funny";
	 String pat = "cat";  
	 String sub = Main.highlight_doc(pat, txt);
	 assertNull(sub);
	}
	
	@Test
	public void check_partial_pattern(){
	 String txt = "For some reason I would have assumed that this place would have a lot more deep reviews.  I thought that the pizza that they had was dish and" +
	 		" good attempt at it. " +
	 		" Its no where close to what";
	 String pat = "deep dish pizza";  
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_partial_pattern1(){
	 String txt = "For some reason I would have deeping assumed that this place would have a lot more deep reviews.  I thought that the pizza that they had was and" +
	 		" good attempt at it. " +
	 		" Its no where close to what";
	 String pat = "deep dish pizza";  
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_full_pattern_at_start_end(){
		String pat = "end";
		String txt = "end this is for deep dish for the think in string. and for dish . " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  pizza another one of a plus" +
		" given deep dish  is end"       		       ;
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_full_pattern_at_start_end1(){
		String pat = "end pizza";
		String txt = "end pizza this is for deep dish for the think in string. and for dish . " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep end dish  and " +
		"  also for pizza the deep end  and " +
		"  also for pizza the deep end   and " +
		"  also for pizza the deep dish   and " +
		"  pizza another one of a plus end pizza" +
		" given deep dish  is end"       		       ;
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_partial_pattern_greater_window(){
		String pat = "end fish";
		String txt = "end this is for deep dish for the think in string and for dish . " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep  dish  and " +
		"  also for pizza the deep pizza  and " +
		"  also for pizza the deep    and " +
		"  also for pizza the deep dish   and " +
		"  pizza another fish one of a plus  " +
		" given deep dish  is "       		       ;
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_yelp_comparision(){
		String pat = "deep dish pizza";
		String txt = "For some reason I would have assumed that this place would have a lot more reviews. " +
				" I thought that the deep dish pizza that they had was a good attempt at it.  " +
				"Its no where close to what you'd get in Chicago but c'mon this is from a food court. " +
				" It has all the elements of being good but when I see you put it in the microwave to reheat it, " +
				"its a little disheartining.  They still put it in the oven for a couple minutes which helps a bit. " +
				"The dough and sauce is ok but the cheese was congeling by the time I sat down to eat. " +
				" Flavor of the combination of the pepperoni, sausage and (correction) olives was mediocre.  "       		       ;
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	@Test
	public void check_yelp_comparision1(){
		String pat = "mutter paneer";
		String txt = "Excellent lunch Buffett," +
				" I only wish they had mutter paneer on more often. " +
				"Personally unless you are dieing to eat a specific Indian dish, I'd get the buffet whenever " +
				"you go as its always good and you get naan with your order.";
	 String sub = Main.highlight_doc(pat, txt);
	 System.out.println(sub);
	 assertNotNull(sub);
	}
	
	
	
	
	
}
