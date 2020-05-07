package com.assodikyhilmy.myfirstandroidgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by lenovo on 12/08/2017.
 */

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;

    //adding the player to this class
    private Player player;

    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    //Adding an stars list
    private ArrayList<Cloud> clouds = new
            ArrayList<Cloud>();

    //Adding enemies object array
    private Enemy[] enemies;

    //Adding birds object array
    private Bird[] birds;

    //Adding 3 enemies you may increase the size
    private int enemyCount = 2;
    private int displayedEnemyCount = 1;

    //Adding 3 birds you may increase the size
    private int birdCount = 2;

    //defining a boom object to display blast
    private Boom boom;

    //an indicator if the game is Over
    private boolean isGameOver;


    //a screenX holder
    int screenX;

    //the score holder
    int score;

    //the high Scores Holder
    int topScore[] = new int[4];

    //Shared Prefernces to store the High Scores
    SharedPreferences sharedPreferences;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        isGameOver = false;
        this.screenX = screenX;

        //setting the score to 0 initially
        score = 0;

        sharedPreferences = context.getSharedPreferences("TOP SCORES", Context.MODE_PRIVATE);

        //initializing the array high scores with the previous values
        topScore[0] = sharedPreferences.getInt("score1", 0);
        topScore[1] = sharedPreferences.getInt("score2", 0);
        topScore[2] = sharedPreferences.getInt("score3", 0);
        topScore[3] = sharedPreferences.getInt("score4", 0);

        //initializing player object
        //this time also passing screen size to player constructor
        player = new Player(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        //adding 100 stars you may increase the number
        int cloudNums = 15;
        for (int i = 0; i < cloudNums; i++) {
            Cloud s = new Cloud(context, screenX, screenY);
            clouds.add(s);
        }

        //initializing birds object array
        birds = new Bird[birdCount];
        for (int i = 0; i < birdCount; i++) {
            birds[i] = new Bird(context, screenX, screenY);
        }

        //initializing birds object array
        enemies = new Enemy[enemyCount];
        for (int i = 0; i < enemyCount; i++) {
            enemies[i] = new Enemy(context, screenX, screenY);
        }

        //initializing boom object
        boom = new Boom(context);

    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //stopping the boosting when screen is released
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                //boosting the space jet when screen is pressed
                player.setBoosting();
                break;
        }
        return true;
    }

    private void update() {

        player.update();

        //setting boom outside the screen
        boom.setX(-250);
        boom.setY(-250);

        //Updating the stars with player speed
        for (Cloud s : clouds) {
            s.update(player.getSpeed());
        }

        //updating the enemy coordinate with respect to player speed
        for (int i = 0; i < birdCount; i++) {
            birds[i].update(player.getSpeed());

            //if collision occurrs with player
            if (Rect.intersects(player.getDetectCollision(), birds[i].getDetectCollision())) {
                //incrementing score as time passes
                score++;

                //displaying boom at that location
                boom.setX(birds[i].getX());
                boom.setY(birds[i].getY());

                //moving enemy outside the left edge
                birds[i].setX(-birds[i].getBitmap().getWidth() * 3);

                if(score>=30&&displayedEnemyCount==1){
                    displayedEnemyCount=2;
                }
            }
        }

        for (int i = 0; i < displayedEnemyCount; i++) {
            enemies[i].update(player.getSpeed());

            if (Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {
                player.playerLose();

                playing = false;
                isGameOver = true;

                //Assigning the scores to the highscore integer array
                for (int j = 0; j < topScore.length; j++) {
                    if (topScore[j] < score) {
                        int tmp;
                        int tmp1 = score;
                        for (int k = j; k < topScore.length; k++) {
                            tmp = topScore[k];
                            topScore[k] = tmp1;
                            tmp1 = tmp;
                        }
                        break;
                    }
                }

                //storing the scores through shared Preferences
                SharedPreferences.Editor e = sharedPreferences.edit();
                for (int j = 0; j < topScore.length; j++) {
                    int k = j + 1;
                    e.putInt("score" + k, topScore[j]);
                }
                e.commit();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.rgb(150, 200, 255));

            //setting the paint color to white to draw the stars
            paint.setColor(Color.WHITE);

            //drawing all stars
            for (Cloud c : clouds) {
                canvas.drawBitmap(
                        c.getBitmap(),
                        c.getX(),
                        c.getY(),
                        paint);
            }

            //drawing player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            //drawing the birds
            for (int i = 0; i < birdCount; i++) {
                canvas.drawBitmap(
                        birds[i].getBitmap(),
                        birds[i].getX(),
                        birds[i].getY(),
                        paint
                );
            }

            //drawing the birds
            for (int i = 0; i < displayedEnemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            //drawing boom image
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );

            //drawing the score on the game screen
            paint.setTextSize(30);
            canvas.drawText("Score:" + score, 100, 50, paint);

            //draw game Over when the game is over
            if (isGameOver) {
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over", canvas.getWidth() / 2, yPos, paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}