import java.util.Scanner;
import java.lang.String;

public class GameManager {
    Scanner sc = new Scanner(System.in);
    public Team teamA;
    public Team teamB;
    int tempYut;
    private boolean turn; // True : A 팀, false : B 팀

    public int[][] board = {
            { 0, 0, 0,-1, 0, 0, 0},
            { 0, 0,-1,-1,-1, 0, 0},
            { 0,-1, 0,-1, 0,-1, 0},
            {-1,-1,-1, 0,-1,-1,-1},
            { 0,-1, 0,-1, 0,-1, 0},
            { 0, 0,-1,-1,-1, 0, 0},
            { 0, 0, 0,-1, 0, 0, 0},
    };

    GameManager() {
        boolean prog = true;
        while(prog) {
            switch (showMenu()) { //todo input contents
                case "1": playGame(); break;
                case "2": description(); break;
                case "3": prog = false; break;
                default:
                    System.out.println("Input Command is Incorrect Only 1, 2, 3");
            }
        }
        System.out.println("Good Bye!");
    }

    public String showMenu() {
        for(int i=0;i<50;i++) System.out.println();
        System.out.println("Menu");
        System.out.println("1. 게임 시작하기");
        System.out.println("2. 설명 보기");
        System.out.println("3. 종료하기");
        System.out.println("Input Command Only 1, 2, 3");
        System.out.print(">>>");

        return sc.nextLine().trim();
    }

    public void playGame() {
        teamA = new Team(1);
        teamB = new Team(2);
        boolean isEnd = false;
        tempYut = 5;
        turn = true;
        int isCan = 0;

        while(!isEnd) {
            Team teamTmp;
            if(turn) teamTmp = teamA;
            else teamTmp = teamB;
            teamTmp.rollCnt = 1;
            teamTmp.isTurnEnd = false;

            while(!teamTmp.isTurnEnd) {
                drawMap();
                teamTmp.printYut(tempYut);
                teamTmp.printSrc();

                printCommand(turn, isCan);
                System.out.print(">>>");
                isCan = checkCommand(sc.nextLine(), teamTmp);

                if(turn) teamA = teamTmp;
                else teamB = teamTmp;
            }

            //todo 일단 돌지 않고 끝내게 했음.
            //isEnd = true;
        }
    }

    /**
     * map 그리기
     * */
    public void drawMap() {
        for(int i=0;i<20;i++) System.out.println();
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                switch(board[i][j]){
                    //todo Fill Mark
                    //Used black in Unicode (sp => dif size)
                    case -1: System.out.print("ㅤ "); break;
                    case  0: System.out.print("○ "); break;
                    //Team A
                    case  1: System.out.print("ⓐ "); break;
                    case  2: System.out.print("ⓑ "); break;
                    case  3: System.out.print("ⓒ "); break;
                    case  4: System.out.print("ⓓ "); break;
                    case  5: System.out.print("Ⓐ "); break;
                    case  6: System.out.print("Ⓑ "); break;
                    //Team B
                    case 11: System.out.print("○ "); break;
                    case 12: System.out.print("○ "); break;
                    case 13: System.out.print("○ "); break;
                    case 14: System.out.print("○ "); break;
                    case 15: System.out.print("ㅤ "); break;
                    case 16: System.out.print("○ "); break;
                }
            }
            System.out.println();
        }
        //todo 말 크기가 다름. 말 띄울 수 있게 변환
        System.out.println("\n<말 대기현황>");
        System.out.println("A팀  a : ①  b :   c :  ③  d :  ");
        System.out.println("B팀  a : ➊  b :   c :  ➌  d :  ➍");
        System.out.println("\n\n");
    }

    /**
     * 명령어 확인
     * */
    public int checkCommand(String str, Team tm){
        String cmd[] = str.trim().split(" ");//양쪽 공백 날리고, 공백으로 명령어랑 인자 구분
        switch(cmd[0].toLowerCase()){//명령어 부분만 소문자로 변환
            case "move":
            case "m":
                //cmd[]
                //todo : 인자부분 처리
                boolean canMove = false;
                for(int i=0;i<6;i++) {
                    if(tm.yut[i] != 0) canMove = true;
                }
                if(!canMove) return 1;

                break;
            case "roll":
            case "r":
                if(tm.rollCnt == 0) return 2;
                tempYut = tm.controller(cmd[0]);
                break;
            case "grouping":
            case "g":

                break;

            default:
                //todo 올바른 입력형식 보여줄 것.
                return -1;
        }
        return 0;
    }
    /**
     * 이동한 위치에 상대말이 있는지
     * */
    public void checkHorse(){

    }
    public void printCommand(boolean turn, int op) {
        if(turn) System.out.print("Team A: ");
        else System.out.print("Team B: ");
        switch (op) {
            case  0: System.out.println("Input Command"); break;
            case  1: System.out.println("Cannot Move"); break;
            case  2: System.out.println("Not Enough Roll Chance"); break;
            case  3: System.out.println("Cannot Grouping"); break;
            default: System.out.println("Input Command is Incorrect"); break;
        }
    }

    //todo 설명창 채우기
    public void description() {
        System.out.println("설명창입니다. 여기를 채워주세요.");
        System.out.println("Press Enter to Quit");
        sc.nextLine();
    }
}