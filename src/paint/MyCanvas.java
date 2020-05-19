package paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas {

	// 검은색 점 안찍히게 하기 위해서 x, y 값을 -로 지정해준다.
	public int X = -70;
	public int Y = -70;
	public int W = 7;
	public int H = 7;
	
	public Color color = Color.BLACK;

	@Override
	public void paint(Graphics graphics) {
		graphics.setColor(color);
		graphics.fillOval(X, Y, W, H); // 70, 70 크기의 원 그리기 
	}

	@Override
	public void update(Graphics graphics) {
		paint(graphics);
	}

}
