package cn.tedu.shoot;

public class Bigplane extends Enemy{
	public Bigplane() {
		this.imgs = Main.bigplanes;
		this.img = Main.bigplanes[0];
		this.stepPx = 2;
		this.life = 4;
		this.damage = 2;
		this.score = 2;
		init();
	}
}
