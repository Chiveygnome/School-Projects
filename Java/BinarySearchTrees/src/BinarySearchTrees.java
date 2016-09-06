import java.util.Random;
import java.util.Scanner;


public class BinarySearchTrees 
{
	
	static BST m_objBST = new BST();
	static int[] data = {30,15,45,0};
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		int i = 0;
		while(i != -1)
		{
			System.out.print("Please enter a key below:\n(1) generate\n(2)delete\n(3) print\n(4) insert ");
			i = in.nextInt();
			if(i == 1)
			{
				System.out.print("Please enter the size of tree that you want");
				i = in.nextInt();
				Generate(i);
			}
			else if(i == 2)
			{
				System.out.print("Please enter the number you want deleted");
				i = in.nextInt();
				m_objBST.Delete(i);
			}
			else if(i == 3)
			{
				m_objBST.printTree(m_objBST.m_objRootNode);
				System.out.println();
				m_objBST.printSubtree(m_objBST.m_objRootNode);
				System.out.println();
			}
			else if (i == 4)
			{
				System.out.println("Please enter the number you want to insert");
				i = in.nextInt();
				m_objBST.Insert(i);
			}
			else continue;
		}
		
	}
	public static void Generate(int i)
	{
		Random rand = new Random();
		int[] arr= new int[i];
		for(int j = 0;j<arr.length;j++)
		{
			arr[j] = rand.nextInt(100000)+1;
		}
		for(int j = 0;j<arr.length;j++)
		{
			m_objBST.Insert(arr[j]);
		}
	}
}
