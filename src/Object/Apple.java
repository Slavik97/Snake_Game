package Object;


import ElementsGame.GameSnakesMain;

import java.util.Random;

public class Apple {

    GameSnakesMain main;

    public int posX, posY;

    public Apple(int startX, int startY){
        posX = startX;
        posY = startY;
    }

    /*
    *Set random position for object Apple
    */
        @SuppressWarnings("static-access")
    public void setRandomPosition(){
        posX = (int)(Math.random() * main.WIDTH);
        posY = (int)(Math.random() * main.HEIGHT);
    }
}
