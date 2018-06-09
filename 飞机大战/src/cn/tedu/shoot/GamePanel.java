package cn.tedu.shoot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	static final int NOT_START = 0;
	static final int RUN = 1;
	static final int PAUSE = 2;
	static final int OVER = 3;
	
	Background bg = new Background();
	Hero hero = new Hero();
	ArrayList<Enemy> enemys = new ArrayList<>();
	ArrayList<Bullet> bullets = new ArrayList<>();
	static int counter;
	int status = NOT_START;
	
	public GamePanel() {
		setPreferredSize(new Dimension(400, 654));
	}
	
	@Override
	public void paint(Graphics g) {
		bg.paint(g);
		if(status != OVER){
			hero.paint(g);
		}
		paintAllEnemy(g);
		paintAllBullet(g);
		paintAllScore(g);
		
		if(status == NOT_START){
			g.drawImage(Main.start, 0, 0, null);
		} else if(status == OVER){
			g.drawImage(Main.gameover, 0, 0, null);
		} else if(status == PAUSE){
			g.drawImage(Main.pause, 0, 0, null);
		}
	}
	
	private void paintAllScore(Graphics g) {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD,14);
		g.setFont(f);
		
		g.setColor(Color.BLACK);
		g.drawString("生命:", 8, 20);
		g.drawString("" + hero.life, 180, 20);
		g.drawString("得分:" + hero.score, 8, 36);
		
		g.setColor(Color.WHITE);
		g.drawString("生命:", 7, 20);
		g.drawString("" + hero.life, 179, 20);
		g.drawString("得分:" + hero.score, 7, 36);
		
		g.setColor(Color.BLACK);
		g.drawRect(50, 6, 120, 14);
		g.setColor(Color.RED);
		g.fillRect(51, 7, hero.life * 12, 13);
	}

	private void paintAllBullet(Graphics g) {
		for(Iterator it = bullets.iterator(); it.hasNext(); ){
			Bullet b = (Bullet)it.next();
			b.paint(g);
		}
	}

	private void paintAllEnemy(Graphics g) {
		for(Iterator it = enemys.iterator(); it.hasNext(); ){
			Enemy e = (Enemy) it.next();
			e.paint(g);
		}
	}

	public void action(){
		addMouseMotionListener(new MouseAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				hero.moveTo(e.getX(), e.getY());
			}
		});
		
		addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(status == NOT_START){
					status = RUN;
				}
				if(status == OVER){
					status = NOT_START;
					hero = new Hero();
					enemys.clear();
					bullets.clear();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(status == PAUSE){
					status = RUN;
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(status == RUN){
					status = PAUSE;
				}
			}
		});
		
		Timer time = new Timer();
		time.schedule(new TimerTask(){

			@Override
			public void run() {
				counter++;
				if(status != PAUSE){
					bg.step();
					if(status != OVER){
						hero.step();						
					}
					if(status != NOT_START){
						enemyIn();
						enemyStep();						
					}
					bulletIn();
					bulletStep();
					pengzhuang();
				}
				repaint();
			}
			
		}, 0, 1000/60);
	}

	protected void pengzhuang() {
		for(Iterator it1 = enemys.iterator(); it1.hasNext();){
			Enemy e = (Enemy)it1.next();
			if(e.isNormal()){
				for(Iterator it2 = bullets.iterator(); it2.hasNext();){
					Bullet b = (Bullet)it2.next();
					if(e.hit(b)){
						it2.remove();
						e.life--;
						if(e.life <= 0){
							e.life = 0;
							e.status = Enemy.STATUS_EXPLODE;
						}
					}
				}
				
				if(hero.isNormal() && e.hit(hero)){
					if(e instanceof Award){
						e.life = 0;
						e.status = Enemy.STATUS_EXPLODE;
						Award a = (Award)e;
						hero.life += a.getLife();
						if(hero.life >= 10){
							hero.life = 10;
						}
						
						hero.level++;
						if(hero.level > Main.weapon.length - 1){
							hero.level = Main.weapon.length - 1;
						}
						hero.weapon = Main.weapon[hero.level];
					} else {
						hero.life -= e.damage;
						hero.status = Hero.STATUS_HURT;
						e.life = 0;
						e.status = Enemy.STATUS_EXPLODE;
						if(hero.life <= 0){
							hero.life = 0;
							status = OVER;
							hero.status = Hero.STATUS_EXPLODE;
						}
						hero.level--;
						if(hero.level < 0){
							hero.level = 0;
						}
						hero.weapon = Main.weapon[hero.level];
					}
					
				}
			}
		}
	}

	protected void bulletIn() {
		if(hero.isDead() || hero.isExplode()) return;
		if(counter % 15 != 0) return;
		
		Bullet[] b = hero.shoot();
		for(int i = 0; i < b.length; i++){
			if(b[i] != null){
				bullets.add(b[i]);
			}
		}
	}

	protected void bulletStep() {
		for(Iterator it = bullets.iterator(); it.hasNext();){
			Bullet b = (Bullet)it.next();
			b.step();
			if(b.isOut()){
				it.remove();
			}
		}
	}

	protected void enemyIn() {
		if(counter % 30 != 0) return;
		
		double d = Math.random();
		if(d < 0.6){
			enemys.add(new Airplane());
		} else if(d < 0.9){
			enemys.add(new Bigplane());
		} else {
			enemys.add(new Bee());
		}
	}

	protected void enemyStep() {
		for(Iterator it = enemys.iterator(); it.hasNext(); ){
			Enemy e = (Enemy)it.next();
			e.step();
			if(e.isDead()){
				hero.score += e.score;
			}
			if(e.isOut() || e.isDead()){
				it.remove();
			}
		}
	}
}
