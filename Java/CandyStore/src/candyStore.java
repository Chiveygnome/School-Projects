import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class candyStore {
	public int DoCandyStore(String name) throws FileNotFoundException
	{
		File F = new File(name);
		Scanner in = new Scanner(F);
		int i = in.nextInt();
		int f;
		while(i != 0)
		{
			f = (int) ((int)in.nextDouble()*100 +.01);
			// Run the DP in cents.
				int[] dp = new int[f+1];

						// Loop through each item
				for (int j=0; j<i; j++) {

					// Get this info.
					int cal = in.nextInt();
					int thisPrice = (int) ((int)in.nextDouble()*100+.01);

					// Run loop forward to allow to take as many of this candy as possible.
					for (int k=thisPrice; k<dp.length; k++)
					{
						dp[k] = Math.max(dp[k], dp[k-thisPrice]+cal);
					}

						// Print the answer and go to the next case.
					System.out.println(dp[dp.length-1]);
					i = in.nextInt();
					f = (int)(in.nextDouble()*100+.01);
					}
		}
		return 0;
	}
}
