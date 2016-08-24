
public class Corpse extends Entity {
	public Corpse(World world,int x,int y) {
		super(world,x,y);
		type=1;
	}
	public void update(){
		super.update();
		//System.out.println("Update Corpse");
		if(age>=5){
			die();
		}
	}
	private void die() {
		remove();
		world.fertilize(coord.x,coord.y);
	}
}
