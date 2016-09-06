	
public class LinearHash 
{
int m_nTableSize = 1000;
DataObject[] m_ObjectArray;

	public LinearHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}

	public LinearHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
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
		long lStart = lHash;
		// remember starting hash

		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null && m_ObjectArray[(int) (lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			lHash++;
			if((lStart % m_nTableSize) == (lHash % m_nTableSize))
			{
				resize();
				throw new Exception("No empty slot\nresizing...");
			}



		}

		m_ObjectArray[(int)(lHash%m_nTableSize)] = objData;
	}

	public DataObject get( String strKey ) throws Exception
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		long lStart = lHash;

		while( m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey() != null && !m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey));
		{

		lHash++;
	
		if((lStart % m_nTableSize) == (lHash % m_nTableSize))
			throw new Exception("No empty slot");
		}

	return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
}
	public void resize()
	{
		DataObject A[] = new DataObject[m_nTableSize];
		A = m_ObjectArray;
		m_nTableSize *= 2;
		m_ObjectArray = new DataObject[m_nTableSize];
		m_ObjectArray = A;
	}
}
