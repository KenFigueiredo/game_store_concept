import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/* Class: DisplayGame
 * This class allows for the creation of an aesthetically pleasing display of the
 * games in each tab. When games are added in Categories.printGame(), an instance of this
 * class is created for each added game.
 * 
 */
	@SuppressWarnings("serial")
	public class DisplayGame extends JPanel{
	
		public DisplayGame(Games g){
			
			this.setLayout(new BorderLayout());
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), BorderFactory.createLoweredBevelBorder()));
			
			JPanel pane1 = new JPanel();
				pane1.setLayout(new FlowLayout());	
				JLabel img = new JLabel(g.getIcon());
				img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				pane1.setBackground(Color.WHITE);
				pane1.add(img);
						
			JPanel pane2 = new JPanel();
				GridLayout grid = new GridLayout(3,2) ;				
				pane2.setLayout(grid);
				grid.setVgap(-50);
				pane2.setBackground(Color.WHITE);
					
					JLabel name = new JLabel(g.getName());
					name.setFont(new Font(name.getFont().getName(), Font.PLAIN, 16));
					pane2.add(name);
					
					pane2.add(new JLabel("Genre(s): " + g.getGenre()));		
					pane2.add(new JLabel("Release Date: " + g.sdf.format(g.getRD())));
					pane2.add(new JLabel("Players: " + Integer.toString(g.getnumOfPlayers())));				
					pane2.add(new JLabel("Rating: " + Float.toString(g.getRating())));
					
						if(g.getMultiplayer())
							pane2.add(new JLabel("Multiplayer: Yes"));
						
						else
							pane2.add(new JLabel("Multiplayer: No"));
						
			this.add(pane1, BorderLayout.LINE_START);
			this.add(pane2, BorderLayout.CENTER);
					
		}		
	}
