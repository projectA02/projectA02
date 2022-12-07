package config;



public enum BaseLogStatus {
    /**
     * @about : input
     * */
    INPUT_CORRECT_COMMAND(0,"명령어를 입력하세요."),
    INCORRECT_INPUT_COMMAND(1,"올바르지 않은 입력입니다.[move | roll | grouping]"),
    INCORRECT_INPUT_MOVE_COMMAND(2,"올바르지 않은 입력입니다.[move] [말][이동칸][방향]"),
    INCORRECT_INPUT_ROLL_COMMAND(3,"올바르지 않은 입력입니다.[roll]"),
    INCORRECT_INPUT_GROUPING_COMMAND(4,"\"올바르지 않은 입력입니다.[grouping] [말][말]\""),
    CHANGE_TURN(5,"가능한 행동이 없습니다. 엔터만 입력하면 상대 턴으로 넘어갑니다."),

    /**
     * @about : horse
     * */
    HORSE_OUTING(10,"말이 났습니다."),
    HORSE_GROUPED(11,"그룹에 속해 있는 말입니다."),
    HORSE_LAST_MOVED(12,"마지막으로 움직인 말이 아닙니다."),
    NOT_HORSE_OR_GROUP(13,"말 또는 그룹이 아닙니다.[말: a, b, c, d][그룹: A, B]"),
    HORSE_OUTED(14,"난 말입니다."),
    NOT_STARTED_HORSE(15,"출발하지 않은 말입니다."),
    NOT_EXIST_GROUP(16,"존재하지 않는 그룹입니다."),

    /***
     * @about : move
     */
    SUCCESS_MOVE(20,"이동 성공"),
    FAIL_MOVE(21,"이동 실패"),
    NOT_MOVABLE_DISTANCE(22,"이동 가능한 거리가 아닙니다.[백도: 0, 도: 1, 개: 2, 걸: 3, 윷: 4, 모: 5"),
    NOT_MOVABLE_DIRECTION(23,"이동 가능한 방향이 아닙니다.[1시: E, 4시: S, 7시: W, 10시: N]"),
    FALL_MOVE_COUNT(24,"이동 횟수가 부족합니다."),
    NOT_EXIST_HORSE_BACK(25,"백도를 이동할 말이 존재하지 않습니다. 엔터만 입력하면 상대턴으로 넘어갑니다"),

    /**
     * @about : roll
     * */
    SUCCESS_ROLL(30,"던지기 성공"),
    NOT_EXIST_ROLL_COUNT(31,"던질 기회가 없습니다."),
    CAN_ROLL_AGAIN(32,"한번 더 던질 수 있습니다."),
    CATCH_HORSE(33,"잡았습니다. 한번 더 던질 수 있습니다."),

    /**
     * @about : grouping
     * */
    GROUPING_WAIT(40,"그룹핑 가능합니다. 다른 행동을 하거나 엔터만 누르면 그룹핑 기회가 사라집니다."),
    GROUPING_ENTER(41,"그룹핑 가능합니다. 엔터만 입력하면 상대턴으로 넘어갑니다."),
    NOT_GROUPING_POSITION(42,"그룹핑 가능한 위치가 아닙니다."),
    NOT_GROUPING_STATUS(43,"그룹핑 가능한 상태가 아닙니다."),
    FAIL_GROUPING(44,"그룹핑 실패"),
    SUCCESS_GROUPING(45,"그룹핑 성공"),
    DO_NOT_GROUPING(46,"그룹핑 하지 않음"),

    VICTORY(99,"승리!!!"),
    GO_TO_MENU(100,"엔터를 누르면 메뉴 화면으로 이동합니다."),
    INCORRECT_INPUT(101,"올바르지 않은 입력입니다.");
    //switch (op) {
        /**
         * @about : input
         * */
        //case  0: System.out.println("명령어를 입력하세요."); break;
        //case  1: System.out.println("올바르지 않은 입력입니다.[move | roll | grouping]"); break;
        //case  2: System.out.println("올바르지 않은 입력입니다.[move] [말][이동칸][방향]"); break;
        //case  3: System.out.println("올바르지 않은 입력입니다.[roll]"); break;
        //case  4: System.out.println("올바르지 않은 입력입니다.[grouping] [말][말]"); break;
        //case  5: System.out.println("가능한 행동이 없습니다. 엔터만 입력하면 상대 턴으로 넘어갑니다."); break;
        /**
         * @about : horse
         * */
         //case 10: System.out.println("말이 났습니다."); break;
         //case 11: System.out.println("그룹에 속해 있는 말입니다."); break;
         //case 12: System.out.println("마지막으로 움직인 말이 아닙니다."); break;
         //case 13: System.out.println("말 또는 그룹이 아닙니다.[말: a, b, c, d][그룹: A, B]"); break;
         //case 14: System.out.println("난 말입니다."); break;
         //case 15: System.out.println("출발하지 않은 말입니다."); break;
         //case 16: System.out.println("존재하지 않는 그룹입니다."); break;

         //move에 관한
         //case 20: System.out.println("이동 성공"); break;
         //case 21: System.out.println("이동 실패"); break;
         //ase 22: System.out.println("이동 가능한 거리가 아닙니다.[백도: 0, 도: 1, 개: 2, 걸: 3, 윷: 4, 모: 5"); break;
         //case 23: System.out.println("이동 가능한 방향이 아닙니다.[1시: E, 4시: S, 7시: W, 10시: N]"); break;
         //case 24: System.out.println("이동 횟수가 부족합니다."); break;
         //case 25: System.out.println("백도를 이동할 말이 존재하지 않습니다. 엔터만 입력하면 상대턴으로 넘어갑니다"); break;

         //roll에 관한
         //case 30: System.out.println("던지기 성공"); break;
         //case 31: System.out.println("던질 기회가 없습니다."); break;
         //case 32: System.out.println("한번 더 던질 수 있습니다."); break;
        //case 33: System.out.println("잡았습니다. 한번 더 던질 수 있습니다."); break;
        //grouping에 관한
        //case 40: System.out.println("그룹핑 가능합니다. 다른 행동을 하거나 엔터만 누르면 그룹핑 기회가 사라집니다."); break;
        //case 41: System.out.println("그룹핑 가능합니다. 엔터만 입력하면 상대턴으로 넘어갑니다."); break;
        //case 42: System.out.println("그룹핑 가능한 위치가 아닙니다."); break;
        //case 43: System.out.println("그룹핑 가능한 상태가 아닙니다."); break;
        //case 44: System.out.println("그룹핑 실패"); break;
        //case 45: System.out.println("그룹핑 성공"); break;
        //case 46: System.out.println("그룹핑 하지 않음"); break;
    //
        //case 99: System.out.println("승리!!!");
          //  System.out.println("엔터를 누르면 메뉴 화면으로 이동합니다.");break;
        //default: System.out.println("올바르지 않은 입력입니다."); break;
    //
    //}
    //private final boolean isSuccess;
    private final int code;
    private final String message;
    private BaseLogStatus(/*boolean isSuccess ,*/ int code, String message) {
        //this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
