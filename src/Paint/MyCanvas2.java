package Paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPanel;

// 그림을 그릴 수 있는 도화지 클래스

public class MyCanvas2 extends Canvas {
	
	// 처음에 까만색 점 안찍히게 하기 위해서 x, y -값 지정
	int x = -50;
	int y = -50;
	int w = 7;
	int h = 7;
	Color cr = Color.BLACK;
	
	
	
	
	@Override
	public void paint(Graphics g) {
		g.setColor(cr);
		g.fillOval(x, y, w, h); // x, y 지점에 70, 70 크기의 원 그리기 
	} 
	

	@Override
	public void repaint() {
		System.out.println("repaint() 메소드 호출");
		super.repaint();
	}



	@Override
	public void update(Graphics g) {
		paint(g);
	}
}
