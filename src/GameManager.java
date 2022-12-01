import java.util.Scanner;

public class GameManager {
    Scanner sc = new Scanner(System.in);
    Team teamA;
    Team teamB;
    boolean isEnd;
    int useLog;
    private boolean turn; // True : A 팀, false : B 팀
    boolean canGroup = false;
    boolean endTurn = false;
    boolean changeTurn = false;
    int[][] board;
    boolean useIsland = false;

    /**
     * 생성자
     * */
    //생성자
    GameManager() {
        boolean prog = true; //프로그램을 계속 진행할 것인가
        boolean menu = true; //올바른 명령어를 입력했는가
        while(prog) {
            String[] cmd = parseInput(showMenu(menu));
            //띄어쓰기를 통해 2단어 이상 입력시 예외: 불필요한 입력
            if(cmd[4].length() > 1) {
                menu = false;
                continue;
            }
            //명령어 처리
            switch (cmd[0]) {
                case "1": menu = true; playGame(); break;
                case "2": menu = true; description(); break;
                case "3": menu = true; prog = false; break;
                default : menu = false; break;  //Bad command
            }
        }
        //프로그램 종료
        System.out.println("GAME OVER");
    }
    //메뉴 출력
    public String showMenu(boolean menu) {
        for(int i=0; i<50; i++) System.out.println();
        System.out.println("Menu");
        System.out.println("1. 게임 시작하기");
        System.out.println("2. 설명 보고 세팅하기");
        System.out.println("3. 종료하기\n");
        //이전 입력이 올바르지 않으면 로그 변경
        if(menu) System.out.println("메뉴를 선택하세요. [1, 2, 3]");
        else System.out.println("올바르지 않은 입력입니다. [1, 2, 3]");

        System.out.print(">>>");
        return sc.nextLine();
    }

    // 입력값 나누기 -> 0:cmd, 1:h1, 2:toMove|h2, 3:direction, 4:else
    // res[4].length = 명령어 이후 문자의 개수 + 1
    public String[] parseInput(String str) {
        String[] res = new String[5];
        //공백: 명령어 구분, "$": 각 위치에 null string 값이 들어가지 않게
        str = str.trim()+" $$$$";

        //null -> 0: "", 1~4: "$"
        int fstBlank = str.indexOf(" ");
        //첫번째 띄어쓰기까지 명령어로 인식함
        res[0] = str.substring(0, fstBlank);
        //명령어만 제외하고 나머지 문자는 붙임
        str = str.substring(fstBlank).replace(" ","");
        res[1] = String.valueOf(str.charAt(0));
        res[2] = String.valueOf(str.charAt(1));
        res[3] = String.valueOf(str.charAt(2));
        res[4] = str.substring(3); //불필요한 값들이 들어감

        return res;
    }

    /**
     * Play Game
     * */
    public void playGame() {
        teamA = new Team(0);
        teamB = new Team(1);

        turn = true; //teamA 먼저 시작
        isEnd = false; //게임이 끝났는가?

        //게임이 지속되는 동안, teamA teamB 번갈아
        while(!isEnd) {
            Team teamTmp = turn ? teamA : teamB;
            teamTmp.rollCnt = 1;
            teamTmp.prevYut = 5;
            if (teamTmp.islandF)    useLog = 51;
            else                    useLog = 0;

            endTurn = false;
            changeTurn = false;
            //턴이 지속되는 동안, 한 team이 계속 진행
            while(!changeTurn) {
                //맵, 현황 등 출력
                drawMap();
                teamTmp.printSrc();

                /* todo remove: check position
                System.out.println("팀 A 말 위치 현황");
                for (int i = 0; i < 4; i++) {
                    System.out.print(i + 1 + "번 말 : (" + teamA.horse[i].position.first + " ," + teamA.horse[i].position.second + ")");
                }
                System.out.println("\n팀 B 말 위치 현황");
                for (int i = 0; i < 4; i++) {
                    System.out.print(i + 1 + "번 말 : (" + teamB.horse[i].position.first + " ," + teamB.horse[i].position.second + ")");
                }
                System.out.println(); */


                //Log 출력과 입력 받기
                printLog(turn, useLog);
                System.out.print(">>>");
                useLog = checkCommand(sc.nextLine(), teamTmp);

                //게임이 끝났는가: 메뉴로 이동
                if(teamTmp.checkIsEnd()) {
                    for (int i = 0; i < 50; i++) System.out.println();
                    printLog(turn, 99);
                    sc.nextLine();
                    return;
                }
                //턴이 끝났는가: 상대팀으로 넘어가는 문구 출력 후 엔터 입력시 전환
                endTurn = teamTmp.isTurnEnd();
                if(endTurn) {
                    //확인 받은 경우 넘어감
                    if(changeTurn) continue;
                    //그룹핑 남아 있거나, 확인을 안 받은 경우
                    if(canGroup) useLog = 41;
                    else if(teamTmp.yut[0] > 0) useLog = 25;
                    else useLog = 5;
                }

            }
            if(turn) teamA = teamTmp;
            else teamB = teamTmp;
            turn = !turn; //턴 끝났을 경우 도달
        }
    }

    //윷판 갱신 후 출력, 각 팀 말 현황 출력
    public void drawMap() {
        board = new int[][] {
                { 0, 0, 0,-1, 0, 0, 0},
                { 0, 0,-1,-1,-1, 0, 0},
                { 0,-1, 0,-1, 0,-1, 0},
                {-1,-1,-1, 0,-1,-1,-1},
                { 0,-1, 0,-1, 0,-1, 0},
                { 0, 0,-1,-1,-1, 0, 0},
                { 0, 0, 0,-1, 0, 0, 0},
        };
        //각 팀 별로 윷판 갱신
        teamA.marking(board);
        teamB.marking(board);
        //윷판 출력 <<○ㅤ①②③④ⒶⒷ❶❷➌➍🅐🅑>>
        for (int i = 0; i < 20; i++) System.out.println();
        if(turn) System.out.println("< A팀 턴 >");
        else System.out.println("< B팀 턴 >");
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                switch (board[i][j]) {
                    //Initial
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
        //말 현황 출력
        System.out.println("<말 대기현황>");
        teamA.printHorse();
        teamB.printHorse();
        System.out.println();
    }

    /**
     * @param str 입력값
     * @param tm 현재 팀
     * @return use-log 출력
     */
    public int checkCommand(String str, Team tm) {
        //입력값 나누기
        String[] cmd = parseInput(str);
        //명령어
        String op = cmd[0].toLowerCase();
        char h1, h2;
        int toMove;
        char direction;
        switch(op) {
            //todo remove: replay game
            case "re" : for(int i=0; i<4; i++) tm.isEnd[i] = true; return 99;

            //말 이동
            case "move":
            case "m":
                //입력을 잘 못 했을 때: op제외 2~3개 값 입력: [h|g] [toMove] <direction>
                if(cmd[4].length() != 3 & cmd[4].length() != 4) return 2;
                //toMove가 숫자가 아닐 경우 예외, 입력값 2개일 때 direction = "$"
                h1 = cmd[1].charAt(0);
                if(Character.isDigit(cmd[2].charAt(0)))
                    toMove = Character.getNumericValue(cmd[2].charAt(0));
                else return 22;
                direction = cmd[3].charAt(0);

                //이동 실패하면 Pair<-1, -1>, 성공하면 이동한 후 좌표값
                Pair<Integer, Integer> cur = tm.controller(op, h1, toMove, direction);

                if(useIsland && cur.first == 3 && cur.second == 3) {
                    tm.islandF = true;
                    useLog = 50;
                }

                if(cur.first == -1) { //이동 실패
                    useLog = cur.second;
                    break;
                }
                else { //이동 성공
                    canGroup = false;
                    if(cur.second == 7) { //말이 났을 경우
                        useLog = 10;
                        break;
                    }
                    else useLog = 20;
                }

                int now_y = cur.first;
                int now_x = cur.second;

                //이동 후 좌표에 원래 위치해 있던 말 처리
                switch (checkHorse(turn, now_y, now_x)) {
                    case 1: //아군: 업기 가능
                        canGroup = true;
                        useLog = 40;
                        break;
                    case 2: //적군: 무조건 잡음
                        kill(now_y, now_x);
                        useLog = 33;
                        tm.rollCnt++;   //잡으면 기회 증가
                        break;
                    default:
                        break;
                }

                // if (tm.islandF) -> endTurn = true
                if (tm.islandF) {
                    tm.rollCnt = 0;
                    for (int i = 0; i < 6; i++) tm.yut[i] = 0;
                }

                break;

            case "roll":
            case "r":
                //명령어 제외 불필요한 값 입력 시 예외
                if(cmd[4].length() > 1) return 3;
                useLog = tm.controller(op);
                if(useLog == 30 | useLog == 32) canGroup = false;
                break;

            case "grouping":
            case "g":
                if(!canGroup) return 43; //그룹이 불가능 하면 예외

                //입력을 잘 못 했을 때
                if(cmd[4].length() != 3) return 4;
                h1 = cmd[1].charAt(0);
                h2 = cmd[2].charAt(0);

                useLog = tm.controller(op, h1, h2);
                if(useLog == 45) canGroup = false;
                break;

            case "":
                if(endTurn) { //턴 종료 확인 문구 처리: Log <5, 25, 41>
                    if(canGroup) canGroup = false;
                    tm.yut[0] = 0;
                    changeTurn = true;
                    return 0;
                }
                else if(canGroup) { //grouping만 취소할 때
                    return  46;
                }
            default:
                return 1;
        }
        return useLog;
    }

    //return 아군:1, 적군:2
    public int checkHorse(boolean turn, int y, int x) {
        if(y == 6 & x == 7) return 0; //난 말입니다.

        int now = board[y][x];
        if(now == 1 || now == 2 || now == 3 || now == 4 || now == 5 || now == 6) {
            return turn ? 1 : 2;
        }
        else if(now == 11 || now == 12 || now == 13 || now == 14 || now == 15 || now == 16) {
            return turn ? 2 : 1;
        }
        return 0;
    }
    //맵 갱신은 drawMap에서 진행
    public void kill(int y, int x) {
        int now = board[y][x];
        switch (now) {
            //teamA
            case 1:
            case 2:
            case 3:
            case 4:
                teamA.horse[now-1] = new Horse();
                teamA.islandF = false;
                break;
            case 5:
                for(int i=0; i<teamA.horse.length; i++) {
                    if(teamA.groupA.contains(teamA.horse[i]))
                        teamA.horse[i] = new Horse();
                }
                teamA.groupA.clear();
                teamA.islandF = false;
                break;
            case 6:
                for(int i=0; i<teamA.horse.length; i++) {
                    if(teamA.groupB.contains(teamA.horse[i]))
                        teamA.horse[i] = new Horse();
                }
                teamA.groupB.clear();
                teamA.islandF = false;
                break;
            //teamB
            case 11:
            case 12:
            case 13:
            case 14:
                teamB.horse[now-11] = new Horse();
                teamB.islandF = false;
                break;
            case 15:
                for(int i=0; i<teamB.horse.length; i++) {
                    if(teamB.groupA.contains(teamB.horse[i]))
                        teamB.horse[i] = new Horse();
                }
                teamB.groupA.clear();
                teamB.islandF = false;
                break;
            case 16:
                for(int i=0; i<teamB.horse.length; i++) {
                    if(teamB.groupB.contains(teamB.horse[i]))
                        teamB.horse[i] = new Horse();
                }
                teamB.groupB.clear();
                teamB.islandF = false;
                break;
        }
    }

    //todo 로그 보강
    public void printLog(boolean turn, int op) {
        if (turn) System.out.print("Team A: ");
        else System.out.print("Team B: ");
        switch (op) {
            //입력에 관한
            case  0: System.out.println("명령어를 입력하세요."); break;
            case  1: System.out.println("올바르지 않은 입력입니다.[move | roll | grouping]"); break;
            case  2: System.out.println("올바르지 않은 입력입니다.[move] [말][이동칸][방향]"); break;
            case  3: System.out.println("올바르지 않은 입력입니다.[roll]"); break;
            case  4: System.out.println("올바르지 않은 입력입니다.[grouping] [말][말]"); break;
            case  5: System.out.println("가능한 행동이 없습니다. 엔터만 입력하면 상대 턴으로 넘어갑니다."); break;
            //말에 관한
            case 10: System.out.println("말이 났습니다."); break;
            case 11: System.out.println("그룹에 속해 있는 말입니다."); break;
            case 12: System.out.println("마지막으로 움직인 말이 아닙니다."); break;
            case 13: System.out.println("말 또는 그룹이 아닙니다.[말: a, b, c, d][그룹: A, B]"); break;
            case 14: System.out.println("난 말입니다."); break;
            case 15: System.out.println("출발하지 않은 말입니다."); break;
            case 16: System.out.println("존재하지 않는 그룹입니다."); break;
            //move에 관한
            case 20: System.out.println("이동 성공"); break;
            case 21: System.out.println("이동 실패"); break;
            case 22: System.out.println("이동 가능한 거리가 아닙니다.[백도: 0, 도: 1, 개: 2, 걸: 3, 윷: 4, 모: 5"); break;
            case 23: System.out.println("이동 가능한 방향이 아닙니다.[1시: E, 4시: S, 7시: W, 10시: N]"); break;
            case 24: System.out.println("이동 횟수가 부족합니다."); break;
            case 25: System.out.println("백도를 이동할 말이 존재하지 않습니다. 엔터만 입력하면 상대턴으로 넘어갑니다"); break;
            //roll에 관한
            case 30: System.out.println("던지기 성공"); break;
            case 31: System.out.println("던질 기회가 없습니다."); break;
            case 32: System.out.println("한번 더 던질 수 있습니다."); break;
            case 33: System.out.println("잡았습니다. 한번 더 던질 수 있습니다."); break;
            //grouping에 관한
            case 40: System.out.println("그룹핑 가능합니다. 다른 행동을 하거나 엔터만 누르면 그룹핑 기회가 사라집니다."); break;
            case 41: System.out.println("그룹핑 가능합니다. 엔터만 입력하면 상대턴으로 넘어갑니다."); break;
            case 42: System.out.println("그룹핑 가능한 위치가 아닙니다."); break;
            case 43: System.out.println("그룹핑 가능한 상태가 아닙니다."); break;
            case 44: System.out.println("그룹핑 실패"); break;
            case 45: System.out.println("그룹핑 성공"); break;
            case 46: System.out.println("그룹핑 하지 않음"); break;
            //무인도
            case 50: System.out.println("무인도에 도착했습니다. 이번 턴에서는 더 이상 이동할 수 없습니다."); break;
            case 51: System.out.println("걸이 나오면 무인도에서 탈출할 수 있습니다."); break;

            case 99: System.out.println("승리!!!");
                     System.out.println("엔터를 누르면 메뉴 화면으로 이동합니다.");break;
            default: System.out.println("올바르지 않은 입력입니다."); break;
        }
    }

    /**
     * System
     * */
    //todo 설명 보강, 설정 보강
    //설명문 => 설정창 => 엔터 입력으로 메뉴로 이동
    public void description() {
        for(int i=0; i<50; i++) System.out.println();
        System.out.println("게임은 A, B팀으로 진행된다. 각 팀 당 말 4개(a, b, c, d)를 가지고 있으며 이 말들이 모두 먼저 난 팀이 승리한다.\n" +
                "방위는 시작 점 기준으로 시작점은 S, 시작점의 대각선 위쪽은 N, 시작점의 위쪽은 E, 시작점의 오른쪽은 W이다.\n" +
                "윷가락 던지기,움직이기, 그룹핑 하기는 명령어로 입력되며 각 명령어의 형식은 아래와 같다.\n\n" +
                "던지기: roll 또는 r \n" +
                "움직이기: move 또는 m [말 또는 그룹] [이동 칸 수] [이동 방향] (ex. move a 3 N) \n" +
                "그룹핑: grouping 또는 g [말 또는 그룹] [말 또는 그룹] (ex. grouping a b) \n\n" +
                "윷가락의 결과는 도, 개, 걸, 윷, 모, 백도 가 가능하며 각각 한 칸, 두 칸, 세 칸, 네 칸, 다섯 칸, 뒤로 한칸 이동한다.\n" +
                "윷이나 모가 나오면 추가로 윷가락을 던질 수 있고, 이동 후 다른 팀의 말을 잡을 때도 한 번 더 던질 수 있다.\n" +
                "만약 첫 '도' 위치에서 백도가 나오면 결승점(시작점)으로 이동하고, 결승점(시작점)에서 백도가 한번 더 나올 경우" +
                "W방위 쪽으로 한 칸 이동한다.\n");

        setting();
        System.out.println("Press Enter to Quit");
        sc.nextLine(); //Enter 입력을 기다림.
    }

    public void setting() {
        //??
    }

    //todo remove: too much comment > 이해완료 했으면 필요 이상의 주석은 제거하세요...
    public static void main(String[] args) {
        GameManager Gm = new GameManager();
    }
}
