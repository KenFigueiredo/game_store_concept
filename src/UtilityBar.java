
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UtilityBar extends JPanel {

	//gselect holds the current drop down menu option.
	public static String gselect = "All";
	
	//mcheck holds the current status of the checkbox.
	public static boolean mcheck = false;

// The bottom bar is initialized.
	UtilityBar(){

			this.setLayout(new FlowLayout());
		
			JLabel glabel = new JLabel("Select Genre: ");
			this.add(glabel);
		
			int size = Categories.gameList.size();
			JComboBox<String> jbox = createJbox(size);
			this.add(jbox);
						
			JCheckBox mbox = createCbox();
			this.add(mbox);		
	}

/* Method : getGenres()
 * Iterates through the filled gamelist and pulls genres from the game.
 * The dropdown menu is filled with the string list of genre when this is finished.
 * This method allows for a game that doesn't have one of the default genre's
 * [RPG, indie, casual, fps, action] to be categorized as well.
 */
	public String[] getGenres(int n) {
		
		ArrayList<String> list = new ArrayList<String>();
			list.add("All");
			
			for(int i = 0; i < n; i++){
				String[] temp = Categories.gameList.get(i).getGenre().split("/");
								
				for(int j = 0; j < temp.length; j++){
					temp[j] = temp[j].trim();
				}
								
				for(int j = 0; j < temp.length; j++){					
					if(!list.contains(temp[j]))
						list.add(temp[j]);
				}		
			}
			
			String[] temp2 = new String[list.size()];
			temp2 = list.toArray(temp2);	
		return temp2;
	}

/* Method : createJBox
 * This method creates the JComboBox used in the utility bar.
 * The itemListener is added here, which allows for an update
 * whenever the dropdown choice is altered.s 
 */		
	public JComboBox<String> createJbox(int n){
		
		final JComboBox<String> jbox = new JComboBox<String>(getGenres(n));
		jbox.setPreferredSize(new Dimension(200,30));
		
			jbox.addItemListener(new ItemListener(){

				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange() == ItemEvent.SELECTED){
							gselect = jbox.getSelectedItem().toString();
							Categories.update();														
					}
								
				}
						
			}
				
		);
		return jbox;
	}

/* Method : createCbox
 * Method creates the check box used for the multiplayer flag.
 * It updates whenever selected or deselected by changing the state of
 * mcheck and updating the tabs in categories.
 * 
 */
	public JCheckBox createCbox(){
		
		final JCheckBox mbox = new JCheckBox("Show Multiplayer Games Only");
		
			mbox.addItemListener(new ItemListener(){

				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange() == ItemEvent.SELECTED){
						mcheck = true;
						Categories.update();
					}
					
					if(e.getStateChange() == ItemEvent.DESELECTED){
						mcheck = false;
						Categories.update();
					}
				}
					
			});
		
		
		return mbox;
		
	}
}
