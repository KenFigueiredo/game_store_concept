
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/* Class: Frame
 * Window is created here and instances of Categories.class & UtilityBar.class are added to the window.
 * The window properties are also set to default here.
 */

public class Frame {
	
	
	public Frame() throws IOException{
		
		JFrame frame = new JFrame();
		
			frame.setLayout(new BorderLayout());
			
			frame.add(new Categories(), BorderLayout.CENTER);
			frame.add(new UtilityBar(), BorderLayout.PAGE_END);
			
				frame.setTitle("Xbawks Game Store");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800,600);
				frame.setResizable(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
	}
	
	// This function changes the default look of the GUI to the look of the currently running system.
		public static void setLook(){
		    try {
	         
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
		    }catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		    }catch (ClassNotFoundException e) {
		       // handle exception
		    }catch (InstantiationException e) {
		       // handle exception
		    }catch (IllegalAccessException e) {
		       // handle exception
		    }
		}
		
		public static void main(String[] args) throws IOException{
			setLook();
			new Frame();
		}

}
