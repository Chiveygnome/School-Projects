
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	private int k = 3;
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    	countRank(m_objRootNode,0);
    	Subtree(m_objRootNode);
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
		// This node is null and simply needs to be allocated.
    	BSTNode NullNode = new BSTNode();
		if( objNode == null )
    	{
    		objNode = new BSTNode( nKeyValue );
    		return objNode;
    	}
		else if(Math.abs(nKeyValue - objNode.m_nKeyValue)>k)
    	{
        	// Here we need to walk left.
        	if( nKeyValue < objNode.GetKeyValue() )
        	{
        		// Set the left node of this object by recursively walking left.
        		objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        	}
        
        	// Here we need to talk right.
        	else if( nKeyValue > objNode.GetKeyValue() )
        	{
        	// Set the right node of this object by recursively walking right.
        		objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        	}
        	objNode.Subtree = count(objNode);
        	return( objNode );
    	}
    	else return NullNode;
    }
    public void Delete(int val)
    {
    	m_objRootNode = Delete(val,m_objRootNode);
    }
    protected BSTNode Delete(int val,BSTNode objNode)
    {
    	if(objNode == null)
    	{
    		System.out.println("Value "+ val + " not found.");
    		return null;
    	}
    	if(objNode.m_nKeyValue > val) objNode.SetLeftNode(Delete(val,objNode.GetLeftNode()));
    	else if(objNode.m_nKeyValue < val) objNode.SetRightNode(Delete(val,objNode.GetRightNode()));
    	else
    	{
    		if(objNode.GetLeftNode() == null && objNode.GetRightNode() == null)
    		{
    	    	BSTNode Parent = GetParent(m_objRootNode,val);
    			if(objNode.m_nKeyValue>Parent.m_nKeyValue) Parent.SetRightNode(null);
    			else Parent.SetLeftNode(null);
    			Subtree(m_objRootNode);
    			return null;
    		}
    		else if(objNode.GetLeftNode() != null && objNode.GetRightNode() == null) 
    		{
    			objNode = objNode.GetLeftNode();
    		}
    		else if(objNode.GetLeftNode() == null && objNode.GetRightNode() != null)
    		{
    			objNode = objNode.GetRightNode();
    		}
    		else
    		{
    			objNode.m_nKeyValue = GetMax(objNode.GetLeftNode());
    			if(GetMaxNode(objNode.GetLeftNode()).GetLeftNode() != null)
    				SetMaxNode(objNode.GetLeftNode(),GetMaxNode(objNode.GetLeftNode()).GetLeftNode());
    			
    		}
    		Subtree(m_objRootNode);
    	}
    	return objNode;
    }
    public int GetMin(BSTNode root)
    {
    	if(root.GetLeftNode() != null) return GetMin(root.GetLeftNode());
    	else return root.m_nKeyValue;
    }
    public int GetMax(BSTNode root)
    {
    	if(root.GetRightNode() != null) return GetMax(root.GetRightNode());
    	else return root.m_nKeyValue;
    }
    public BSTNode GetMinNode(BSTNode root)
    {
    	if(root.GetLeftNode() != null) return GetMinNode(root.GetLeftNode());
    	else return root;
    }
    public BSTNode GetMaxNode(BSTNode root)
    {
    	if(root.GetRightNode() != null) return GetMaxNode(root.GetRightNode());
    	else return root;
    }
    public void SetMaxNode(BSTNode root,BSTNode Node)
    {
    	if(root.GetRightNode() != null) SetMaxNode(root.GetRightNode(),Node);
    	else 
    	{
    		root.SetKeyValue(Node.GetKeyValue());
    		root.SetLeftNode(Node.GetLeftNode());
    	}
    }
    /**
     * Gets the entire tree's subtree counts.  also can be used to update the tree's subtree count
     * @param root:  Usually going to be the tree main root, unlees you want a specific subtree
     */
    public void Subtree(BSTNode root)
    {
    	if(root!=null)
    	{
    		root.SetSubtreeSize(count(root));
    		Subtree(root.GetLeftNode());
    		Subtree(root.GetRightNode());
    	}
    }
    protected int count(BSTNode root)
    {
    	if(root != null)
    	{
    		return 1 + count(root.GetLeftNode()) + count(root.GetRightNode());
    	}
    	else return 0;
    }
    public void countRank(BSTNode root, int i)
    {
    	if(root != null)
    	{
    		countRank(root.GetLeftNode(),i+1);
    		root.SetRank(i);
    		countRank(root.GetRightNode(),i+1);
    	}
    }
    public BSTNode GetParent(BSTNode root, int val)
    {
    	if(root.GetLeftNode().m_nKeyValue == val || root.GetRightNode().m_nKeyValue == val) return root;
    	else if(root.m_nKeyValue>val) return GetParent(root.GetLeftNode(),val);
    	else return GetParent(root.GetRightNode(),val);
    }
    public void printTree(BSTNode root)
    {
    	if(root == null) return;
    	if(root != null)
    	{
    		printTree(root.GetLeftNode());
    		System.out.print(root.m_nKeyValue+" ");
    		printTree(root.GetRightNode());
    	}
    }
    public void printSubtree(BSTNode root)
    {
    	if(root != null)
    	{
    		printSubtree(root.GetLeftNode());
    		System.out.print(root.GetSubtreeSize() + " ");
    		printSubtree(root.GetRightNode());
    	}
    }
}