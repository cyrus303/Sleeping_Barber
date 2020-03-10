public class Parameters {
	
	public int no_of_chairs; 
	public int no_of_hair_cut_done; 
	public int no_of_customer_visit ;
	public int target_no_of_customers;
	
	public Parameters(int a)
	{
		no_of_chairs=a;
		no_of_hair_cut_done=0;
		no_of_customer_visit=0;
		 
	}

	public synchronized void set_target_no_of_customers(int i)
	{
		target_no_of_customers=i;
		//set the target no of customers
	}
	
	
	public synchronized int get_target_no_of_customers()
	{
		return target_no_of_customers;
		//return the target no of customers
	}

	
	
	public synchronized int get_no_of_chairs()
	{
		return no_of_chairs;
		//return the current no of chairs (empty)
	}
	
	
	public synchronized int get_no_of_hair_cut_done()
	{
		return no_of_hair_cut_done;
		//return the total no of hair cut done so far 
		
	}
	
	public synchronized void increment()  
	{
	        		{
        			
                no_of_chairs++; 
                no_of_hair_cut_done++;
                System.out.println("\n\nChair remaining = "+no_of_chairs);
                System.out.println("No of hair cut done = "+no_of_hair_cut_done);
                notifyAll();
					}
	        		
	}
	
	public synchronized void decrement() throws InterruptedException {
     
	   	 {
           	if(no_of_chairs==0) 
        	{
        		no_of_customer_visit++;
        		System.out.println("No of customers visited saloon = "+no_of_customer_visit);
        		wait();
        	}
        	else
        	{
        		no_of_chairs--;
        		no_of_customer_visit++; 
        	}
        }
	}
		

}