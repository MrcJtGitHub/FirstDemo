package cn.tedu.shoot;

public class DoubleWeapon implements Weapon{
	public Bullet[] fire(Hero hero){
		Bullet[] b = new Bullet[2];
		b[0] = new Bullet();
		b[1] = new Bullet();
		
		b[0].x = hero.x + hero.img.getWidth() / 4 - 13;
		b[1].x = hero.x + (hero.img.getWidth()*3) / 4 + 8;
		
		b[0].y = hero.y + 40;
		b[1].y = hero.y + 40;
		
		for(int i = 0; i < b.length; i++){
			if(b[i].isOut()){
				b[i] = null;
			}
		}
		
		return b;
	}
}
