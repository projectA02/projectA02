import java.util.Random;

public class Team {
    public Horse[] horse;// = new Horse[4];
    public Horse[] groupA;// = new Horse[4];
    public Horse[] groupB;// = new Horse[4];
    public boolean[] isEnd;// = new boolean[4];
    public int yut[] = new int[6]; // 백도,도,개,걸,윷,모
    public int rollCnt; // roll 던질 수 있는 횟수

    /**
     * todo : 생성자 작성
     * */
    public Team(){
        this.horse = new Horse[4];
        this.groupA = new Horse[4];
        this.groupB = new Horse[4];
        this.isEnd = new boolean[4];
        this.rollCnt = 0;
    }
    /**
     * roll 이 하는 역할 : 윷 굴리기
     * todo : 윷 확률 결정해주기
     * https://keichee.tistory.com/312 -> ref
     * */
    public void roll(){
        int yut=0;
        int count =10;
        for(int i =0 ;i<count;i++) {
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
     * */
    public boolean checkIsEnd(){
        boolean check = true;
        // 하나라도 false 존재 -> false;
        for(int i = 0;i<4;i++){
            if(isEnd[i]==false) check = false;
        }
        return check;
    }

    /**
     * 실제 그룹 배열
     * 그룹핑 어떻게 할지
     * 
     * */
    public void grouping(){

    }
    public void printYut(int yut){
        switch (yut){
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
