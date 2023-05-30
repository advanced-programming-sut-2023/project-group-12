package org.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Boot extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Rectangle player1;
    private Rectangle player2;
    private Rectangle ball;
    private float ballSpeed;
    private float ballDirectionX;
    private float ballDirectionY;
    private int player1Score;
    private int player2Score;

    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player1 = new Rectangle(20, Gdx.graphics.getHeight() / 2 - 50, 10, 100);
        player2 = new Rectangle(Gdx.graphics.getWidth() - 30, Gdx.graphics.getHeight() / 2 - 50, 10, 100);
        ball = new Rectangle(Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() / 2 - 10, 20, 20);
        ballSpeed = 300;
        ballDirectionX = Math.random() < 0.5 ? -1 : 1;
        ballDirectionY = Math.random() < 0.5 ? -1 : 1;
        player1Score = 0;
        player2Score = 0;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Draw players and ball
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(player1.x, player1.y, player1.width, player1.height);
        shapeRenderer.rect(player2.x, player2.y, player2.width, player2.height);
        shapeRenderer.rect(ball.x, ball.y, ball.width, ball.height);
        shapeRenderer.end();

        // Draw score
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, player1Score + " : " + player2Score, Gdx.graphics.getWidth() / 2 - 20, Gdx.graphics.getHeight() - 20);
        batch.end();

        // Move ball
        float deltaTime = Gdx.graphics.getDeltaTime();
        ball.x += ballSpeed * ballDirectionX * deltaTime;
        ball.y += ballSpeed * ballDirectionY * deltaTime;

        // Check collision with players and walls
        if (ball.overlaps(player1) || ball.overlaps(player2)) {
            ballDirectionX *= -1;
        }
        if (ball.y < 0 || ball.y + ball.height > Gdx.graphics.getHeight()) {
            ballDirectionY *= -1;
        }

        // Check if ball goes out of bounds
        if (ball.x + ball.width < 0) {
            player2Score++;
            ball.x = Gdx.graphics.getWidth() / 2 - 10;
            ball.y = Gdx.graphics.getHeight() / 2 - 10;
            ballDirectionX = Math.random() < 0.5 ? -1 : 1;
            ballDirectionY = Math.random() < 0.5 ? -1 : 1;
        }
        if (ball.x > Gdx.graphics.getWidth()) {
            player1Score++;
            ball.x = Gdx.graphics.getWidth() / 2 - 10;
            ball.y = Gdx.graphics.getHeight() / 2 - 10;
            ballDirectionX = Math.random() < 0.5 ? -1 : 1;
            ballDirectionY = Math.random() < 0.5 ? -1 : 1;
        }

        // Move players
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.y += 300 * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1.y -= 300 * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player2.y += 300 * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2.y -= 300 * deltaTime;
        }

        // Clamp player positions
        if (player1.y < 0) {
            player1.y = 0;
        }
        if (player1.y + player1.height > Gdx.graphics.getHeight()) {
            player1.y = Gdx.graphics.getHeight() - player1.height;
        }
        if (player2.y < 0) {
            player2.y = 0;
        }
        if (player2.y + player2.height > Gdx.graphics.getHeight()) {
            player2.y = Gdx.graphics.getHeight() - player2.height;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }
}
