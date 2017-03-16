
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/* Class: Categories
 * This class creates the tabs used in the program. It uses scroll panes to deal with longer inputs.
 */

@SuppressWarnings("serial")
public class Categories  extends JTabbedPane  {
	
	//All inputed games will be added to gameList.
	public static ArrayList<Games> gameList = new ArrayList<Games>();
	
	//The created JPanels are added to each of their corresponding tabs.
	static JPanel allGames, newReleases, rating;
	static JScrollPane scroll1, scroll2, scroll3;
		
		public Categories() throws IOException {
		
					this.setTabPlacement(Categories.TOP);	
					readInGames();
				
				// Here the tabs are filled with Jpanels that will hold all relevant information to the
				// tab.
					allGames = new JPanel(new BorderLayout());
						scroll1 = new JScrollPane(getAll());
						scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						scroll1.getVerticalScrollBar().setUnitIncrement(16);
						allGames.add(scroll1,BorderLayout.CENTER);
					this.addTab("All Games", allGames);
					
					newReleases = new JPanel(new BorderLayout());
						scroll2 = new JScrollPane(getNewReleases());
						scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						scroll2.getVerticalScrollBar().setUnitIncrement(16);
						newReleases.add(scroll2,BorderLayout.CENTER);
					this.addTab("New Releases", newReleases);
					
					rating = new JPanel(new BorderLayout());
						scroll3 = new JScrollPane(getTopRated());
						scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						scroll3.getVerticalScrollBar().setUnitIncrement(16);
						rating.add(scroll3,BorderLayout.CENTER);
					this.addTab("Top Rated", rating);
					
					JPanel readme = new JPanel(new BorderLayout());
						JScrollPane scroll4 = new JScrollPane(readInManual());
						scroll4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						scroll4.getVerticalScrollBar().setUnitIncrement(16);
						readme.add(scroll4, BorderLayout.CENTER);			
					this.addTab("User Manual", readme);
			}
	
	/* Method: readInGames
	 * Function gets the game library from INPUT.txt. It reads in the 5 lines corresponding to a game then creates a new instance
	 * of Games.class. It then checks if the current game is already in the list by making a call to checkForDuplicates.
	 * If it is already in the list, it is not added. 
	 */
			public void readInGames() throws IOException {
										
					BufferedReader br = new BufferedReader(new FileReader("INPUT.txt"));
					int items = Integer.parseInt(br.readLine());
					
						for(int i = 0; i < items; i ++){										
								
								String s = br.readLine();
								
									if(s.length() == 0)
										i--;	
								
									else{
										Games newgame;
										try {
											newgame = new Games(s, br.readLine(), br.readLine(), br.readLine(), br.readLine());
											
											if(!checkForDuplicates(newgame))
												gameList.add(newgame);
											
										} catch (ParseException e) {	
											e.printStackTrace();
										}
										
									}
					}	
					br.close();			
			}
	
	/* Method: readInManual
	 * Reads in the manual from README.txt into a JTextArea. This allows
	 * for the manual to be accessed from the program.
	 */
			public JTextArea readInManual() throws IOException{
				
				BufferedReader br = new BufferedReader(new FileReader("README.txt"));				
				JTextArea manual = new JTextArea();
				
				manual.read(br, "manual");
				
				br.close();
				return manual;
			} 
	
	/* Method : checkForDuplicates
	 * Takes a game (g) and compares all aspects of it to current games in the
	 * gameList. If all of the game information of the game(g) matches a game
	 * in the gameList, true is returned(this game is a duplicated). else false
	 * is returned and the game will be added to the gameList.
	 */
			public boolean checkForDuplicates(Games g){
				int count = 0;
				
					for(Games e : gameList){
						
						if(g.getName().equals(e.getName()))
							count++;
						
						if(g.getRD().equals(e.getRD()))
							count++;
						
						if(g.getGenre().equals(e.getGenre()))
							count++;
							
						if(g.getRating() == e.getRating())
							count++;
								
						if(g.getnumOfPlayers() == e.getnumOfPlayers())
							count++;
						
						
						if(count == 5)
							return true;
						
						count = 0;
					}

				return false;
			}
	
	/*  Method: getAll
	 * 	Sorts the gameList alphabetically for use in the 'All' tab.
	 *  Then makes a call to printGame()		
	 */
			public static JPanel getAll(){
				Collections.sort(gameList, new Comparator<Games>(){
					@Override
					public int compare(Games r1, Games r2) {		
						return r1.getName().compareTo(r2.getName());
					}				
				});
								
				return printGame(1);
			}
	
	/* Method : getNewReleases
	 * 	Sorts the gameList by release date chronologically then reverses the list,
	 *  making the gameList have all games in order from newest to oldest.
	 *  printGame is then called. 
	 */
			public static JPanel getNewReleases(){
				
				Collections.sort(gameList, new Comparator<Games>(){
					@Override
					public int compare(Games r1, Games r2) {
						
						return r1.getRD().compareTo(r2.getRD());
					}				
				});
				
				Collections.reverse(gameList);	
				
				return printGame(2);
			}
	
	/* Method: getTopRated
	 *  Sorts gameList by rating with highest rated at the front of the list and lowest towards the back.
	 *  Call is made to printgame() after the sort.
	 */
			public static JPanel getTopRated(){
				
				Collections.sort(gameList, new Comparator<Games>(){
					@Override
					public int compare(Games r1, Games r2) {
						
						return Float.compare(r1.getRating(),r2.getRating());
					}				
				});
				
				Collections.reverse(gameList);
				
				return printGame(3);
			}
	
	/* Method: printGame
	 *  Takes in an integer(1 - 3) which signifies which tab it is printing to.
	 *  (1 denotes All tab, 2 denotes newReleases tab, 3 denotes topRated tab)
	 *  
	 *  The method creates a Panel and fills it with games based on the current active filters. 
	 */
			public static JPanel printGame(int tab){
				
				JPanel pane = new JPanel(new GridLayout(0,1));
			
			// This count is used to break the loop since the program calls for only 3 of a category to be displayed
			// The count is increased whenever a game is added to the toprated tab or the newreleases tab	
				int count = 0;
				
				for(int i = 0; i < gameList.size(); i ++){
									
					if(count > 2)
						break;
										
					//If the current game in the gameList has a genre which matches that of the current selected on the drop down menu,
					// or if the currently selected genre on the drop down menu is "all" it will run this if statement.
						if(gameList.get(i).getGenre().contains(UtilityBar.gselect) || (UtilityBar.gselect.equals("All"))){
							
							
							//If the checkbox for multiplayer is checked.
								if(UtilityBar.mcheck){
								
								//If the current game has multiplayer this runs
									if(gameList.get(i).getMultiplayer()){
										
									//If this call was made for the toprated tab, it checks to make sure
									//the game rating is greater than zero to be added.
										if(tab == 3){	
											if(gameList.get(i).getRating() > 0){												
													pane.add(new DisplayGame(gameList.get(i)));
													count++;
											}
										}
									
									//The call was made from newreleases tab, game is added and count is increased.
										else if (tab == 2){
												pane.add(new DisplayGame(gameList.get(i)));
												count++;
										}
									
									//The call was made from allgames tab, the game is added.	
										else	
											pane.add(new DisplayGame(gameList.get(i)));
									}
								}
								
							//Same as above, just without the multiplayer flag so all games (single player and multiplayer) will be added.							
								else{									
									if(tab == 3){	
										if(gameList.get(i).getRating() > 0){
											pane.add(new DisplayGame(gameList.get(i)));										
											count++;
										}
									}
									
									else if(tab == 2){
										pane.add(new DisplayGame(gameList.get(i)));
										count++;
									}
									
									else
										pane.add(new DisplayGame(gameList.get(i)));
								}
					}						
				}
			
			//If no games match the selected filter this is added.
				if(pane.getComponentCount() == 0)
					pane.add(new JLabel("There are no games that match your criteria."));
				
			//The pane is added to another panel to prevent stretching.
				JPanel pane2 = new JPanel(new BorderLayout());		   
					   pane2.add(pane, BorderLayout.NORTH);
					  					   
				return pane2;
			}
	
	/* Method : update
	 *  Method is called whenever a filter is added, removed, or changed. It removes the scrollpane which
	 *  had all of the game information, creates a new one, and updates the tab. When a filter is changed,
	 *  all three of the panels are updated.
	 */
			public static void update(){
				
				allGames.remove(scroll1);
				scroll1 = new JScrollPane(getAll());
				scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll1.getVerticalScrollBar().setUnitIncrement(16);
				allGames.add(scroll1);			
				allGames.revalidate();		
				allGames.repaint();
			
				newReleases.remove(scroll2);
				scroll2 = new JScrollPane(getNewReleases());
				scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll2.getVerticalScrollBar().setUnitIncrement(16);
				newReleases.add(scroll2);			
				newReleases.revalidate();		
				newReleases.repaint();

				rating.remove(scroll3);
				scroll3 = new JScrollPane(getTopRated());
				scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll3.getVerticalScrollBar().setUnitIncrement(16);
				rating.add(scroll3);			
				rating.revalidate();		
				rating.repaint();

		
		}
}
