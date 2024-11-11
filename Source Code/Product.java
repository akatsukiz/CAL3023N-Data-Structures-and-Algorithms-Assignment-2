package Assignment2;

public class Product {
	//instance variables
	private String productCode;  //the code that identifies the product
	private String country; //the country where the product is manufactured
    private String processor; //the type of processor used in the product
    private String hardDiskCapacity; // the capacity of the hard disk in the product
    private String internalMemoryCapacity; //the capacity of the internal memory in the product
    private int quantity; //the quantity of the product available

    // Constructor
    public Product(String productCode,String country, String processor, 
    		String hardDiskCapacity, String internalMemoryCapacity,int quantity) 
    {
        this.productCode = productCode;
    	this.country = country;
        this.processor = processor;
        this.hardDiskCapacity = hardDiskCapacity;
        this.internalMemoryCapacity = internalMemoryCapacity;
        this.quantity = quantity;
    }
    //Empty constructor for creating an object with default values
    public Product() 
    {
    	
    }
    //Getters and Setters for the instance variables
    public String getProductCode() 
    {
    	return productCode;
    }
    public void setProductCode(String productCode) 
    {
    	this.productCode = productCode;
    }
    public String getCountry() 
    {
        return country;
    }

    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getProcessor() 
    {
        return processor;
    }

    public void setProcessor(String processor) 
    {
        this.processor = processor;
    }

    public String getHardDiskCapacity() 
    {
        return hardDiskCapacity;
    }

    public void setHardDiskCapacity(String hardDiskCapacity) 
    {
        this.hardDiskCapacity = hardDiskCapacity;
    }

    public String getInternalMemoryCapacity() 
    {
        return internalMemoryCapacity;
    }

    public void setInternalMemoryCapacity(String internalMemoryCapacity) 
    {
        this.internalMemoryCapacity = internalMemoryCapacity;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
    // Returns a formatted string representation of the object
    public String toString() 
    {
    	return String.format("%-16s%-17s%-20s%-28s%-26s%d\n", productCode,country, processor, 
    			hardDiskCapacity, internalMemoryCapacity, quantity);
    }
    
}

