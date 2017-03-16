import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

/* Class Games
 *  This class has an outline of what each game needs to contain by the outlines required in the instructions.
 *  An instance of this class is created when Categories.readInGames() is called. This class holds all 
 *  relevant information to a single game, and holds getters to access the games.
 */

public class Games {
	
	private String name;
	private Date releaseDate;
	private String genre;
	private float rating;
	private int numOfPlayers;
	private boolean isMultiplayer;
	private ImageIcon icon = new ImageIcon("img.png");
	public SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yy");
	
		public Games(String n, String rD, String g, String rate, String numPlay) throws ParseException{
			name = n;
			releaseDate = sdf.parse(rD);
			genre = g;
			rating = Float.parseFloat(rate);
			numOfPlayers = Integer.parseInt(numPlay);	
			
			if(numOfPlayers > 1)
				isMultiplayer = true;
		}
				
			public String getName(){
				return name;
			}
			
			public Date getRD(){
				return releaseDate;
			}
			
			public String getGenre(){
				return genre;
			}
						
			public float getRating(){
				return rating;
			}
			
			public boolean getMultiplayer(){
				return isMultiplayer;
			}
			
			public int getnumOfPlayers(){
				return numOfPlayers;
			}
			
			public ImageIcon getIcon(){
				return icon;
			}
			
}
