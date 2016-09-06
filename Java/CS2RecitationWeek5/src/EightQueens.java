import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EightQueens extends Applet implements MouseListener, MouseMotionListener, Runnable, ActionListener 
{
	Image queen_Img;
	MediaTracker tracker = new MediaTracker(this);
	static final int NUMROWS = 8;
	static final int NUMCOLS = 8;
	static final int SQUAREWIDTH = 50;
	static final int SQUAREHEIGHT = 50;
	static final int BOARDLEFT = 50;
	static final int BOARDTOP = 50;
	int m_nBoard[][] = new int[8][8];
	private static boolean m_bClash = false;
	private String m_strStatus ="";
	Button But = new Button("Solve");
	boolean solve = false;
	Thread thread = new Thread(this);
	private Image m_objOffscreen;	
	public void init()
	{
		addMouseListener(this);
		setSize(1020,700);
		add(But);
		But.addActionListener(this);
		thread.start();
		m_objOffscreen = createImage(1020, 700);
        try //get the image
        {
		queen_Img = ImageIO.read(EightQueens.class.getResourceAsStream("queen.png"));
        } 
        catch (IOException e1) 
        {
	}
	tracker.addImage(queen_Img, 100);// add the image to the canvas with an offset of 100
	try 
	{
		tracker.waitForAll();//wait for input
	} 
	catch (InterruptedException e) 
	{
	}
	}
	public void run() {
	// TODO Auto-generated method stub
		System.out.println("Inside run function.");
		try
		{
			while(true)
			{
				if(solve == true)
				{
					long start = System.currentTimeMillis();
					Solve(0);
					long end = System.currentTimeMillis();
					System.out.println("execution time: "+(end-start));
				}
				solve = false;
				But.setEnabled(true);
				Thread.sleep(200);
			}
		}
		catch(Exception e)
		{
		}
	}
	public void paint (Graphics canvas)
	{
		m_bClash = false;
		DrawSquares( canvas );
		canvas.setColor(Color.RED);
		CheckColumns( canvas );
		CheckRows( canvas );
		CheckDiagonal1( canvas );
		CheckDiagonal2( canvas );
		canvas.setColor(Color.BLUE);
		canvas.drawString(m_strStatus, 
	BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 8 + 20);
	}
	public void repaint()
	{
		Graphics g = m_objOffscreen.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 1020,700);
		paint(g);
		getGraphics().drawImage(m_objOffscreen, 0, 0, null);
	}
	void DrawSquares( Graphics canvas )
	{
		canvas.setColor(Color.BLACK);
		for( int nRow=0; nRow<NUMROWS; nRow++ )
		{
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				canvas.drawRect( BOARDLEFT + nCol * SQUAREWIDTH,
					BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
					
				if( m_nBoard[nRow][nCol] != 0 )
				{
					canvas.drawImage( queen_Img,
						BOARDLEFT + nCol * SQUAREWIDTH + 8, BOARDTOP + nRow * SQUAREHEIGHT + 6, null );
				}
			}
		}
	}	
	void CheckColumns( Graphics canvas )
	{
		// Check all columns
		for(  int nCol=0; nCol<NUMCOLS; nCol++ )
		{
			int nColCount = 0;
			for( int nRow=0; nRow<NUMROWS; nRow++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nColCount++;
				}
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) );
				m_bClash = true;
			}
		}
	}
	void CheckRows( Graphics canvas )
	{
		for(  int nRow=0; nRow<NUMROWS; nRow++ )
		{
			int nRowCount = 0;
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nRowCount++;
				}
			}
			if( nRowCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				m_bClash = true;
			}
		}
	}		
	void CheckDiagonal1( Graphics canvas )
	{
		// Check diagonal 1
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = 0;
			int nThisRow = nRow;
			int nThisCol = nCol;
			int nColCount = 0;
			while( nThisCol < NUMCOLS && nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				m_bClash = true;
			}
		}
		for( int nCol=1; nCol<NUMCOLS; nCol++)
		{
			int nRow = 0;
			int nThisRow = nRow;
			int nThisCol = nCol;
			int nColCount = 0;
			while( nThisCol < NUMCOLS && nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );	
				m_bClash = true;
			}
		}
	}
	void CheckDiagonal2( Graphics canvas )
	{
		// Check diagonal 2
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = NUMCOLS - 1;
			int nThisRow = nRow;
			int nThisCol = nCol;
			int nColCount = 0;
			while( nThisCol >= 0 && nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				m_bClash = true;
			}
		}
		for( int nCol=NUMCOLS-1; nCol>=0; nCol--)
		{
			int nRow = 0;
			int nThisRow = nRow;
			int nThisCol = nCol;
			int nColCount = 0;
			while( nThisCol >= 0 && nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				m_bClash = true;
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent ms) {
		// TODO Auto-generated method stub
		int Col = (ms.getX() - BOARDLEFT)/SQUAREWIDTH;
		int Row = (ms.getY() - BOARDTOP)/SQUAREHEIGHT;
		if(Col>=0&&Row>=0&&Col<NUMCOLS&&Row<NUMROWS)
		{
			if(m_nBoard[Row][Col] == 0)m_nBoard[Row][Col] = 1;
			else m_nBoard[Row][Col] = 0;
		}
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		But.setEnabled(false);
		ClearBoard();
		repaint();
		m_bClash = false;
		solve = true;
	}
	public void ClearBoard()
	{
		for(int i =0;i< m_nBoard.length;i++)
		{
			for(int j = 0;j<m_nBoard[i].length;j++)
			{
				m_nBoard[i][j]=0;
			}
		}
	}
	public boolean Solve(int i)
	{
		if(i>m_nBoard.length) return true;
		for(int j = 0;j<m_nBoard.length;j++)
		{	
			if(m_nBoard[i][j] == 1)
				{
					m_nBoard[i][j] = 0;
					continue;
				}
			else m_nBoard[i][j]=1;
			repaint();
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(m_bClash)
			{
				m_nBoard[i][j] =0;
				repaint();
				continue;
			}
			if(j>m_nBoard.length) return false;
			if(!m_bClash)return Solve(i+1)||Solve(i);
		}	
		return false;
	}
}