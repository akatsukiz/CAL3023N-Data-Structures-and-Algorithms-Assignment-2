package Assignment2;
public class Menu {
	public void menu() //Header
	{
		System.out.println("===================================================="
				+ "====================================================================");
		System.out.println("Product Code \tCountry \tProcessor\tHard Disk Capacity\t"
				+ "Internal Memory Capacity\tQuantity");
		System.out.println("===================================================="
				+ "====================================================================");
	}
	public void menu1() //Product code table
	{
		System.out.println("\t\t    Product Code Table");
		System.out.println("==============================================================");
		System.out.println("\tMeaning \t      Characters \t Translation");
		System.out.println("==============================================================");
		System.out.println("Manufacturing Country \t\t  M \t\t Malaysia");
		System.out.println("\t\t\t\t  J \t\t Japan");
		System.out.println("\t\t\t\t  A \t\t America");
		System.out.println("==============================================================");
		System.out.println("Type of Processor \t\t  5 \t\t Intel i5");
		System.out.println("\t\t\t\t  7 \t\t Intel i7");
		System.out.println("\t\t\t\t  9 \t\t Intel i9");
		System.out.println("==============================================================");
		System.out.println("Capacity of Hard Disk \t\t  320 \t\t 320 GB");
		System.out.println("\t\t\t\t  500 \t\t 500 GB");
		System.out.println("\t\t\t\t  1024 \t\t 1 TB");
		System.out.println("==============================================================");
		System.out.println("Capacity of Internal Memory \t  1024 \t\t 1 TB");
		System.out.println("\t\t\t\t  2048 \t\t 2 GB");
		System.out.println("\t\t\t\t  4096 \t\t 4 GB");
		System.out.println("==============================================================");
		System.out.println("The product code table is only for references before any updation or deletion.\n");
	}
	public void menu2() //Main Menu
	{
		System.out.println("Please key in your selection.");
		System.out.println("(1) Add New Products");
		System.out.println("(2) Update Records");
        System.out.println("(3) Delete Records");
        System.out.println("(4) Display Records Based on Different Criteria");
        System.out.println("(5) Display Product Code Table");
        System.out.println("(6) Exit ");
        System.out.print("Selection: ");
	}
	public void menu3() //(4) Display Records Based on Different Criteria
	{
        System.out.println("Select the criteria you wish to view products based on it.");
        System.out.println("(1) Display According to Manufacturing Country");
        System.out.println("(2) Display According to Processor Type");
        System.out.println("(3) Display According to Hard Disk Capacity");
        System.out.println("(4) Display According to Internal Memory Capacity");
        System.out.println("(5) Display According to Quantity");
        System.out.println("(6) Display All");
        System.out.println("(7) Exit");
        System.out.print("Selection: ");
	}
	public void menu4() //(1)  Manufacturing Country
	{
        System.out.println("(1) Malaysia");
        System.out.println("(2) Japan");
        System.out.println("(3) America");
        System.out.print("Selection: ");
	}
	public void menu5() //(2) Processor Type
	{
        System.out.println("(1) Intel i5");
        System.out.println("(2) Intel i7");
        System.out.println("(3) Intel i9");
        System.out.print("Selection: ");
	}
	public void menu6() //(3) Hard Disk Capacity
	{
		System.out.println("(1) 320 GB");
        System.out.println("(2) 500 GB");
        System.out.println("(3) 1 TB");
        System.out.print("Selection: ");
	}
	public void menu7() //(4) Internal Memory Capacity
	{
        System.out.println("(1) 1 TB ");
        System.out.println("(2) 2 GB ");
        System.out.println("(3) 4 GB ");
        System.out.print("Selection: ");
	}
	public void menu8() //(2) Update Records
	{
		System.out.println("Select the criteria of the product you wish to update.");
        System.out.println("(1) Manufacturing Country");
        System.out.println("(2) Processor Type");
        System.out.println("(3) Hard Disk Capacity");
        System.out.println("(4) Internal Memory Capacity");
        System.out.println("(5) Quantity");
        System.out.println("(6) Exit");
        System.out.print("Selection: ");
	}
	public void menu9() //Update all or update one by one
	{
		System.out.println("(1) Update All");
		System.out.println("(2) Update One");
		System.out.print("Selection: ");
	}
	public void menu10() //Yes or no menu
	{
		System.out.println("(1) Yes");
		System.out.println("(2) No");
		System.out.print("Selection: ");
	}
	public void menu11() //(3) Delete Records
	{
		System.out.println("(1) Delete Records");
		System.out.println("(2) Exit");
		System.out.print("Selection: ");
	}
	public void menu12() // (1) Add New Products
	{
		System.out.println("(1) Add Records");
		System.out.println("(2) Exit");
		System.out.print("Selection: ");
	}
}
