
public class Coord {
	private World world;
	public int x;
	public int y;
	public Coord(World world, int x, int y) {
		this.x=x;
		this.y=y;
		this.world=world;
	}
	//Tiles
	public Tile getTile(int xCoord, int yCoord){
		if(xCoord<0){
			xCoord = world.tiles[0].length+xCoord;
		}
		if(xCoord>(world.tiles[0].length-1)){
			xCoord = xCoord-world.tiles[0].length;
		}
		if(yCoord<0){
			yCoord = world.tiles.length+yCoord;
		}
		if(yCoord>(world.tiles.length-1)){
			yCoord = yCoord-world.tiles.length;
		}
		return world.tiles[yCoord][xCoord];
	}
	
	public Entity getEntity(int xCoord, int yCoord){
		if(xCoord<0){
			xCoord = world.tiles[0].length+xCoord;
		}
		if(xCoord>(world.tiles[0].length-1)){
			xCoord = xCoord-world.tiles[0].length;
		}
		if(yCoord<0){
			yCoord = world.tiles.length+yCoord;
		}
		if(yCoord>(world.tiles.length-1)){
			yCoord = yCoord-world.tiles.length;
		}
		return world.entities[yCoord][xCoord];
	}
	
	public void updateCoord(int xCoord, int yCoord){
		if(xCoord<0){
			xCoord = world.tiles[0].length+xCoord;
		}
		if(xCoord>(world.tiles[0].length-1)){
			xCoord = xCoord-world.tiles[0].length;
		}
		if(yCoord<0){
			yCoord = world.tiles.length+yCoord;
		}
		if(yCoord>(world.tiles.length-1)){
			yCoord = yCoord-world.tiles.length;
		}
		x=xCoord;
		y=yCoord;
	}
	public int getX(int xCoord){
		if(xCoord<0){
			xCoord = world.tiles[0].length+xCoord;
		}
		if(xCoord>(world.tiles[0].length-1)){
			xCoord = xCoord-world.tiles[0].length;
		}
		return xCoord;
	}
	public int getY(int yCoord){
		if(yCoord<0){
			yCoord = world.tiles.length+yCoord;
		}
		if(yCoord>(world.tiles.length-1)){
			yCoord = yCoord-world.tiles.length;
		}
		return yCoord;
	}
}
