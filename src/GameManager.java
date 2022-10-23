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

    public int[][] board;

    GameManager() {
        boolean prog = true;
        while (prog) {
            switch (showMenu()) { //todo input contents
                case "1": playGame(); break;
                case "2": description(); break;
                case "3": prog = false; break;
                default: break;
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

        board = new int[][]{
                { 0, 0, 0,-1, 0, 0, 0},
                { 0, 0,-1,-1,-1, 0, 0},
                { 0,-1, 0,-1, 0,-1, 0},
                {-1,-1,-1, 0,-1,-1,-1},
                { 0,-1, 0,-1, 0,-1, 0},
                { 0, 0,-1,-1,-1, 0, 0},
                { 0, 0, 0,-1, 0, 0, 0},
        };

        while (!isEnd) { //Turn이 바뀔 때 while문 투입
            Team teamTmp;
            if (turn) teamTmp = teamA;
            else teamTmp = teamB;
            teamTmp.rollCnt = 100; // todo 수정 필요
            teamTmp.yut[0] = 10; // todo 수정 필요
            teamTmp.yut[3] = 100; // todo 수정 필요
            teamTmp.yut[5] = 100; // todo 수정 필요
            //teamTmp.isTurnEnd = false;

            while (true) { //해당 Team의 Turn이 지속될 때 유지
                drawMap();
                teamTmp.printYut(tempYut);
                teamTmp.printSrc();
                System.out.println("팀 A 말 위치 현황");
                for(int i = 0;i<4;i++){
                    System.out.print(i+1+"번 말 :  ("+teamA.horse[i].position.first + " ,"+ teamA.horse[i].position.second + ") ");
                }
                System.out.println("\n팀 B 말 위치 현황");
                for(int i = 0;i<4;i++){
                    System.out.print(i+1+"번 말 :  ("+teamB.horse[i].position.first + " ,"+ teamB.horse[i].position.second+ ") ");
                }
                printCommand(turn, isCan);
                System.out.print(">>>");
                isCan = checkCommand(sc.nextLine(), teamTmp);

                // 해당 팀에게 갱신
                if (turn) teamA = teamTmp;
                else teamB = teamTmp;
                // Turn End 확인 todo 말이 없을 때 백도 나오면 종료해야함.
                boolean canMove = false;
                for (int i = 0; i < 6; i++) {
                    if (teamTmp.yut[i] != 0) canMove = true;
                }
                if(teamTmp.rollCnt == 0 && !canMove) { //rollCnt 없고, 못 움직일 때, turn 바뀜
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
        for (int i = 0; i < 20; i++) System.out.println(); //화면 출력
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                switch (board[i][j]) {

                    //Used black in Unicode (sp => dif size)
                    case -1: System.out.print("ㅤ "); break;
                    case  0: System.out.print("○ "); break;
                    //Team A
                    case  1: System.out.print("① "); break;
                    case  2: System.out.print("② "); break;
                    case  3: System.out.print("③ "); break;
                    case  4: System.out.print("④ "); break;
                    case  5: System.out.print("Ⓐ "); break;
                    case  6: System.out.print("Ⓑ "); break;
                    //Team B
                    case 11: System.out.print("❶ "); break;
                    case 12: System.out.print("❷ "); break;
                    case 13: System.out.print("➌ "); break;
                    case 14: System.out.print("➍ "); break;
                    case 15: System.out.print("\uD83C\uDD50 "); break;//🅐
                    case 16: System.out.print("\uD83C\uDD51 "); break;//🅑
                }
            }
            System.out.println();
        }
        //ⓐⓑⓒⓓⒶⒷ○①②③④❶❷➌➍🅐 🅑 Ⓐ Ⓑ
        //todo 말 크기가 다름. 말 띄울 수 있게 변환
        System.out.println("<말 대기현황>");
        System.out.println("A팀  a : ①  b : ②  c :  ③  d : ④ ");
        System.out.println("B팀  a : ❶  b : ❷  c :  ➌  d : ➍");
        //System.out.println("\n\n");
    }

    /**
     * 명령어 확인
     */
    public int checkCommand(String str, Team tm) {
        String cmd[] = str.trim().split("\\s+");//양쪽 공백 날리고, 가변적 공백으로 명령어랑 인자 구분
        if(cmd.length>4) return -1;//명령어, 인자 수 안맞을때
        switch (cmd[0].toLowerCase()) {//명령어 부분만 소문자로 변환
            case "move":
            case "m":
                char h='e';
                int toMove=0;
                char direction='A';
                if(cmd[1].length()>3){
                    System.out.println("error: 명령어 오류");
                }
                else if(cmd[1].length()==3){//cmd[1]에 3글자
                    h=cmd[1].charAt(0);
                    toMove = Character.getNumericValue(cmd[1].charAt(1));
                    direction = cmd[1].charAt(2);
                }
                else if(cmd[1].length()==2){//cmd[1]에 2글자
                    h=cmd[1].charAt(0);
                    toMove = Character.getNumericValue(cmd[1].charAt(1));
                    direction = Character.toUpperCase(cmd[2].charAt(0));//이동시킬 방향

                }
                else if(cmd[1].length()==1){//cmd[1]에 1글자
                    h = cmd[1].charAt(0);//이동시킬 말
                    if(cmd[2].length()==2){//cmd[2]에 두글자
                        toMove = Character.getNumericValue(cmd[2].charAt(0));
                        direction = Character.toUpperCase(cmd[2].charAt(1));//이동시킬 방향
                    }
                    else{//cmd 2에 1글자
                        toMove = Character.getNumericValue(cmd[2].charAt(0));
                        direction = Character.toUpperCase(cmd[3].charAt(0));//이동시킬 방향
                    }
                }
                else{
                    System.out.println("error: 인자 입력 필요");
                }
                /*명령어 오류 걸러내기*/
                if ((h < 'a' || h > 'd') && (h < 'A' || h > 'B')) {
                    System.out.println("error: 가능한 말이 아님");
                    return 1;
                }//말 번호가 아닐 때
                if (tm.yut[toMove] <= 0) {
                    System.out.println("error: 이동 불가");
                    return 1;
                }
                if (!(direction == 'N' || direction == 'E' || direction == 'W' || direction == 'S')) {
                    System.out.println("error: 가능한 방향이 아님");
                    return 1;
                }
                //기존 좌표 및 이동 가능여부
                Pair<Integer, Integer> p = new Pair<>(-1, -1);;
                if(h == 'A'){
                    for (int i = 0; i < tm.horse.length; i++) {
                        if (tm.groupA.contains(tm.horse[i])) {
                            p = tm.horse[i].position;
                        }
                    }
                }else if (h == 'B'){
                    for (int i = 0; i < tm.horse.length; i++) {
                        if (tm.groupB.contains(tm.horse[i])) {
                            p = tm.horse[i].position;
                        }
                    }
                }else{
                    p = tm.horse[h-'a'].position;
                }
                Pair<Integer, Integer> prev = new Pair<>(p.first, p.second);
                if(prev.first == -1 && prev.second == -1) return 1;
                tm.controller(cmd[0], h, toMove, direction);

                //이동 후 좌표
                Pair<Integer, Integer> cur = new Pair<>(-1, -1);;
                if(h == 'A'){
                    for (int i = 0; i < tm.horse.length; i++) {
                        if (tm.groupA.contains(tm.horse[i])) {
                            cur = tm.horse[i].position;
                        }
                    }
                }else if (h == 'B'){
                    for (int i = 0; i < tm.horse.length; i++) {
                        if (tm.groupB.contains(tm.horse[i])) {
                            cur = tm.horse[i].position;
                        }
                    }
                }else{
                    cur = tm.horse[h-'a'].position;
                }
                int now_y = cur.first;
                int now_x = cur.second;

                // 겹쳐지는가?
                switch (checkHorse(turn, now_y, now_x)) {
                    case 1: canGroup = true; return 31; //아군
                    case 2: kill(tm, now_y, now_x); break; //적군
                    default: break; //없을 때
                }
                //맵에 표시
                switch (h) {
                    case 'a':
                        if (turn) {
                            board[now_y][now_x] = 1;
                            System.out.println('a');
                        }
                        else {
                            board[now_y][now_x] = 11;
                            System.out.println('b');
                        }
                        System.out.println('c');
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
        if(now == 1 || now == 2 || now == 3 || now == 4 || now == 5 || now == 6) {
            if(turn) return 1;
            else return 2;
        }
        else if(now == 11 || now == 12 || now == 13 || now == 14 || now == 15 || now == 16) {
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
    public void kill(Team tm, int y, int x) {
        int now = board[y][x];
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
        tm.rollCnt++;
        board[y][x] = 0;
    }
    //todo 설명창 채우기
    public void description() {
        System.out.println("설명창입니다. 여기를 채워주세요.");
        System.out.println("Press Enter to Quit");
        sc.nextLine(); //Enter 입력을 기다림.
    }
}