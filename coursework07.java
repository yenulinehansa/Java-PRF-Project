import java.util.*;
class coursework07{
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//Order status 
	public static final int PREPARING=0;  
	public static final int DELIVERED=1; 
	public static final int CANCEL=2; 
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getOrderStatus(int status) {
    switch(status) {
        case 0: return "PREPARING";
        case 1: return "DELIVERED";
        case 2: return "CANCEL";
        default: return "UNKNOWN";
    }
}

public static void displayOrder(String a) {
    boolean found = false;

    for (int i = 0; i < orderIdArray.length; i++) {
        if (orderIdArray[i].equalsIgnoreCase(a)) {
			 System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Order Id\tCustomer Id\tCustomer Name\tBurger Quantity\t\tOrder Status|");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(orderIdArray[i] + "\t\t" + customerIdArray[i] + "\t\t" + nameArray[i] + "\t\t" + quantityArray[i] + "\t\t" + getOrderStatus(statusArray[i])+"   |");
            System.out.println("-------------------------------------------------------------------------------------");
            Scanner input = new Scanner(System.in);
            System.out.print("Do you want to search another order details? (Y/N): ");
            char retry = input.next().charAt(0);
            if (retry == 'Y' || retry == 'y') {
            searchOrder();
        }else if(retry=='N'|| retry=='n'){
			homepage();
		}            
            found = true;
            break; 
        }
	}
    

    if (!found) {
        Scanner input = new Scanner(System.in);
        System.out.println("Invalid Order ID. Do you want to enter again? (Y/N): ");
        char retry = input.next().charAt(0);
        if (retry == 'Y' || retry == 'y') {
            searchOrder();
        }else if(retry=='N' || retry=='n'){
			homepage();
		}
    }



}

	
/////////////////////////////////////////////////////////////////////////////////////////////////
	final static double BURGERPRICE=500;
	////////////////////////////////////////////////////////////////////////////////////
	public static void extendsArray(){
		int size=orderIdArray.length;
		String temporderIdArray[]=new String[size+1];
		String tempNameArray[]=new String[size+1];
		String tempcustomerIdArray[]=new String[size+1];
		int tempquantityArray[]=new int[size+1];
		double temptotalArray[]=new double[size+1];
		int tempstatusArray[] = new int[size + 1];

		
		for (int i = 0; i < size; i++){
			temporderIdArray[i]=orderIdArray[i];
			tempNameArray[i]=nameArray[i];
			tempcustomerIdArray[i]=customerIdArray[i];
			tempquantityArray[i]=quantityArray[i];
			temptotalArray[i]=totalArray[i];
			tempstatusArray[i]=statusArray[i];

		}
		orderIdArray=temporderIdArray;
		nameArray=tempNameArray;
		customerIdArray=tempcustomerIdArray;
		quantityArray=tempquantityArray;
		totalArray=temptotalArray;
		statusArray = tempstatusArray; 

		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////
	public static String generateId(){
		String lastId=orderIdArray[orderIdArray.length-1];
		int lastIdNo=Integer.parseInt(lastId.substring(1));
		String newId=String.format("B%04d",lastIdNo+1);
		return newId;

		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void addOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			String id=generateId();
			System.out.println("Order Id : "+id);
			
			System.out.print("Input Customer's ID(Phone number) : ");
			String cuid=input.next();
			
			System.out.print("Input Customer's Name : ");
			String name=input.next();
			
			System.out.print("Input Burger Quantity : ");
			int amount=input.nextInt();
			
			double total=amount*BURGERPRICE;
			System.out.println("Total Value="+total);
			
			extendsArray();
			orderIdArray[orderIdArray.length-1]=id;
			nameArray[nameArray.length-1]=name;
			customerIdArray[customerIdArray.length-1]=cuid;
			quantityArray[quantityArray.length-1]=amount;
            statusArray[statusArray.length - 1] = PREPARING; // default status
			totalArray[totalArray.length-1]=total;
			
			System.out.println("");
			System.out.println("Added Success..");
			L2:do{
				System.out.println("");
				System.out.print("Do you want to add more(Y/N) : ");
				char op=input.next().charAt(0);
				if(op=='Y'||op=='y'){
					clearConsole();
					continue L1;
				}else if(op=='N'||op=='n'){
					homepage();
				}else{
					System.out.println("Wrong option../Select again...");
					continue L2;
				}
			}while(true);

			
			
			}while(true);

		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public static String[] orderIdArray = new String[]{"B0001", "B0002","B0003","B0004"};
	public static String[] customerIdArray = new String[]{"0714093637", "0763473517","0713463735","0743455678"};
	public static String[] nameArray = new String[]{"yenuli", "nayana","ruvini","kenuli"};
	public static int[] quantityArray = new int[]{1, 6,8,4};
	public static int[] statusArray = new int[]{0,0,1,2}; // or use 0,0
	public static double[] totalArray=new double[]{1000.0,1500.0,4000.0,2000.0};

	
	/////////////////////////////////////////////////////////////////////////////////////////////
	public final static void clearConsole() { 
				try { 
				final String os = 
				System.getProperty("os.name"); if 
				(os.contains("Windows")) { 
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
				} else { 
				System.out.print("\033[H\033[2J"); 
				System.out.flush(); 
				} 
				} catch (final Exception e) { 
				e.printStackTrace(); 
				// Handle any exceptions. 
				} 
				} 
   //////////////////////////////////////////////////////////////////////////////////////////////
	public static void placeOrder(){
    clearConsole();
    Scanner input = new Scanner(System.in);
    System.out.println("PLACE ORDER");
    addOrder();
    
   
}

	////////////////////////////////////////////////////////////////////////////////////////////
	
    public static void searchBestCustomer() {
    Scanner input = new Scanner(System.in);
    clearConsole();
    System.out.println("SEARCH BEST CUSTOMERS (Descending by Total Bill)");
    System.out.println("-------------------------------------------------------------------------------------");
    System.out.println("Customer Id\tCustomer Name\t\tTotal Bill");
    System.out.println("-------------------------------------------------------------------------------------");

    
    for (int i = 0; i < totalArray.length - 1; i++) {
        for (int j = 0; j < totalArray.length - 1 - i; j++) {
            if (totalArray[j] < totalArray[j + 1]) {
                
                double tempTotal = totalArray[j];
                totalArray[j] = totalArray[j + 1];
                totalArray[j + 1] = tempTotal;


                String tempName = nameArray[j];
                nameArray[j] = nameArray[j + 1];
                nameArray[j + 1] = tempName;

         
                String tempId = customerIdArray[j];
                customerIdArray[j] = customerIdArray[j + 1];
                customerIdArray[j + 1] = tempId;
            }
        }
    }


    for (int i = 0; i < totalArray.length; i++) {
        System.out.println(customerIdArray[i] + "\t\t" + nameArray[i] + "\t\t" + totalArray[i]);
    }

    System.out.println("-------------------------------------------------------------------------------------");
    System.out.print("Do you want to go to homepage (Y/N) => ");
    char op = input.next().charAt(0);
    if (op == 'Y' || op == 'y') {
        clearConsole();
        homepage();
    } else if (op == 'N' || op == 'n') {
        searchBestCustomer(); // restart
    }
}

	//////////////////////////////////////////////////////////////////////////////////////////
	public static void searchOrder(){
		Scanner input=new Scanner(System.in);
		clearConsole();
				System.out.println("SEARCH ORDER"); 
				System.out.print("Enter Order ID="); 
				String searchOrderId=input.next();
				displayOrder(searchOrderId);
	}
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void searchCustomer() {
    char op;
    Scanner input = new Scanner(System.in);
    clearConsole();

    System.out.println("SEARCH CUSTOMER");
    System.out.println("");
    System.out.print("Enter Customer ID = ");
    String id = input.next();

    boolean found = false;

    System.out.println(""); 
    for (int i = 0; i < customerIdArray.length; i++) {
        if (customerIdArray[i].equalsIgnoreCase(id)) {
            if (!found) { // Print header once
                System.out.println("Customer ID = " + customerIdArray[i]);
                System.out.println("Name = " + nameArray[i]);
                System.out.println("");
                System.out.println("Customer Order Details");
                System.out.println("======================");
                System.out.println("");
                System.out.println("Order ID\tQuantity\tTotal");
                System.out.println("=====================================");
                found = true;
            }

            // Print matching order details
            System.out.println(orderIdArray[i] + "\t\t" + quantityArray[i] + "\t\t" + totalArray[i]);
        }
    }

    if (found) {
        System.out.println(""); 
        System.out.print("Do you want to go to homepage (Y/N) => ");
    } else {
        System.out.println("This customer ID is not added yet........");
        System.out.print("Do you want to try again or go to homepage (Y/N) => ");
    }

    op = input.next().charAt(0);
    if (op == 'Y' || op == 'y') {
        clearConsole();
        homepage();
    } else {
        searchCustomer();
    }
}

	////////////////////////////////////////////////////////////////////////////////////////
	public static void viewOrders(){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("[1]Delivered Order");
		System.out.println("[2]Prepairing Order");
		System.out.println("[3]Cancelled Order");
		System.out.println("");
		System.out.println("");
		System.out.print("Enter an option=>");
		int option=input.nextInt();
		char op;
		switch (option){
			case 1:
			clearConsole();
			System.out.println("Order Id\tCustomer Id\tCustomer Name\tBurger Quantity\t\tOrder Status|");
			System.out.println("-------------------------------------------------------------------------------------");
			for(int i=0;i<orderIdArray.length;i++){
				if(statusArray[i]==1){
					
					System.out.println(orderIdArray[i] + "\t\t" + customerIdArray[i] + "\t\t" + nameArray[i] + "\t\t" + quantityArray[i] + "\t\t" + getOrderStatus(statusArray[i])+"   |");
					
					
				}
				}
				System.out.println("-------------------------------------------------------------------------------------");
				System.out.print("Do you want to go to homepage(Y/N)=>");
				op=input.next().charAt(0);
				if(op=='Y'||op=='y'){
					clearConsole();
					homepage();
				}else if(op=='N' || op=='n'){
					viewOrders();
				}
				
			break;
			case 2:
			clearConsole();
			System.out.println("Order Id\tCustomer Id\tCustomer Name\tBurger Quantity\t\tOrder Status|");
			System.out.println("-------------------------------------------------------------------------------------");
			for(int i=0;i<orderIdArray.length;i++){
				if(statusArray[i]==0){
					
					System.out.println(orderIdArray[i] + "\t\t" + customerIdArray[i] + "\t\t" + nameArray[i] + "\t\t" + quantityArray[i] + "\t\t" + getOrderStatus(statusArray[i])+"   |");
					
					
				}
				}
				System.out.println("-------------------------------------------------------------------------------------");
				System.out.print("Do you want to go to homepage(Y/N)=>");
				op=input.next().charAt(0);
				if(op=='Y'||op=='y'){
					clearConsole();
					homepage();
				}else if(op=='N' || op=='n'){
					viewOrders();
				}
			break;
			case 3:
			clearConsole();
			System.out.println("Order Id\tCustomer Id\tCustomer Name\tBurger Quantity\t\tOrder Status|");
			System.out.println("-------------------------------------------------------------------------------------");
			for(int i=0;i<orderIdArray.length;i++){
				if(statusArray[i]==2){
					
					System.out.println(orderIdArray[i] + "\t\t" + customerIdArray[i] + "\t\t" + nameArray[i] + "\t\t" + quantityArray[i] + "\t\t" + getOrderStatus(statusArray[i])+"   |");
					
					
				}
				}
				System.out.println("-------------------------------------------------------------------------------------");
				System.out.print("Do you want to go to homepage(Y/N)=>");
				op=input.next().charAt(0);
				if(op=='Y'||op=='y'){
					clearConsole();
					homepage();
				}else if(op=='N' || op=='n'){
					viewOrders();
				}
			
		}
	
	}
		
	////////////////////////////////////////////////////////////////////////////////////////////
	public static void updateOrderDetails(){
		clearConsole();
				Scanner input=new Scanner(System.in);
				System.out.println("UPDATE ORDER DETAILS");
                System.out.println("");
				System.out.print("Enter Order ID=");
				String id=input.next();
				for(int i=0;i<orderIdArray.length;i++){
					 if (orderIdArray[i].equalsIgnoreCase(id)) {
					if(statusArray[i]==1){
						System.out.println("This order is already deliverd......You can not update this order");
					}else if(statusArray[i]==2){
						System.out.println("This order is already cancelled......You can not update this order");
					}else if(statusArray[i]==0){
						System.out.println("");
						System.out.println("Order="+orderIdArray[i]);
						System.out.println("Customer ID="+customerIdArray[i]);
						System.out.println("Name="+nameArray[i]);
						System.out.println("Quantity="+quantityArray[i]);
						System.out.println("Order Value="+totalArray[i]);
						System.out.println("Order Status="+statusArray[i]);
						System.out.println("");
						System.out.println("What do yo want to update?");
						System.out.println("");
						System.out.println("[01]Quantity");
						System.out.println("[02]Status");
						System.out.println("");
						System.out.print("Enter an option=>");
						int option=input.nextInt();
						
						switch(option){
							case 1:
							System.out.println("Quantity Update");
							System.out.println("===============");
							System.out.println("");
							System.out.println("Order="+orderIdArray[i]);
							System.out.println("Customer ID="+customerIdArray[i]);
							System.out.println("Name="+nameArray[i]);
							System.out.println("");
							System.out.print("Enter your quantity update value-");
							int value=input.nextInt();
							quantityArray[i]=value;
							System.out.println("");
							System.out.println("Update succesfull.....");
							System.out.println("");
							System.out.println("");
							System.out.println("New order quantity="+value);
							System.out.println("New order value="+value*BURGERPRICE);
							System.out.println("");
							System.out.print("Do you want to go to homepage(Y/N)=>");
							 char op=input.next().charAt(0);
							if(op=='Y'||op=='y'){
								clearConsole();
								homepage();
							}else if(op=='N' || op=='n'){
								updateOrderDetails();
							}
										
							break;
							case 2:
							System.out.println("Status Update");
							System.out.println("=============");
							System.out.println("");
							System.out.println("Order="+orderIdArray[i]);
							System.out.println("Customer ID="+customerIdArray[i]);
							System.out.println("Name="+nameArray[i]);
							System.out.println("");
							System.out.println("");
							System.out.println("[0]Prepairing");
							System.out.println("[1]Delivered");
							System.out.println("[2]Cancelled");
							System.out.println("");
							System.out.println("");
							System.out.println("Enter new order status=");
							int status=input.nextInt();
							statusArray[i]=status;
							System.out.println("");
							System.out.println("");
							System.out.println("Update succesfull.....");
							System.out.println("");
							System.out.println("New order status="+getOrderStatus(statusArray[i]));
							System.out.print("Do you want to go to homepage(Y/N)=>");
							 char op1=input.next().charAt(0);
							if(op1=='Y'||op1=='y'){
								clearConsole();
								homepage();
							}else if(op1=='N' || op1=='n'){
								updateOrderDetails();
							}
							
						}
						
						
						
					}
				}
			 }
                 System.out.print("Do you want to go to homepage(Y/N)=>");
				 char op=input.next().charAt(0);
				if(op=='Y'||op=='y'){
					clearConsole();
					homepage();
				}else if(op=='N' || op=='n'){
					updateOrderDetails();
				}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public static void exit(){ 
          clearConsole(); 
          System.out.println("\n\t\tYou left the program...\n"); 
          System.exit(0); 
    }
	/////////////////////////////////////////////////////////////////////////////////////////////
	public static void homepage(){
		Scanner input=new Scanner(System.in);
		do{
			clearConsole();
		System.out.println("--------------------------------------------------------------");
		System.out.println("|                  iHungry Burger                            |");
		System.out.println("--------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("[01]Place Order   \t   [02]Search Best Customer");
		System.out.println("[03]Search Order  \t   [04]Search Customer");
		System.out.println("[05]View Orders   \t   [06]Update Order Details");
		System.out.println("[07]Exit  ");
		System.out.println("");
		System.out.println("");
		System.out.print("Enter an option to continue:>");
		int num=input.nextInt();
		
		switch(num){
			case 1:
			placeOrder();
			break;
			
			case 2:
			searchBestCustomer();
			break;
			
			case 3:
			searchOrder();
			break;
			
			case 4:
			searchCustomer();
			break;
			
			case 5:
			viewOrders();
			break;
			
			case 6:
			updateOrderDetails();
			break;
			
			case 7:
			exit();
			return;
			
			default:
			System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\nInvalid option. Please try again.");
           
	
			
		}
		
		 }while(true);
		
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String args[]){
		homepage();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////

}
