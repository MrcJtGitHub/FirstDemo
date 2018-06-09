package cn.tedu.shoot;

public class Airplane extends Enemy{
	public Airplane() {
		this.imgs = Main.airplanes;
		this.img = Main.airplanes[0];
		this.stepPx = 4;
		this.life = 2;
		this.damage = 1;
		this.score = 1;
		init();
	}
}
