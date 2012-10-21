package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.input.Keyboard;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;
import net.joesoft.andoria.utils.StopWatch;

public class RenderEngine {
	private final Log log = new Log(this.getClass());
	private long secondCounter = 0;
	private long fps = 0;
	private final Camera cam;
	private final ShapeRenderer renderer = new ShapeRenderer();
	private final StopWatch stopWatch = new StopWatch();
    private final Terrain terrain;
	private final Text text;
	private final Keyboard keyboard;

	public RenderEngine() {
        terrain = new Terrain();
        terrain.generate();
		//cam = new PerspectiveCamera(180, Properties.getResolutionX(), Properties.getResolutionY());
		cam = new OrthographicCamera(Properties.getResolutionX(), Properties.getResolutionY());
		cam.position.set(0, 0, 10);
        cam.far = 100f;
        cam.near = 1f;
        cam.lookAt(0, 0, 0);
		cam.update();
		text = new Text(cam);
		keyboard = new Keyboard(cam);
	}

	public void render() {
		stopWatch.start();
        clearScreen();
        renderTerrain();
		renderer.end();

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // TODO REMOVE THIS LINES -> ONLY TO MAKE duration > 0
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		final long duration = stopWatch.stop();
		keyboard.moveCamera(5);
		text.write("FPS: " + fps, 10, 10);

		secondCounter = secondCounter + duration;
		if (secondCounter > 500) {
			fps = 1000 / duration;
			log.info("FPS: " + fps);
			secondCounter = 0;
		}
	}

    private void clearScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    private void renderTerrain() {
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeType.FilledRectangle);
        renderer.setColor(new Color(1, 0, 0, 1));
        renderer.filledRect(0, 0, 50, 50);

        for(int x = 0; x < terrain.getTerrainSize(); ++x) {
            for(int y = 0; y < terrain.getTerrainSize(); ++y) {
                final Vector3 position = terrain.getPosition(x, y);
                //renderer.filledRect(position.x, position.y, position.z, 1);
            }
        }
    }
}
