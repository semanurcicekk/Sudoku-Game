

	import java.util.*;

	public class sudokuu
	{
		int[] table[];
		int missingDigits; 
		String answerToChange = "";
		int cont = 0;

		Scanner scan = new Scanner(System.in);

		
		sudokuu(int missingDigits){
			this.missingDigits = missingDigits;
			table = new int[9][9];
		}

		
		public void fillValues(){
			fillDiagonal();

			fillRemaining(0, 3);

			removemissingDigits();
		}

		
		void fillDiagonal(){
			for (int i = 0; i<9; i=i+3)
				fillBox(i, i);
		}

		
		
		
		void fillBox(int row,int col){
			int num;
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					do{
						num = random(9);
					}while (!checkForBox(row, col, num));

					table[row+i][col+j] = num;
				}
			}
		}

		
		int random(int num){
			return (int) Math.floor((Math.random()*num+1));
		}

		
		boolean CheckIfSafe(int i,int j,int num){
			return (checkForRow(i, num) && checkForCol(j, num) && checkForBox(i-i%3, j-j%3, num));
		}

		
	    boolean checkForBox(int rowStart, int colStart, int num){
			for (int i = 0; i<3; i++)
				for (int j = 0; j<3; j++)
					if (table[rowStart+i][colStart+j]==num)
						return false;

			return true;
		}

		boolean checkForRow(int i,int num){
			for (int j = 0; j<9; j++)
			if (table[i][j] == num){
				return false;
			}
					
			return true;
		}

		
		boolean checkForCol(int j,int num){
			for (int i = 0; i<9; i++)
				if (table[i][j] == num){
					return false;
				}

		return true;
	}

	boolean fillRemaining(int i, int j){
		if (j>=9 && i<8){
			i = i + 1;
			j = 0;
		}
		if (i>=9 && j>=9){
			return true;
		}
		if (i < 3){
			if (j < 3){
				j = 3;
			}
				
		}
		else if (i < 6){
			if (j==(int)(i/3)*3){
				j = j + 3;
			}	
		}
		else{
			if (j == 6){
				i = i + 1;
				j = 0;
				if (i>=9){
					return true;
				}
			}
		}

		for (int num = 1; num<=9; num++){
			if (CheckIfSafe(i, j, num)){
				table[i][j] = num;
				if (fillRemaining(i, j+1)){
					return true;
				}
				table[i][j] = 0;
				
			}
		}
		return false;
	}

	
	public void removemissingDigits(){
		int count = missingDigits;
		while (count != 0){
			int cellId = random(81)-1;
	
			int i = (cellId/9);
			int j = cellId%9;
			if (j != 0){
				j = j - 1;
			}
	
			if (table[i][j] != 0){
				count--;
				table[i][j] = 0;
			}
		}
	}

	
	public void printTable(){
		for (int i = 0; i<9; i++){
			for (int j = 0; j<9; j++){
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void change() 
	{
		do{
			cont = 0;
			System.out.println("Do you want to change?(Type yes or no)");
			answerToChange = scan.nextLine();

			if(answerToChange.equalsIgnoreCase("yes")){
				System.out.println("Please enter row number of point:");
				int changingRows = scan.nextInt();

				if(changingRows >9){
					System.out.println("There is only 9 rows! \n Please enter row number of point: (BETWEEN 1 TO 9) ");
					changingRows = scan.nextInt();
				}
				else{
					System.out.println("Please enter column number of point: ");
					int changingColumns = scan.nextInt();

					if(changingColumns >9){
						System.out.println("There is only 9 columns! \n Please enter columns number of point: (BETWEEN 1 TO 9) ");
						changingColumns = scan.nextInt();
					}
					else{
						if(table[changingRows-1][changingColumns-1] == 0){
							System.out.println("Please enter the number that you want to change with:");
							int numberToChange = scan.nextInt();

							if(numberToChange <=9 && numberToChange >=1){
								table[changingRows-1][changingColumns-1] = numberToChange;
							}
							else{System.out.println("You cannot use this number in this Sudoku. You can only use 1 to 9.");}
						}

						else{System.out.println("This point has already given to you and you cannot change it.");}

						for(int i = 0; i<9;i++){
							for(int j = 0; j<9;j++){
								if(table[i][j] == 0 ){
									cont++;
								}
							}
						}
						printTable();
						answerToChange = scan.nextLine();
					}
				}
			}
			else{
				System.out.println( "IT IS TIME TO EXIT THEN");
				System.exit(0);
			}
		}while(answerToChange.equalsIgnoreCase("yes") || cont != 0);
	}

	public void control(){
		//yatay duzlemde hata kontrolu
		int countFor1 = 0,countFor2 = 0,countFor3 = 0, countFor4 = 0, countFor5 = 0, countFor6 = 0, countFor7 = 0, countFor8 = 0, countFor9 = 0, countForRow = 0, win = 0, mistake = 0;

		for(int i = 0; i<9;i++){
			for(int j = 0; j<9;j++){
				switch (table[i][j]){
					case 1:
						countFor1++;
						break;
					case 2:
						countFor2++;
						break;
					case 3:
						countFor3++;
						break;
					case 4:
						countFor4++;
						break;
					case 5:
						countFor5++;
						break;
					case 6:
						countFor6++;
						break;
					case 7:
						countFor7++;
						break;
					case 8:
						countFor8++;
						break;
					case 9:
						countFor9++;
						break;
				}
			}
				if (countFor1 == i+1 && countFor2 == i+1 && countFor3 == i+1 && countFor4 == i+1 && countFor5 == i+1 && countFor6 == i+1 && countFor7 == i+1 && countFor8 == i+1 && countFor9 == i+1){
					countForRow++;
				}
		}

			if (countFor1 == 9 && countFor2 == 9 && countFor3 == 9 && countFor4 == 9 && countFor5 == 9 && countFor6 == 9 && countFor7 == 9 && countFor8 == 9 && countFor9 == 9 && countForRow == 9){
				win++;
			}
			else{
				mistake += Math.abs(9 - countFor1);
				mistake += Math.abs(9 - countFor2);
				mistake += Math.abs(9 - countFor3);
				mistake += Math.abs(9 - countFor4);
				mistake += Math.abs(9 - countFor5);
				mistake += Math.abs(9 - countFor6);
				mistake += Math.abs(9 - countFor7);
				mistake += Math.abs(9 - countFor8);
				mistake += Math.abs(9 - countFor9);
			}

		//uclu alanlarda hata kontrolu
		int countFor21 = 0,countFor22 = 0,countFor23 = 0, countFor24 = 0, countFor25 = 0, countFor26 = 0, countFor27 = 0, countFor28 = 0, countFor29 = 0, countForThree = 0;

			for(int count = 0; count < 3; count++ ){
				for(int i = count*3; i<((count+1)*3);i++){
					for(int j = count*3; j<((count+1)*3);j++){
						switch (table[i][j]){
							case 1:
								countFor21++;
								break;
							case 2:
								countFor22++;
								break;
							case 3:
								countFor23++;
								break;
							case 4:
								countFor24++;
								break;
							case 5:
								countFor25++;
								break;
							case 6:
								countFor26++;
								break;
							case 7:
								countFor27++;
								break;
							case 8:
								countFor28++;
								break;
							case 9:
								countFor29++;
								break;
						}
					}
					if (countFor21 == i+1 && countFor22 == i+1 && countFor23 == i+1 && countFor24 == i+1 && countFor25 == i+1 && countFor26 == i+1 && countFor27 == i+1 && countFor28 == i+1 && countFor29 == i+1){
						countForThree++;
					}
				}
			}
			if (countFor21 == 9 && countFor22 == 9 && countFor23 == 9 && countFor24 == 9 && countFor25 == 9 && countFor26 == 9 && countFor27 == 9 && countFor28 == 9 && countFor29 == 9 && countForThree == 9){
				win++;
			}

		//dikey duzlemde hata kontrolu
		int countFor31 = 0,countFor32 = 0,countFor33 = 0, countFor34 = 0, countFor35 = 0, countFor36 = 0, countFor37 = 0, countFor38 = 0, countFor39 = 0, countForColumn = 0;

		for(int i = 0; i<9;i++){
			for(int j = 0; j<9;j++){
				switch (table[j][i]){
					case 1:
						countFor31++;
						break;
					case 2:
						countFor32++;
						break;
					case 3:
						countFor33++;
						break;
					case 4:
						countFor34++;
						break;
					case 5:
						countFor35++;
						break;
					case 6:
						countFor36++;
						break;
					case 7:
						countFor37++;
						break;
					case 8:
						countFor38++;
						break;
					case 9:
						countFor39++;
						break;
				}
			}
				if (countFor31 == i+1 && countFor32 == i+1 && countFor33 == i+1 && countFor34 == i+1 && countFor35 == i+1 && countFor36 == i+1 && countFor37 == i+1 && countFor38 == i+1 && countFor39 == i+1){
					countForColumn++;
				}
		}
			if (countFor31 == 9 && countFor32 == 9 && countFor33 == 9 && countFor34 == 9 && countFor35 == 9 && countFor36 == 9 && countFor37 == 9 && countFor38 == 9 && countFor39 == 9 && countForColumn == 9){
				win++;
			}
			if(win == 2){
				System.out.println("YOU WON THE GAME! \n CONGRATULATIONS!");
				System.exit(0);
			}
			else{
				System.out.println("GAME OVER! \n NUMBER OF MISTAKE: " + mistake);
				System.exit(0);
			}
	}

	public static void main(String[] args){
        System.out.println("            HOW TO PLAY:");
        System.out.println("First pick the difficulty level. 1, 2 or 3.");
        System.out.println("Then type yes before every changing in the mat in order to continue to the game.");
        System.out.println("This game is 9x9. So in every rows and columns, there has to be numbers 1 to 9 and one number will not repeat again");
        System.out.println("Also there is nine little 3x3 boxes in the mat. In that boxes, numbers will not repeat as same as the rows and columns");
		System.out.println("The ones which writes 0 can be change and others cannot");
        int missingDigits = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("CHOSE DIFFICULTY: 1 FOR EASY, 2 FOR MEDIUM, 3 FOR HARD");
		int dif = scanner.nextInt();
		if(dif == 1){missingDigits = 3;}
		else if(dif == 2){missingDigits = 18;}
		else if(dif == 3){missingDigits = 25;}
		else{System.out.println("Please restart the game and enter 1, 2 or 3");System.exit(0);}
		
		sudokuu sudoku = new sudokuu(missingDigits);
		
		sudoku.fillValues();
		sudoku.printTable();
		sudoku.change();
		sudoku.control();
	}
}