package gameOfLife;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * TODO Put here a description of what this class does.
 *
 * @author schneimd.
 *         Created Oct 28, 2013.
 */
public class Cell {
	
	private boolean alive;
	private int row, col;
	
	/**
	 * Builds a cell for a given point with alive or dead.
	 *
	 * @param alive
	 * @param r
	 * @param c
	 */
	public Cell(String alive, int r, int c) {
		this.alive = alive.equals("1") ? true : false;
		this.row = r;
		this.col = c;
	}
	
	/**
	 * Used to clone a cell.
	 *
	 * @param alive
	 * @param r
	 * @param c
	 */
	public Cell(boolean alive, int r, int c) {
		this.alive = alive;
		this.row = r;
		this.col = c;
	}

	/**
	 * Draws the new cell based on it's surrounding cells.
	 *
	 * @param g
	 * @param neighbors
	 */
	public void drawCell(Graphics2D g, Cell[][] neighbors) {
		int localCount = this.countNeighbors(neighbors);
		if(this.alive) {
			g.setColor(Color.BLACK);
			g.fillRect(this.col*16,this.row*16,16,16);
			if(localCount < 2) {
				this.alive = false;
			} else if(localCount < 4) {
				this.alive = true;
			} else {
				this.alive = false;
			}
		} else {
			if(localCount == 3) {
				this.alive = true;
			}
		}
	}
	
	private int countNeighbors(Cell[][] neighbors) {
		int count = 0;
		if(this.row > 0) {
			if(neighbors[this.row-1][this.col].alive) {
				count++;
			}
			if(this.col > 0) {
				if(neighbors[this.row-1][this.col-1].alive) {
					count++;
				}
			}
			if(this.col < 49) {
				if(neighbors[this.row-1][this.col+1].alive) {
					count++;
				}
			}
		}
		if(this.row < 49) {
			if(neighbors[this.row+1][this.col].alive) {
				count++;
			}
			if(this.col > 0) {
				if(neighbors[this.row+1][this.col-1].alive) {
					count++;
				}
			}
			if(this.col < 49) {
				if(neighbors[this.row+1][this.col+1].alive) {
					count++;
				}
			}
		}
		if(this.col > 0) {
			if(neighbors[this.row][this.col-1].alive) {
				count++;
			}
		}
		if(this.col < 49) {
			if(neighbors[this.row][this.col+1].alive) {
				count++;
			}
		}
		
		return count;
	}
	
	@Override
	public Cell clone() {
		return new Cell(this.alive, this.row, this.col);
		
	}
	
}
