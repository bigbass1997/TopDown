package com.staticvoidgames.topdown.states;

import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.entities.Entity;
import com.staticvoidgames.topdown.entities.FirstBoss;
import com.staticvoidgames.topdown.entities.Obstacle;
import com.staticvoidgames.topdown.entities.Player;
import com.staticvoidgames.topdown.entities.PowerUp;
import com.staticvoidgames.topdown.entities.Rock;
import com.staticvoidgames.topdown.entities.SecondBoss;
import com.staticvoidgames.topdown.entities.Switch;
import com.staticvoidgames.topdown.entities.ThirdBoss;
import com.staticvoidgames.topdown.entities.Turret;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class PlayState extends GameState{
	public static float ScrollSpeed=0.2f;
	
	private static final float TIMESTEP = 1/100f;
	private float remaining=0;
	public static Player player;
	private static int score=0;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		this.gsm.mm.play(0);
		player=new Player(100, 100);
	}
	@Override
	public void init() {
		seed= (int) (Math.random()*Integer.MAX_VALUE);
		time=0;
	}

	@Override
	public void update(float delta) {
		remaining+=delta;
		while (remaining>TIMESTEP) {
			remaining-= TIMESTEP;
			tick();
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		Entity[] torender=new Entity[entities.size()];
		entities.toArray(torender);
		for (int i = 0; i < torender.length; i++) {
			torender[i].render(batch);
		}
		batch.end();
		GraphicsMain.shaperenderer.setColor(Color.RED);
		GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		GraphicsMain.shaperenderer.rect(0, 0, player.life/2,10);
		GraphicsMain.shaperenderer.setColor(Color.PINK);
		GraphicsMain.shaperenderer.rect(player.life/2, 0,player.heal/2,10);
		GraphicsMain.shaperenderer.setColor(Color.GREEN);
		GraphicsMain.shaperenderer.rect(0, 10, 10, player.ammo);
		GraphicsMain.shaperenderer.end();
		batch.begin();
		gsm.dm.String("SCORE  "+score, 5, GraphicsMain.SIZE, gsm.fm.fs32, 0xff0000FF);
		gsm.dm.String("SCORE  "+score, 0, GraphicsMain.SIZE, gsm.fm.fs32, 0xFFFFFF99);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public volatile static Vector<Entity> entities= new Vector<Entity>();

	public static Color getcolor(int n){
		float r=MathUtils.cos(n);
		float g=MathUtils.cos(n+MathUtils.PI*2/3f);
		float b=MathUtils.cos(n-MathUtils.PI*2/3f);
		return new Color(r, g, b, 1);
	}
	public static Color getOppositcolor(int n){
		float r=MathUtils.cos(n);
		float g=MathUtils.cos(n+MathUtils.PI*2/3f);
		float b=MathUtils.cos(n-MathUtils.PI*2/3f);
		return new Color(1-r, 1-g, 1-b, 1);
	}
	int time;
	private int seed;
	private boolean BossIsThere=false;
	private Entity Boss;
	private int n=0;
	public static int level=3;
	public static boolean[] Active=new boolean[level];
	/**
	 * Multiple calls are necessary, depending on the time passed.
	 */
	public void tick(){
		ScrollSpeed=1.5f;
		if(time%5==0)score++;
		
		time++;
		if(BossIsThere){
			if(Boss.isdead()){
				BossIsThere=false;
				level++;
				Active=new boolean[level];
				player.losepowerups();
				System.out.println("levelup");
			}
		}
		if(!BossIsThere){
			
			if(score>300*level+(level-1)*level*100){
				switch (level) {
				case 1:
					Boss=new FirstBoss(-150, 350);
					break;
				case 2:
					Boss=new SecondBoss(-150, 350);
					break;
				case 3:
					Boss=new ThirdBoss();
					new Rock(-50, 200, 0.5f, 0, 10000);
					break;
				}
				BossIsThere=true;
			}
			
			if((time*ScrollSpeed)%100>=25&&((time-1)*ScrollSpeed)%100<25){
				createnextpart();
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i+1; j < entities.size(); j++) {
				if(colision(entities.get(i),entities.get(j))){
					entities.get(i).collide(entities.get(j));
					entities.get(j).collide(entities.get(i));
				}
			}
		}
		int i=0;
		while (i < entities.size()) {
			if(entities.get(i).isdead())entities.remove(i);
			else i++;
		}
		if(entities.size()>200)System.out.println("Entities : "+entities.size());
		if(player.life<0)Gameover();
	}
	private void createnextpart(){
		//&(n-1) is the same as %n if n is a power of 2
		n=(int) (time*ScrollSpeed/60)+(seed>>>(1+(time&31)));
		System.out.println(Integer.toBinaryString(n&3));
		if(level>=2){
			if(((n)&0x38)==0)new Obstacle(n%(GraphicsMain.SIZE-2.5f), 400, 20, 100, n%level, (n&4)==0);
		}
		if((n&level)==0){
			new Obstacle(n%(GraphicsMain.SIZE-100), 400, 100, 90,  n%level, (n&4)==0);
			new PowerUp(n%(GraphicsMain.SIZE-100)+50, 420, n%5);
		}
		else if(level>=3&&((n)&0x10)==0){
			new Turret((n&3)*GraphicsMain.SIZE/4f+50, 450, n%level, (n&4)==0);
			new PowerUp((n&3)*GraphicsMain.SIZE/4f+50, 470,n%2);
			new Switch((n%GraphicsMain.SIZE), 430, n%level);
		}
		else if((n&15)==1){
			new Switch(n%(GraphicsMain.SIZE-50)+25, 400, n%level);
			new Obstacle(0, 450, GraphicsMain.SIZE, 50,  n%level, (n&4)==1);
		}
		else{
			new Obstacle(n%(GraphicsMain.SIZE-100), 400, 100, 20,  n%level, (n&4)==0);
			new Switch(n%(GraphicsMain.SIZE-100)+50, 450, n%level);
		}
		
	}
	private void Gameover() {
		
	}
	private boolean colision(Entity e1, Entity e2) {
		Polygon[] c1=e1.getPolygons();
		Polygon[] c2=e2.getPolygons();
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c2.length; j++) {
				if(Intersector.overlapConvexPolygons(c1[i], c2[j])){
					return true;
				}
			}
		}
		return false;
	}

}
