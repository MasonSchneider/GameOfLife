package gameOfLife;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JComponent;


/**
 * Generates a game of life.
 *
 * @author schneimd.
 *         Created Oct 28, 2013.
 */
public class GameofLife extends JComponent{
	
	private Cell[][] cells;
	
	/**
	 * Constructs a game of life from the given file.
	 * @param file 
	 *
	 */
	public GameofLife(String file) {
		this.setPreferredSize(new Dimension(800,800));
		Scanner scanner = new Scanner(file);
		String[][] fileCells = this.parseFile(file);	
		
		this.cells = new Cell[50][50];
		for(int r = 0; r < 50; r++) {
			for(int c = 0; c < 50; c++) {
				this.cells[r][c] = new Cell(fileCells[r][c],r,c);
			}
		}
		
	}
	
	private String[][] parseFile(String file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(file));
		} catch (FileNotFoundException exception) {
			System.err.println("Could not find file");
			System.exit(1);
		}
		String[][] fileCells = new String[50][50];
		for(int r = 0; r < 50; r++) {
			String[] row = new String[50];
			if(scanner.hasNextLine()) {
				row = scanner.nextLine().split(" ");
			} else {
				break;
			}
			for(int c = 0; c < 50; c++) {
				fileCells[r][c] = row[c];
			}
		}
		scanner.close();
		return fileCells;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Fills component with the background color
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Cell[][] currCells = new Cell[50][50];
		for(int r = 0; r < 50; r++) {
			for(int c = 0; c < 50; c++) {
				currCells[r][c] = this.cells[r][c].clone();
			}
		}
		
		for(int r = 0; r < 50; r++) {
			for(int c = 0; c < 50; c++) {
				this.cells[r][c].drawCell(g2, currCells);
			}
		}
	}

}
