import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        // Instances
        Human h = new Human("Zephyr", "♨️");
        //cyclone is the token
        Computer c = new Computer("\uD83C\uDF00");
        Scanner sc = new Scanner(System.in);

        System.out.println("ฬн𝓪Ť 𝓘𝕊 ץό𝔲ᖇ ภ𝓐ϻ𝔼?");
        String username = sc.nextLine();

        System.out.println("𝙃𝙚𝙡𝙡𝙤 " + username + ". " + "𝙒𝙚𝙡𝙘𝙤𝙢𝙚 𝙩𝙤 𝘾𝙤𝙣𝙣𝙚𝙘𝙩 4! 𝘼𝙣 𝙪𝙣𝙙𝙚𝙧𝙨𝙘𝙤𝙧𝙚 [ _ ] 𝙧𝙚𝙥𝙧𝙚𝙨𝙚𝙣𝙩𝙨 𝙖𝙣 𝙚𝙢𝙥𝙩𝙮 𝙨𝙥𝙤𝙩. " +
                "𝙏𝙝𝙚 𝙨𝙩𝙚𝙖𝙢'𝙨 𝙖𝙧𝙚 𝙮𝙤𝙪𝙧 𝙩𝙤𝙠𝙚𝙣𝙨, 𝙖𝙣𝙙 𝘾𝙮𝙘𝙡𝙤𝙣𝙚'𝙨 𝙖𝙧𝙚 𝙩𝙝𝙚 𝙘𝙤𝙢𝙥𝙪𝙩𝙚𝙧'𝙨 𝙩𝙤𝙠𝙚𝙣𝙨. 𝙋𝙧𝙚𝙨𝙨 𝙚𝙣𝙩𝙚𝙧");
        sc.nextLine();
        System.out.println("𝘼 𝙬𝙞𝙣 𝙞𝙨 𝙖𝙬𝙖𝙧𝙙𝙚𝙙 𝙬𝙝𝙚𝙣 𝙛𝙤𝙪𝙧 𝙩𝙤𝙠𝙚𝙣𝙨 𝙖𝙧𝙚 𝙥𝙡𝙖𝙘𝙚𝙙 𝙪𝙣𝙞𝙣𝙩𝙚𝙧𝙧𝙪𝙥𝙩𝙚𝙙 " +
                "𝙫𝙚𝙧𝙩𝙞𝙘𝙖𝙡𝙡𝙮, 𝙝𝙤𝙧𝙞𝙯𝙤𝙣𝙩𝙖𝙡𝙡𝙮, 𝙤𝙧 𝙙𝙞𝙖𝙜𝙤𝙣𝙖𝙡𝙡𝙮. 𝙋𝙧𝙚𝙨𝙨 𝙚𝙣𝙩𝙚𝙧");
        sc.nextLine();

        int[] colCount = new int[8];

        // Checks to see if computer won.
        while (!c.getB().checkWin(c.token)) {

            boolean legalCol = false;
            int moveCol = -1;

            // Human's move.
            while (!legalCol) {

                c.getB().showBoard();
                System.out.println(" Please choose a column to place your token in. " +
                        "You cannot place a token in a full column.");

                if (sc.hasNextInt()) {
                    moveCol = sc.nextInt() - 1;
                    legalCol = true;

                    if (moveCol > 7 || moveCol < 0) {
                        System.out.println("Enter a legal column, an integer 1-8.");
                        legalCol = false;
                    }

                    if (moveCol < 8 && moveCol > -1 && !c.getB().colHasRoom(moveCol)) {
                        System.out.println("This column is full, select a new column.");
                        legalCol = false;
                        sc.next();
                    }
                } else {
                    System.out.println("This is not a valid column. Please enter a new, valid column.");
                    sc.next();
                }

                if (legalCol && c.getB().colHasRoom(moveCol)) {
                    c.getB().makeMove(moveCol, h.token);
                }
            }

            // Counts column's tokens
            colCount[moveCol]++;


            //Tie?
            if (c.getB().getGrid()[0][0].equals("X") || c.getB().getGrid()[0][moveCol].equals("O")) {
                System.out.println("The result of the game is a draw.");
                break;
            }

            // Displays board.
            c.getB().showBoard();
            System.out.println();

            // Checks to see if human won.
            if (c.getB().checkWin(h.token)) {
                break;
            }

            // Computer's move.
            int computerMove = c.determineMove(moveCol);

            int fullestCol = -1;

            if (c.getB().colHasRoom(moveCol)) {
                c.getB().makeMove(computerMove, c.token);
            } else {
                // This will keep track of the fullest column.
                for (int x = 0; x < 8; x++) {
                    if (colCount[x] > fullestCol && c.getB().colHasRoom(x)) {
                        fullestCol = x;
                    }
                }
                c.getB().makeMove(fullestCol, c.token);
            }

//             Counts the computer's number of tokens in each column.
            if (fullestCol != -1) {
                colCount[fullestCol]++;
            } else {
                colCount[computerMove]++;
            }

            c.getB().showBoard();
        }
        //Tells the user who won
        if (c.getB().checkWin(h.token)) {
            //If human wins, the username they printed will show
            System.out.print("Game Over! The Human: " + username + " wins.");
        } else if (c.getB().checkWin(c.token)) {
            System.out.print("Game Over! Computer wins");
        } else {
            //Only other possibility to be accounted for at this stages
            System.out.print("Tie.");
        }
    }
}