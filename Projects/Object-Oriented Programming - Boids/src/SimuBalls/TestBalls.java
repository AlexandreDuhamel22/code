package SimuBalls;

public class TestBalls {
    public static void main(String[] args) {
        Balls balls = new Balls(8,10,10);
        System.out.println(balls);

        for (int i = 0; i < 3; i++) {
            balls.translate();
            System.out.println(balls);
        }

        balls.reInit();
        System.out.println(balls);
    }
}
