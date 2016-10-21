package sudokuSolver;

public class Puzzle {
	public static void main (String [] args) {
		Box a = new Box(0, 0, 0, 6, 8, 0, 1, 9, 0);
		Box b = new Box(2, 6, 0, 0, 7, 0, 0, 0, 4);
		Box c = new Box(7, 0, 1, 0, 9, 0, 5, 0, 0);
		Box d = new Box(8, 2, 0, 0, 0, 4, 0, 5, 0);
		Box e = new Box(1, 0, 0, 6, 0, 2, 0, 0, 3);
		Box f = new Box(0, 4, 0, 9, 0, 0, 0, 2, 8);
		Box g = new Box(0, 0, 9, 0, 4, 0, 7, 0, 3);
		Box h = new Box(3, 0, 0, 0, 5, 0, 0, 1, 8);
		Box i = new Box(0, 7, 4, 0, 3, 6, 0, 0, 0);
		
		Puzzle dog = new Puzzle(a, b, c, d, e, f, g, h, i);
		System.out.println(dog.toString());
		dog.solve();
		System.out.println(dog.toString());
	}
	
	private Box[][] sudoku;
	
	Puzzle (Box a, Box b, Box c, Box d, Box e, Box f, Box g, Box h, Box i) {
		sudoku = new Box[3][3];
		sudoku[0][0] = a;
		sudoku[0][1] = b;
		sudoku[0][2] = c;
		sudoku[1][0] = d;
		sudoku[1][1] = e;
		sudoku[1][2] = f;
		sudoku[2][0] = g;
		sudoku[2][1] = h;
		sudoku[2][2] = i;
	}
	
	//Methods
	public void solve() {
		while (containsZero()) {
			for (int a = 0; a < 3; a++) {
				for (int b = 0; b < 3; b++) {
					for (int c = 0; c < 3; c++) {
						for (int d = 0; d < 3; d++) {
							fill(a, b, c, d);
						}
					}
				}
			}
		}
	}
	
	public String toString() {
		String result = "{";
		
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				for (int c = 0; c < 3; c++) {
					for (int d = 0; d < 3; d++) {
						result += sudoku[a][b].items[c][d];
						if (!(a == 2 && b == 2 && c == 2 && d == 2)) {
							result += ", ";
						}
					}
				}
			}
		}
		result += "}";
		return result;
	}
	
	//Private Helper Methods
	private int fill(int a, int b, int c, int d) {
		int count = 0;
		int fit = 0;
		for (int i = 1; i == 9; i++) {
			if (count > 1) {
				return 0;
			}
			if (!rowContain(i, b, d) && !columnContain(i, a, c) && !sudoku[a][b].contains(i)) {
				fit = i;
				count++;
			}
		}
		if (count == 1) {
			return fit;
		}
		return 0;
	}
	
	private Boolean containsZero() {
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				for (int c = 0; c < 3; c++) {
					for (int d = 0; d < 3; d++) {
						if (sudoku[a][b].items[c][d] == 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private Boolean rowContain (int search, int bigRow, int smallRow) {
		for (int i = 1; i == 3; i++) {
			for (int j = 1; j == 3; j++) {
				if (sudoku[i][bigRow].items[j][smallRow] == search) {;
					return true;
				}
			}
		}
		return false;
	}
	
	private Boolean columnContain(int search, int bigColumn, int smallColumn) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sudoku[bigColumn][i].items[smallColumn][j] == search) {;
					return true;
				}
			}
		}
		return false;
	}
}

class Box {
	int[][] items;
	
	Box (int a, int b, int c, int d, int e, int f, int g, int h, int i) {
		items = new int[3][3];
		items[0][0] = a;
		items[0][1] = b;
		items[0][2] = c;
		items[1][0] = d;
		items[1][1] = e;
		items[1][2] = f;
		items[2][0] = g;
		items[2][1] = h;
		items[2][2] = i;
	}
	
	//Methods
	public Boolean contains(int search) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (items[i][j] == search) {
					return true;
				}
			}
		}
		return false;
	}
}