
public class Entity {
	protected Coord coord;
	protected World world;
	protected int age;
	protected boolean remove;
	protected int type;
	public Entity(World world,int x,int y){
		this.world=world;
		coord=new Coord(world,x,y);
		this.age=0;
		this.remove=false;
		if(!(world.newEntity(this, x, y))){
			this.remove=true;
			return;
		}
	}
	public void remove(){
		this.remove=true;
	}
	public void update(){
		age++;
	}
}
