package Object;

import ElementsGame.GameSnakesMain;

public class Snake {
    public int direction = 0;
    public int length = 2;

   private GameSnakesMain main;

    @SuppressWarnings("static-access")
    public int [] snakeX = new int[main.WIDTH * main.HEIGHT];
    public int [] snakeY = new int[main.WIDTH * main.HEIGHT];


    public Snake(int x0, int y0, int x1, int y1){
        snakeX[0] = x0;     snakeX [1] = x1;
        snakeY[0] = y0;     snakeY [1] = y1;
    }

    public void move(){

        for (int elem = length; elem > 0; elem --){
            snakeX[elem] = snakeX[elem - 1];
            snakeY[elem] = snakeY[elem - 1];
            }

            switch (direction){
                case 0:
                    snakeX[0]++; //snake move
                break;

                case 1:
                    snakeY[0]++;
                break;

                case 2:
                    snakeX[0]--;
                 break;

                case 3:
                    snakeY[0]--;
                break;

        }
        for (int d = length; d > 0; d--){ //
            if ((snakeX[0] == snakeX[d]) & snakeY[0] == snakeY[d]) length = d;
        }

        if(length < 2) {length = 2;}

        if (snakeX[0] > main.WIDTH) {snakeX[0] = 0;}
        if(snakeX[0] < 0) {snakeX[0] = main.WIDTH-1;}

        if (snakeY[0] > main.HEIGHT) {snakeX[0] = 0;}
        if(snakeY[0] < 0) {snakeY[0] = main.HEIGHT-1;}
    }


}
