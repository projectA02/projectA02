import java.io.IOException;

public class testMain {
    public static final String RESET = "\u001B[0m";
    public static final String FONT_BLACK = "\u001B[30m";
    public static final String FONT_RED = "\u001B[31m";
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_YELLOW = "\u001B[33m";
    public static final String FONT_BLUE = "\u001B[34m";
    public static final String FONT_PURPLE = "\u001B[35m";
    public static final String FONT_CYAN = "\u001B[36m";
    public static final String FONT_WHITE = "\u001B[37m";
    public static final String BACKGROUND_BLACK = "\u001B[40m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_BLUE = "\u001B[44m";
    public static final String BACKGROUND_PURPLE = "\u001B[45m";
    public static final String BACKGROUND_CYAN = "\u001B[46m";
    public static final String BACKGROUND_WHITE = "\u001B[47m";

    public static void main(String[] args) throws IOException {
    GameManager gm = new GameManager();
    gm.showMenu();

//    System.out.println("도");
//    System.out.println("" +
//            "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//            "│X│ │X│ │X│ │ │    \n" +
//            "│X│ │X│ │X│ │ │    \n" +
//            "│X│ │X│ │X│ │ │   \n" +
//            "└─┘ └─┘ └─┘ └─┘    ");
//
//    System.out.println("개");
//    System.out.println("" +
//                "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//                "│X│ │X│ │ │ │ │    \n" +
//                "│X│ │X│ │ │ │ │    \n" +
//                "│X│ │X│ │ │ │ │   \n" +
//                "└─┘ └─┘ └─┘ └─┘    ");
//    System.out.println("걸");
//    System.out.println("" +
//                "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//                "│X│ │ │ │ │ │ │    \n" +
//                "│X│ │ │ │ │ │ │    \n" +
//                "│X│ │ │ │ │ │ │   \n" +
//                "└─┘ └─┘ └─┘ └─┘    ");
//    System.out.println("윷");
//        System.out.println("" +
//                "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//                "│ │ │ │ │ │ │ │    \n" +
//                "│ │ │ │ │ │ │X│    \n" +
//                "│ │ │ │ │ │ │ │   \n" +
//                "└─┘ └─┘ └─┘ └─┘    ");
//        System.out.println("모");
//        System.out.println("" +
//                "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//                "│X│ │X│ │X│ │X│    \n" +
//                "│X│ │X│ │X│ │X│    \n" +
//                "│X│ │X│ │X│ │X│   \n" +
//                "└─┘ └─┘ └─┘ └─┘    ");
//
//        System.out.println("백도");
//        System.out.println("" +
//                "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//                "│X│ │X│ │X│ │ │    \n" +
//                "│X│ │X│ │X│ │X│    \n" +
//                "│X│ │X│ │X│ │ │   \n" +
//                "└─┘ └─┘ └─┘ └─┘    ");
//
//        //System.out.println("윷판");
//        System.out.println("방위");
//        String[][] board = {
//            {"O ", "O ","O ","  ","O ","O ","O "},
//            {"O ", "O ","  ","  ","  ","O ","O "},
//            {"O ", "  ","O ","  ","O ","  ","O "},
//            {"  ", "  ","  ","O ","  ","  ","  "},
//            {"O ", "  ","O ","  ","O ","  ","O "},
//            {"O ", "O ","  ","  ","  ","O ","O "},
//            {"O ", "O ","O ","  ","O ","O ","O "},
//        };
//        for(int i= 0;i<7;i++){
//            for(int j=0;j<7;j++){
//                System.out.print(i +""+j+" ");
//            }
//            System.out.println();
//        }


//
//        System.out.println("-----ROLL-----");
//        System.out.println("도 : 0, 개 : 0 ,걸 : 0,윷 : 0 ,모 : 0 ");
//        System.out.println("roll ");
//        System.out.println("" +
//                "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
//                "│X│ │X│ │X│ │ │    \n" +
//                "│X│ │X│ │X│ │ │    \n" +
//                "│X│ │X│ │X│ │ │   \n" +
//                "└─┘ └─┘ └─┘ └─┘    ");
//        System.out.println("도 : 1, 개 : 0 ,걸 : 0,윷 : 0 ,모 : 0 ");
//
//        System.out.println("------MOVE------");
//
//        board[5][6] ="ⓐ ";
//        System.out.println();
//        for(int i= 0;i<7;i++){
//            for(int j=0;j<7;j++){
//                System.out.print(board[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("move a2E\n");
//        board[2][6] ="ⓐ ";
//        board[5][6] ="0 ";
//        for(int i= 0;i<7;i++){
//            for(int j=0;j<7;j++){
//                System.out.print(board[i][j]);
//            }
//            System.out.println();
//        }
////ⓐⓑ
//        System.out.println("----GROUPING----");
//        board[5][6] ="ⓐ ";
//        board[2][6] ="ⓑ ";
//        for(int i= 0;i<7;i++){
//            for(int j=0;j<7;j++){
//                System.out.print(board[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("move a2E\n");
//        System.out.println("grouping a b\n");
//        board[2][6] ="Ⓐ ";
//        board[5][6] ="0 ";
//        for(int i= 0;i<7;i++){
//            for(int j=0;j<7;j++){
//                System.out.print(board[i][j]);
//            }
//            System.out.println();
//        }
//

    }

}
