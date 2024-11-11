package Assignment2;
import java.util.ArrayList; // Data structure chose
import java.util.Collections;
import java.util.Scanner; // To scan input
import java.util.InputMismatchException; //For try catch
import java.util.Comparator; //For sorting
public class ProductManagementSystem {
	private static Scanner sc = new Scanner(System.in); //Declare scanner
	private ArrayList<Product> products = new ArrayList<>(); //Declare arraylist
	private Menu menus =  new Menu(); //To use method in Menu.java
	
	public ProductManagementSystem() //For initially store product records
	{
        products = new ArrayList<>(); //Declare product arraylist
        // Add sample data as it is more realistic and more convenient in testing features
        products.add(new Product("M53201024","Malaysia", "Intel i5", "320 GB", "1 TB", 10));
        products.add(new Product("J53201024","Japan", "Intel i5","320 GB", "1 TB", 5));
        products.add(new Product("A53201024","America", "Intel i5", "320 GB", "1 TB", 20));
        products.add(new Product("M73201024","Malaysia", "Intel i7", "320 GB", "1 TB", 10));
        products.add(new Product("J95002048","Japan", "Intel i9","500 GB", "2 GB", 5));
        products.add(new Product("A510244096","America", "Intel i5", "1 TB", "4 GB", 20));
        products.add(new Product("M55002048","Malaysia", "Intel i5", "500 GB", "2 GB", 10));
        products.add(new Product("J510242048","Japan", "Intel i5","1 TB", "2 GB", 5));
        products.add(new Product("M910244096","Malaysia", "Intel i9", "1 TB", "4 GB", 20));
        products.add(new Product("A910241024","America", "Intel i9", "1 TB", "1 TB", 10));
    }
	
	public void addProduct() //(1) Add New Products
	{
		boolean productCodeDuplication = false; //validate if the product code already exist in the system
		boolean validateCountry = false; //validate if the country of the product code is correct such as 'M'
		boolean validateProcessor = false; //validate if the processor type is correct such as '5'
		boolean validateHardDisk = false; //validate if the hard disk is correct such as '320'
		boolean validateInternalMemory = false; //validate if the internal memory is correct such as '1024'
		boolean validateQuantity = false; //validate the quantity is correct as it is positive and numeric
		boolean validateSelection = false; //validate if the selection is in the option list and numeric
		String productCode = "";
		int selection=0;
		do{   
        try{ //catch possible error
        try { //catch input mismatch error for selection
        System.out.println("Enter your selection.");
        menus.menu12(); // to chose either add record or exit
        selection = sc.nextInt();
        while(selection!=1 && selection!=2) //validate selection in range
        {
        	System.out.println("Please enter either 1 or 2.");
        	System.out.println("Enter your selection.");
            menus.menu12(); // ask to choose add record or exit
            selection = sc.nextInt();
        }
        	}catch(InputMismatchException e)
        	{
        		System.out.println("Invalid selection, please input an integer number.");
    			validateSelection=false;
    			sc.next();
        	}
        switch (selection)
        {
        case 1: //(1) Add Records
        do
        {
    	Product product = new Product();
        validateSelection = true;
        sc.nextLine(); //Consume new line
        System.out.println("Enter the product code of the new product according to the product code table. ");
        System.out.print("Product code: ");
        productCode = sc.nextLine().toUpperCase(); //to handle both upper case and lower case country character (eg:M and m)
        while(productCode.isEmpty()) // validate it is not empty
        {
            System.out.println("The product code should not be empty.");
            System.out.println("Please try again.");
            System.out.println("Enter the product code of the new product according to the product code table. ");
            System.out.print("Product code: ");
            productCode = sc.nextLine().toUpperCase();
        }
        while(productCode.length() < 9 || productCode.length() > 10 ) //Validate the length of the product code
        {
            System.out.println("Invalid length. The product code should only consists of 9 - 10 characters.");
            System.out.println("Please try again.");
            System.out.println("Enter the product code of the new product according to the product code table. ");
            System.out.print("Product code: ");
            productCode = sc.nextLine().toUpperCase();
        }
        do
        {
        for (Product product_ : products) 
        {
        	if(product_.getProductCode().equals(productCode)) //validate if the code already exist
            {
                System.out.println("The records of the product code is already in the "
                		+ "system, please enter unexisting product code."); 
                productCodeDuplication = true;
                System.out.println("Please try again.");
                System.out.println("Enter the product code of the new product according to the product code table. ");
                System.out.print("Product code: ");
                productCode = sc.nextLine().toUpperCase();
            }
        	else
        	{
        		 productCodeDuplication=false;
        	}
        }
        }while(productCodeDuplication);
        String stringProductCode = productCode;
        //Retrieve the records details from the product code using charAt
        char countryCharacter = stringProductCode.charAt(0); //country character of the product code at index 0
        char processorCharacter = stringProductCode.charAt(1); //processor character of the product code at index 0
        char hardDiskCharacter = stringProductCode.charAt(2); //hard disk character of the product code at index 0
        char internalMemoryCharacter=stringProductCode.charAt(5); //default product code length 9
        if (productCode.length()==10) /*if the length of the product code is 10, the 
        	internal memory character will start at index of 6*/
        {
        	internalMemoryCharacter = stringProductCode.charAt(6);
        }

        //Set country according to product code
        switch (countryCharacter) 
        { 
            case 'M':
                product.setCountry("Malaysia");
                validateCountry = true;
                break;

            case 'J':
                product.setCountry("Japan");
                validateCountry = true;
                break;

            case 'A':
                product.setCountry("America");
                validateCountry = true;
                break;
            default:
                System.out.println("Invalid country.");
                validateCountry = false;
        }
        //Set processor type according to product code
        switch(processorCharacter) {
            case '5':
                product.setProcessor("Intel i5");
                validateProcessor = true;
                break;

            case '7':
                product.setProcessor("Intel i7");
                validateProcessor = true;
                break;

            case '9':
                product.setProcessor("Intel i9");
                validateProcessor = true;
                break;

            default:
                System.out.println("Invalid processor type.");
                validateProcessor = false;  
        }
        //hard disk character start from index 2 to index 4
        String hardDiskChar = productCode.substring(2,5);
      //Set hard disk capacity according to product code
        switch(hardDiskCharacter) {
            case '3':
            	    
                    //Validate is 320 correctly typed
                    if(hardDiskChar.equals("320"))
                    {
                        product.setHardDiskCapacity("320 GB");
                        validateHardDisk = true;  
                        break;
                    }
                    else
                    {
                        System.out.println("The hard disk capacity should be 320. Please enter again.");
                        validateHardDisk = false;  
                        break;
                    }
               

            case '5':
            	    //Validate is 500 correctly typed
                    if(hardDiskChar.equals("500"))
                    {
                        product.setHardDiskCapacity("500 GB");
                        validateHardDisk = true;  
                        break;
                    }
                    else
                    {
                        System.out.println("The hard disk capacity should be 500. Please enter again. ");
                        validateHardDisk = false;  
                        break;
                    }
                    
               
                
             case '1':
                	String hardDiskChar2 = productCode.substring(2,6);
                	//Validate is 1024 correctly typed
                    if(hardDiskChar2.equals("1024"))
                    {
                        product.setHardDiskCapacity("1 TB");
                        validateHardDisk = true;
                        break;
                    }
                    else
                    {
                        System.out.println("The hard disk capacity should be 1024. Please enter again. ");
                        validateHardDisk = false; 
                        break;
                    }
                
                
            default:
                //If not equal to either 320, 500, 1024
                System.out.println("Invalid hard disk capacity.");
                validateHardDisk = false; 
        }
        //Set internal memory capacity according to product code
        switch(internalMemoryCharacter) {
            case '1':
            		// If the length of the product code is 9, then the internal memory character start from index 6
                	if(productCode.length() == 9)
                	{
                		String memoryCharacter = productCode.substring(5);
                		//Validate is 1024 correctly typed
                		if(memoryCharacter.equals("1024")){
                			product.setInternalMemoryCapacity("1 TB");
                			validateInternalMemory = true; 
                			break;
                		}
                		else
                		{
                			System.out.println("The internal memory capacity should be 1024. Please enter again.");
                			validateInternalMemory = false; 
                			break;
                		} 
                   }
               
                    // If the length of the product code is 10, then the internal memory character start from index 6
                   else if(productCode.length() == 10){
                        String memoryCharacter = productCode.substring(6);
                        //Validate is 1024 correctly typed
                        if(memoryCharacter.equals("1024")){
                            product.setInternalMemoryCapacity("1 TB");
                            validateInternalMemory = true; 
                            break;
                        }else{
                            System.out.println("The internal memory capacity should be 1024. Please enter again.");
                            validateInternalMemory = false; 
                            break;
                        }
                    }
            case '2':
            	// If the length of the product code is 9, then the internal memory character start from index 6
            	if(productCode.length() == 9)
            	{
            		String memoryCharacter = productCode.substring(5);
            		//Validate is 2048 correctly typed
            		if(memoryCharacter.equals("2048")){
            			product.setInternalMemoryCapacity("2 GB");
            			validateInternalMemory = true; 
            			break;
            		}
            		else
            		{
            			System.out.println("The internal memory capacity should be 2048. Please enter again.");
            			validateInternalMemory = false; 
            			break;
            		} 
               }
           
                // If the length of the product code is 10, then the internal memory character start from index 6
               else if(productCode.length() == 10){
                    String memoryCharacter = productCode.substring(6);
                    //Validate is 2048 correctly typed
                    if(memoryCharacter.equals("2048")){
            			product.setInternalMemoryCapacity("2 GB");
                        validateInternalMemory = true; 
                        break;
                    }else{
                        System.out.println("The internal memory capacity should be 2048. Please enter again.");
                        validateInternalMemory = false; 
                        break;
                    }
                }
                break;
            case '4':
            	// If the length of the product code is 9, then the internal memory character start from index 6
            	if(productCode.length() == 9)
            	{
            		String memoryCharacter = productCode.substring(5);
            		//Validate is 4096 correctly typed
            		if(memoryCharacter.equals("4096")){
            			product.setInternalMemoryCapacity("4 GB");
            			validateInternalMemory = true; 
            			break;
            		}
            		else
            		{
            			System.out.println("The internal memory capacity should be 4096. Please enter again.");
            			validateInternalMemory = false; 
            			break;
            		} 
               }
           
                // If the length of the product code is 10, then the internal memory character start from index 6
               else if(productCode.length() == 10){
                    String memoryCharacter = productCode.substring(6);
                    //Validate is 4096 correctly typed
                    if(memoryCharacter.equals("4096")){
            			product.setInternalMemoryCapacity("4 GB");
                        validateInternalMemory = true; 
                        break;
                    }else
                    {
                        System.out.println("The internal memory capacity should be 4096. Please enter again.");
                        validateInternalMemory = false; 
                        break;
                    }
                }
                break;
            default :
                System.out.println("Invalid internal memory capacity.");
                validateInternalMemory = false; 
        }
        //only run if the product code is totally validate
        if(validateSelection && !productCodeDuplication && validateCountry && validateProcessor && 
        		validateHardDisk && validateInternalMemory && validateSelection)
		{
        do
        {
		try //catch input mismatch errors and other possible errors
		{
		//prompt user to input the quantity for the new record
		System.out.print("Enter product quantity: ");
		int quantity = sc.nextInt();
		while(quantity<0) //validate quantity should be positive
		{
			System.out.println("Invalid quantity number. It should be positive, please enter again.");
			System.out.print("Enter product quantity: ");
			quantity = sc.nextInt();
		}
		validateQuantity = true;
		product.setProductCode(productCode); //set product code according to user input
		product.setQuantity(quantity); //set quantity according to user input
        products.add(product); //add product
        System.out.println("Product record successfully added.");
		}
		catch(InputMismatchException e)
		{
			System.out.println("Invalid selection, please input an integer number.");
			validateQuantity=false;
			sc.next();
		}
		catch(Exception e)
		{
			System.out.println("Something is error.");
		}
        }while(!validateQuantity);
		}
        //prompt user for not adding the product as it is invalid
        if(productCodeDuplication || !validateCountry || !validateProcessor || 
        		!validateHardDisk || !validateInternalMemory || !validateQuantity || !validateSelection)
        {
        	System.out.println("Product failed to add, please check again the product code.");
        }
        break;
        }while(productCodeDuplication || !validateCountry || !validateProcessor || 
        		!validateHardDisk || !validateInternalMemory || !validateQuantity || !validateSelection);
        
        case 2: //(2) Exit
        	validateSelection = true;
        	break;
        }
        }
        catch(Exception e)
		{
			System.out.println("Something is error.");
		}
    }while(!validateSelection || selection!=2); 
		//will keep looping if either selection is invalid or user not chose to exit
	}
	
	public void updateProduct() //(2) Update Records
	{
		boolean validSelection = false;
		int selection=0;
		do
		{
		try // catch input mismatch error and other possible errors
		{
		boolean validSelection2=false;
	    menus.menu8();
	    selection = sc.nextInt();
	    switch(selection)
	    {
	    case 1: // Update (1) Manufacturing Country
	    	do
	    	{
	    	try //catch mismatch input error and other possible errors
	    	{
	    	int selecCount1=0, selecCount2=0, updateSelection = 0; /*selecCount1 is old country option
	    	selecCount2 is new country option*/
	    	int count=0; //to calculate how many records equal to the old country
	    	String originalCountry="", newCountry="";
	    	do
	    	{
	    	do
	    	{
	    	System.out.println("Select the manufacturing country of the product you want to update.");
	    	menus.menu4(); //display country for user to select
	    	selecCount1 = sc.nextInt();
	    	if(selecCount1<1||selecCount1>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount1<1||selecCount1>3);
	    	do
	    	{
	    	System.out.println("Select the new manufacturing country:");
	    	menus.menu4();
	    	selecCount2 = sc.nextInt();
	    	if(selecCount2<1||selecCount2>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount2<1||selecCount2>3);
	    	if (selecCount1==selecCount2) //validate if old and new is same
	    	{
	    		System.out.println("The old and new manufacturing country should not be the same. Please try again.");
	    	}
	    	}while(selecCount1==selecCount2);
	    	switch(selecCount1) //assign original country according to option
	    	{
	    	case 1:
	    		originalCountry = "Malaysia";
	    		break;
	    		
	    	case 2:
	    		originalCountry = "Japan";
	    		 break;
	    		 
	    	case 3:
	    		originalCountry = "America";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	switch(selecCount2) //assign new country according to option
	    	{
	    	case 1:
	    		newCountry = "Malaysia";
	    		break;
	    		
	    	case 2:
	    		newCountry = "Japan";
	    		 break;
	    		 
	    	case 3:
	    		newCountry = "America";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	for (Product product : products) //count number of records same as original country
        	{
        		if(product.getCountry().equals(originalCountry))
                {
                    count++;
                }
        	}
	    	System.out.println("There are "+count+" records with manufacturing country of "+originalCountry+":");
	    	if (count!=0) // if at least one record will display the record that is old country
	    	{
	    	menus.menu(); //print header
	    	for (Product product : products) //display records that are same as original country
        	{
        		if(product.getCountry().equals(originalCountry))
                {
                    System.out.print(product);
                }
        	}
	    	menus.menu9(); //ask either update all or update one
	    	updateSelection = sc.nextInt();
	    	switch(updateSelection)
	    	{
	    	case 1: // (1) Update All
	    		for (Product product : products) //set every product that are original country to new country
	    	    {
	    	        if (product.getCountry().equals(originalCountry)) 
	    	        {
	    	            product.setCountry(newCountry);
	    	        }
	    	    }
	    		System.out.println("The product records has been update.");
	    		break;
	    		
	    	case 2: // (2) Update One
	    		int cont=0; //for user to continue update or not
	    		do
	    		{
	    		Product productX = null; // to store the product
	    		String productCode="";
	    		boolean validProductCode = true, validProduct = true;
	    		do
	    		{
	    			System.out.println("Please enter the product code you want to update.");
	    			System.out.print("Product code: ");
	    			if(validProductCode && validProduct)
	    			{
	    				sc.nextLine(); //Consume new line
	    			}
		    		productCode = sc.nextLine().toUpperCase();
	    			for (Product product : products) //validate if the product is in the system
		    	    {
		    	        if (!product.getProductCode().equals(productCode)) 
		    	        {
		    	            validProductCode = false;
		    	        }
		    	        else
		    	        {
		    	        	productX = product;
		    	        	validProductCode = true;
			    	        break;
		    	        }
		    	    }
	    			//if the product code is validate but the product country is already the new country
	    			if (validProductCode && productX != null && productX.getCountry().equals(newCountry))
	    			{
	    				System.out.println("The country of "+productCode+" is already "+newCountry+".");
	    				validProduct = false;
	    			}
	    			else
	    			{
	    				validProduct = true;
	    			}
	    			if(!validProductCode)
	    			{
	    				System.out.println("Product code not exist, please add the product to the system first.");
	    			}
	    		}while(!validProductCode || !validProduct); //will keep looping if the product or product code invalid
	    		for (Product product : products) //update the product country to new country
	    	    {
	    			if (product.getProductCode().equals(productCode))
	    	        {
	    	            product.setCountry(newCountry);
	    	        }
	    	    }
	    		System.out.println(productCode+" successfully updated.");
	    		System.out.println("Continue update?");
	    		menus.menu10(); //ask user to select yes or no
	    		cont = sc.nextInt();
	    		}while(cont!=2); //will keep looping until user choose no for the continue option
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	}
	    	validSelection2=true;
	    	}catch(InputMismatchException e)
			{
				System.out.println("Invalid selection, please input an integer number.");
				validSelection=false;
				sc.next();
			}
	    	catch(Exception e)
			{
				System.out.println("Something is error.");
			}
			}while(!validSelection2);
	    	break;
	    	
	    case 2: // Update (2) Processor Type
	    	do
	    	{
	    	try //catch mismatch input error and other possible errors
	    	{
	    	int selecCount1=0, selecCount2=0, updateSelection = 0; //selecCount1 is old option selecCount2 is new option
	    	int count=0; //count product with original processor
	    	String originalProcessor="", newProcessor="";
	    	do
	    	{
	    	do
	    	{
	    	System.out.println("Select the processor type of the product you want to update.");
	    	menus.menu5(); //ask user to choose original processor type i5 i7 i9
	    	selecCount1 = sc.nextInt();
	    	if(selecCount1<1||selecCount1>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount1<1||selecCount1>3);
	    	do
	    	{
	    	System.out.println("Select the new processor type:");
	    	menus.menu5(); //ask user to choose new processor type i5 i7 i9
	    	selecCount2 = sc.nextInt();
	    	if(selecCount2<1||selecCount2>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount2<1||selecCount2>3);
	    	if (selecCount1==selecCount2) //validate if original and new is same option
	    	{
	    		System.out.println("The old and new processor type should not be the same. Please try again.");
	    	}
	    	}while(selecCount1==selecCount2);
	    	switch(selecCount1) //assign original according to option
	    	{
	    	case 1:
	    		originalProcessor = "Intel i5";
	    		break;
	    		
	    	case 2:
	    		originalProcessor = "Intel i7";
	    		 break;
	    		 
	    	case 3:
	    		originalProcessor = "Intel i9";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	switch(selecCount2) //assign new according to option
	    	{
	    	case 1:
	    		newProcessor = "Intel i5";
	    		break;
	    		
	    	case 2:
	    		newProcessor = "Intel i7";
	    		 break;
	    		 
	    	case 3:
	    		newProcessor = "Intel i9";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	for (Product product : products) //count product same as original processor
        	{
        		if(product.getProcessor().equals(originalProcessor))
                {
                    count++;
                }
        	}
	    	System.out.println("There are "+count+" records with processor type of "+originalProcessor+":");
	    	if (count!=0)
	    	{
	    	menus.menu(); //print header
	    	for (Product product : products) //display record with same original processor
        	{
	    		if(product.getProcessor().equals(originalProcessor))
                {
                    System.out.print(product);
                }
        	}
	    	menus.menu9(); //ask update all or one
	    	updateSelection = sc.nextInt();
	    	switch(updateSelection) 
	    	{
	    	case 1: // (1) Update All
	    		for (Product product : products) 
	    	    {
	    			if(product.getProcessor().equals(originalProcessor))
	    	        {
	    	            product.setProcessor(newProcessor);
	    	        }
	    	    }
	    		System.out.println("The product records has been update.");
	    		break;
	    	case 2:
	    		int cont=0; // for user choose to continue or not
	    		do
	    		{
	    			Product productX = null; //store product
		    		String productCode="";
		    		boolean validProductCode = true, validProduct = true;
	    		do
	    		{
	    			System.out.println("Please enter the product code you want to update.");
	    			System.out.print("Product code: ");
	    			if(validProductCode && validProduct)
	    			{
	    				sc.nextLine(); //Consume new line
	    			}
		    		productCode = sc.nextLine().toUpperCase();
	    			for (Product product : products) //check if product code is validate
		    	    {
		    	        if (!product.getProductCode().equals(productCode)) 
		    	        {
		    	            validProductCode = false;
		    	        }
		    	        else
		    	        {
		    	        	productX = product;
		    	        	validProductCode = true;
			    	        break;
		    	        }
		    	    }
	    			//if product code is validate but old same as new
	    			if (validProductCode && productX != null && productX.getProcessor().equals(newProcessor))
	    			{
	    				System.out.println("The processor type of "+productCode+" is already "+newProcessor+".");
	    				validProduct = false;
	    			}
	    			else
	    			{
	    				validProduct = true;
	    			}
	    			if(!validProductCode)
	    			{
	    				System.out.println("Product code not exist, please add the product to the system first.");
	    			}
	    		}while(!validProductCode || !validProduct);
	    		for (Product product : products) //set product to new processor
	    	    {
	    			if (product.getProductCode().equals(productCode))
	    	        {
	    	            product.setProcessor(newProcessor);
	    	        }
	    	    }
	    		System.out.println(productCode+" successfully updated.");
	    		System.out.println("Continue update?");
	    		menus.menu10();
	    		cont = sc.nextInt();
	    		}while(cont!=2); //will keep looping until user choose no for continue option
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	}
	    	validSelection2 = true;
	    	}catch(InputMismatchException e)
			{
				System.out.println("Invalid selection, please input an integer number.");
				validSelection=false;
				sc.next();
			}
	    	catch(Exception e)
			{
				System.out.println("Something is error.");
			}
			}while(!validSelection2);
	    	break;
	    	
	    case 3: // Update (3) Hard Disk Capacity
	    	do
	    	{
	    	try //catch mismatch input error and other possible errors
	    	{
	    	int selecCount1=0, selecCount2=0, updateSelection = 0; //selecCount1 is old hard disk selecCount2 is new
	    	int count=0; //count product with same original hard disk
	    	String originalHardDisk="", newHardDisk="";
	    	do
	    	{
	    	do
	    	{
	    	System.out.println("Select the hard disk capacity of the product you want to update.");
	    	menus.menu6(); //ask to select old capacity 320 500 1024
	    	selecCount1 = sc.nextInt();
	    	if(selecCount1<1||selecCount1>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount1<1||selecCount1>3);
	    	do
	    	{
	    	System.out.println("Select the new hard disk capacity:");
	    	menus.menu6(); //ask to select new capacity 320 500 1024
	    	selecCount2 = sc.nextInt();
	    	if(selecCount2<1||selecCount2>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount2<1||selecCount2>3);
	    	if (selecCount1==selecCount2) //check if old and new is same
	    	{
	    		System.out.println("The old and new hard disk capacity should not be the same. Please try again.");
	    	}
	    	}while(selecCount1==selecCount2);
	    	switch(selecCount1) //assign old hard disk according to option
	    	{
	    	case 1:
	    		originalHardDisk = "320 GB";
	    		break;
	    		
	    	case 2:
	    		originalHardDisk = "500 GB";
	    		 break;
	    		 
	    	case 3:
	    		originalHardDisk = "1 TB";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	switch(selecCount2) //assign new hard disk according to option
	    	{
	    	case 1:
	    		newHardDisk = "320 GB";
	    		break;
	    		
	    	case 2:
	    		newHardDisk = "500 GB";
	    		 break;
	    		 
	    	case 3:
	    		newHardDisk = "1 TB";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    		
	    	}
	    	for (Product product : products) //count product same as original hard disk
        	{
        		if(product.getHardDiskCapacity().equals(originalHardDisk))
                {
                    count++;
                }
        	}
	    	System.out.println("There are "+count+" records with hard disk capacity of "+originalHardDisk+":");
	    	if (count!=0)
	    	{
	    	menus.menu(); //print header
	    	for (Product product : products) //display product with same hard disk as original
        	{
	    		if(product.getHardDiskCapacity().equals(originalHardDisk))
                {
                    System.out.print(product);
                }
        	}
	    	menus.menu9(); //ask to update all or one
	    	updateSelection = sc.nextInt();
	    	switch(updateSelection)
	    	{
	    	case 1: //(1) Update All
	    		for (Product product : products) 
	    	    {
	    			if(product.getHardDiskCapacity().equals(originalHardDisk))
	    	        {
	    	            product.setHardDiskCapacity(newHardDisk);
	    	        }
	    	    }
	    		System.out.println("The product records has been update.");
	    		break;
	    	case 2:
	    		int cont=0; //for user to choose whether continue update or not
	    		do
	    		{
	    			Product productX = null; //to store product
		    		String productCode="";
		    		boolean validProductCode = true, validProduct = true;
	    		do
	    		{
	    			System.out.println("Please enter the product code you want to update.");
	    			System.out.print("Product code: ");
	    			if(validProductCode && validProduct)
	    			{
	    				sc.nextLine(); //Consume new line
	    			}
		    		productCode = sc.nextLine().toUpperCase();
	    			for (Product product : products)  //validate productCode exist or not
		    	    {
		    	        if (!product.getProductCode().equals(productCode)) 
		    	        {
		    	            validProductCode = false;
		    	        }
		    	        else
		    	        {
		    	        	productX = product;
		    	        	validProductCode = true;
			    	        break;
		    	        }
		    	    }
	    			// if productCode valid but original same with new hard disk
	    			if (validProductCode && productX != null && productX.getHardDiskCapacity().equals(newHardDisk))
	    			{
	    				System.out.println("The hard disk capacity of "+productCode+" is already "+newHardDisk+".");
	    				validProduct = false;
	    			}
	    			else
	    			{
	    				validProduct = true;
	    			}
	    			if(!validProductCode)
	    			{
	    				System.out.println("Product code not exist, please add the product to the system first.");
	    			}
	    		}while(!validProductCode || !validProduct);
	    		for (Product product : products)  //set product hard disk to new hard disk
	    	    {
	    			if (product.getProductCode().equals(productCode))
	    	        {
	    	            product.setHardDiskCapacity(newHardDisk);
	    	        }
	    	    }
	    		System.out.println(productCode+" successfully updated.");
	    		System.out.println("Continue update?");
	    		menus.menu10(); //ask user yes or no
	    		cont = sc.nextInt();
	    		}while(cont!=2); //will keep looping until user choose exit option
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	}
	    	validSelection2 = true;
	    	}catch(InputMismatchException e)
			{
				System.out.println("Invalid selection, please input an integer number.");
				validSelection=false;
				sc.next();
			}
	    	catch(Exception e)
			{
				System.out.println("Something is error.");
			}
			}while(!validSelection2);
	    	break;
	    	
	    case 4: // Update (4) Internal Memory Capacity
	    	do
	    	{
	    	try
	    	{
	    	int selecCount1=0, selecCount2=0, updateSelection = 0; //selecCount1 is old capacity option selecCount2 is new
	    	int count=0; //for counting numbers of product same as original capacity
	    	String originalInternalMemory="", newInternalMemory="";
	    	do
	    	{
	    	do
	    	{
	    	System.out.println("Select the internal memory capacity of the product you want to update.");
	    	menus.menu7(); //ask user to select old capacity 
	    	selecCount1 = sc.nextInt();
	    	if(selecCount1<1||selecCount1>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount1<1||selecCount1>3);
	    	do
	    	{
	    	System.out.println("Select the new internal memory capacity:");
	    	menus.menu7(); //ask user to select new capacity
	    	selecCount2 = sc.nextInt();
	    	if(selecCount2<1||selecCount2>3)
	    	{
	    		System.out.println("Invalid selection. Please enter between 1 - 3.");
	    	}
	    	}while(selecCount2<1||selecCount2>3);
	    	if (selecCount1==selecCount2) //validate if old and new option are the same
	    	{
	    		System.out.println("The old and new internal memory capacity should not be the same. Please try again.");
	    	}
	    	}while(selecCount1==selecCount2);
	    	switch(selecCount1) //assign old capacity according to option
	    	{
	    	case 1:
	    		originalInternalMemory = "1 TB";
	    		break;
	    		
	    	case 2:
	    		originalInternalMemory = "2 GB";
	    		 break;
	    		 
	    	case 3:
	    		originalInternalMemory = "4 GB";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	switch(selecCount2) //assign new capacity according to option
	    	{
	    	case 1:
	    		newInternalMemory = "1 TB";
	    		break;
	    		
	    	case 2:
	    		newInternalMemory = "2 GB";
	    		 break;
	    		 
	    	case 3:
	    		newInternalMemory = "4 GB";
	    		break;
	    		
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	for (Product product : products) //count product same as original internal memory capacity
        	{
        		if(product.getInternalMemoryCapacity().equals(originalInternalMemory))
                {
                    count++;
                }
        	}
	    	System.out.println("There are "+count+" records with internal memory capacity of "+originalInternalMemory+":");
	    	if (count!=0)
	    	{
	    	menus.menu(); //print headers
	    	for (Product product : products) //display product same as original internal memory
        	{
	    		if(product.getInternalMemoryCapacity().equals(originalInternalMemory))
                {
                    System.out.print(product);
                }
        	}
	    	menus.menu9(); //ask to update all or one
	    	updateSelection = sc.nextInt();
	    	switch(updateSelection)
	    	{
	    	case 1: //(1) Update All
	    		for (Product product : products) 
	    	    {
	    			if(product.getInternalMemoryCapacity().equals(originalInternalMemory))
	    	        {
	    	            product.setInternalMemoryCapacity(newInternalMemory);
	    	        }
	    	    }
	    		System.out.println("The product records has been update.");
	    		break;
	    		
	    	case 2:
	    		int cont=0; //for user to choose whether continue or not
	    		do
	    		{
	    			Product productX = null; //to store product
		    		String productCode="";
		    		boolean validProductCode = true, validProduct = true;
	    		do
	    		{
	    			System.out.println("Please enter the product code you want to update.");
	    			System.out.print("Product code: ");
	    			if(validProductCode && validProduct)
	    			{
	    				sc.nextLine(); //Consume new line
	    			}
		    		productCode = sc.nextLine().toUpperCase();
	    			for (Product product : products)  //to validate if product code exist
		    	    {
		    	        if (!product.getProductCode().equals(productCode)) 
		    	        {
		    	            validProductCode = false;
		    	        }
		    	        else
		    	        {
		    	        	productX = product;
		    	        	validProductCode = true;
			    	        break;
		    	        }
		    	    }
	    			//if product code exist but original memory same as new memory
	    			if (validProductCode && productX != null && productX.getInternalMemoryCapacity().
	    					equals(newInternalMemory))
	    			{
	    				System.out.println("The internal memory capacity of "+productCode+" is already "+newInternalMemory+".");
	    				validProduct = false;
	    			}
	    			else
	    			{
	    				validProduct = true;
	    			}
	    			if(!validProductCode)
	    			{
	    				System.out.println("Product code not exist, please add the product to the system first.");
	    			}
	    		}while(!validProductCode || !validProduct);
	    		for (Product product : products)  //set product memory to new memory
	    	    {
	    			if (product.getProductCode().equals(productCode))
	    	        {
	    	            product.setInternalMemoryCapacity(newInternalMemory);
	    	        }
	    	    }
	    		System.out.println(productCode+" successfully updated.");
	    		System.out.println("Continue update?");
	    		menus.menu10(); //ask user to choose yes or no
	    		cont = sc.nextInt();
	    		}while(cont!=2); //will keep looping until user choose no for continue option
	    		break;
	    	default:
	    		System.out.println("Invalid selection. Please try again.");
	    		validSelection2 = false;
	    	}
	    	}
	    	validSelection2=true;
	    	}catch(InputMismatchException e)
			{
				System.out.println("Invalid selection, please input an integer number.");
				validSelection=false;
				sc.next();
			}
	    	catch(Exception e)
			{
				System.out.println("Something is error.");
			}
			}while(!validSelection2);
	    	break;
	    	
	    case 5: // Update (5) Quantity
	    	do
	    	{
	    	try
	    	{
	    		int quant1, quant2, updateSelection, count=0; 
	    		/*quant1=old quantity 
	    		 quant2=new 
	    		update selection = update all or one 
	    		count use to count product with same old quantity*/
		    	boolean validQuantity = false;
	    		do
	    		{
	    		do
	    		{
	    		System.out.println("Enter the quantity of the product you want to update.");
	    		System.out.print("Quantity: ");
		    	quant1 = sc.nextInt();
		    	if(quant1<0)
		    	{
		    		System.out.println("Invalid quantity, the quantity should not be negative.");
		    	}
	    		}while(quant1<0);
		    	for (Product product : products) //check whether there are record with old quantity
	        	{
		    		if(product.getQuantity()!=quant1)
	                {
	                    validQuantity = false;
	                }
		    		else
		    		{
		    			validQuantity = true;
		    			break;
		    		}
	        	}
		    	if (!validQuantity)
		    	{
		    		System.out.println("No records with quantity of "+quant1+". Please enter again.");
		    	}
	    		}while(!validQuantity);
	    		do
	    		{
	    		System.out.println("Enter the new quantity:");
		    	System.out.print("Quantity: ");
		    	quant2 = sc.nextInt();
		    	if(quant2<0)
		    	{
		    		System.out.println("Invalid quantity, the quantity should not be negative.");
		    	}
	    		}while(quant2<0);
		    	if (quant1==quant2) //check if old and new quantity are the same
		    	{
		    		System.out.println("The old and new quantity should not be the same. Please try again.");
		    	}
		    	for (Product product : products) //count for the product with old quantity
	        	{
	        		if(product.getQuantity()==quant1)
	                {
	                    count++;
	                }
	        	}
		    	System.out.println("There are "+count+" records with quantity of "+quant1+":");
		    	if (count!=0)
		    	{
		    	menus.menu(); //print header
		    	for (Product product : products) //display product with old quantity
	        	{
		    		if(product.getQuantity()==quant1)
	                {
	                    System.out.print(product);
	                }
	        	}
		    	}
		    	menus.menu9(); //ask to update all or one
		    	updateSelection = sc.nextInt();
		    	switch(updateSelection)
		    	{
		    	case 1: //(1) Update All
		    		for (Product product : products) 
		    	    {
		    			if(product.getQuantity()==quant1)
		    	        {
		    	            product.setQuantity(quant2);
		    	        }
		    	    }
		    		System.out.println("The product records has been update.");
		    		break;
		    		
		    	case 2: //(2) Update one
		    		int cont=0; //use for user to choose continue update or not
		    		do
		    		{
		    			Product productX = null; //to store product
			    		String productCode="";
			    		boolean validProductCode = true, validProduct = true;
		    		do
		    		{
		    			System.out.println("Please enter the product code you want to update.");
		    			System.out.print("Product code: ");
		    			if(validProductCode && validProduct)
		    			{
		    				sc.nextLine(); //Consume new line
		    			}
			    		productCode = sc.nextLine().toUpperCase();
		    			for (Product product : products) //validate product code exist or not
			    	    {
			    	        if (!product.getProductCode().equals(productCode)) 
			    	        {
			    	            validProductCode = false;
			    	        }
			    	        else
			    	        {
			    	        	productX = product;
			    	        	validProductCode = true;
				    	        break;
			    	        }
			    	    }
		    			//if product code validate but its quantity same as new quantity
		    			if (validProductCode && productX != null && productX.getQuantity()==(quant2))
		    			{
		    				System.out.println("The quantity of "+productCode+" is already "+quant2+".");
		    				validProduct = false;
		    			}
		    			else
		    			{
		    				validProduct = true;
		    			}
		    			if(!validProductCode)
		    			{
		    				System.out.println("Product code not exist, please add the product to the system first.");
		    			}
		    		}while(!validProductCode || !validProduct);
		    		for (Product product : products) //set product quantity to new quantity
		    	    {
		    			if (product.getProductCode().equals(productCode))
		    	        {
		    	            product.setQuantity(quant2);
		    	        }
		    	    }
		    		System.out.println(productCode+" successfully updated.");
		    		System.out.println("Continue update?");
		    		menus.menu10(); //ask for user to choose yes or no
		    		cont = sc.nextInt();
		    		}while(cont!=2); //will keep looping until user choose no for continue option
		    		break;
		    	default:
		    		
		    		System.out.println("Invalid selection. Please try again.");
		    		validSelection2 = false;
		    	}
	    	validSelection2=true;
	    	}catch(InputMismatchException e)
			{
				System.out.println("Invalid selection, please input an integer number.");
				validSelection=false;
				sc.next();
			}
	    	catch(Exception e)
			{
				System.out.println("Something is error.");
			}
			}while(!validSelection2);
	    	break;
	    	
	    case 6: // (6) Exit Update
	    	validSelection = true;
	    	break;
	    	
	    default:
        	System.out.println("Please enter valid selection.");
	    }
		}catch(InputMismatchException e)
		{
			System.out.println("Invalid selection, please input an integer number.");
			validSelection=false;
			sc.next();
		}
		catch(Exception e)
		{
			System.out.println("Something is error.");
		}
		}while(selection!=6 || !validSelection);
	}
	
	public void deleteProduct()
	{
		int selection = 0;
		do
		{
			String productCode;
			boolean validSelection = false;
			try //catch mismatch input error and other possible errors
			{
			System.out.println("Enter your selection.");
			menus.menu11(); //ask user to choose delete record or leave
			selection = sc.nextInt();
			while(selection!=1 && selection!=2) //check if selection in range
			{
				System.out.println("Invalid selection, please input either 1 or 2.");
				System.out.println("Enter your selection.");
				menus.menu11();
				selection = sc.nextInt();
			}
			switch(selection)
			{
			case 1: //(1) Delete Records
				boolean validProductCode = true;
				int x = 0, z=1; //to check whether need to consume new line
				do
				{
					try //catch mismatch input error and other possible errors
					{
				int sel=0, del=0; //sel is selection whether want to delete, del is the index of the product need to delete
				System.out.println("Enter the product code of the record that you want to remove.");
				System.out.print("Product Code: ");
				if(validProductCode|| ((x!=0)&&(validProductCode))|| (!validProductCode && z==0))
				{ 
					sc.nextLine(); //Consume new line
				}
				productCode = sc.nextLine().toUpperCase();
				for (Product product_ : products) 
		        {
		        	if(product_.getProductCode().equals(productCode)) //find the record that need to be delete
		            {
		                System.out.println(productCode+" is found."); 
		                validProductCode = true;
		                System.out.println("Are you sure you want to delete it permanently?");
		                menus.menu10(); //ask user to choose yes or no
		                sel = sc.nextInt();
		                if(sel==1) //(1) Yes, which is delete record
		                {
		                	validSelection = true;
		                    del = products.indexOf(product_); //get the index of product that need to delete
		                    z++;
		                }
		                else if (sel==2)
		                {
		                	validSelection = true;
		                	x=0;
		                	System.out.println(productCode+" has not deleted.");
		                	x++; /*variable x is used to determine whether need to print the line for consume new line
		                	the reason is it can check whether the code goes through this line*/
		                	z++;
		                	break;
		                }
		                else {
		                	{
		                		z=0;
		                		validSelection = false;
		                		System.out.println("Invalid selection. Please input either 1 or 2.");
		                	}
						}
		            }
		        	else
		        	{
		        		 validSelection = false;
		        		 validProductCode = false; //to make the consume new line condition work
		        	}
		        }
				if(sel==1) //if user choose to delete
				{
					products.remove(del); //delete the product
					System.out.println(productCode+" successfully deleted.");
					break;
				}
				if(!validProductCode)
	        	{
	        		System.out.println("The product code does not exist in the system.");
	        	}
					}catch(InputMismatchException e)
					{
						System.out.println("Invalid selection, please input an integer number.");
						validSelection=false;
						sc.next();
					}
					catch(Exception e)
					{
						System.out.println("Something is error.");
					}
				}while(!validProductCode || !validSelection); /*will keep looping if either invalid productCode 
				or invalid selection*/
				break;
				
			case 2: // (2) No, which is cancel deletion
				break;
			}
			}catch(InputMismatchException e)
			{
				System.out.println("Invalid selection, please input an integer number.");
				validSelection=false;
				sc.next();
			}
			catch(Exception e)
			{
				System.out.println("Something is error.");
			}
		}while(selection!=2);
	}
	
	public void displayProduct()
	{
		int displaySelection=0, countrySelection, processorSelection, hardDiskSelection, 
		internalMemorySelection, x, y, z;
		boolean validSelection = false, validSelection2 = false;
		do
		{
		try { //catch mismatch input error and other possible errors
        menus.menu3(); //display display menu
        displaySelection = sc.nextInt();
        while(displaySelection < 1 || displaySelection > 7) //validate selection in range
        {
        	System.out.println("Invalid selection. Please input between 1 and 7.");
        	menus.menu3(); //display display menu
        	displaySelection = sc.nextInt();
        }
        switch(displaySelection)
        {
            case 1 :  //(1) Display According to Manufacturing Country
            	do
            	{
            	try //catch input mismatch error and other possible errors
            	{
            	validSelection=true;
            	int sortingSelection=0;
            	boolean validSortingSelection = false;
            	System.out.println("Select the Manufacturing Country.");
            	menus.menu4(); //display country menu
                countrySelection = sc.nextInt();
                while(countrySelection < 1 || countrySelection > 3) //validate selection in range
                {
                    System.out.println("Invalid selection. Please input between 1 and 3.");
                    System.out.println("Select the Manufacturing Country.");
                    menus.menu4(); //display country menu
                    countrySelection = sc.nextInt();
                }
                do
                {
                try
                {
                System.out.println("Select the criteria to sorted as."); //Sort according to criteria
                System.out.println("(1) Processor Type");
                System.out.println("(2) Hard Disk Capacity");
                System.out.print("Selection: ");
                sortingSelection = sc.nextInt();
                while (sortingSelection!= 1 && sortingSelection!=2) //validate sorting selection in range
                {
                	System.out.println("Invalid selection. Please input either 1 or 2.");
                	System.out.println("Select the criteria to sorted as.");
                    System.out.println("(1) Processor Type");
                    System.out.println("(2) Hard Disk Capacity");
                    System.out.print("Selection: ");
                    sortingSelection = sc.nextInt();
                }
                products.sort(new Comparator<Product>() 
            	{
                    public int compare(Product p1, Product p2) {
                        return p1.getCountry().compareTo(p2.getCountry());
                    }
                });
                validSortingSelection = true;
                }catch(InputMismatchException e)
                {
                	System.out.println("Invalid selection, please input an integer number.");
        			validSortingSelection = false;
        			sc.next();
                }
                catch(Exception e)
                {
                	System.out.println("Something wrong.");
                }
                }while(!validSortingSelection);
                switch(countrySelection)
                {
                    case 1: //(1) Malaysia
                    	ArrayList<Product> productsDisplay1 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct1 = new Product("", "Malaysia", "", "", "", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex1 = Collections.binarySearch(products, targetProduct1, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getCountry().compareTo(p2.getCountry());
                    	    }
                    	});
                    	
                    	// Only print headers if there are matching records
                    	if (firstIndex1 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord1 = false;
                    	while (firstIndex1 >= 0) {
                    	    foundRecord1 = true;
                    	    // Find the last index of products with country of manufacture equals to Malaysia
                    	    int lastIndex = firstIndex1;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getCountry().equals("Malaysia")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex1; i < lastIndex; i++) {
                    	        productsDisplay1.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex1 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct1,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getCountry().compareTo(p2.getCountry());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex1 >= 0) {
                    	        firstIndex1 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord1) {
                    	    System.out.println("No record with manufacturing country of Malaysia.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	productsDisplay1.sort(new Comparator<Product>() 
                            	{
                                    public int compare(Product p1, Product p2) {
                                        return p1.getProcessor().compareTo(p2.getProcessor());
                                    }
                                });
                            	for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            case 2:
                            	productsDisplay1.sort(new Comparator<Product>() {
                            	    public int compare(Product p1, Product p2) {
                            	        String hdd1 = p1.getHardDiskCapacity();
                            	        String hdd2 = p2.getHardDiskCapacity();

                            	        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                            	            return 0;
                            	        } else if (hdd1.equals("320 GB")) { // 320 GB
                            	            return -1;
                            	        } else if (hdd2.equals("320 GB")) { // 320 GB
                            	            return 1;
                            	        } else if (hdd1.equals("500 GB")) { // 500 GB
                            	            return -1;
                            	        } else if (hdd2.equals("500 GB")) { // 500 GB
                            	            return 1;
                            	        } else if (hdd1.equals("1 TB")) {	// 1 TB
                            	            return -1;
                            	        } else {
                            	            return 1;						// 1 TB
                            	        }
                            	    }
                            	});
                            	for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;

                    case 2: //(2) Japan
                    	ArrayList<Product> productsDisplay2 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct2 = new Product("", "Japan", "", "", "", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex2 = Collections.binarySearch(products, targetProduct2, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getCountry().compareTo(p2.getCountry());
                    	    }
                    	});
                    	if(products.get(firstIndex2-1).getCountry().equals("Japan")) /* to fix the bug of binary search
                    	 where it return 0 for multiple target results, after it compare two same result, it will
                    	 give not the first element index*/
                    	{
                    		firstIndex2-=1;
                    	}
                    	// Only print headers if there are matching records
                    	if (firstIndex2 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord2 = false;
                    	while (firstIndex2 >= 0) {
                    	    foundRecord2 = true;
                    	    // Find the last index of products with country of manufacture equals to Japan
                    	    int lastIndex = firstIndex2;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getCountry().equals("Japan")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex2; i < lastIndex; i++) {
                    	        productsDisplay2.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex2 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct2,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getCountry().compareTo(p2.getCountry());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex2 >= 0) {
                    	        firstIndex2 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord2) {
                    	    System.out.println("No record with manufacturing country of Japan.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	productsDisplay2.sort(new Comparator<Product>() 
                            	{
                                    public int compare(Product p1, Product p2) {
                                        return p1.getProcessor().compareTo(p2.getProcessor());
                                    }
                                });
                            	for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            case 2:
                            	productsDisplay2.sort(new Comparator<Product>() {
                            	    public int compare(Product p1, Product p2) {
                            	        String hdd1 = p1.getHardDiskCapacity();
                            	        String hdd2 = p2.getHardDiskCapacity();

                            	        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                            	            return 0;
                            	        } else if (hdd1.equals("320 GB")) { // 320 GB
                            	            return -1;
                            	        } else if (hdd2.equals("320 GB")) { // 320 GB
                            	            return 1;
                            	        } else if (hdd1.equals("500 GB")) { // 500 GB
                            	            return -1;
                            	        } else if (hdd2.equals("500 GB")) { // 500 GB
                            	            return 1;
                            	        } else if (hdd1.equals("1 TB")) {	// 1 TB
                            	            return -1;
                            	        } else {
                            	            return 1;						// 1 TB
                            	        }
                            	    }
                            	});
                            	for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;

                    case 3: //(3) America
                    	ArrayList<Product> productsDisplay3 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct3 = new Product("", "America", "", "", "", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex3 = Collections.binarySearch(products, targetProduct3, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getCountry().compareTo(p2.getCountry());
                    	    }
                    	});
                    	if(products.get(firstIndex3-1).getCountry().equals("America")) /* to fix the bug of binary search
                    	 where it return 0 for multiple target results, after it compare two same result, it will
                    	 give not the first element index*/
                    	{
                    		firstIndex3-=1;
                    	}
                    	// Only print headers if there are matching records
                    	if (firstIndex3 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord3 = false;
                    	while (firstIndex3 >= 0) {
                    	    foundRecord3 = true;
                    	    // Find the last index of products with country of manufacture equals to America
                    	    int lastIndex = firstIndex3;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getCountry().equals("America")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex3; i < lastIndex; i++) {
                    	        productsDisplay3.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex3 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct3,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getCountry().compareTo(p2.getCountry());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex3 >= 0) {
                    	        firstIndex3 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord3) {
                    	    System.out.println("No record with manufacturing country of Japan.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	productsDisplay3.sort(new Comparator<Product>() 
                            	{
                                    public int compare(Product p1, Product p2) {
                                        return p1.getProcessor().compareTo(p2.getProcessor());
                                    }
                                });
                            	for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            case 2:
                            	productsDisplay3.sort(new Comparator<Product>() {
                            	    public int compare(Product p1, Product p2) {
                            	        String hdd1 = p1.getHardDiskCapacity();
                            	        String hdd2 = p2.getHardDiskCapacity();

                            	        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                            	            return 0;
                            	        } else if (hdd1.equals("320 GB")) { // 320 GB
                            	            return -1;
                            	        } else if (hdd2.equals("320 GB")) { // 320 GB
                            	            return 1;
                            	        } else if (hdd1.equals("500 GB")) { // 500 GB
                            	            return -1;
                            	        } else if (hdd2.equals("500 GB")) { // 500 GB
                            	            return 1;
                            	        } else if (hdd1.equals("1 TB")) {	// 1 TB
                            	            return -1;
                            	        } else {
                            	            return 1;						// 1 TB
                            	        }
                            	    }
                            	});
                            	for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        System.exit(countrySelection);       
                }
            	}catch(InputMismatchException e)
        		{
        			System.out.println("Invalid selection, please input an integer number.");
        			validSelection2=false;
        			sc.next();
        		}
        		catch(Exception e)
        		{
        			System.out.println("Something is error.");
        		}
            	}while(!validSelection2);
                break;
           
            case 2: //(2) Display According to Processor Type
            	do
            	{
            	try //catch input mismatch error and other possible errors
            	{
            	validSelection=true;
            	x=0; //to check if got product equals to i5
            	y=0; //to check if got product equals to i7
            	z=0; //to check if got product equals to i9
            	int sortingSelection;
            	boolean validSortingSelection = false;
        		System.out.println("Select the Processor Type.");
                menus.menu5();//display processor type menu
                processorSelection = sc.nextInt();
                while(processorSelection < 1 || processorSelection > 3) //validate selection in range
                {
                    System.out.println("Invalid selection. Please input between 1 and 3.");
            		System.out.println("Select the Processor Type.");
                    menus.menu5(); //display processor type menu
                    processorSelection = sc.nextInt();
                }
                do
                {
                try
                {
                System.out.println("Select the criteria to sorted as."); //Sort according to criteria
                System.out.println("(1) Manufacturing Country");
                System.out.println("(2) Hard Disk Capacity");
                System.out.print("Selection: ");
                sortingSelection = sc.nextInt();
                while (sortingSelection!= 1 && sortingSelection!=2) //validate sorting selection in range
                {
                	System.out.println("Invalid selection. Please input either 1 or 2.");
                	System.out.println("Select the criteria to sorted as.");
                	System.out.println("(1) Manufacturing Country");
                    System.out.println("(2) Hard Disk Capacity");
                    System.out.print("Selection: ");
                    sortingSelection = sc.nextInt();
                }
                switch(sortingSelection)
                {
                case 1:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() 
                	{
                        public int compare(Product p1, Product p2) {
                            return p1.getCountry().compareTo(p2.getCountry());
                        }
                    });
                	break;
                case 2:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() {
                	    public int compare(Product p1, Product p2) {
                	        String hdd1 = p1.getHardDiskCapacity();
                	        String hdd2 = p2.getHardDiskCapacity();

                	        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                	            return 0;
                	        } else if (hdd1.equals("320 GB")) { // 320 GB
                	            return -1;
                	        } else if (hdd2.equals("320 GB")) { // 320 GB
                	            return 1;
                	        } else if (hdd1.equals("500 GB")) { // 500 GB
                	            return -1;
                	        } else if (hdd2.equals("500 GB")) { // 500 GB
                	            return 1;
                	        } else if (hdd1.equals("1 TB")) {	// 1 TB
                	            return -1;
                	        } else {
                	            return 1;						// 1 TB
                	        }
                	    }
                	});
                	break;
                }
                }catch(InputMismatchException e)
                {
                	System.out.println("Invalid selection, please input an integer number.");
        			validSortingSelection = false;
        			sc.next();
                }
                catch(Exception e)
                {
                	System.out.println("Something wrong.");
                }
                }while(!validSortingSelection);
                switch(processorSelection)
                {
                    case 1: //(1) Intel i5
                    	validSelection2=true;
                        System.out.println("The product records with processor type of Intel i5");
                        for(Product product : products)
                        {
                            if(product.getProcessor().equals("Intel i5"))
                            {
                                x++;
                            }
                        }
                        if (x==0)
                        {
                        	System.out.println("No record with processor type of Intel i5.");
                        }
                        else if (x!=0)
                        {
                        	menus.menu(); //print headers
                        	for (Product product : products)
                        	{
                        		if(product.getProcessor().equals("Intel i5")) //print records that processor i5
                                {
                                    System.out.print(product);
                                }
                        	}
                        }
                        break;
                        
                    case 2: //(2) Intel i7
                    	validSelection2=true;
                    	System.out.println("The product records with processor type of Intel i7");
                        for(Product product : products)
                        {
                            if(product.getProcessor().equals("Intel i7"))
                            {
                                y++;
                            }
                        }
                        if (y==0)
                        {
                        	System.out.println("No record with processor type of Intel i7.");
                        }
                        else if (y!=0)
                        {
                        	menus.menu(); //print headers
                        	for (Product product : products) //print records that processor i7
                        	{
                        		if(product.getProcessor().equals("Intel i7"))
                                {
                                    System.out.print(product);
                                }
                        	}
                        }
                        break;
                        
                    case 3: //(3) Intel i9
                    	validSelection2=true;
                        System.out.println("The product records with processor type of Intel i9");
                        for(Product product : products)
                        {
                            if(product.getProcessor().equals("Intel i9"))
                            {
                                z++;
                            }
                        }
                        if (z==0)
                        {
                        	System.out.println("No record with processor type of Intel i9.");
                        }
                        else if (z!=0)
                        {
                        	menus.menu(); //print headers
                        	for (Product product : products) //print records with processor i9
                        	{
                        		if(product.getProcessor().equals("Intel i9"))
                                {
                                    System.out.print(product);
                                }
                        	}
                        }
                        break;
                        
                    default:
                    	System.out.println("Invalid selection. Please try again.");
                        System.exit(processorSelection); 
                }
            	}catch(InputMismatchException e)
        		{
        			System.out.println("Invalid selection, please input an integer number.");
        			validSelection2=false;
        			sc.next();
        		}
        		catch(Exception e)
        		{
        			System.out.println("Something is error.");
        		}
            	}while(!validSelection2);
                break;      
                
            case 3 : //(3) Display According to Hard Disk Capacity
            	do
            	{
            	try //catch input mismatch error and other possible errors
            	{
            	validSelection=true;
            	int sortingSelection = 1;
            	boolean validSortingSelection = false;
        		System.out.println("Select the Hard Disk Capacity.");
                menus.menu6(); //display hard disk menu
                hardDiskSelection = sc.nextInt();
                while(hardDiskSelection < 1 || hardDiskSelection > 3) //validate selection range
                {
                    System.out.println("Invalid selection. Please input between 1 and 3.");
            		System.out.println("Select the Hard Disk Capacity.");
                    menus.menu6(); //display hard disk menu
                    hardDiskSelection = sc.nextInt();
                }
                do
                {
                try
                {
                System.out.println("Select the criteria to sorted as."); //Sort according to criteria
                System.out.println("(1) Manufacturing Country");
                System.out.println("(2) Processor Type");
                System.out.print("Selection: ");
                sortingSelection = sc.nextInt();
                while (sortingSelection!= 1 && sortingSelection!=2) //validate sorting selection in range
                {
                	System.out.println("Invalid selection. Please input either 1 or 2.");
                	System.out.println("Select the criteria to sorted as.");
                	System.out.println("(1) Manufacturing Country");
                	System.out.println("(2) Processor Type");
                    System.out.print("Selection: ");
                    sortingSelection = sc.nextInt();
                }
                products.sort(new Comparator<Product>() 
            	{
                    public int compare(Product p1, Product p2) {
                        return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    }
                });
                validSortingSelection = true;
                }catch(InputMismatchException e)
                {
                	System.out.println("Invalid selection, please input an integer number.");
        			validSortingSelection = false;
        			sc.next();
                }
                catch(Exception e)
                {
                	System.out.println("Something wrong.");
                }
                }while(!validSortingSelection);
                switch(hardDiskSelection)
                {
                    case 1: //(1) 320 GB
                    	ArrayList<Product> productsDisplay1 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct1 = new Product("", "", "", "320 GB", "", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex1 = Collections.binarySearch(products, targetProduct1, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    	    }
                    	});
                    	if(products.get(firstIndex1-1).getHardDiskCapacity().equals("320 GB")) /* to fix the bug of 
                    	 binary search where it return 0 for multiple target results, after it compare two 
                    	 same result, it will give not the first element index*/
                    	{
                    		firstIndex1-=1;
                    	}
                    	System.out.println(firstIndex1);
                    	// Only print headers if there are matching records
                    	if (firstIndex1 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord1 = false;
                    	while (firstIndex1 >= 0) {
                    	    foundRecord1 = true;
                    	    // Find the last index of products with hard disk capacity equals to 320 GB
                    	    int lastIndex = firstIndex1;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getHardDiskCapacity().equals("320 GB")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex1; i < lastIndex; i++) {
                    	        productsDisplay1.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex1 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct1,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex1 >= 0) {
                    	        firstIndex1 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord1) {
                    	    System.out.println("No record with Hard Disk Capacity of 320 GB.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	 productsDisplay1.sort(new Comparator<Product>() 
                                 {
                                     public int compare(Product p1, Product p2) {
                                         return p1.getCountry().compareTo(p2.getCountry());
                                     }
                                 });
                            	for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            case 2:
                            	productsDisplay1.sort(new Comparator<Product>() 
                                {
                                    public int compare(Product p1, Product p2) {
                                        return p1.getProcessor().compareTo(p2.getProcessor());
                                    }
                                });
                            	for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    case 2 : //(2) 500 GB
                    	ArrayList<Product> productsDisplay2 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct2 = new Product("", "", "", "500 GB", "", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex2 = Collections.binarySearch(products, targetProduct2, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    	    }
                    	});
                    	if(products.get(firstIndex2-1).getHardDiskCapacity().equals("500 GB")) /* to fix the bug of 
                    	 binary search where it return 0 for multiple target results, after it compare two 
                    	 same result, it will give not the first element index*/
                    	{
                    		firstIndex2-=1;
                    	}
                    	// Only print headers if there are matching records
                    	if (firstIndex2 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord2 = false;
                    	while (firstIndex2 >= 0) {
                    	    foundRecord2 = true;
                    	    // Find the last index of products with hard disk capacity equals to 500 GB
                    	    int lastIndex = firstIndex2;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getHardDiskCapacity().equals("500 GB")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex2; i < lastIndex; i++) {
                    	        productsDisplay2.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex2 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct2,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex2 >= 0) {
                    	        firstIndex2 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord2) {
                    	    System.out.println("No record with Hard Disk Capacity of 500 GB.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	 productsDisplay2.sort(new Comparator<Product>() 
                                 {
                                     public int compare(Product p1, Product p2) {
                                         return p1.getCountry().compareTo(p2.getCountry());
                                     }
                                 });
                            	for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            case 2:
                            	productsDisplay2.sort(new Comparator<Product>() 
                                {
                                    public int compare(Product p1, Product p2) {
                                        return p1.getProcessor().compareTo(p2.getProcessor());
                                    }
                                });
                            	for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    case 3 : //(3) 1 TB
                    	ArrayList<Product> productsDisplay3 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct3 = new Product("", "", "", "1 TB", "", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex3 = Collections.binarySearch(products, targetProduct3, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    	    }
                    	});
                    	if(products.get(firstIndex3-1).getHardDiskCapacity().equals("1 TB")) /* to fix the bug of 
                    	 binary search where it return 0 for multiple target results, after it compare two 
                    	 same result, it will give not the first element index*/
                    	{
                    		firstIndex3-=1;
                    	}
                    	// Only print headers if there are matching records
                    	if (firstIndex3 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord3 = false;
                    	while (firstIndex3 >= 0) {
                    	    foundRecord3 = true;
                    	    // Find the last index of products with hard disk capacity equals to 1 TB
                    	    int lastIndex = firstIndex3;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getHardDiskCapacity().equals("1 TB")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex3; i < lastIndex; i++) {
                    	        productsDisplay3.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex3 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct3,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getHardDiskCapacity().compareTo(p2.getHardDiskCapacity());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex3 >= 0) {
                    	        firstIndex3 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord3) {
                    	    System.out.println("No record with Hard Disk Capacity of 1 TB.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	 productsDisplay3.sort(new Comparator<Product>() 
                                 {
                                     public int compare(Product p1, Product p2) {
                                         return p1.getCountry().compareTo(p2.getCountry());
                                     }
                                 });
                            	for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            case 2:
                            	productsDisplay3.sort(new Comparator<Product>() 
                                {
                                    public int compare(Product p1, Product p2) {
                                        return p1.getProcessor().compareTo(p2.getProcessor());
                                    }
                                });
                            	for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    default:
                    	System.out.println("Invalid selection. Please try again.");
                        System.exit(hardDiskSelection); 
                }
            	}catch(InputMismatchException e)
        		{
        			System.out.println("Invalid selection, please input an integer number.");
        			validSelection2=false;
        			sc.next();
        		}
        		catch(Exception e)
        		{
        			System.out.println("Something is error.");
        		}
            	}while(!validSelection2);
                break;          
                
            case 4: //(4) Display According to Internal Memory Capacity
            	do
            	{
            	try //catch input mismatch error and other possible errors
            	{
            	validSelection=true;
            	int sortingSelection = 1;
            	boolean validSortingSelection = false;
        		System.out.println("Select the Internal Memory Capacity.");
            	menus.menu7(); //display internal memory capacity menu
                internalMemorySelection = sc.nextInt();
                while(internalMemorySelection < 1 || internalMemorySelection > 3) //validate selection in range
                {
                    System.out.println("Invalid selection. Please input between 1 and 3.");
            		System.out.println("Select the Internal Memory Capacity.");
                    menus.menu7(); //display internal memory capacity menu
                    internalMemorySelection = sc.nextInt();
                }
                do
                {
                try
                {
                System.out.println("Select the criteria to sorted as."); //Sort according to criteria
                System.out.println("(1) Manufacturing Country");
                System.out.println("(2) Processor Type");
                System.out.println("(3) Hard Disk Capacity");
                System.out.print("Selection: ");
                sortingSelection = sc.nextInt();
                while (sortingSelection<1 || sortingSelection>3) //validate sorting selection in range
                {
                	System.out.println("Invalid selection. Please input between 1 and 3.");
                	System.out.println("Select the criteria to sorted as.");
                	System.out.println("(1) Manufacturing Country");
                    System.out.println("(2) Processor Type");
                    System.out.println("(3) Hard Disk Capacity");
                    System.out.print("Selection: ");
                    sortingSelection = sc.nextInt();
                }
                products.sort(new Comparator<Product>() 
            	{
                    public int compare(Product p1, Product p2) {
                        return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    }
                });
                validSortingSelection = true;
                }catch(InputMismatchException e)
                {
                	System.out.println("Invalid selection, please input an integer number.");
        			validSortingSelection = false;
        			sc.next();
                }
                catch(Exception e)
                {
                	System.out.println("Something wrong.");
                }
                }while(!validSortingSelection);
                switch(internalMemorySelection){
                    case 1: //(1) 1 TB
                    	ArrayList<Product> productsDisplay1 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct1 = new Product("", "", "", "", "1 TB", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex1 = Collections.binarySearch(products, targetProduct1, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    	    }
                    	});
                    	if(products.get(firstIndex1-1).getInternalMemoryCapacity().equals("1 TB")) /* to fix the bug of 
                    	 binary search where it return 0 for multiple target results, after it compare two 
                    	 same result, it will give not the first element index*/
                    	{
                    		firstIndex1-=1;
                    	}
                    	System.out.println(firstIndex1);
                    	// Only print headers if there are matching records
                    	if (firstIndex1 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord1 = false;
                    	while (firstIndex1 >= 0) {
                    	    foundRecord1 = true;
                    	    // Find the last index of products with hard disk capacity equals to 1 TB
                    	    int lastIndex = firstIndex1;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getInternalMemoryCapacity().equals("1 TB")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex1; i < lastIndex; i++) {
                    	        productsDisplay1.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex1 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct1,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex1 >= 0) {
                    	        firstIndex1 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord1) {
                    	    System.out.println("No record with Internal Memory Capacity of 1 TB.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                                 productsDisplay1.sort(new Comparator<Product>() 
                                 {
                                     public int compare(Product p1, Product p2) {
                                         return p1.getCountry().compareTo(p2.getCountry());
                                     }
                                 });
                            	for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            	
                            case 2:
                            	productsDisplay1.sort(new Comparator<Product>() 
                                  {
                                      public int compare(Product p1, Product p2) {
                                          return p1.getProcessor().compareTo(p2.getProcessor());
                                      }
                                });
                            	for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            	
                            case 3:
                            	productsDisplay1.sort(new Comparator<Product>() {
                                    public int compare(Product p1, Product p2) {
                                        String hdd1 = p1.getHardDiskCapacity();
                                        String hdd2 = p2.getHardDiskCapacity();

                                        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                                            return 0;
                                        } else if (hdd1.equals("320 GB")) { // 320 GB
                                            return -1;
                                        } else if (hdd2.equals("320 GB")) { // 320 GB
                                            return 1;
                                        } else if (hdd1.equals("500 GB")) { // 500 GB
                                            return -1;
                                        } else if (hdd2.equals("500 GB")) { // 500 GB
                                            return 1;
                                        } else if (hdd1.equals("1 TB")) {    // 1 TB
                                            return -1;
                                        } else {
                                            return 1;                        // 1 TB
                                        }
                                    }
                                });
                                for (Product product : productsDisplay1)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    case 2: //(2) 2 GB
                    	ArrayList<Product> productsDisplay2 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct2 = new Product("", "", "", "", "2 GB", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex2 = Collections.binarySearch(products, targetProduct2, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    	    }
                    	});
                    	if(products.get(firstIndex2-1).getInternalMemoryCapacity().equals("2 GB")) /* to fix the bug of 
                    	 binary search where it return 0 for multiple target results, after it compare two 
                    	 same result, it will give not the first element index*/
                    	{
                    		firstIndex2-=1;
                    	}
                    	System.out.println(firstIndex2);
                    	// Only print headers if there are matching records
                    	if (firstIndex2 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord2 = false;
                    	while (firstIndex2 >= 0) {
                    	    foundRecord2 = true;
                    	    // Find the last index of products with hard disk capacity equals to 2 GB
                    	    int lastIndex = firstIndex2;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getInternalMemoryCapacity().equals("2 GB")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex2; i < lastIndex; i++) {
                    	        productsDisplay2.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex2 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct2,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex2 >= 0) {
                    	        firstIndex2 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord2) {
                    	    System.out.println("No record with Internal Memory Capacity of 2 GB.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	productsDisplay2.sort(new Comparator<Product>() 
                                 {
                                     public int compare(Product p1, Product p2) {
                                         return p1.getCountry().compareTo(p2.getCountry());
                                     }
                                 });
                            	for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            	
                            case 2:
                            	productsDisplay2.sort(new Comparator<Product>() 
                                  {
                                      public int compare(Product p1, Product p2) {
                                          return p1.getProcessor().compareTo(p2.getProcessor());
                                      }
                                });
                            	for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            	
                            case 3:
                            	productsDisplay2.sort(new Comparator<Product>() {
                                    public int compare(Product p1, Product p2) {
                                        String hdd1 = p1.getHardDiskCapacity();
                                        String hdd2 = p2.getHardDiskCapacity();

                                        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                                            return 0;
                                        } else if (hdd1.equals("320 GB")) { // 320 GB
                                            return -1;
                                        } else if (hdd2.equals("320 GB")) { // 320 GB
                                            return 1;
                                        } else if (hdd1.equals("500 GB")) { // 500 GB
                                            return -1;
                                        } else if (hdd2.equals("500 GB")) { // 500 GB
                                            return 1;
                                        } else if (hdd1.equals("1 TB")) {    // 1 TB
                                            return -1;
                                        } else {
                                            return 1;                        // 1 TB
                                        }
                                    }
                                });
                                for (Product product : productsDisplay2)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    case 3: //(3) 4 GB
                    	ArrayList<Product> productsDisplay3 = new ArrayList<>();
                    	validSelection2 = true;
                    	Product targetProduct3 = new Product("", "", "", "", "4 GB", 0);
                    	// Binary search for the first occurrence of the target product
                    	int firstIndex3 = Collections.binarySearch(products, targetProduct3, new Comparator<Product>() {
                    	    public int compare(Product p1, Product p2) {
                    	        return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    	    }
                    	});
                    	if(products.get(firstIndex3-1).getInternalMemoryCapacity().equals("4 GB")) /* to fix the bug of 
                    	 binary search where it return 0 for multiple target results, after it compare two 
                    	 same result, it will give not the first element index*/
                    	{
                    		firstIndex3-=1;
                    	}
                    	System.out.println(firstIndex3);
                    	// Only print headers if there are matching records
                    	if (firstIndex3 >= 0) {
                    	    menus.menu();
                    	}

                    	// Loop to find all occurrences of the target product
                    	boolean foundRecord3 = false;
                    	while (firstIndex3 >= 0) {
                    	    foundRecord3 = true;
                    	    // Find the last index of products with hard disk capacity equals to 4 GB
                    	    int lastIndex = firstIndex3;
                    	    while (lastIndex < products.size() && products.get(lastIndex).getInternalMemoryCapacity().equals("4 GB")) {
                    	        lastIndex++;
                    	    }
                    	    // Print the products found
                    	    for (int i = firstIndex3; i < lastIndex; i++) {
                    	        productsDisplay3.add(products.get(i));
                    	    }
                    	    
                    	    // Binary search for the next occurrence of the target product
                    	    firstIndex3 = Collections.binarySearch(products.subList(lastIndex, products.size()), targetProduct3,
                    	            new Comparator<Product>() {
                    	                public int compare(Product p1, Product p2) {
                    	                    return p1.getInternalMemoryCapacity().compareTo(p2.getInternalMemoryCapacity());
                    	                }
                    	            });

                    	    // Add 1 to the index to adjust for sublist offset
                    	    if (firstIndex3 >= 0) {
                    	        firstIndex3 += lastIndex;
                    	    }
                    	}
                    	
                    	// Print "no records" message if no records were found
                    	if (!foundRecord3) {
                    	    System.out.println("No record with Internal Memory Capacity of 4 GB.");
                    	}
                    	else
                    	{
                    		switch(sortingSelection)
                            {
                            case 1:
                            	productsDisplay3.sort(new Comparator<Product>() 
                                 {
                                     public int compare(Product p1, Product p2) {
                                         return p1.getCountry().compareTo(p2.getCountry());
                                     }
                                 });
                            	for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            	
                            case 2:
                            	productsDisplay3.sort(new Comparator<Product>() 
                                  {
                                      public int compare(Product p1, Product p2) {
                                          return p1.getProcessor().compareTo(p2.getProcessor());
                                      }
                                });
                            	for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            	
                            case 3:
                            	productsDisplay3.sort(new Comparator<Product>() {
                                    public int compare(Product p1, Product p2) {
                                        String hdd1 = p1.getHardDiskCapacity();
                                        String hdd2 = p2.getHardDiskCapacity();

                                        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                                            return 0;
                                        } else if (hdd1.equals("320 GB")) { // 320 GB
                                            return -1;
                                        } else if (hdd2.equals("320 GB")) { // 320 GB
                                            return 1;
                                        } else if (hdd1.equals("500 GB")) { // 500 GB
                                            return -1;
                                        } else if (hdd2.equals("500 GB")) { // 500 GB
                                            return 1;
                                        } else if (hdd1.equals("1 TB")) {    // 1 TB
                                            return -1;
                                        } else {
                                            return 1;                        // 1 TB
                                        }
                                    }
                                });
                                for (Product product : productsDisplay3)
                            	{
                                        System.out.print(product);
                            	}
                            	break;
                            }
                    	}
                    	break;
                        
                    default:
                    	System.out.println("Invalid selection. Please try again.");
                        System.exit(internalMemorySelection); 
                }
            	}catch(InputMismatchException e)
        		{
        			System.out.println("Invalid selection, please input an integer number.");
        			validSelection2=false;
        			sc.next();
        		}
        		catch(Exception e)
        		{
        			System.out.println("Something is error.");
        		}
            	}while(!validSelection2);
                break;
                
            case 5: //(5) Display According to Quantity
            	boolean validQuantity = false;
            	do
            	{
            	try //catch input mismatch error and other possible erros
            	{
            	x=0; //to check if there is product that quantity same with input quantity
            	validSelection=true;
            	int quant, sortingSelection;
            	boolean validSortingSelection = false;
            	System.out.println("Please enter the number of quantity.");
            	System.out.print("Quantity: ");
            	quant = sc.nextInt();
            	while(quant < 0) //validate quantity should be positive
            	{
            		System.out.println("Invalid number of quantity. Please enter an positive integer.");
            		System.out.println("Please enter the number of quantity.");
            		System.out.print("Quantity: ");
                	quant = sc.nextInt();
            	}
            	do
                {
                try
                {
                System.out.println("Select the criteria to sorted as."); //Sort according to criteria
                System.out.println("(1) Manufacturing Country");
                System.out.println("(2) Processor Type");
                System.out.println("(3) Hard Disk Capacity");
                System.out.print("Selection: ");
                sortingSelection = sc.nextInt();
                while (sortingSelection<1 || sortingSelection>3) //validate sorting selection in range
                {
                	System.out.println("Invalid selection. Please input between 1 and 3.");
                	System.out.println("Select the criteria to sorted as.");
                	System.out.println("(1) Manufacturing Country");
                    System.out.println("(2) Processor Type");
                    System.out.println("(3) Hard Disk Capacity");
                    System.out.print("Selection: ");
                    sortingSelection = sc.nextInt();
                }
                switch(sortingSelection)
                {
                case 1:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() 
                	{
                        public int compare(Product p1, Product p2) {
                            return p1.getCountry().compareTo(p2.getCountry());
                        }
                    });
                	break;
                case 2:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() 
                	{
                        public int compare(Product p1, Product p2) {
                            return p1.getProcessor().compareTo(p2.getProcessor());
                        }
                    });
                	break;
                case 3:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() {
                	    public int compare(Product p1, Product p2) {
                	        String hdd1 = p1.getHardDiskCapacity();
                	        String hdd2 = p2.getHardDiskCapacity();

                	        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                	            return 0;
                	        } else if (hdd1.equals("320 GB")) { // 320 GB
                	            return -1;
                	        } else if (hdd2.equals("320 GB")) { // 320 GB
                	            return 1;
                	        } else if (hdd1.equals("500 GB")) { // 500 GB
                	            return -1;
                	        } else if (hdd2.equals("500 GB")) { // 500 GB
                	            return 1;
                	        } else if (hdd1.equals("1 TB")) {	// 1 TB
                	            return -1;
                	        } else {
                	            return 1;						// 1 TB
                	        }
                	    }
                	});
                	break;
                }
                }catch(InputMismatchException e)
                {
                	System.out.println("Invalid selection, please input an integer number.");
        			validSortingSelection = false;
        			sc.next();
                }
                catch(Exception e)
                {
                	System.out.println("Something wrong.");
                }
                }while(!validSortingSelection);
            	validQuantity = true;
            	for(Product product : products) 
                {
                    if(product.getQuantity()==(quant))
                    {
                        x++;
                    }
                }
            	if (x==0)
            	{
            		System.out.println("No record with quanity of "+quant+".");
            	}
            	else if (x!=0)
                {
                	menus.menu(); //print headers
                	for (Product product : products) //print products with selected quantity
                	{
                		if(product.getQuantity()==(quant))
                        {
                            System.out.print(product);
                        }
                	}
                }
            	}catch(InputMismatchException e)
        		{
        			System.out.println("Invalid quantity, please input an integer number.");
        			validQuantity=false;
        			sc.next();
        		}
        		catch(Exception e)
        		{
        			System.out.println("Something is error.");
        		}
            	}while(!validQuantity);
                break;
                
            case 6: //(6) Display All
            	int sortingSelection = 1;
            	boolean validSortingSelection = false;
            	validSelection = true;
            	do
                {
                try
                {
                System.out.println("Select the criteria to sorted as."); //Sort according to criteria
                System.out.println("(1) Manufacturing Country");
                System.out.println("(2) Processor Type");
                System.out.println("(3) Hard Disk Capacity");
                System.out.print("Selection: ");
                sortingSelection = sc.nextInt();
                while (sortingSelection<1 || sortingSelection>3) //validate sorting selection in range
                {
                	System.out.println("Invalid selection. Please input between 1 and 3.");
                	System.out.println("Select the criteria to sorted as.");
                	System.out.println("(1) Manufacturing Country");
                    System.out.println("(2) Processor Type");
                    System.out.println("(3) Hard Disk Capacity");
                    System.out.print("Selection: ");
                    sortingSelection = sc.nextInt();
                }
                switch(sortingSelection)
                {
                case 1:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() 
                	{
                        public int compare(Product p1, Product p2) {
                            return p1.getCountry().compareTo(p2.getCountry());
                        }
                    });
                	break;
                case 2:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() 
                	{
                        public int compare(Product p1, Product p2) {
                            return p1.getProcessor().compareTo(p2.getProcessor());
                        }
                    });
                	break;
                case 3:
                	validSortingSelection = true;
                	products.sort(new Comparator<Product>() {
                	    public int compare(Product p1, Product p2) {
                	        String hdd1 = p1.getHardDiskCapacity();
                	        String hdd2 = p2.getHardDiskCapacity();

                	        if (hdd1 == hdd2) { //sort by 320GB, 500GB, 1TB
                	            return 0;
                	        } else if (hdd1.equals("320 GB")) { // 320 GB
                	            return -1;
                	        } else if (hdd2.equals("320 GB")) { // 320 GB
                	            return 1;
                	        } else if (hdd1.equals("500 GB")) { // 500 GB
                	            return -1;
                	        } else if (hdd2.equals("500 GB")) { // 500 GB
                	            return 1;
                	        } else if (hdd1.equals("1 TB")) {	// 1 TB
                	            return -1;
                	        } else {
                	            return 1;						// 1 TB
                	        }
                	    }
                	});
                	break;
                }
            	menus.menu(); //print headers
            	for (Product product : products) //print all products
            	{
                        System.out.print(product);
            	}
                }catch(InputMismatchException e)
                {
                	System.out.println("Invalid selection, please input an integer number.");
        			validSortingSelection = false;
        			sc.next();
                }
                catch(Exception e)
                {
                	System.out.println("Something wrong.");
                }
                }while(!validSortingSelection);
            case 7: //(7) Exit
            	validSelection = true;
            	break;
        }
		}catch(InputMismatchException e)
		{
			System.out.println("Invalid selection, please input an integer number.");
			validSelection=false;
			sc.next();
		}
		catch(Exception e)
		{
			System.out.println("Something is error.");
		}
        }while(displaySelection!=7 || !validSelection); //will keep looping if user not choose to exit or invalid selection
	}
}