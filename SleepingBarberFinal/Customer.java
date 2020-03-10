import java.util.concurrent.Semaphore;
	
class Customer implements Runnable

{
	
private int customer_id;
private Semaphore cust, barb;
private Parameters p;
long threadId;

	public Customer(int n, Semaphore customers, Semaphore barbers, Parameters param)
	{
		customer_id=n;
		cust = customers;
		barb=barbers;
		p=param;
		System.out.println("\n\nCustomer "+ customer_id+" is walking into barber shop ");
	}
	
	public int getcustomer_id()
	{
		return customer_id;
	}
	
	public void setcustomer_id(int n)
	{
		customer_id=n;
	}
	
	public void run()
	{
		threadId=Thread.currentThread().getId();
		try {
			customer_work(cust,barb,p);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.flush();
		
		System.out.println("\n\n Customer Child thread finished!"+threadId);
		
		return;
	
	}
	
	public void customer_work(Semaphore customers, Semaphore barbers, Parameters param) throws InterruptedException
	{
		if(param.no_of_chairs>0)
		
		{
			param.decrement();
			System.out.println("waiting for barber..Chair remaining = "+param.no_of_chairs);
    				
			customers.release();

			barbers.acquire();
						
			System.out.println("Customers "+customer_id+"  is getting his haircut");
			
			
			
			
//			try
//             {
//				 TimeUnit.MILLISECONDS.sleep(10000);
//             }
//             catch (InterruptedException ex) {}
			
		}
		else
		{
			
			System.out.println("Customers "+customer_id+"  is leaving saloon as no free seats");
			System.out.flush();	
		} 
		
	}


}