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
	public static boolean[] Active=new boolean[5];
	private static final float TIMESTEP = 0.00416f;
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
		System.out.println(delta);
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
		GraphicsMain.shaperenderer.rect(0, 0, player.life,10);
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
	public static int level=1;
	/**
	 * Multiple calls are necessary, depending on the time passed.
	 */
	public void tick(){
		ScrollSpeed=(level+2)/10f;
		if(time%10==0)score++;
		
		time++;
		if(BossIsThere){
			if(Boss.isdead()){
				BossIsThere=false;
				level++;
				player.losepowerups();
				System.out.println("levelup");
			}
		}
		if(!BossIsThere){
			
			if(score>500*level+(level-1)*level*200){
				switch (level) {
				case 1:
					Boss=new FirstBoss(-150, 350);
					break;
				case 2:
					Boss=new SecondBoss(-150, 350);
					break;
				case 3:
					Boss=new ThirdBoss();
					new Rock(-50, 200, 0.1f, 0, 10000);
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
		int i=0;
		while(i<4){
			if(level>=2){
				if(((n+i)&0x38)==0)new Obstacle(((n%3+1)*GraphicsMain.SIZE/4f)-2.5f, 450, 5, 100, n%level, (n&4)==0);
			}
			if(level>=3){
				if(((n+i*16)&0xf0)==0&&i!=3){
					if((n&1)==0){
						new Turret(i*GraphicsMain.SIZE/4f+50, 450, n%level, (n&4)==0);
						new PowerUp(i*GraphicsMain.SIZE/4f+50, 480,n%2);
						i++;
						new Switch(i*GraphicsMain.SIZE/4f+50, 430, n%level);
					}
					else{
						new Switch(i*GraphicsMain.SIZE/4f+50, 430, n%level);
						i++;
						new Turret(i*GraphicsMain.SIZE/4f+50, 450, n%level, (n&4)==0);
						new PowerUp(i*GraphicsMain.SIZE/4f+50, 480,n%2);
						
					}
					i++;
				}
				if(i>3)return;
			}
			if(((n+i)&15)==1){
				new Switch(i*GraphicsMain.SIZE/4f+50, 450, n%level);
				i++;
			}
			else if(((n+i)&3)==0){
				new Obstacle(i*GraphicsMain.SIZE/4f, 500, 100, 5,  n%level, (n&4)==0);
				new PowerUp(i*GraphicsMain.SIZE/4f+50, 520, n%5);
			}
			if(i>3)return;
			i++;
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
