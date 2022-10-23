
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Team {
    public Horse[] horse;// = new Horse[4];
    public Set<Horse> groupA;// = new Horse[4];
    public Set<Horse> groupB;// = new Horse[4];
    public boolean[] isEnd;// = new boolean[4];
    public int yut[] = new int[6]; // 백도,도,개,걸,윷,모
    public int rollCnt; // roll 던질 수 있는 횟수

    /**
     * todo : 생성자 작성
     */
    public Team() {
        this.horse = new Horse[4];
        this.groupA = new HashSet<Horse>();
        this.groupB = new HashSet<Horse>();
        this.isEnd = new boolean[4];
        this.rollCnt = 0;
    }

    /**
     * roll 이 하는 역할 : 윷 굴리기
     * todo : 윷 확률 결정해주기
     * https://keichee.tistory.com/312 -> ref
     */
    public void roll() {
        int yut = 0;
        int count = 10;
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            int num = random.nextInt(10000);
            //System.out.println(num + "\n");
            if (num < 625)
                yut = 0;
            else if (num < 625 + 1875)
                yut = 1;
            else if (num < 625 + 1875 + 3750)
                yut = 2;
            else if (num < 625 + 1875 + 3750 + 2500)
                yut = 3;
            else if (num < 625 + 1875 + 3750 + 2500 + 625)
                yut = 4;
            else
                yut = 5;
            printYut(yut);
        }

    }

    /**
     * 말들이 낫는지를 체크
     */
    public boolean checkIsEnd() {
        boolean check = true;
        // 하나라도 false 존재 -> false;
        for (int i = 0; i < 4; i++) {
            if (isEnd[i] == false) check = false;
        }
        return check;
    }

    /**
     * 실제 그룹 배열
     * 그룹핑 어떻게 할지
     */
    public void grouping(char h1, char h2) {
        /** 명령어 검사(GameManager의 checkCommander) 에서
         * 말 또는 그룹 문자를 Parse 한 것을 grouping의 인자로 받는다
         * 입력의 세 가지 경우:
         * 1. 말 + 말 (둘 다 소문자)-> 비어 있는 그룹에 말 추가
         * 2. 말 + 그룹 / 그룹 + 말 (소문자, 대문자)-> 입력된 그룹에 말 추가
         * 3. 그룹 + 그룹 (둘 다 대문자)-> 그룹을 하나로 병합, 나머지 그룹은 초기화
         */
        if (Character.isLowerCase(h1) && Character.isLowerCase(h2)) {           // case 1
            if (groupA.isEmpty()) {
                groupA.add(horse[h1 - 'a']);
                groupA.add(horse[h2 - 'a']);
            } else {
                groupB.add(horse[h1 - 'a']);
                groupB.add(horse[h2 - 'a']);
            }
        } else if (Character.isUpperCase(h1) && Character.isUpperCase(h2)) {    // case 3
            while (!groupB.isEmpty()) {
                groupA.addAll(groupB);
                groupB.clear();
            }
        } else {                                                                // case 2
            char g = Character.isUpperCase(h1) ? h1 : h2;
            char h = Character.isLowerCase(h1) ? h1 : h2;
            if (g == 'A')   groupA.add(horse[h - 'a']);
            else            groupB.add(horse[h - 'a']);
        }
    }

    public void printYut(int yut) {
        switch (yut) {
            case 0: // 백도
                System.out.println("" +
                        "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
                        "│X│ │X│ │X│ │ │    \n" +
                        "│X│ │X│ │X│ │X│    \n" +
                        "│X│ │X│ │X│ │ │   \n" +
                        "└─┘ └─┘ └─┘ └─┘    ");
                break;
            case 1: // 도
                System.out.println("" +
                        "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
                        "│X│ │X│ │X│ │ │    \n" +
                        "│X│ │X│ │X│ │ │    \n" +
                        "│X│ │X│ │X│ │ │   \n" +
                        "└─┘ └─┘ └─┘ └─┘    ");
                break;
            case 2: // 개
                System.out.println("" +
                        "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
                        "│X│ │X│ │ │ │ │    \n" +
                        "│X│ │X│ │ │ │ │    \n" +
                        "│X│ │X│ │ │ │ │   \n" +
                        "└─┘ └─┘ └─┘ └─┘    ");
                break;
            case 3: // 걸
                System.out.println("" +
                        "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
                        "│X│ │ │ │ │ │ │    \n" +
                        "│X│ │ │ │ │ │ │    \n" +
                        "│X│ │ │ │ │ │ │   \n" +
                        "└─┘ └─┘ └─┘ └─┘    ");
                break;
            case 4: // 윷
                System.out.println("" +
                        "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
                        "│ │ │ │ │ │ │ │    \n" +
                        "│ │ │ │ │ │ │X│    \n" +
                        "│ │ │ │ │ │ │ │   \n" +
                        "└─┘ └─┘ └─┘ └─┘    ");
                break;
            case 5: // 모
                System.out.println("" +
                        "┌─┐ ┌─┐ ┌─┐ ┌─┐     \n" +
                        "│X│ │X│ │X│ │X│    \n" +
                        "│X│ │X│ │X│ │X│    \n" +
                        "│X│ │X│ │X│ │X│   \n" +
                        "└─┘ └─┘ └─┘ └─┘    ");
                break;
        }

    }

}
