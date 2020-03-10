import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BarberShop extends Thread

{

	
	public int hairCutTime;
	private int no_of_barbers;
	private int no_of_customers;
		
	private ArrayList<Customer>  theCustomers = new ArrayList<Customer>();
	private ArrayList<Barber>  theBarbers = new ArrayList<Barber>();
	
	public Parameters param; 
	
	public static Semaphore customers;// = new Semaphore(0);     
    public static Semaphore barbers; //= new Semaphore(0);
	
	public void set_customerno(int i)
	{
		no_of_customers=i;
	}
	
	public void set_barberno(int i)
	{
		no_of_barbers=i;
	}

	
	public int  get_barberno()
	{
		return no_of_barbers;
	}
	
	public int  get_customerno()
	{
		return no_of_customers;
	}

	public void set_hairCutTime(int i)
	{
		hairCutTime=i;
	}
	
	public int  get_hairCutTime()
	{
		return hairCutTime;
	}
	
	public BarberShop(int barber, int customer, int haircuttime )
	{
	
		System.out.println("Welcome to the Barber Shop \n");
		customers = new Semaphore(0);
		barbers = new Semaphore(2);
			
		no_of_barbers=barber; //	
		no_of_customers=customer;
		
		hairCutTime=haircuttime;
	}
	
	public void runshop(Parameters param) throws InterruptedException{
		
		int j; 
		//System.out.println("\n\n Now Barbers started arriving..............\n\n\n");	
		ExecutorService executor = Executors.newFixedThreadPool(15);//creating a pool of 5 threads  	
				for (j=1;j<=no_of_barbers;j++)
							{
					Barber barbernew = new Barber(j,customers, barbers, param);
					theBarbers.add(barbernew);
					Runnable tb = new Thread(barbernew);
//					Runnable tb2 = new Thread(barbernew);

					//					System.out.println("New Barber thread started: " + tb);
					 executor.execute(tb);//calling execute method of ExecutorService  
					//tb.start();
					System.out.flush();	
							}
			
			System.out.println("\n\n Customers started arriving .............\n\n\n");
			
//			while(!exit && j<=no_of_customers) {
			for (j=1;j<=no_of_customers;j++)
			{
			Customer customernew = new Customer(j,customers, barbers, param);  
			
			theCustomers.add(customernew);
			Runnable tc=new Thread(customernew);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@New Customer thread started:" + tc); 
			

			 Random randomno = new Random();
			    
		      // check next Gaussian value  
		      double RValue = randomno.nextGaussian();
			
			try
            {			
				 		executor.execute(tc);
						//tc.start();
						System.out.println("cus sleep value = "+ Math.abs(RValue));
						System.out.println("cus sleep long"+(long) (Math.abs(RValue) * 5000));
						sleep((long) (Math.abs(RValue) * 5000));
                        //sleep((long)(Math.random() * 4000));
				
            }
            catch(InterruptedException ex) {};
            
			}
//			executor.shutdown();  
//	        while (!executor.isTerminated()) {   }  
//	  
//	        System.out.println("Finished all threads");  
			System.out.flush();	
				
		
		return;
					
	 	}
		
			
					
	
	}