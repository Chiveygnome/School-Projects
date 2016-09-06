import java.util.Hashtable;


public class HashExperiments 
{
	public static void main(String[] args) throws Exception 
	{
		System.out.println("List one is "+Lists.ListOne.length+" entries long.");
		Hashtable<String,DataObject> lh = new Hashtable<String,DataObject>();
		LinearHash Lh = new LinearHash();
		QuadraticHash Qh = new QuadraticHash();
		SeparateChainingHash Sh = new SeparateChainingHash();
		// Start the clock.	
		long start = System.currentTimeMillis();
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			lh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Normal Hash took "+(end-start)+" ms.");
		long start1 = System.currentTimeMillis();
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			try {
				Lh.put(new DataObject(Lists.ListOne[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		long end1 = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Linear Hash took "+(end1-start1)+" ms.");
		long start2 = System.currentTimeMillis();
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			try {
				Qh.put(new DataObject(Lists.ListOne[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		long end2 = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Quadratic Hash took "+(end2-start2)+" ms.");
		long start3 = System.currentTimeMillis();
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			try {
				Sh.put(new DataObject(Lists.ListOne[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		long end3 = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Separate Chaining took "+(end3-start3)+" ms.");
		System.out.println("get func: "+Sh.);
	}

}
