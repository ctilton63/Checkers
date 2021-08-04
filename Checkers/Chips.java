public class Chips {
    //SETUP
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //differentiate which chips belong to the player
    private boolean player;
    private boolean alive;
    private double xL;
    private double yL;

    //CONSTRUCTOR
    public Chips(double originalX, double originalY, boolean alive, boolean player) {
        this.player = player;
        this.xL = originalX;
        this.yL = originalY;
        this.alive = alive;
    }

    //get x location
    public double getX() {
        return this.xL;
    }

    //get y location
    public double getY() {
        return this.yL;
    }

    //get alive?
    public boolean alive() {
        return this.alive;
    }


    //draw a chip
    public void draw() {
        StdDraw.setPenColor(StdDraw.WHITE);
        if (alive && this.player) {
            StdDraw.filledCircle((xL * 50 / 500) + 25 / 500.0, (yL * 50 / 500) + 25 / 500.0, (23 / 500.0));
        } else if (alive && !this.player) {
            StdDraw.circle((xL * 50 / 500) + 25 / 500.0, (yL * 50 / 500) + 25 / 500.0, (23 / 500.0));
        }
    }

    //COMPUTERS MOVE
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //mobile for comp tells the program if the randomly selected chip can move
    public boolean mobile(double[][] board) {
        //get x and y of selected chip
        double x = this.xL;
        double y = this.yL;
        //booleans to keep track of which possible new locations for the chips are still on teh board
        boolean validUpX = true;
        boolean validDownX = true;
        boolean validUpY = true;
        boolean validDownY = true;

        int upX = (int) x + 1;
        if (upX == 10) {
            validUpX = false;
            upX = (int) (x);
        }
        int downX = (int) (x - 1);
        if (downX < 0) {
            validDownX = false;
            downX = (int) (x);
        }
        int upY = (int) (y + 1);
        if (upY == 10) {
            validUpY = false;
            upY = (int) (y);
        }
        int downY = (int) (y - 1);
        if (downY < 0) {
            validDownY = false;
            downY = (int) (y);
        }
        boolean validUp2X = true;
        boolean validDown2X = true;
        boolean validUp2Y = true;
        boolean validDown2Y = true;

        int up2X = (int) x + 2;
        if (up2X >= 10) {
            validUp2X = false;
            up2X = (int) (x);
        }
        int down2X = (int) (x - 2);
        if (down2X < 0) {
            validDown2X = false;
            down2X = (int) (x);
        }
        int up2Y = (int) (y + 2);
        if (up2Y >= 10) {
            validUp2Y = false;
            up2Y = (int) (y);
        }
        int down2Y = (int) (y - 2);
        if (down2Y < 0) {
            validDown2Y = false;
            down2Y = (int) (y);
        }
        //if the new option is off the board, return false
        if (board[upX][upY] == 0 && validUpX && validUpY) {
            return true;
        } else if (board[upX][downY] == 0 && validUpX && validDownY) {
            return true;
        } else if (board[downX][upY] == 0 && validDownX && validUpY) {
            return true;
        } else if (board[downX][downY] == 0 && validDownX && validDownY) {
            return true;
        } else if (board[up2X][up2Y] == 0 && validUp2X && validUp2Y && board[upX][upY] == 1) {
            return true;
        } else if (board[up2X][down2Y] == 0 && validUp2X && validDown2Y && board[upX][downY] == 1) {
            return true;
        } else if (board[down2X][up2Y] == 0 && validDown2X && validUp2Y && board[downX][upY] == 1) {
            return true;
        } else if (board[down2X][down2Y] == 0 && validDown2X && validDown2Y && board[downX][downY] == 1) {
            return true;
        } else {
            return false;
        }
    }

    //method for comp options (a simpler way to figure out where the computer can move and hopefully randomly generates a move from that
    public void compOptions(double[][] board, Chips[] player, Chips[] comp) {
        //randomly choose a number between 1 and 4, and if the chip can move to that new square, move it
        double x = this.xL;
        double y = this.yL;

        //need boolean variables that tell if certain movements are valid (inside or outside of the board)
        boolean validUpX = true;
        boolean validDownX = true;
        boolean validUpY = true;
        boolean validDownY = true;

        int upX = (int) (x + 1);
        if (upX == 10) {
            validUpX = false;
            upX = (int) (x);

        }
        int downX = (int) (x - 1);
        if (downX < 0) {
            validDownX = false;
            downX = (int) (x);
        }
        int upY = (int) (y + 1);
        if (upY == 10) {
            validUpY = false;
            upY = (int) (y);
        }
        int downY = (int) (y - 1);
        if (downY < 0) {
            validDownY = false;
            downY = (int) (y);
        }

        boolean validUp2X = true;
        boolean validDown2X = true;
        boolean validUp2Y = true;
        boolean validDown2Y = true;
        boolean canSkip2Xup2Yup = false;
        boolean canSkip2Xup2Ydown = false;
        boolean canSkip2Xdown2Yup = false;
        boolean canSkip2Xdown2Ydown = false;


        int up2X = (int) x + 2;
        if (up2X >= 10) {
            validUp2X = false;
            up2X = (int) (x);
        }
        int down2X = (int) (x - 2);
        if (down2X < 0) {
            validDown2X = false;
            down2X = (int) (x);
        }
        int up2Y = (int) (y + 2);
        if (up2Y >= 10) {
            validUp2Y = false;
            up2Y = (int) (y);
        }
        int down2Y = (int) (y - 2);
        if (down2Y < 0) {
            validDown2Y = false;
            down2Y = (int) (y);
        }
        //check if the chip can skip
        if (validUp2X && validUp2Y && board[(int) x + 1][(int) y + 1] == 1 && board [(int)x+2][(int)y+2]==0) {
            canSkip2Xup2Yup = true;
        }
        if (validUp2X && validDown2Y && board[(int) x + 1][(int) y - 1] == 1 && board [(int)x+2][(int)y-2]==0) {
            canSkip2Xup2Ydown = true;
        }
        if (validDown2X && validUp2Y && board[(int) x - 1][(int) y + 1] == 1 && board [(int)x-2][(int)y+2]==0) {
            canSkip2Xdown2Yup = true;
        }
        if (validDown2X && validDown2Y && board[(int) x - 1][(int) y - 1] == 1 && board [(int)x-2][(int)y-2]==0) {
            canSkip2Xdown2Ydown = true;
        }
        System.out.println (canSkip2Xup2Yup + " " + canSkip2Xup2Ydown + " " + canSkip2Xdown2Yup + " " + canSkip2Xdown2Ydown);
        System.out.println ( validUp2X + " " + " " + validUp2Y + " " + validDown2X + " " + validDown2Y );


        //want to run a while loop until a random number is selected out of the 4 different possible moves and CAN move
        int run = 0;
        int compMove = (int) (Math.random() * 4);

        if (canSkip2Xup2Yup || canSkip2Xup2Ydown || canSkip2Xdown2Yup || canSkip2Xdown2Ydown) {
            while (run == 0) {
                if (canSkip2Xup2Yup && compMove == 0 && validUp2X && validUp2Y && board[up2X][up2Y] == 0 && board[(int) x + 1][(int) y + 1] == 1) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    //if the computer skips over the players chip, it will die
                    //so want to go over players array of chips to see if we skip one
                    for (int a = 0; a < 10; a++) {
                        if (player[a].xL == (x + 1) && player[a].yL == (y + 1)) {
                            player[a].dieChip(player, comp, board);
                            board[(int) x + 1][(int) y + 1] = 0;
                        }
                    }
                    this.xL = up2X;
                    this.yL = up2Y;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else if (canSkip2Xup2Ydown && compMove == 1 && validDown2Y && validUp2X && board[up2X][down2Y] == 0 && board[(int) x + 1][(int) y - 1] == 1) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    //if the computer skips over the players chip, it will die
                    //so want to go over players array of chips to see if we skip one
                    for (int a = 0; a < 10; a++) {
                        if (player[a].xL == (x + 1) && player[a].yL == (y - 1)) {
                            player[a].dieChip(player, comp, board);
                            board[(int) x + 1][(int) y - 1] = 0;
                        }
                    }
                    this.xL = up2X;
                    this.yL = down2Y;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else if (canSkip2Xdown2Yup && compMove == 2 && validUp2Y && validDown2X && board[down2X][up2Y] == 0 && board[(int) x - 1][(int) y + 1] == 1) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    //if the computer skips over the players chip, it will die
                    //so want to go over players array of chips to see if we skip one
                    for (int a = 0; a < 10; a++) {
                        if (player[a].xL == (x - 1) && player[a].yL == (y + 1)) {
                            player[a].dieChip(player, comp, board);
                            board[(int) x - 1][(int) y + 1] = 0;
                        }
                    }
                    this.xL = down2X;
                    this.yL = up2Y;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else if (canSkip2Xdown2Ydown && compMove == 3 && validDown2Y && validDown2X && board[down2X][down2Y] == 0 && board[(int) x - 1][(int) y - 1] == 1) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    //if the computer skips over the players chip, it will die
                    //so want to go over players array of chips to see if we skip one
                    for (int a = 0; a < 10; a++) {
                        if (player[a].xL == (x - 1) && player[a].yL == (y - 1)) {
                            player[a].dieChip(player, comp, board);
                            board[(int) x - 1][(int) y - 1] = 0;
                        }
                    }
                    this.xL = down2X;
                    this.yL = down2Y;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else {
                    compMove = (int) (Math.random() * 4);
                }
            }
        } else {
            run =0;
            while (run == 0) {
                System.out.println(upX + " " + upY + " " + downY + " " + downX);
                compMove = (int) (Math.random() * 4);
                if (compMove == 0 && validUpX && validUpY && board[upX][upY] == 0) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    this.xL = upX;
                    this.yL = upY;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else if (compMove == 1 && validDownY && validUpX && board[upX][downY] == 0) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    this.xL = upX;
                    this.yL = downY;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else if (compMove == 2 && validUpY && validDownX && board[downX][upY] == 0) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    this.xL = downX;
                    this.yL = upY;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else if (compMove == 3 && validDownY && validDownX && board[downX][downY] == 0) {
                    board[(int) this.xL][(int) this.yL] = 0;
                    this.xL = downX;
                    this.yL = downY;
                    board[(int) this.xL][(int) this.yL] = 1;
                    run += 1;
                } else {
                    compMove = (int) (Math.random() * 4);
                }
            }


        }

        //want to redraw all the chips
        //want to redraw board and chips
//draw the squares in checkered pattern
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        for(int z = 0; z<10; z++) {
            for (int w = 0; w < 10; w++) {
                if( z%2==0 && w%2==0 || (z%2!=0 && w%2!=0)) {
                    StdDraw.filledSquare((50 * z + 25)/500.0, (50 * w + 25)/500.0, 1.0/20);
                }
            }
        }
        for (int z = 0; z < 10; z++) {
            player[z].draw();
            comp[z].draw();
        }

    }


//PLAYERS MOVE
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //method displaying movement options
    public void options(Chips [] player, Chips [] opp, double [][] board){
        //draw four possible options for moves

        //get x and y of selected chip
        double x = this.xL;
        double y = this.yL;

        //check up 1 and over to the left (ul stands for up left)
        //the int ul and similar ints below keep track of how many chips are NOT on the square in question
        //i.e. if there are no chips on the square that is in question, ul =20 because all 20 chips are not on that square
        StdDraw.setPenColor(StdDraw.GREEN);
        //check up and to the right
        if((x+1)<10 && (y+1)<10) {
            if (board[(int) x + 1][(int) y + 1] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) + .15), (y * (50 / 500.0) + .15), (25 / 500.0));
            }
        }
        //check up and to the left
        if((x-1)>=0 && (y+1)<10) {
            if (board[(int) x - 1][(int) y + 1] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) - .05), (y * (50 / 500.0) + .15), (25 / 500.0));
            }
        }
        //check down and to the right
        if((x+1)<10 && (y-1)>=0) {
            if (board[(int) x + 1][(int) y - 1] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) + .15), (y * (50 / 500.0) - .05), (25 / 500.0));
            }
        }
        //check down and to the left
        if((x-1)>=0 && (y-1)>=0) {
            if (board[(int) x - 1][(int) y - 1] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) - .05), (y * (50 / 500.0) - .05), (25 / 500.0));
            }
        }
        //want to check up 2 and to the right 2
        //check up and to the right
        if((x+2)<10 && (y+2)<10 && (board[(int) x + 1][(int) y + 1] == 1)) {
            if (board[(int) x + 2][(int) y + 2] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) + .25), (y * (50 / 500.0) + .25), (25 / 500.0));
            }
        }
        //check up 2 and to the left 2
        if((x-2)>=0 && (y+2)<10 && (board[(int) x - 1][(int) y + 1] == 1)) {
            if (board[(int) x - 2][(int) y + 2] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) - .15), (y * (50 / 500.0) + .25), (25 / 500.0));
            }
        }
        //check down 2 and to the right 2
        if((x+2)<10 && (y-2)>=0 && (board[(int) x + 1][(int) y - 1] == 1)) {
            if (board[(int) x + 2][(int) y - 2] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) + .25), (y * (50 / 500.0) - .15), (25 / 500.0));
            }
        }
        //check down 2 and to the left 2
        if((x-2)>=0 && (y-2)>=0 && (board[(int) x - 1][(int) y - 1] == 1) ) {
            if (board[(int) x - 2][(int) y - 2] == 0) {
                StdDraw.filledSquare((x * (50 / 500.0) - .15), (y * (50 / 500.0) - .15), (25 / 500.0));
            }
        }
    }

    //method for making 1 move
    public void move(Chips[] player, Chips[] comp, double [][] board) {
        while (true) {
            //check if mouse is pressed
            while (!StdDraw.isMousePressed()) {
            }
            double mouseX = StdDraw.mouseX();
            double mouseY = StdDraw.mouseY();
            while (StdDraw.isMousePressed()) {

            }
            //assign variables to locate the click of the mouse

            int x = (int) this.getX();
            int y = (int) this.getY();
            double oldX = this.xL;
            double oldY = this.yL;
            board[x][y] = 0;
            //want to check which option was clicked on by running through the possible options
            //check up and to the left

            if (((x * 50 / 500.0) - .1) <= mouseX && mouseX <= (x * 50 / 500.0) && (y * 50 / 500.0 + .1) <= mouseY && mouseY <= (50 / 500.0 * y + .2)) {
                board[(int) this.xL][(int) this.yL] = 0;
                this.xL = x - 1;
                this.yL = y + 1;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
            //want to check up and to the right
            else if (((x * 50 / 500.0 + .1) <= mouseX && mouseX <= (x * 50 / 500.0) + .2 && (y * 50 / 500.0 + .1) <= mouseY && mouseY <= (y * 50 / 500.0 + .2))) {
                board[(int) this.xL][(int) this.yL] = 0;
                this.xL = x + 1;
                this.yL = y + 1;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
            //want to check down and to the left
            else if (((x * 50 / 500.0) - .1 <= mouseX && mouseX <= (x * 50 / 500.0) && (y * 50 / 500.0 - .1) <= mouseY && mouseY <= (y * 50 / 500.0))) {
                board[(int) this.xL][(int) this.yL] = 0;
                this.xL = x - 1;
                this.yL = y - 1;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
            //want to check down and to the right
            else if ((x * 50 / 500.0 + .1 <= mouseX && mouseX <= x * 50 / 500.0 + .2 && (y * 50 / 500.0 - .1) <= mouseY && mouseY <= (y * 50 / 500.0))) {
                board[(int) this.xL][(int) this.yL] = 0;
                this.xL = x + 1;
                this.yL = y - 1;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }

            //check up 2 and to the left 2
            else if (((x * 50 / 500.0) - .2) <= mouseX && mouseX <= (x * 50 / 500.0) - .1 && (y * 50 / 500.0 + .2) <= mouseY && mouseY <= (50 / 500.0 * y + .3)) {
                board[(int) this.xL][(int) this.yL] = 0;
                //check if skipping over the opp chips
                //run through array to see if the opponents chips are on the square in the middle
                for (int a = 0; a < 10; a++) {
                    if (comp[a].xL == x - 1 && comp[a].yL == y + 1) {
                        comp[a].dieChip(player, comp, board);
                        board[x - 1][y + 1] = 0;
                    }
                }
                this.xL = x - 2;
                this.yL = y + 2;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
            //want to check up 2 and to the right 2
            else if (((x * 50 / 500.0 + .2) <= mouseX && mouseX <= (x * 50 / 500.0) + .3 && (y * 50 / 500.0 + .2) <= mouseY && mouseY <= (y * 50 / 500.0 + .3))) {
                board[(int) this.xL][(int) this.yL] = 0;
                //check if skipping over the opp chips
                //run through array to see if the opponents chips are on the square in the middle
                for (int a = 0; a < 10; a++) {
                    if (comp[a].xL == x + 1 && comp[a].yL == y + 1) {
                        comp[a].dieChip(player, comp, board);
                        board[x + 1][y + 1] = 0;
                    }
                }
                this.xL = x + 2;
                this.yL = y + 2;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
            //want to check down 2 and to the left 2
            else if (((x * 50 / 500.0) - .2 <= mouseX && mouseX <= (x * 50 / 500.0) - .1 && (y * 50 / 500.0 - .2) <= mouseY && mouseY <= (y * 50 / 500.0) - .1)) {
                board[(int) this.xL][(int) this.yL] = 0;
                //check if skipping over the opp chips
                //run through array to see if the opponents chips are on the square in the middle
                for (int a = 0; a < 10; a++) {
                    if (comp[a].xL == x - 1 && comp[a].yL == y - 1) {
                        comp[a].dieChip(player, comp, board);
                        board[x - 1][y - 1] = 0;
                    }
                }
                this.xL = x - 2;
                this.yL = y - 2;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
            //want to check down and to the right
            else if ((x * 50 / 500.0 + .2 <= mouseX && mouseX <= x * 50 / 500.0 + .3 && (y * 50 / 500.0 - .2) <= mouseY && mouseY <= (y * 50 / 500.0) - .1)) {
                board[(int) this.xL][(int) this.yL] = 0;
                //check if skipping over the opp chips
                //run through array to see if the opponents chips are on the square in the middle
                for (int a = 0; a < 10; a++) {
                    if (comp[a].xL == x + 1 && comp[a].yL == y - 1) {
                        comp[a].dieChip(player, comp, board);
                        board[x + 1][y - 1] = 0;
                    }
                }
                this.xL = x + 2;
                this.yL = y - 2;
                board[(int) this.xL][(int) this.yL] = 1;
                break;
            }
        }

            //want to redraw board and chips
//draw the squares in checkered pattern
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            for (int z = 0; z < 10; z++) {
                for (int w = 0; w < 10; w++) {
                    if (z % 2 == 0 && w % 2 == 0 || (z % 2 != 0 && w % 2 != 0)) {
                        StdDraw.filledSquare((50 * z + 25) / 500.0, (50 * w + 25) / 500.0, 1.0 / 20);
                    }
                }
            }
            for (int z = 0; z < 10; z++) {
                player[z].draw();
                comp[z].draw();
            }


    }
    public void dieChip(Chips [] player, Chips [] comp, double [][] board ){
        this.alive = false;
    }
}
