import java.util.*;
public class Horse {

    public Pair<Integer, Integer> position = new Pair<>(6, 6);
    public Stack<Pair<Integer, Integer>> historyStack = new Stack<Pair<Integer, Integer>>(); // 한칸,한칸 마다 기록
    // 백도
    // todo : mark unicode로 넣어주기
    char[] mark = {'1', '2', '3', '4'};

    public void move(int toMove, char direction) {

        // 백도일때 처리
        if (toMove == 0) {
            if (historyStack.isEmpty()) {
                // todo: historyStack이 비었을 때 채워줘야함.
            }
            position = historyStack.pop();
            return; // move 종료
        }

        int dy = 0;
        int dx = 0;
        int x_pos; // x좌표
        int y_pos; // y좌표

        while (toMove > 0) {    // 움직일 기회 0되면 종료
            historyStack.push(position);    // 자취 등록

            y_pos = position.first.intValue();
            x_pos = position.second.intValue();

            // 출발 분기점. 일단 분기점이어서 따로 만듬.
            if (y_pos == 6 && x_pos == 6) {
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
            else if (y_pos == 0 && x_pos == 6) {
                dx = 1;
            }
            // 말이 (3,3)에 있는 경우
            else if (y_pos == 6 && x_pos == 0) {
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

            // --------------여기서부터 분기점 아닌 칸들---------
            // 오른쪽 라인 분기점 제외 4개
            else if (x_pos == 6 && (1 <= y_pos && y_pos <= 5)) {
                dy = -1;
            }
            // 왼쪽 라인 분기점 제외 4개
            else if (x_pos == 1 && (1 <= y_pos && y_pos <= 5)) {
                dy = 1;
            }
            // 위쪽 라인 분기점 제외 4개
            else if (y_pos == 0 && (1 <= x_pos && x_pos <= 5)) {
                dx -= -1;
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
                return
                // todo: 잘못된 칸에 들어온 경우로 오류발생시켜줘야함.
            }

            x_pos = x_pos + dx;
            y_pos = y_pos + dy;

            // (3,0), (0,3), (3,6), (6,3) 인 경우 뛰어넘기
            if ((x_pos == 3 && (y_pos == 0 || y_pos == 6)) || (y_pos == 3 && (x_pos == 0 || x_pos == 6))) {
                x_pos = x_pos + dx;
                y_pos = y_pos + dy;
            }

            // 도착지에 도착했을 경우
            if (x_pos == 6 || y_pos == 6) {
                // todo: 게임 끝
                return;
            }

            // 현재 위치 및 남은 이동횟수 업데이트
            position.first = new Integer(y_pos);
            position.second = new Integer(x_pos);
            toMove--;
            direction = 'A';    // 방향 없애주기 임의로 A로 함
        }
    }

}