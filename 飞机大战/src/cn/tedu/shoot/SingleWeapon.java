package cn.tedu.shoot;

public class SingleWeapon implements Weapon{
	public Bullet[] fire(Hero hero){
		Bullet[] b = new Bullet[1];
		b[0] = new Bullet();
		
		b[0].x = hero.x + hero.img.getWidth() / 2;
		
		b[0].y = hero.y;
		
		if(b[0].isOut()){
			b[0] = null;
		}
		return b;
	}
}
