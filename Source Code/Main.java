package Assignment2;
import java.util.InputMismatchException; // For try catch
import java.util.Scanner; // To scan input
public class Main {
	public static ProductManagementSystem pms = new ProductManagementSystem(); // To use method in ProductManagementSystem.java
	public static Scanner sc = new Scanner(System.in);  // Declare scanner
	public static void main (String[]args) // Arguments in main method
	{
		int selection=0; // Selection of Manager's input in main menu
		boolean validSelection=false, exitProgram=false;
		Menu menus =  new Menu(); // To use method in Menu.java
		while(!exitProgram) //If manager does not chose to exit program, the program will keep looping
        {
			System.out.println("Welcome to the product management system.\n");
			menus.menu1(); //Show Product Code Table
        do {
        	try{ //catch mismatch input error and other possible errors
        	menus.menu2(); //Show main menu
        	selection = sc.nextInt();
        	// Validation for Selection
        	if (selection < 1 || selection > 6)
        	{
        		System.out.println("Invalid selection, please input between 1 and 6.");
        		validSelection=false;
        		menus.menu2();//Show main menu
            	selection = sc.nextInt();
        	}
        	else
        	{
        		validSelection=true;
        	}
        	}
        	catch(InputMismatchException e)
        	{
        		System.out.println("Invalid selection, please input an integer number.");
        		validSelection = false;
    			sc.next();
        	}
        	catch(Exception e)
    		{
    			System.out.println("Something is error.");
    		}
		}while(!validSelection); //The menu will keep looping if the manager does not input valid selection
        switch (selection)
        {
        case 1: //Manager chose to add new product
        	menus.menu1(); //Show Product Code Table
        	pms.addProduct();
        	break;
        case 2: // Manager chose to update product
        	pms.updateProduct();
        	break;
        case 3: // Manager chose to delete product
        	pms.deleteProduct();
        	break;
        case 4: //Manager chose to display product based on different criteria
        	pms.displayProduct();
        	break;
        case 5: //Manager chose to display product code table
        	menus.menu1();
        	break;
        case 6: //Manager chose to exit the program
        	System.out.println("Shutting down the system. Have a nice day.");
        	exitProgram=true;
        	break;
        default:
        	System.out.println("Please enter valid selection.");
        }
	}
	}
}


