package cn.tedu.shoot;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 飞机大战
 * @author CJT
 *
 */

public class Main {
	static BufferedImage[] airplanes = new BufferedImage[5]; 
	static BufferedImage[] bigplanes = new BufferedImage[5]; 
	static BufferedImage[] bees = new BufferedImage[5]; 
	static BufferedImage[] heros = new BufferedImage[6];
	static BufferedImage background;
	static BufferedImage bullet;
	static BufferedImage pause;
	static BufferedImage start;
	static BufferedImage gameover;
	static Weapon[] weapon = {new SingleWeapon(),new DoubleWeapon(),new FourWeapon()};
	
	public static void main(String[] args) {
		try {
			background = ImageIO.read(Main.class.getResource("/imgs/background.png"));
			bullet = ImageIO.read(Main.class.getResource("/imgs/bullet.png"));
			pause = ImageIO.read(Main.class.getResource("/imgs/pause.png"));
			start = ImageIO.read(Main.class.getResource("/imgs/start.png"));
			gameover = ImageIO.read(Main.class.getResource("/imgs/gameover.png"));
			for(int i = 0; i < airplanes.length; i++){
				airplanes[i] = ImageIO.read(Main.class.getResource("/imgs/airplane" + i + ".png"));
			}
			for(int i = 0; i < bigplanes.length; i++){
				bigplanes[i] = ImageIO.read(Main.class.getResource("/imgs/bigplane" + i + ".png"));
			}
			for(int i = 0; i < bees.length; i++){
				bees[i] = ImageIO.read(Main.class.getResource("/imgs/bee" + i + ".png"));
			}
			for(int i = 0; i < heros.length; i++){
				heros[i] = ImageIO.read(Main.class.getResource("/imgs/hero" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("图片加载失败");
		}
		
		JFrame f = new JFrame();
		f.setTitle("飞机大战");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel gp = new GamePanel();
		f.add(gp);
		f.pack();
		
		f.setVisible(true);
		gp.action();
	}

}
