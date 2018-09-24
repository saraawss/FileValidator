package com;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static void main(String[] args) {
		
		Pattern PatternStr = Pattern.compile("^Test$");
		Pattern PatternPortfolioCode = Pattern.compile("^[A|B|C]$");
		Pattern PatternDateFormat = Pattern.compile("^((0[1-9]|\\d|3[01])(0[1-9]|1[0-2])[12]\\d{3})$");
		Pattern PatternDigits = Pattern.compile("^[0-9]{1,2}[:.,-]?$");
		Pattern PatternExtension = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
		Pattern PatternFull = Pattern.compile("[A-Za-z]*_[A-Za-z]*_[0-9]*(_[0-9])?(.[a-z]*)");
		
		
		String[] fileNameList = {
		        "Test_A_07121987.csv", 
		        "Test_E_07121987.csv",
		        "Test_A_13121987.csv",
		        "Hello_A_07121987.csv",
		        "Test_A_07121987.txt", 
		        "Test.txt"
		    };
		
		
		    for (String fileName : fileNameList) {
		    	
		    	//Check full File Name
		    	Matcher matcherFull = PatternFull.matcher(fileName);
		        boolean matchesFull = matcherFull.matches();
		        
		        if(!matchesFull) {
		        	System.out.println("File "+ fileName +" failed validation.File format should be ‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv’");
		        	continue;
		        }
		    	
		    	
		        Matcher matcherFileName = PatternExtension.matcher(fileName);
		        boolean matchesfileName = matcherFileName.matches();
		        String[] fileNameExtList = fileName.split("\\.");
		        
		        
		        if(matchesfileName) {
		        	fileName = fileName.replaceAll(".csv", "");
		        	
		        	String[] fileNameStrList = fileName.split("_");
		        	
		        	//Checking hardcoded string
		        	Matcher matcherStr = PatternStr.matcher(fileNameStrList[0]);
			        boolean matchesStr = matcherStr.matches();
			        
			        if(matchesStr) {
			        	
			        	//Checking portfolio code
			        	Matcher matcherpc = PatternPortfolioCode.matcher(fileNameStrList[1]);
				        boolean matchespc = matcherpc.matches();
				        
				        if(matchespc) {
				        	//Checking date format
				        	Matcher matcherDate = PatternDateFormat.matcher(fileNameStrList[2]);
					        boolean matchesDate = matcherDate.matches();
					        
					        if(matchesDate) {
					        	
					        	if(fileNameStrList.length == 4) {
						        	//Checking 2 sequence digits
						        	Matcher matcherDigits = PatternDigits.matcher(fileNameStrList[3]);
							        boolean matchesDigits = matcherDigits.matches();
							        
							        if(matchesDigits) {
							        	System.out.println("File '"+ fileName +"'  passed validation.");
							        }else {
							        	System.out.println("File '"+ fileName +"'  failed validation.");
							        }
					        	}else {
					        		System.out.println("File '"+ fileName +"'  passed validation.");
					        	}
					        	
					        }else {
					        	System.out.println("File '"+ fileName +"'  failed validation. Valuation Date is not a valid date format ‘ddmmyyyy’.");
					        }
				        	
				        }else {
				        	System.out.println("File '"+ fileName +"'  failed validation. PortfolioCode should be A/B/C found ‘ "+ fileNameStrList[1] + "’.");
				        }
			        	
			        }else {
			        	System.out.println("File '"+ fileName +"'  failed validation. Prefix for the file should be ‘Test’ found ‘ "+ fileNameStrList[0] + "’.");
			        }
		    	
		        }else {
		        	System.out.println("File "+ fileName +" failed validation.Invalid File format.Expected ‘csv’ found " + fileNameExtList[1]);
		        	
		        }

		    }

	}

}
