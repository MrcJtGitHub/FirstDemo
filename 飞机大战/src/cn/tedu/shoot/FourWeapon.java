package cn.tedu.shoot;

public class FourWeapon implements Weapon{
	public Bullet[] fire(Hero hero){
		Bullet[] b = new Bullet[4];
		b[0] = new Bullet();
		b[1] = new Bullet();
		b[2] = new Bullet();
		b[3] = new Bullet();
		
		b[0].x = hero.x + hero.img.getWidth() / 4 - 6;
		b[1].x = hero.x + (hero.img.getWidth()*3) / 4 + 6;
		b[2].x = hero.x + hero.img.getWidth() / 4 - 12;
		b[3].x = hero.x + (hero.img.getWidth()*3) / 4 + 12;
		
		b[0].y = hero.y + 40;
		b[1].y = hero.y + 40;
		b[2].y = hero.y + 40;
		b[3].y = hero.y + 40;
		
		for(int i = 0; i < b.length; i++){
			if(b[i].isOut()){
				b[i] = null;
			}
		}
		return b;
	}
}
