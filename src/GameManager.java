public class GameManager {
    public Team teamA = new Team();
    public Team teamB = new Team();
    private boolean turn; // True : A 팀, false : B 팀

    public void showMenu(){
        System.out.println("This is Menu");
        for(int i = 1;i<=3;i++){
            System.out.println(i+". menu");
        }
    }
    /**
     * 명령어 확인
     * */
    public void checkCommand(){
        //양 끝 공백 제거 String.trim()
        //switch-case
        //roll이면->roll()
        //grouping이면->
        //move면->
    }
    /**
     * map 그리기
     * */
    public void drawMap() {

    }
    /**
     * 이동한 위치에 상대말이 있는지
     * */
    public void checkHorse(){

    }
    public void roll(){

    }

}
