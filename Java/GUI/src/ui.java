import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ui {
	protected JButton Button[];
	protected JPanel ButtonPanel = new JPanel();
	protected JTextArea Text[];
	protected JPanel TextPanel = new JPanel();
	protected Dimension size = new Dimension(50,25);
	public ui()
	{
		JFrame frame = new JFrame();
		frame.setSize(750,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Button = new JButton[3];

		Button[0] = new JButton("OK");
		Button[1] = new JButton("Help");
		Button[2] = new JButton("Cancel");
		
		Text = new JTextArea[2];
		Text[0] = new JTextArea(5,20);
		Text[1] = new JTextArea(5,20);
		
		TextPanel.add(Text[0]);
		TextPanel.add(Text[1]);
		
		for(int i = 0;i<3;i++)
			ButtonPanel.add(Button[i]);
		
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.EAST,ButtonPanel);
		frame.add(BorderLayout.CENTER,TextPanel);
		
	}
	public static void main(String[] args)
	{
		new ui();
	}
}
