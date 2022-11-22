import java.util.Stack;

public class Horse {
    public Pair<Integer, Integer> position;
    public Stack<Pair<Integer, Integer>> historyStack; //한칸 마다 기록
    public boolean isVertex; //분기점에서 시작하는가?

    //생성자
    public Horse() {
        position = new Pair<>(6, 6);
        historyStack = new Stack<>();
        isVertex = false;
    }

    //return 10:도착, 14:난 말, 15:출발X, 20:이동성공, 23:방향이슈
    public int move(int toMove, char direction) {
        int y = position.first.intValue(), x = position.second.intValue();
        //난 말은 이동 불가
        if(y == 6 & x == 7) return 14;
        /**
         * check Vertex with Direction
         * */
        //분기점 => S E N W & C
        if ((y == 6 | y == 0) & (x == 6 | x == 0)) isVertex = true;
        else if (y == 3 & x == 3) isVertex = true;
        else isVertex = false;

        /**
         * Back-DO
         * */
        if (toMove == 0) {
            //아직 출발하지 않은 말
            if(historyStack.isEmpty()) return 15;
            //자취에서 자기 자신 제거 & 자취가 없을 때 추가
            historyStack.pop();
            if (historyStack.isEmpty()) {
                //자기 자신을 제외하고 한줄씩 추가
                if (y == 6 && x == 6) { //S
                    for(int tmpX=0; tmpX<x; tmpX++){ //W to S
                        if(tmpX == 3) continue;
                        historyStack.push(new Pair<>(y, tmpX));
                    }
                }
                else if (y == 6 && x == 0) { //W
                    for(int tmpY=0; tmpY<y; tmpY++){ //N to W
                        if(tmpY == 3) continue;
                        historyStack.push(new Pair<>(tmpY, x));
                    }
                }
                else if (y == 0 && x == 0) { //N
                    for(int tmpX=6; tmpX>x; tmpX--){ //E to N
                        if(tmpX == 3) continue;
                        historyStack.push(new Pair<>(y, tmpX));
                    }
                }
                else if (y == 0 && x == 6) { //E
                    for(int tmpY=6; tmpY>y; tmpY--){ //S to E
                        if(tmpY == 3) continue;
                        historyStack.push(new Pair<>(tmpY, x));
                    }
                }
            }
            position = historyStack.peek(); //check move after
            return 20;
        }

        /**
         * Else - Not a Back-Do
         * */
        int dy = 0, dx = 0;
        while (toMove-- > 0) {

            //출발 전 방향 지정 해주기
            //분기점에서 시작할 때, 방향에 따라 dy, dx 변화
            if (isVertex) {
                if (y == 6 & x == 6) { //S
                    if(historyStack.isEmpty()) { //출발점, 출발할 때 출발점 스택에 추가
                        dy = -1; dx = 0;
                        historyStack.push(new Pair<>(6, 6));
                    }
                    else { //도착점
                        dy = 0; dx = 1;
                    }
                }
                else if (y == 0 & x == 6) { //E
                    if (direction == 'N') { dy = 0; dx = -1; }
                    else if (direction == 'W') { dy = 1; dx = -1; }
                    else return 23;
                }
                else if (y == 0 & x == 0) { //N
                    if (direction == 'W') { dy = 1; dx = 0; }
                    else if (direction == 'S') { dy = 1; dx = 1; }
                    else return 23;
                }
                else if (y == 6 & x == 0) { //W
                    if (direction == 'S') { dy = 0; dx = 1; }
                    else return 23;
                }
                else if (y == 3 & x == 3) { //Center
                    if (direction == 'S') { dy = 1; dx = 1; }
                    else if (direction == 'W') { dy = 1; dx = -1;}
                    else return 23;
                }
                isVertex = false;
            }
            //분기점에서 시작하지 않을 때, 분기점을 지나칠 때
            else {
                //테두리 이동
                // S(x) - E(x)
                if (x == 6 & (y != 6 & y != 0)) { dy = -1; dx = 0; }
                // E(o) - N(x)
                else if (y == 0 & x != 0) { dy = 0; dx = -1; }
                // N(o) - W(x)
                else if (x == 0 & y != 6) { dy = 1; dx = 0; }
                // W(o) - S(o)
                else if (y == 6) { dy = 0; dx = 1; }
                //대각선 이동
                else if (y == 3 & x == 3) { } //Center는 가는 방향 그대로
                // E(x) - C(x) - W(x)
                else if (y + x == 6) { dy = 1; dx = -1; }
                // N(x) - C(x) - S(x)
                else if (y == x) { dy = 1; dx = 1; }
            }

            /**
             * Real move, 위에서는 방향만 잡아주고 여기서 실제로 움직임
             * */
            y += dy; x += dx;
            //Center를 제외한 3일 때 건너뛴다.
            if((y == 3 | x == 3) & !(y == 3 & x == 3)) {
                y += dy; x += dx;
            }
            //한칸씩 이동했을 때의 정보를 저장(마지막 이동까지 저장)
            position = new Pair<>(y, x);
            historyStack.push(position);
            //난 말에 대한 처리
            if(y == 6 & x == 7) return 10;
        }
        return 20;
    }
}