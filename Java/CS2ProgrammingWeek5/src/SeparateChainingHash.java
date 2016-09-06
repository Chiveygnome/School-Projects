import java.util.LinkedList;
import java.util.List;


public class SeparateChainingHash {
	

static int m_nTableSize = 1000;
LinkedList<DataObject>[] m_ObjectArray;

	public  SeparateChainingHash()
	{
		this(m_nTableSize);
	}

	public SeparateChainingHash(int nTableSize)
	{
		m_ObjectArray = new LinkedList[nTableSize];
		for(int i = 0; i<m_ObjectArray.length;i++)
		{
			m_ObjectArray[i] = new LinkedList<DataObject>();
		}
	}

	public void put(DataObject objData ) throws Exception
	{
		if(objData == null)
		{
			throw new NullPointerException();
		}

		String strKey = objData.GetKey();
		// check for resizing
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		// remember starting hash
		( m_ObjectArray[(int)(lHash%m_nTableSize)]).add(objData);
		//System.out.print("Sucessful input of "+objData);
		if(m_ObjectArray[(int)(lHash%m_nTableSize)].size()>=10)
			resize();
	}
	public void rehash(LinkedList<DataObject>[] A)
	{
		for(int i = 0;i<A.length;i++)
		{
			for(int j = 0; j<A[i].size();j++)
			{
				try {
					put(A[i].pop());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public DataObject get( String strKey ) throws Exception
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		int i = m_ObjectArray[(int)(lHash%m_nTableSize)].indexOf(strKey);
		if(i == -1)
			throw new Exception("Does not exist in current context"); 
		return( m_ObjectArray[(int)(lHash%m_nTableSize)].get(i) );
	}
	public void resize()
	{
		LinkedList<DataObject>[] A = new LinkedList[m_nTableSize];
		A = m_ObjectArray;
		m_nTableSize *= 2;
		rehash(A);
	}
}


