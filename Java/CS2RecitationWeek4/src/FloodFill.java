import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FloodFill extends Applet implements MouseListener{
	int m_nUpperLeftX = 10;//all constants to define the color canveses
	int m_nUpperLeftY = 10;
	int m_nColorWidth = 50;
	int m_nColorHeight = 50;
	int m_nLowerRightX;
	int m_nLowerRightY;
	Color m_objSelectedColor = Color.blue;//defualt color
	BufferedImage m_objShape;//the image
	MediaTracker tracker = new MediaTracker(this);//mouse tracker?  idk was included in the given code
	static String GetNameAndPID()
	{
		return( "Wheaton,Ernest,e3388709");
	}

	public void init()
	{
		addMouseListener(this);//initialize mouse listener
		setSize(1020,700);//set the size of the back canvas to 1020x720 pixels

	        try //get the image
	        {
			m_objShape = ImageIO.read(FloodFill.class.getResourceAsStream("Untitled.png"));
		} 
	        catch (IOException e1) 
	        {
		}
		tracker.addImage(m_objShape, 100);// add the image to the canvas with an offset of 100
		try 
		{
			tracker.waitForAll();//wait for input
		} 
		catch (InterruptedException e) 
		{
		}
			
	}
	static Color[] m_Colors =//array of colors
	{
		Color.blue, Color.red, Color.green, Color.yellow,
		Color.gray, Color.magenta, Color.orange, Color.cyan
	};

	void DrawColors( Graphics canvas )//method to inititailize the color pallet on the side of the screen
	{
		for( int i=0; i<m_Colors.length; i++ )//loop through the array
		{
			canvas.setColor( m_Colors[i] );//set the color of this rectangle to the color
			canvas.fillRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, //create the rectangle
			m_nColorWidth, m_nColorHeight );
			canvas.setColor( Color.black );//set the back of the rectangle to black
			canvas.drawRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
			m_nColorWidth, m_nColorHeight );//draw another rectangle
					m_nLowerRightX = m_nUpperLeftX + m_nColorWidth;
					m_nLowerRightY = ( i + 1 ) * m_nColorHeight;
		}
	}
	@Override
	public void mouseClicked(MouseEvent ms) //method to decide what to do with mouse input
	{
		if( ms.getX() >= m_nUpperLeftX &&//if in one of the rectangles
			ms.getY() >= m_nUpperLeftY &&
			ms.getX() < m_nLowerRightX &&
			ms.getY() < m_nLowerRightY )
		{
			int nColorIndex = ( ms.getY() - m_nUpperLeftY ) / m_nColorHeight;
			if( nColorIndex >= 0 && nColorIndex <= 7 )
			{
				m_objSelectedColor = m_Colors[nColorIndex];// make the color selector the color selected
			}
		}
	else if( ms.getX() >= 100 &&// if in the image
			ms.getY()>=100 &&
			ms.getX() < 100 + m_objShape.getWidth() &&
			ms.getY() < 100 + m_objShape.getHeight())
	{
		int Color = -1;//set default color to white
		for(int i = 0;i <=m_Colors.length;i++)
		{
			if(m_Colors[i] == m_objSelectedColor)//if the current color == the selected color
			{
				Color = m_Colors[i].getRGB();//return its RBG value
				break;//break from the loop
			}
		}
		DoFloodFill( ms.getX()-100, ms.getY()-100,m_objShape.getWidth(),m_objShape.getHeight(),Color);//floodfill at an off set of x-100 and y-100
	}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	void DrawTestShape( Graphics canvas )
	{
		canvas.drawImage(m_objShape, 100, 100, null);
	}
	public void paint( Graphics canvas )
	{
		DrawColors( canvas );
		DrawTestShape( canvas );
	}
	void SetPixel( int x, int y, Graphics canvas )
	{
		canvas.drawLine(x, y, x, y);
	}
	void SetPixel( int x, int y, int nColor )
	{
		m_objShape.setRGB(x, y, nColor);
	}
	public int GetPixel( int x, int y )
	{
		int i =( m_objShape.getRGB(x, y) );
		return i;
	}
	public void DoFloodFill(int x, int y, int w, int h, int c)
	{
		int StartColor = GetPixel(x,y);//get the color of the pixel selcted
		if(StartColor == c) return;//if the selected color and color of the selcted pixel are the same, return
		Floodfill(x,y,w,h,StartColor,c);//else floodfill
	}
	public void Floodfill(int x, int y, int w, int h, int StartColor, int c)
	{
		if(x<0||y<0||x>=w||y>=h) return;//if out side the bounds, return
		if(GetPixel(x,y)!=StartColor) return;//another color checker
		int xx = x;//set the trivial variable xx to x
		while(xx<w&&GetPixel(xx,y) == StartColor)//while in bounds and color == start color
		{
			SetPixel(xx,y,c);//set the pixel color to c
			xx++;//increase 
			repaint();//repaint the canvas with the new colors
		}
		xx = x-1;//set xx to x-1 
		while(xx>=0&&GetPixel(xx,y) == StartColor)
		{
			SetPixel(xx,y,c);
			xx--;//decrease
			repaint();
		}
		Floodfill(x,y-1,w,h,StartColor,c);//recursive down 
		Floodfill(x,y+1,w,h,StartColor,c);//recursive up
		
	}
}
