public class Project {
    public static void main(String[] args){
        //my program does not take any command line arguments, but the player will
        // be able to click on the chips to make them move
        //INSTRUCTIONS
        //click on one of the white chips to see the options for moving that chip
        //After the green squares appear, click on one of them, and the chip
        //will move to the desired location
        //want to print error message if the user does not input exactly two positive command line arguments
        if(args.length != 0){
            System.err.print ("Error: There are no inputs required to run this program.");
            System.exit(1);
        }

        //THE BOARD
        StdDraw.setCanvasSize (500, 500);
        //draw the squares in checkered pattern
        for(int a = 0; a<10; a++) {
            for (int b = 0; b < 10; b++) {
                if( a%2==0 && b%2==0 || (a%2!=0 && b%2!=0)) {
                    StdDraw.filledSquare((50 * a + 25)/500.0, (50 * b + 25)/500.0, 1.0/20);
                }
            }
        }

        //ARRAYS
        //array for players chips
        Chips [] player = new Chips [10];
        for(int a = 0; a<5; a++){
            for(int b =0; b<2; b++){
                if(b==0){
                    player [a] = new Chips(2*a, b, true, true);
                }
                else{
                    player[5+a]= new Chips(2*a +1, b, true, true);
                }
            }
        }
        //array for computers chips
        Chips [] comp = new Chips [10];
        for(int a = 0; a<5; a++){
            for(int b =9; b>7; b--){
                if(b==9){
                    comp [5+ a] = new Chips(2*a+1, b, true, false);
                }
                else{
                    comp [a]= new Chips(2*a, b, true, false);
                }
            }
        }

        //array to keep track of which squares are live (have chips on them)
        double [][] board = new double [10][10];
        //set original board squares that have chips on them as alive
        for(int a=0; a<2; a++){
            for(int b =0; b<10; b++){
                board[b][a] = 1;
            }
        }
        for(int a =8; a<10; a++){
            for(int b =0; b<10; b++){
                board[b][a] = 1;
            }
        }


//RUNNING OF GAME

        //draw the chips
        for (int a = 0; a < 10; a++) {
            player[a].draw();
            comp[a].draw();
        }


//draw instructions
        //int instructions helps display the instructions only once
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledEllipse(.5, .5, .4, .2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.5,.61, "Please click on whichever chip");
        StdDraw.text(.5, .57, "you would like to move, and your options");
        StdDraw.text(.5, .53, "will appear in green.Then please select");
        StdDraw.text(.5, .49, "the square you would like to move your ");
        StdDraw.text(.5, .45, "chip to. The computer will then make its move.");
        StdDraw.text(.5, .41, "Your chips are white.");
        StdDraw.text(.5, .37, "Good luck!");

        //WHILE LOOP FOR GAME
        //sum keeps track of how many chips are left in the game
        int playersChips = 10;
        int compChips = 10;

        while(compChips>0 && playersChips>0) {
            boolean validChip = false;
            while(!validChip) {
                //PLAYERS MOVE
                //makes the program wait until the mouse is pressed
                while (!StdDraw.isMousePressed()) {

                }
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();
                //waits until mouse is no longer pressed (Slows down the program)
                while (StdDraw.isMousePressed()) {
                }
                //check which chip is clicked
                //then display the options for that chip
                //run through the chips of the player to see which one was selected
                for (int a = 0; a < 10; a++) {
                    double x = player[a].getX();
                    double y = player[a].getY();
                    if ((x * 50 / 500.0) <= mouseX && mouseX <= x * 50 / 500.0 + .1 && (y * 50 / 500.0) <= mouseY && mouseY <= (y * 50 / 500.0 + .1)) {
                        player[a].options(player, comp, board);
                        StdDraw.pause(1000);
                        player[a].move(player, comp, board);
                        validChip = true;
                        break;
                    }
                }
            }
            compChips = 0;
            for (int a = 0; a < 10; a++) {
                if (comp[a].alive()) {
                    compChips = compChips + 1;
                }
            }
            if(compChips==0){
                break;
            }
                //COMPUTERS MOVE
                //randomly choose a chip to move
                int chip = (int) (Math.random() * 10);
                //check if that chip is still alive, and if not, pick a new random number
                while (!comp[chip].alive() || !comp[chip].mobile(board)) {
                    //check if chip can move
                    chip = (int) (Math.random() * 10);

                }

                //SHOW OPTIONS FOR COMPUTER

                comp[chip].options(comp, player, board);
                StdDraw.pause(1000);
                comp[chip].compOptions(board, player, comp);

                //Is there a winner?
                //Are there any Chips left?
                playersChips = 0;
                compChips = 0;
                for (int a = 0; a < 10; a++) {
                    if (player[a].alive()) {
                        playersChips = playersChips + 1;
                    }
                    if (comp[a].alive()) {
                        compChips = compChips + 1;
                    }
                }

        }
        //want to declare winner
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledEllipse(.5,.5, .3,.2);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        //StdDraw.setFont(StdDraw.ARIAL);
        if(compChips ==0){
            StdDraw.text(.5,.6, "You Win!");
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.filledCircle(.5, .4, .07);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.ellipse(.5, .37, .04, .03);
            StdDraw.circle(.47, .42, .02);
            StdDraw.circle(.53, .42, .02);
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.filledRectangle(.5, .397, .053, .027);


        }else{
            StdDraw.text(.5,.5, "The Computer Wins.");
        }

    }
}
