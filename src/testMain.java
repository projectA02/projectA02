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
//        System.out.println(FONT_BLACK + "안녕하세요 !!!" + RESET);
//        System.out.println(FONT_RED + "안녕하세요 !!!" + RESET);
//        System.out.println(FONT_YELLOW + "안녕하세요 !!!" + RESET);
//        System.out.println(FONT_CYAN + "안녕하세요 !!!" + RESET);
//        System.out.println(FONT_WHITE + "안녕하세요 !!!" + RESET);
//        System.out.println("");
//
//        System.out.println(BACKGROUND_BLACK + FONT_WHITE + "안녕하세요 !!!" + RESET);
//        System.out.println(BACKGROUND_WHITE + FONT_BLUE + "안녕하세요 !!!" + RESET);
//        System.out.println(BACKGROUND_YELLOW + FONT_RED + "안녕하세요 !!!" + RESET);
//        System.out.println("");
//        try {
//            for(int i = 0; i < 10; ++i) {
//                for(int j= 0;j<5;j++){
//                    System.out.print('\n');
//                }
//                System.out.println(FONT_RED + i + "- ➊ ❷");
//                System.out.println(FONT_BLUE + i + "- ➀ ➊");
//                Thread.sleep(1000);
//            }
//        } catch (InterruptedException var2) {
//            var2.printStackTrace();
//        }
        //todo 말 크기가 다름. 말 띄울 수 있게 변환
//        System.out.println("\n<말 대기현황>");
//        System.out.println("A팀  a : ⓐ  b : ⓑ  c :  ③  d :  ");
//        System.out.println("B팀  a :     b : ○  c :     d :  ○");
//        System.out.println("\n\n");
//
//        System.out.println("말 대기현황");
//        System.out.println("A팀  a : ①  b :   c :  ③  d :  ");
//        //➊➋➌➍$
//        System.out.println("B팀  a : ➊  b :   c :  ➌  d :  ➍");
    GameManager gm = new GameManager();
    gm.showMenu();









        /**
     * @command
     * roll , move , grouping
     *
     * @baard
     * 윷판
     *
     * @dice
     * 윷
     *
     * @ direction
     * */

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
