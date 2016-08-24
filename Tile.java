
public class Tile {
	public int xPos;
	public int yPos;
	public int type;
	public int food;
	public Tile(int x, int y, int type){
		this.xPos=x;
		this.yPos=y;
		this.type=type;
		this.food=1;
	}
	public void update(){
		if(food<5){
			//food++;
		}
		else if(food>5){
			food--;
		}
	}
}
