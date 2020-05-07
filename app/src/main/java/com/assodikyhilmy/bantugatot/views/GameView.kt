package com.assodikyhilmy.bantugatot.views

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.assodikyhilmy.bantugatot.models.*
import java.util.*

/**
 * Created by lenovo on 12/08/2017.
 */
class GameView(context: Context, //a screenX holder
               var screenX: Int, screenY: Int) : SurfaceView(context), Runnable {
    @Volatile
    var playing = false
    private var gameThread: Thread? = null

    //adding the player to this class
    private val player: Player

    //These objects will be used for drawing
    private val paint: Paint
    private lateinit var canvas: Canvas
    private val surfaceHolder: SurfaceHolder

    //Adding an stars list
    private val clouds = ArrayList<Cloud>()

    //Adding enemies object array
    private val enemies: Array<Enemy?>

    //Adding birds object array
    private val birds: Array<Bird?>

    //Adding 3 enemies you may increase the size
    private val enemyCount = 2
    private var displayedEnemyCount = 1

    //Adding 3 birds you may increase the size
    private val birdCount = 2

    //defining a boom object to display blast
    private val boom: Boom

    //an indicator if the game is Over
    private var isGameOver = false

    //the score holder
    var score = 0

    //the high Scores Holder
    var topScore = IntArray(4)

    //Shared Prefernces to store the High Scores
    var sharedPreferences: SharedPreferences
    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP ->                 //stopping the boosting when screen is released
                player.stopBoosting()
            MotionEvent.ACTION_DOWN ->                 //boosting the space jet when screen is pressed
                player.setBoosting()
        }
        return true
    }

    private fun update() {
        player.update()

        //setting boom outside the screen
        boom.x = -250
        boom.y = -250

        //Updating the stars with player speed
        for (s in clouds) {
            s.update(player.speed)
        }

        //updating the enemy coordinate with respect to player speed
        for (i in 0 until birdCount) {
            birds[i]!!.update(player.speed)

            //if collision occurrs with player
            if (Rect.intersects(player.detectCollision, birds[i]!!.detectCollision)) {
                //incrementing score as time passes
                score++

                //displaying boom at that location
                boom.x = birds[i]!!.x
                boom.y = birds[i]!!.y

                //moving enemy outside the left edge
                birds[i]!!.x = -birds[i]!!.bitmap.width * 3
                if (score >= 30 && displayedEnemyCount == 1) {
                    displayedEnemyCount = 2
                }
            }
        }
        for (i in 0 until displayedEnemyCount) {
            enemies[i]!!.update(player.speed)
            if (Rect.intersects(player.detectCollision, enemies[i]!!.detectCollision)) {
                player.playerLose()
                playing = false
                isGameOver = true

                //Assigning the scores to the highscore integer array
                for (j in topScore.indices) {
                    if (topScore[j] < score) {
                        var tmp: Int
                        var tmp1 = score
                        for (k in j until topScore.size) {
                            tmp = topScore[k]
                            topScore[k] = tmp1
                            tmp1 = tmp
                        }
                        break
                    }
                }

                //storing the scores through shared Preferences
                val e = sharedPreferences.edit()
                for (j in topScore.indices) {
                    val k = j + 1
                    e.putInt("score$k", topScore[j])
                }
                e.commit()
            }
        }
    }

    private fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.rgb(150, 200, 255))

            //setting the paint color to white to draw the stars
            paint.color = Color.WHITE

            //drawing all stars
            for (c in clouds) {
                canvas.drawBitmap(
                        c.bitmap,
                        c.x.toFloat(),
                        c.y.toFloat(),
                        paint)
            }

            //drawing player
            canvas.drawBitmap(
                    player.bitmap,
                    player.x.toFloat(),
                    player.y.toFloat(),
                    paint)

            //drawing the birds
            for (i in 0 until birdCount) {
                canvas.drawBitmap(
                        birds[i]!!.bitmap,
                        birds[i]!!.x.toFloat(),
                        birds[i]!!.y.toFloat(),
                        paint
                )
            }

            //drawing the birds
            for (i in 0 until displayedEnemyCount) {
                canvas.drawBitmap(
                        enemies[i]!!.bitmap,
                        enemies[i]!!.x.toFloat(),
                        enemies[i]!!.y.toFloat(),
                        paint
                )
            }

            //drawing boom image
            canvas.drawBitmap(
                    boom.bitmap,
                    boom.x.toFloat(),
                    boom.y.toFloat(),
                    paint
            )

            //drawing the score on the game screen
            paint.textSize = 30f
            canvas.drawText("Score:$score", 100f, 50f, paint)

            //draw game Over when the game is over
            if (isGameOver) {
                paint.textSize = 150f
                paint.textAlign = Paint.Align.CENTER
                val yPos = (canvas.getHeight() / 2 - (paint.descent() + paint.ascent()) / 2).toInt()
                canvas.drawText("Game Over", canvas.getWidth() / 2.toFloat(), yPos.toFloat(), paint)
            }
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    private fun control() {
        try {
            Thread.sleep(17)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun pause() {
        playing = false
        try {
            gameThread!!.join()
        } catch (e: InterruptedException) {
        }
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    init {

        //setting the score to 0 initially
        sharedPreferences = context.getSharedPreferences("TOP SCORES", Context.MODE_PRIVATE)

        //initializing the array high scores with the previous values
        topScore[0] = sharedPreferences.getInt("score1", 0)
        topScore[1] = sharedPreferences.getInt("score2", 0)
        topScore[2] = sharedPreferences.getInt("score3", 0)
        topScore[3] = sharedPreferences.getInt("score4", 0)

        //initializing player object
        //this time also passing screen size to player constructor
        player = Player(context, screenX, screenY)

        //initializing drawing objects
        surfaceHolder = holder
        paint = Paint()

        //adding 100 stars you may increase the number
        val cloudNums = 15
        for (i in 0 until cloudNums) {
            val s = Cloud(context, screenX, screenY)
            clouds.add(s)
        }

        //initializing birds object array
        birds = arrayOfNulls(birdCount)
        for (i in 0 until birdCount) {
            birds[i] = Bird(context, screenX, screenY)
        }

        //initializing birds object array
        enemies = arrayOfNulls(enemyCount)
        for (i in 0 until enemyCount) {
            enemies[i] = Enemy(context, screenX, screenY)
        }

        //initializing boom object
        boom = Boom(context)
    }
}