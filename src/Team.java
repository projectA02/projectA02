import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class Team {
    public Horse[] horse;
    public HashSet<Horse> groupA;
    public HashSet<Horse> groupB;
    public boolean[] isEnd; // 난 말
    public char moved; // 직전에 움직인 말 그룹핑을 결정
    public int[] yut = new int[6]; // 백도,도,개,걸,윷,모
    public int rollCnt; // roll 던질 수 있는 횟수
    public int prevYut; //직전에 던진 윷 저장
    public int teamNum; // teamA:0 , teamB:1
    public boolean islandF;

    //생성자
    public Team(int n) {
        horse = new Horse[4];
        for(int i=0; i<horse.length; i++) horse[i] = new Horse();
        groupA = new HashSet<>();
        groupB = new HashSet<>();
        isEnd = new boolean[4]; // 자동으로 false 로 초기화 됩니다.
        rollCnt = 0;
        teamNum = n;
        //yut[2] = 2; //todo remove: check end turn grouping
        //for(int i=0; i<yut.length; i++) yut[i] = 10; //todo remove: check movement
        //yut[0] = 100; //todo remove: check back-do movement
        islandF = false;
    }

    /**
     * Move
     * */
    //움직여서 도착한 위치 반환, log 필요시 수정(first: -1 고정, second: log 처리 값)
    //return 11:그룹속함, 13:말X, 14:난 말, 16:그룹X, 21:실패, 22:거리이슈, 24:횟수이슈
    public Pair controller(String command, char h, int toMove, char direction) { // move controller
        Pair<Integer, Integer> res = new Pair<>(-1, 21);

        if(toMove > 5 | toMove < 0) return new Pair(-1, 22); //불가능한 이동인가
        else if(yut[toMove] < 1) return new Pair(-1, 24); //기회가 존재하는가

        if(h == 'A') { //h가 그룹일 때
            //그룹이 존재하는가
            if(groupA.isEmpty()) return new Pair(-1, 16);
            int end = -1;
            //해당 그룹에 포함된 말은 전부 좌표를 이동시킨다.
            for(int i=0; i< horse.length; i++) {
                if(groupA.contains(horse[i])) {
                    end = horse[i].move(toMove, direction);
                    switch (end) {
                        case  10: isEnd[i] = true; //그룹이 나면 해당 말도 남
                        case  20: res = horse[i].position; break; //이동한 그룹의 좌표 반환
                        default: return new Pair(-1, end); //이동 실패
                    }
                }
            }
            if(end == 1) groupA.clear(); //난 그룹에 대해 모든 이동 끝난 뒤 그룹 비우기
        }
        else if(h == 'B') { //h가 그룹일 때
            //그룹이 존재하는가
            if(groupB.isEmpty()) return new Pair(-1, 16);
            int end = -1;
            //해당 그룹에 포함된 말은 전부 좌표를 이동시킨다.
            for(int i=0; i< horse.length; i++) {
                if(groupB.contains(horse[i])) {
                    end = horse[i].move(toMove, direction);
                    switch (end) {
                        case  10: isEnd[i] = true; //그룹이 나면 해당 말도 남
                        case  20: res = horse[i].position; break; //이동한 그룹의 좌표 반환
                        default: return new Pair(-1, end); //이동 실패
                    }
                }
            }
            if(end == 1) groupB.clear(); //난 그룹에 대해 모든 이동 끝난 뒤 그룹 비우기
        }
        else {
            //말이 아닐 때
            if (h < 'a' | 'd' < h) return new Pair(-1, 13);
            //그룹에 속한 말일 경우 예외
            if (groupA.contains(horse[h - 'a']) || groupB.contains(horse[h - 'a'])) return new Pair(-1, 11);
            //이미 난 말일 경우 예외
            if (isEnd[h - 'a']) return new Pair(-1, 14);
            //말에 대한 예외처리 성공
            int end = horse[h - 'a'].move(toMove, direction);
            switch (end) {
                case 10:
                    isEnd[h - 'a'] = true; //말이 남
                case 20:
                    res = horse[h - 'a'].position;
                    break; //이동한 말의 좌표 반환
                default:
                    return new Pair(-1, end); //이동 실패
            }
        }

        //이동을 성공적으로 마치면 여기에 도달
        moved = h;
        yut[toMove]--;
        return res; //이동 후 좌표 반환
    }

    /**
     * Grouping
     * */
    //return: 11:그룹 속함, 12:마지막 X, 13:말X, 14:난 말, 16:그룹X, 42:겹침X, 44:실패, 45:성공 47 : not useGrouping
    public int controller(String command, char h1, char h2) { // grouping controller
        //기회가 있는가: 마지막에 움직인 말에 대해서만 그룹핑 가능
        if(moved != h1 & moved != h2) return 12;

        Pair<Integer, Integer> p1 = new Pair<>(-1, -1);
        Pair<Integer, Integer> p2 = new Pair<>(-1, -1);
        // case 0: horse + horse
        if (Character.isLowerCase(h1) && Character.isLowerCase(h2)) {
            //존재하는 말인가
            if(h1 < 'a' | 'd' < h1 | h2 < 'a' | 'd' < h2) return 13;
            //가능한 말인가: 그룹에 포함되어 있는가, 난 말인가
            if(groupA.contains(horse[h1 - 'a']) | groupB.contains(horse[h1 - 'a'])) return 11;
            if(groupA.contains(horse[h2 - 'a']) | groupB.contains(horse[h2 - 'a'])) return 11;
            if(isEnd[h1-'a'] | isEnd[h2-'a']) return 14;

            //기회가 있는가: 겹쳐진 말인가
            p1 = horse[h1 - 'a'].position;
            p2 = horse[h2 - 'a'].position;
            if (p1.first.equals(p2.first) & p1.second.equals(p2.second)) return grouping0(h1, h2);
            else return 42;
        }
        //case 2: group + group
        else if (Character.isUpperCase(h1) && Character.isUpperCase(h2)) {    // case 3 그룹 + 그룹
            //그룹이 존재하는가
            if(!((h1 == 'A' & h2 == 'B') | (h1 == 'B' & h2 == 'A'))) return 13;
            //가능한 그룹인가
            if(groupA.isEmpty() | groupB.isEmpty()) return 16;

            for (int i = 0; i < horse.length; i++) {
                if (groupA.contains(horse[i])) p1 = horse[i].position;
                if (groupB.contains(horse[i])) p2 = horse[i].position;
            }
            //기회가 있는가: 겹쳐져 있는가
            if (p1.first.equals(p2.first) & p1.second.equals(p2.second)) return grouping2(h1, h2);
            else return 42;
        }
        //case 1: group + horse
        else {                                                                // case 2 그룹 + 말
            char g = Character.isUpperCase(h1) ? h1 : h2;
            char h = Character.isLowerCase(h1) ? h1 : h2;
            HashSet tmpG;

            if(g == 'A') tmpG = groupA;
            else if(g == 'B') tmpG = groupB;
            else return 13;

            if(isEnd[h-'a']) return 14;
            if(h < 'a' | 'd' < h) return 13;
            if(groupA.contains(horse[h - 'a']) | groupB.contains(horse[h - 'a'])) return 11;

            for (int i = 0; i < horse.length; i++) {
                if (tmpG.contains(horse[i])) p1 = horse[i].position;
            }
            p2 = horse[h - 'a'].position;

            if (p1.first.equals(p2.first) & p1.second.equals(p2.second)) return grouping1(g, h);
            else return 42;
        }
    }

    //말 + 말 //return 44:실패, 45:성공
    public int grouping0(char h1, char h2) {
        //historyStack 동기화
        if(h1 == moved) {
            horse[h2 - 'a'].historyStack.clear();
            horse[h2 - 'a'].historyStack.addAll(horse[h1 - 'a'].historyStack);
        }
        else {
            horse[h1 - 'a'].historyStack.clear();
            horse[h1 - 'a'].historyStack.addAll(horse[h2 - 'a'].historyStack);
        }
        //그룹핑
        if(groupA.isEmpty()) {
            groupA.add(horse[h1 - 'a']);
            groupA.add(horse[h2 - 'a']);
            return 45;
        }
        else if(groupB.isEmpty()){
            groupB.add(horse[h1 - 'a']);
            groupB.add(horse[h2 - 'a']);
            return 45;
        }
        else return 44;
    }
    //그룹 + 말
    public int grouping1(char g, char h){
        if(g == 'A') {
            for (int i = 0; i < horse.length; i++) {
                if (groupA.contains(horse[i])) {
                    if(h == moved) {
                        horse[i].historyStack.clear();
                        horse[i].historyStack.addAll(horse[h-'a'].historyStack);
                    }
                    else {
                        horse[h-'a'].historyStack.clear();
                        horse[h-'a'].historyStack.addAll(horse[i].historyStack);
                    }
                }
            }
            groupA.add(horse[h - 'a']);
            return 45;
        }
        else if(g == 'B') {
            groupB.add(horse[h - 'a']);
            for (int i = 0; i < horse.length; i++) {
                if (groupB.contains(horse[i])) {
                    if(h == moved) {
                        horse[i].historyStack.clear();
                        horse[i].historyStack.addAll(horse[h-'a'].historyStack);
                    }
                    else {
                        horse[h-'a'].historyStack.clear();
                        horse[h-'a'].historyStack.addAll(horse[i].historyStack);
                    }
                }
            }
            return 45;
        }
        else return 44;
    }
    //그룹 + 그룹
    public int grouping2(char g1, char g2) {
        Stack<Pair<Integer, Integer>> tmpStack1 = new Stack<>();
        Stack<Pair<Integer, Integer>> tmpStack2 = new Stack<>();
        //tmpStack 에 히스토리 저장
        for (int i = 0; i < horse.length; i++) {
            if(groupA.contains(horse[i])) {
                tmpStack1.clear();
                tmpStack1.addAll(horse[i].historyStack);
            }
            if(groupB.contains(horse[i])) {
                tmpStack2.clear();
                tmpStack2.addAll(horse[i].historyStack);
            }
        }
        //grouping
        groupA.addAll(groupB);
        groupB.clear();
        //Group A만 남으니까 A에 있는 말 동기화
        for (int i = 0; i < horse.length; i++) {
            if(groupA.contains(horse[i])) {
                horse[i].historyStack.clear();
                if(g1 == moved) horse[i].historyStack.addAll(tmpStack1);
                else horse[i].historyStack.addAll(tmpStack2);
            }
        }
        return 45;
    }

    /**
     * Roll AND Yut
     * https://keichee.tistory.com/312 -> ref
     * */
    //return: 30:성공, 31:기회X, 32:한번더
    public int controller(String command,boolean useFall) {
        if(rollCnt < 1) return 31; //기회가 있는가
        prevYut = roll(useFall);
        //prevYut = 0; //todo remove: check end turn back-do
        if (prevYut == 6 ) {
            rollCnt = 0; // roll cnt 초기화
            Arrays.fill(yut,0); // yut 배열 0 으로 초기화
            return 34; //
        }
       // yut[prevYut]++;

        if (islandF) {
            if (prevYut == 3)   yut[prevYut]++;
            islandF = false;
            rollCnt = 0;
            return 30;
        } else                  yut[prevYut]++;
        if(prevYut == 4 | prevYut == 5) return 32;
        return 30;
    }
    //윷가락을 던저 나온 값 반환
    public int roll(boolean useFall) {
        int yut;
        // todo : useFall 해결
        if(useFall){ // 낙 허용
            int num = new Random().nextInt(10500);
            rollCnt--;
            if(num < 625) yut = 0; // 백도
            else if(num < 625 + 1875) yut = 1; // 도
            else if(num < 625 + 1875 + 3750) yut = 2; // 개
            else if(num < 625 + 1875 + 3750 + 2500) yut = 3; // 걸
            else if(num < 625 + 1875 + 3750 + 2500 + 625) yut = 4; // 윷
            else if(num < 625 + 1875 + 3750 + 2500 + 625 + 625) yut = 5; // 모
            else yut = 6;  // 낙
        }else{
            int num = new Random().nextInt(10000);
            rollCnt--;
            if(num < 625) yut = 0; // 백도
            else if(num < 625 + 1875) yut = 1; // 도
            else if(num < 625 + 1875 + 3750) yut = 2; // 개
            else if(num < 625 + 1875 + 3750 + 2500) yut = 3; // 걸
            else if(num < 625 + 1875 + 3750 + 2500 + 625) yut = 4; // 윷
            else yut = 5; // 모
        }
        if(yut == 4 | yut == 5) rollCnt++;

        return yut;
    }

    /**
     * For System
     * */
    //drawMap 하기 전에 각 말의 위치 갱신: 기존 코드 상 말 이동 표시한 후 다시 갱신하길래 따로 뽑음
    public void marking(int[][] board) {
        int mark = teamNum * 10; // teamA:0, teamB:10

        for(int i = 0; i < horse.length; i++) {
            //출발하지 않은 말, 난 말에 대한 처리
            if (horse[i].historyStack.isEmpty() | isEnd[i]) continue;

            board[horse[i].position.first][horse[i].position.second] = mark + i + 1;
        }
        if(!groupA.isEmpty()) {
            for(int i = 0; i < horse.length; i++) {
                if(groupA.contains(horse[i]))
                    board[horse[i].position.first][horse[i].position.second] = mark + 5;
            }
        }
        if(!groupB.isEmpty()) {
            for(int i = 0; i < horse.length; i++) {
                if(groupB.contains(horse[i]))
                    board[horse[i].position.first][horse[i].position.second] = mark + 6;
            }
        }
    }
    //윷 이미지와 윷 현황 출력: (+)printYut
    public void printSrc() {
        printYut(prevYut);
        System.out.println("<윷 현황>");
        System.out.println("남은 기회: " + rollCnt);
        System.out.println("백도:" + yut[0] + " 도:" + yut[1] + " 개:" + yut[2] + " 걸:" + yut[3] + " 윷:" + yut[4] + " 모:" + yut[5]);
        System.out.println();
    }
    //윷 이미지 출력
    public void printYut(int yut) {
        switch (yut) {
            case 0: // 백도
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐ ┌─┐   \s
                        │X│ │X│ │X│ │ │   \s
                        │X│ │X│ │X│ │X│   \s
                        │X│ │X│ │X│ │ │   \s
                        └─┘ └─┘ └─┘ └─┘   \s""");
                break;
            case 1: // 도
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐ ┌─┐   \s
                        │X│ │X│ │X│ │ │   \s
                        │X│ │X│ │X│ │ │   \s
                        │X│ │X│ │X│ │ │   \s
                        └─┘ └─┘ └─┘ └─┘   \s""");
                break;
            case 2: // 개
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐ ┌─┐   \s
                        │X│ │X│ │ │ │ │   \s
                        │X│ │X│ │ │ │ │   \s
                        │X│ │X│ │ │ │ │   \s
                        └─┘ └─┘ └─┘ └─┘   \s""");
                break;
            case 3: // 걸
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐ ┌─┐   \s
                        │X│ │ │ │ │ │ │   \s
                        │X│ │ │ │ │ │ │   \s
                        │X│ │ │ │ │ │ │   \s
                        └─┘ └─┘ └─┘ └─┘   \s""");
                break;
            case 4: // 윷
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐ ┌─┐   \s
                        │ │ │ │ │ │ │ │   \s
                        │ │ │ │ │ │ │X│   \s
                        │ │ │ │ │ │ │ │   \s
                        └─┘ └─┘ └─┘ └─┘   \s""");
                break;
            case 5: // 모
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐ ┌─┐   \s
                        │X│ │X│ │X│ │X│   \s
                        │X│ │X│ │X│ │X│   \s
                        │X│ │X│ │X│ │X│   \s
                        └─┘ └─┘ └─┘ └─┘   \s""");
                break;
            default : // 낙
                System.out.println("""
                        ┌─┐ ┌─┐ ┌─┐    \s
                        │X│ │X│ │X│    \s
                        │X│ │X│ │X│    \s
                        │X│ │X│ │X│ ┌─────────┐\s
                        └─┘ └─┘ └─┘ └─────────┘\s
                        """);
        }
    }
    //모든 말이 나서 게임이 끝났는가
    public boolean checkIsEnd() {
        for(boolean check : isEnd) if(!check) return false;
        return true;
    }
    //턴이 끝났는가, canGroup 은 GameManager 에서 관리
    public boolean isTurnEnd() {
        if(rollCnt > 0) return false;   //윷가락 던질 기회 남았을 때

        for(int i=1; i<yut.length; i++) { //도,개,걸,윷,모가 남았을 때
            if(yut[i] > 0) return false;
        }
        if(yut[0] > 0) {    //백도가 남았는데 윷판에 말이 있을 때
            for(int i=0; i< horse.length; i++) {
                //출발하지 않았을 때 또는 종료되었을 때는 윷판에 없다.
                if(horse[i].historyStack.isEmpty() | isEnd[i]) continue;

                return false;
            }
        }
        return true;
    }

    //말 현황 출력
    public void printHorse() {
        char ch;
        if(teamNum == 0) ch = '①';
        else if(teamNum == 1) ch = '❶';
        else ch = '$';

        System.out.print((char)('A' + teamNum) + "팀  ");
        for(int i = 0; i < horse.length; i++) {
            System.out.print((char)('a' + i) + " : ");
            //출발하지 않은 말
            if(horse[i].historyStack.isEmpty())
                System.out.print((char)(ch + i)+"  ");
            //윷판 위에 존재하는 말
            else
                System.out.print("ㅤ  ");
        }
        System.out.println();
    }
}
