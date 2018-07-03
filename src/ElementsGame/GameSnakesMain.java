package ElementsGame;

import Object.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameSnakesMain extends JPanel implements ActionListener {

    private static JFrame frame, frame1;
    private static JButton [] btn = new JButton[2];

    public static final int SCALE = 33;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SPEED = 5;

    private Apple apple = new Apple((int)(Math.random()* WIDTH), (int)(Math.random() * HEIGHT));
    private Snake snake = new Snake(10,10, 9, 10);
    private Timer t = new Timer(1000/SPEED,this);

    public GameSnakesMain() {
        t.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    @Override
    public void paint (Graphics g){

//        drawing rectangle for playing area
        g.setColor(color(239, 141, 12));
        g.fillRect(0,0, WIDTH * SCALE, HEIGHT * SCALE);
        g.setColor(color(0,0,0));

//        drawing lines to create grid
        for (int xx = 0; xx < WIDTH * SCALE; xx += SCALE){

                g.drawLine( xx, 0, xx,HEIGHT*SCALE);
        }

        for (int yy = 0; yy < HEIGHT * SCALE; yy += SCALE){
            g.drawLine(0, yy, HEIGHT*SCALE, yy);
        }

        g.setColor(color(255,7,7));
        for (int d = 0; d < snake.length; d++){
            g.fillRect(snake.snakeX[d] * SCALE + 1, snake.snakeY[d] * SCALE + 1, SCALE - 1, SCALE - 1);
        }

//        apple drawing
        g.setColor(color(68,45,37));
        g.fillRect(apple.posX * SCALE + 1, apple.posY * SCALE + 1, SCALE - 1, SCALE - 1);
    }

    public Color color (int red, int green, int blue){

        return new Color(red, green,blue);

    }

    public static void main(String[] args) {
        frame = new JFrame("Snake");
        frame.setSize(WIDTH*SCALE + 14,HEIGHT*SCALE + 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new GameSnakesMain());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();



//        Apple arrangement and snake length increase
        for (int k = 0; k < snake.length; k++){
            if (snake.snakeX[k] == apple.posX && snake.snakeY[k] == apple.posY){
                apple.setRandomPosition();
                snake.length++;
            }
        }

        repaint();
    }

    public void newWindow(){
        frame1 = new JFrame("Break");
        frame1.setSize(300,200);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setVisible(true);

        for (int i = 0; i < btn.length; i++){
            btn[i] = new JButton();
            btn[i].setBounds(100 + i * 5, 45 + i * 55, 100, 30);
            frame1.add(btn[i]);
        }

        btn[0].setText("Resume");
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.setVisible(false);
                t.start();
            }
        });

        btn[1].setText("Exit");
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame1.setLayout(null);
    }

    private class KeyBoard extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event){
            int key = event.getKeyCode();

//            snake control using the keyboard
            if ((key == KeyEvent.VK_RIGHT) & snake.direction != 2) snake.direction = 0;
            if ((key == KeyEvent.VK_DOWN) & snake.direction != 3) snake.direction = 1;
            if ((key == KeyEvent.VK_LEFT) & snake.direction != 0) snake.direction = 2;
            if ((key == KeyEvent.VK_UP) & snake.direction != 1) snake.direction = 3;

//            Paused
            if ((key == KeyEvent.VK_SPACE)) {
                t.stop();
                newWindow(); // pause window with two buttons
            }

        }

        @Override
        public void keyReleased(KeyEvent event){

        }

        @Override
        public void keyTyped(KeyEvent event){

        }
    }
}
