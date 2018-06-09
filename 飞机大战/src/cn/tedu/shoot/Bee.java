package cn.tedu.shoot;

public class Bee extends Enemy implements Award{
	int dx;
	
	public Bee() {
		this.imgs = Main.bees;
		this.img = Main.bees[0];
		this.stepPx = 5;
		this.life = 1;
		this.damage = 0;
		this.score = 20;
		init();
	}
	
	@Override
	public void init() {
		super.init();
		double d = Math.random();
		if(d < 0.5){
			dx = 1;
		} else {
			dx = -1;
		}
	};
	
	@Override
	public void step() {
		super.step();
		this.x += dx;
	}

	@Override
	public int getLife() {
		return 1;
	}

	@Override
	public Weapon getWeapon(int level) {
		if(level <= 0){
			return Main.weapon[0];
		} else {
			if(level > Main.weapon.length - 1){
				level = Main.weapon.length - 1;
			}
			return Main.weapon[level];
		}
		
	}
}
