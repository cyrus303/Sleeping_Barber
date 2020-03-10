import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws InterruptedException 
	{
		int ch, barb, cust, htime;
		Thread t = Thread.currentThread(); 
		t.setName("My Main Thread"); 
		System.out.println("\n\n");
		
		ch=barb=cust=htime=0;
		
		ch=5;
		barb=2;
//		cust=20;
		//htime =10000; 		
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter no of customers: ");
		cust = reader.nextInt();
		reader.close();
		
		System.out.println("\nStarting Multiple Barber Multiple Customer Simulation with following parameters:");
		System.out.println("No of Barbers="+barb);
		System.out.println("No of Customers="+cust);
		System.out.println("No of Chairs in waiting room="+ch);
		//System.out.println("Hair Cut time in millisec="+htime);
		System.out.println("");
		
		Parameters param = new Parameters(ch);
		param.set_target_no_of_customers(cust);
	
		BarberShop shop = new BarberShop(barb,cust,htime);
			
		shop.runshop(param);
		System.out.flush();	
		return;
	}

}