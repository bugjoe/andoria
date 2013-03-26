package net.joesoft.andoria.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.joesoft.andoria.utils.Settings;

public class FpsLabel extends Label {
	public FpsLabel() {
		super("FPS: ", Resources.getSkin());
		setY(Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_Y) - Resources.getSkin().getFont("default").getLineHeight());
	}

	public void setFps(int fps) {
		setText("FPS: " + fps);
	}
}