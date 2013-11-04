package gameOfLife;
import javax.swing.JFrame;


/**
 * Runs the Game of Life.
 *
 * @author schneimd.
 *         Created Oct 28, 2013.
 */
public class Main {

	/**
	 * Start the game of life.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		String file = (args.length > 0) ? args[0] : "start.txt";
		
		JFrame frame = new JFrame();
		frame.setTitle("Game of Life");
		
		frame.add(new GameofLife(file));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		while(true) {
			frame.repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		
	}

}
