import java.lang.String;

import static com.sun.org.apache.xerces.internal.util.XMLChar.trim;

public class GameManager {
    public Team teamA = new Team(1);
    public Team teamB = new Team(2);
    private boolean turn; // True : A 팀, false : B 팀
    public int[][] board = {
            {0,0,0,-1,0,0,0},
            {0,0,-1,-1,-1,0,0},
            {0,-1,0,-1,0,-1,0},
            {-1,-1,-1,0,-1,-1,-1},
            {0,-1,0,-1,0,-1,0},
            {0,0,-1,-1,-1,0,0},
            {0,0,0,-1,0,0,0},
    };

    public void showMenu(){
        System.out.println("Menu");
        System.out.println("1. 게임 시작하기");
        System.out.println("2. 설명 보기");
        System.out.println("3. 종료하기");
        System.out.println("This is Menu");
    }
    /**
     * 명령어 확인
     * */
    public void checkCommand(String str){
        String cmd[] = trim(str).split(" ");//양쪽 공백 날리고, 공백으로 명령어랑 인자 구분
        switch(cmd[0].toLowerCase()){//명령어 부분만 소문자로 변환
            case "move":
            case "m":
                //cmd[]
                //todo : 인자부분 처리
                break;
            case "roll":
            case "r":
                break;
            case "grouping":
            case "g":

                break;
        }
    }
    /**
     * map 그리기
     * */
    public void drawMap() {
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                switch(board[i][j]){
                    case -1:
                        System.out.print("  ");
                        break;
                    case 0:
                        System.out.print("○");
                        break;
                    case 1:
                        System.out.print("ⓐ");
                        break;
                    case 2:
                        System.out.print("ⓑ");
                        break;
                    case 3:
                        System.out.print("ⓒ");
                        break;
                    case 4:
                        System.out.print("ⓓ");
                        break;
                    case 5:
                        System.out.print("Ⓐ");
                        break;
                    case 6:
                        System.out.print("Ⓑ");
                        break;
                    case 11:
                        System.out.print("○");
                        break;
                    case 12:
                        System.out.print("○");
                        break;
                    case 13:
                        System.out.print("○");
                        break;
                    case 14:
                        System.out.print("○");
                        break;
                    case 15:
                        System.out.print("");
                        break;
                    case 16:
                        System.out.print("○");
                        break;
                }
            }
        }
    }
    /**
     * 이동한 위치에 상대말이 있는지
     * */
    public void checkHorse(){

    }

}