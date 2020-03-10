import java.util.Random;
import java.util.concurrent.*;

class Barber implements Runnable

{
	
	private int no_barber;
	private Semaphore cust, barb;
	
	
	private Parameters p;
	long threadId;
	
	public Barber(int n, Semaphore customers, Semaphore barbers, Parameters param)
	{
		cust = customers;
		barb=barbers;
		p=param;
		no_barber=n;
	}

	public int getno_barber()
	{
		return no_barber;
	}
	
	public void setno_barber(int n)
	{
		no_barber=n;
	}
		
	
	public void run()
	{
		threadId=Thread.currentThread().getId();	

		try {
			barber_work(cust,barb,p);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("\n\n-----------------intrupted -------------");
			//e.printStackTrace();
		}
		
		return;
	}
	
	public void barber_work(Semaphore customers, Semaphore barbers, Parameters param) throws InterruptedException
		
	{
			//Barbers will wait till the target number of customers is NOT met 
			// AND the number of customers waiting in non-zero
			//There may be a situation where target is not met but no customer is waiting as they came and left 
		   int Count = 0;
			while (param.get_no_of_hair_cut_done()!=param.get_target_no_of_customers())  
			
						{
//				System.out.println("Hi, inside barber thread and executing the barber_work method");
//				System.out.println("Inside barber_Work:target no of customers="+param.get_targetNoCustomers());
//				System.out.println("Inside barber_Work:Number of hair cut done="+param.get_noHairCutDone());
				
				customers.acquire();
				System.out.println("\n\nBarber has taken one customer to cut his hair");
							
				//TimeUnit.MILLISECONDS.sleep(5050);
				
				Random randomno = new Random();
			    
			      // check next Gaussian value  
			      double RValue = randomno.nextGaussian();
				
				try
	            {			
							
							System.out.println("barber sleep value = "+ Math.abs(RValue));
							System.out.println("barber sleep long"+(long) (Math.abs(RValue) * 10000));
							TimeUnit.MILLISECONDS.sleep((long) (Math.abs(RValue) * 10000));
	                        //sleep((long)(Math.random() * 4000));
	            }
	            catch(InterruptedException ex) {};
			
				param.increment(); 
				System.out.println("Barber released one chair..Chair remaining = "+param.no_of_chairs);
				barbers.release();
				System.out.println("Barber"+no_barber+"  is done with 1 hair cut & is available for next job..");
				
				Count = param.get_target_no_of_customers()- param.no_of_customer_visit;
				System.out.println("-------------------this----------------"+ Count);
				System.out.println("-------------------this----------------"+ param.get_no_of_hair_cut_done());
		
//				int flag = Count + param.get_no_of_hair_cut_done();
//				if (flag==param.get_target_no_of_customers()) {
//					break;
//				}
				System.out.flush();					
			} 
					
	
			System.out.println("\n\ntarget no of customers="+param.get_target_no_of_customers());
			System.out.println("\n\nNumber of hair cut done="+param.get_no_of_hair_cut_done());
			System.out.println("\n\nBarber work is over for this barber thread ");
//			System.out.println("------------------Terminating the program------------------");
//        	System.exit(0);
			System.out.flush();
		}
	
}