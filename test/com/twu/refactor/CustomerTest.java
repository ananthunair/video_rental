package com.twu.refactor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	private static final String GOLD_PATH = "test/data";

    private Customer dinsdale = new Customer("Dinsdale Pirhana");

    private Movie python = new Movie("Monty Python and the Holy Grail", MoviePricingCategory.REGULAR);
	private Movie ran = new Movie("Ran", MoviePricingCategory.REGULAR);
	private Movie la = new Movie("LA Confidential", MoviePricingCategory.NEW_RELEASE);
	private Movie trek = new Movie("Star Trek 13.2", MoviePricingCategory.NEW_RELEASE);
	private Movie wallace = new Movie("Wallace and Gromit", MoviePricingCategory.KIDS);

    public void setUp (){
       dinsdale.addRental(new Rental (python, 3));
       dinsdale.addRental(new Rental (ran, 1));
       dinsdale.addRental(new Rental (la, 2));
       dinsdale.addRental(new Rental (trek, 1));
       dinsdale.addRental(new Rental (wallace, 6));
   }

    public void testEmpty() throws Exception {
        dinsdale = new Customer("Dinsdale Pirhana");
        Statement s = new Statement(dinsdale);
        equalsFile("1st Output", "outputEmpty", s.getStatement());
    }
    public void testCustomer() throws Exception {
        Statement s = new Statement(dinsdale);
        equalsFile("1st Output", "output1", s.getStatement());
    }

    public void testChange() throws Exception {
        Statement s = new Statement(dinsdale);
    	la.setPriceCode(MoviePricingCategory.REGULAR);
        equalsFile("1st Output", "outputChange", s.getStatement());
    }

//    public void testHtml() throws Exception {
//        equalsFile("1st Output", "outputHtml", dinsdale.htmlStatement());
//    }


    protected void equalsFile(String message, String fileName, String actualValue) throws IOException{
        BufferedReader file = new BufferedReader (new FileReader (GOLD_PATH + '/' + fileName));
        BufferedReader actualStream = new BufferedReader (new StringReader (actualValue));
        String thisFileLine = null;
        while  ((thisFileLine = file.readLine()) != null) {
            assertEquals ("in file: " + fileName, thisFileLine, actualStream.readLine());
        }
    }

}
