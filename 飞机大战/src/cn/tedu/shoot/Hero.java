package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hero {
	static final int STATUS_NORMAL = 0;
	static final int STATUS_HURT = 1;
	static final int STATUS_EXPLODE = 2;
	static final int STATUS_DEAD = 3;
	
	BufferedImage[] imgs = Main.heros;
	BufferedImage img = imgs[1];
	int x = 200 - img.getWidth() / 2;
	int y = 554 - img.getHeight();
	int tx = x;
	int ty = y;
	int life = 10;
	int curIndex = 1;
	int status = STATUS_NORMAL;
	int level;
	int score;
	Weapon weapon = Main.weapon[0];
	
	public void moveTo(int tx, int ty){
		this.tx = tx - img.getWidth() / 2;
		this.ty = ty - img.getHeight() / 2;
	}
	
	public void step(){
		if(status == STATUS_NORMAL){
			if(x == tx && y == ty){
				img = imgs[1];
			} else{
				img = imgs[0];
				x = tx;
				y = ty;
			}
		} else if(status == STATUS_HURT){
			if(x != tx || y != ty){
				x = tx;
				y = ty;
			}
			if(GamePanel.counter % 6 == 0){
				img = imgs[++curIndex];
				if(curIndex >= 4){
					curIndex = 1;
					status = STATUS_NORMAL;
				}
			}
		} else if(status == STATUS_EXPLODE){
			if(GamePanel.counter % 6 == 0){
				img = imgs[++curIndex];
				if(curIndex >= imgs.length - 1){
					status = STATUS_DEAD;
				}
			}
		}
		
	}
	
	public void paint(Graphics g){
		if(!isDead()){
			g.drawImage(img, x, y, null);			
		}
	}
	
	public Bullet[] shoot(){
		return weapon.fire(this);
	}
	
	public boolean isNormal(){
		return status == STATUS_NORMAL;
	}
	
	public boolean isExplode(){
		return status == STATUS_EXPLODE;
	}
	
	public boolean isHurt(){
		return status == STATUS_HURT;
	}
	
	public boolean isDead(){
		return status == STATUS_DEAD;
	}
}
