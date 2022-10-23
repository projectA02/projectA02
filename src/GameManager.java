import java.util.Iterator;
import java.util.Scanner;
import java.lang.String;

public class GameManager {
    Scanner sc = new Scanner(System.in);
    public Team teamA;
    public Team teamB;
    int tempYut;
    private boolean turn; // True : A 팀, false : B 팀
    boolean canGroup = false;

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
        while (prog) {
            switch (showMenu()) { //todo input contents
                case "1": playGame(); break;
                case "2": description(); break;
                case "3": prog = false; break;
                default: System.out.println("Input Command is Incorrect Only 1, 2, 3");
            }
        }
        System.out.println("Good Bye!");
    }

    public String showMenu() {
        for (int i = 0; i < 50; i++) System.out.println();
        System.out.println("Menu");
        System.out.println("1. 게임 시작하기");
        System.out.println("2. 설명 보기");
        System.out.println("3. 종료하기");
        System.out.println("Input Command Only 1, 2, 3");
        System.out.print(">>>");

        return sc.nextLine().trim(); //GameManage()의 switch를 작동하게 함
    }

    public void playGame() {
        teamA = new Team(1);
        teamB = new Team(2);
        boolean isEnd = false;
        tempYut = 5;
        turn = true;
        int isCan = 0;

        while (!isEnd) { //Turn이 바뀔 때 while문 투입
            Team teamTmp;
            if (turn) teamTmp = teamA;
            else teamTmp = teamB;
            teamTmp.rollCnt = 1;
            //teamTmp.isTurnEnd = false;

            while (true) { //해당 Team의 Turn이 지속될 때 유지
                drawMap();
                teamTmp.printYut(tempYut);
                teamTmp.printSrc();

                printCommand(turn, isCan);
                System.out.print(">>>");
                isCan = checkCommand(sc.nextLine(), teamTmp);

                // 해당 팀에게 갱신
                if (turn) teamA = teamTmp;
                else teamB = teamTmp;
                // Turn End 확인
                boolean canMove = false;
                for (int i = 0; i < 6; i++) {
                    if (teamTmp.yut[i] != 0) canMove = true;
                }
                if(teamTmp.rollCnt == 0 & !canMove) { //rollCnt 없고, 못 움직일 때, turn 바뀜
                    turn = !turn;
                    break;
                }
            }
            //isEnd = true;
        }
    }

    /**
     * map 그리기
     */
    public void drawMap() {
        for (int i = 0; i < 20; i++) System.out.println();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                switch (board[i][j]) {
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
        System.out.println("A팀  a :  b :   c :  ③  d :  ");
        System.out.println("B팀  a :  b :   c :  ➌  d :  ➍");
        System.out.println("\n\n");
    }

    /**
     * 명령어 확인
     */
    public int checkCommand(String str, Team tm) {
        String cmd[] = str.trim().split(" ");//양쪽 공백 날리고, 공백으로 명령어랑 인자 구분
        for(int i=0; i<cmd.length; i++)
            System.out.println(i + ":" + cmd[i]);
        switch (cmd[0].toLowerCase()) {//명령어 부분만 소문자로 변환
            case "move":
            case "m":
                boolean canMove = false;
                for (int i = 0; i < 6; i++) {
                    if (tm.yut[i] != 0) canMove = true;
                }
                if (!canMove) return 1;

                char h = cmd[1].charAt(0);
                int toMove = Integer.parseInt(cmd[2]);
                char direction = cmd[3].charAt(0);
                //기존 좌표 및 이동 가능여부
                Pair<Integer, Integer> prev = tm.horse[h-'a'].position;
                if(prev.first == -1 & prev.second == -1) return 1;
                tm.controller(cmd[0], h, toMove, direction);

                //이동 후 좌표
                Pair<Integer, Integer> cur = tm.horse[h-'a'].position;
                int now_y = cur.first;
                int now_x = cur.second;

                // 겹쳐지는가?
                switch (checkHorse(turn, now_y, now_x)) {
                    case 1: canGroup = true; return 31; //아군
                    case 2: kill(now_y, now_x); return 32; //적군
                    default: break; //없을 때
                }

                //맵에 표시
                switch (h) {
                    case 'a':
                        if (turn) board[now_y][now_x] = 1;
                        else board[now_y][now_x] = 11;
                        break;
                    case 'b':
                        if (turn) board[now_y][now_x] = 2;
                        else board[now_y][now_x] = 12;
                        break;
                    case 'c':
                        if (turn) board[now_y][now_x] = 3;
                        else board[now_y][now_x] = 13;
                        break;
                    case 'd':
                        if (turn) board[now_y][now_x] = 4;
                        else board[now_y][now_x] = 14;
                        break;
                    case 'A':
                        if (turn) board[now_y][now_x] = 5;
                        else board[now_y][now_x] = 15;
                        break;
                    case 'B':
                        if (turn) board[now_y][now_x] = 6;
                        else board[now_y][now_x] = 16;
                        break;
                }
                board[prev.first][prev.second] = 0;
                tm.yut[toMove]--;
                break;

            case "roll":
            case "r":
                if (tm.rollCnt == 0) return 2;
                tempYut = tm.controller(cmd[0]);
                break;

            case "grouping":
            case "g":
                if(!canGroup) return 3;
                tm.controller(cmd[0], cmd[1].charAt(0), cmd[2].charAt(0));
                break;

            //첫 단어가 올바른 명령어가 아닐 경우
            default:
                return -1;
        }
        return 0;
    }

    /**
     * 이동한 위치에 상대말이 있는지
     */
    public int checkHorse(boolean turn, int y, int x) {
        int now = board[y][x];
        if(now == 1 | now == 2 | now == 3 | now == 4 | now == 5 | now == 6) {
            if(turn) return 1;
            else return 2;
        }
        else if(now == 11 | now == 12 | now == 13 | now == 14 | now == 15 | now == 16) {
            if(turn) return 2;
            else return 1;
        }
        return 0;
    }

    public void printCommand(boolean turn, int op) {
        if (turn) System.out.print("Team A: ");
        else System.out.print("Team B: ");
        switch (op) {
            case  0: System.out.println("Input Command"); break;
            case  1: System.out.println("Cannot Move"); break;
            case  2: System.out.println("Not Enough Roll Chance"); break;
            case  3: System.out.println("Cannot Grouping"); break;
            case 31: System.out.println("Can Grouping"); break;
            case 32: System.out.println("Can Kill"); break;
            default: System.out.println("Input Command is Incorrect"); break;
        }
    }
    public void kill(int y, int x) {
        int now = board[y][x];
        Iterator<Horse> iter;
        switch (now) {
            case 1:
            case 2:
            case 3:
            case 4: teamA.horse[now-1] = new Horse(); break;
            //todo 그룹화된 거 위치 초기화 어떻게 할지 생각 좀 해봅시다. 5, 6, 15, 16
            case 5:
                teamA.groupA.clear();
                break;
            case 6:
                teamA.groupB.clear();
                break;
            case 11:
            case 12:
            case 13:
            case 14: teamB.horse[now-11] = new Horse(); break;
            case 15:
                teamB.groupA.clear();
                break;
            case 16:
                teamB.groupB.clear();
                break;
        }

        board[y][x] = 0;
    }
    //todo 설명창 채우기
    public void description() {
        System.out.println("설명창입니다. 여기를 채워주세요.");
        System.out.println("Press Enter to Quit");
        sc.nextLine(); //Enter 입력을 기다림.
    }
}