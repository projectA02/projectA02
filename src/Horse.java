import java.util.*;

public class Horse {
    public Pair<Integer, Integer> position; // position
    public Stack<Pair<Integer, Integer>> historyStack = new Stack<Pair<Integer, Integer>>(); // 한칸,한칸 마다 기록

    public Horse(){
        this.position = new Pair<>(6, 6);
    }

    public boolean move(int toMove, char direction) {

        // 백도일때 처리
        if (toMove == 0) {
            // historyStack이 비어있을 때

            // 스택 내용 확인
            for (int i =0; i< historyStack.size(); i++){
                Pair<Integer, Integer> p = new Pair<>(0,0); // position
                p = historyStack.elementAt(i);
                System.out.println(p.first+"," +p.second);
            }


            historyStack.pop();
            if (historyStack.isEmpty()) {
                Integer i_x = 6, j_y = 6;

                for (j_y = 6; j_y >= 0; j_y--) {
                    if (j_y == 3)
                        continue;
                    historyStack.push(new Pair<>(j_y, i_x));
                }
                j_y = 0;
                for (i_x = 5; i_x >= 0; i_x--) {
                    if (i_x == 3)
                        continue;
                    historyStack.push(new Pair<>(j_y, i_x));
                }
                i_x = 0;
                for (j_y = 1; j_y <= 6; j_y++) {
                    if (j_y == 3)
                        continue;
                    historyStack.push(new Pair<>(j_y, i_x));
                }
                j_y = 6;
                for (i_x = 1; i_x <= 6; i_x++) {
                    if (i_x == 3)
                        continue;
                    historyStack.push(new Pair<>(j_y, i_x));
                }

            }

            position = historyStack.peek();

        }

        int dy = 0; // 이동할 x축 방향 (-1, 0, 1)
        int dx = 0; // 이동할 y축 방향 (-1, 0, 1)
        int x_pos; // x좌표
        int y_pos; // y좌표
        Integer c_y = position.first;   // 현재위치 y좌표
        Integer c_x = position.second;  // 현재위치 x좌표

        /**
         알고리즘 설명
         1. toMove
         2. 분기점에서 출발한다면 방향을 고려해서 나아감
         3. 현재 위치에서 방향을 고려해서 나아감
         */

        while (toMove > 0) {    // 움직일 기회 0되면 종료
            // 현재 위치 int형으로 변환한 값
            y_pos = position.first.intValue();
            x_pos = position.second.intValue();
            System.out.println(y_pos+":"+x_pos);
            // 출발 분기점. 일단 분기점이어서 따로 만듬.
            if ((y_pos == 6 && x_pos == 6) && historyStack.isEmpty() == true) {
                dy = -1;
            }
            // 말이 (0,6)에 있는 경우
            else if (y_pos == 0 && x_pos == 6) {
                if (direction == 'N') {
                    dx = -1;
                } else if (direction == 'W') {
                    dy = 1;
                    dx = -1;
                }
                // 이어서 오는경우
                else {
                    dx = -1;
                }
            }
            // 말이 (0,0)에 있는 경우
            else if (y_pos == 0 && x_pos == 0) {
                if (direction == 'W') {
                    dy = 1;
                } else if (direction == 'S') {
                    dy = 1;
                    dx = 1;
                }
                // 이어서 오는경우
                else {
                    dy = 1;
                }
            }
            // 말이 (6,0)에 있는 경우
            else if (y_pos == 6 && x_pos == 0) {
                dx = 1;
            }
            // 말이 (3,3)에 있는 경우
            else if (y_pos == 3 && x_pos == 3) {
                if (direction == 'W') {
                    dy = 1;
                    dx = -1;
                } else if (direction == 'S') {
                    dy = 1;
                    dx = 1;
                } else {   // 이어서 오는경우
                    if (historyStack.peek().first < 3) {
                        dy = 1;
                        dx = 1;
                    } else {
                        dy = 1;
                        dx = -1;
                    }
                }
            }

            // 말이 (6,6)에 있는 경우  // 해당말 끝
            else if (y_pos == 6 && x_pos == 6) {
                y_pos = 6;
                x_pos = 7;
                position.first = Integer.valueOf(y_pos);
                position.second = Integer.valueOf(x_pos);
                return true;
            }
//            else if (y_pos == 6 && x_pos == 7) {
//                continue;
//            }

            // --------------여기서부터 분기점 아닌 칸들-------------
            // 오른쪽 라인 분기점 제외 4개
            else if (x_pos == 6 && (1 <= y_pos && y_pos <= 5)) {
                dy = -1;
            }
            // 왼쪽 라인 분기점 제외 4개
            else if (x_pos == 0 && (1 <= y_pos && y_pos <= 5)) {
                dy = 1;
            }
            // 위쪽 라인 분기점 제외 4개
            else if (y_pos == 0 && (1 <= x_pos && x_pos <= 5)) {
                dx = -1;
            }
            // 아래쪽 라인 분기점 제외 4개
            else if (y_pos == 6 && (1 <= x_pos && x_pos <= 5)) {
                dx = 1;
            }
            // 왼쪽 위 분기점 제외 2개
            else if ((y_pos == 1 || y_pos == 2) && (x_pos == 1 || x_pos == 2)) {
                dy = 1;
                dx = 1;
            }
            // 오른쪽 아래 분기점 제외 2개
            else if ((y_pos == 4 || y_pos == 5) && (x_pos == 4 || x_pos == 5)) {
                dy = 1;
                dx = 1;
            }
            // 오른쪽 위 분기점 제외 2개
            else if ((y_pos == 1 || y_pos == 2) && (x_pos == 4 || x_pos == 5)) {
                dy = 1;
                dx = -1;
            }
            // 왼쪽 아래 분기점 제외 2개
            else if ((y_pos == 4 || y_pos == 5) && (x_pos == 1 || x_pos == 2)) {
                dy = 1;
                dx = -1;
            }
            //잘못된 칸에 들어가면 오류 발생
            else {
                System.out.println("MOVE함수 오류 잘못들어왔습니다! 오류난 좌표" + "(" + position.first + ", " + position.second + ")");
            }


            x_pos = x_pos + dx;
            y_pos = y_pos + dy;

            // (3,0), (0,3), (3,6), (6,3) 인 경우 뛰어넘기
            if ((x_pos == 3 && (y_pos == 0 || y_pos == 6)) || (y_pos == 3 && (x_pos == 0 || x_pos == 6))) {
                x_pos = x_pos + dx;
                y_pos = y_pos + dy;
            }
            System.out.println("이동중인 좌표 : "+x_pos+","+y_pos);
            // 현재 위치 및 남은 이동횟수 업데이트
            position.first = Integer.valueOf(y_pos);
            position.second = Integer.valueOf(x_pos);

            // 변수 초기화
            toMove--;   // 남은 이동횟수 차감
            dx = 0;     // dx 0으로 초기화
            dy = 0;     // dy 0으로 초기화
            direction = 'Z';    // 방향 없애주기 없는 방향은 Z로 함
            c_y = position.first;
            c_x = position.second;

            historyStack.push(new Pair<>(c_y, c_x));    // 자취 등록
        }

        return false;   // 말이 나지 않았으므로 false 반환
    }
}
