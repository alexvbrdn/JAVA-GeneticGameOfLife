import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;


public class World {
	private Window window;
	public Tile[][] tiles;
	public Entity[][] entities;
	private ArrayList<Entity> newEntities = new ArrayList<Entity>();
	private ArrayList<Entity> listToRefresh = new ArrayList<Entity>();
	public int day;
	public static int instance=0;
	public World(Tile[][] tiles){
		instance++;
		this.day=0;
		entities = new Entity[tiles.length][tiles[0].length];
		this.tiles = tiles;
		this.window = new Window(this);
		
	}
	public boolean newEntity(Entity entity, int x, int y){
		if(entities[y][x] == null){
			entities[y][x]=entity;
			newEntities.add(entity);
			return true;
		}
		else{
			return false;
		}
	}
	public void update(){
		if(instance>1){
			System.out.println("Error  instances: "+instance);
		}
		day++;
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles[0].length;j++){
				tiles[i][j].update();
			}
		}
		
		
		
		Iterator<Entity> iter = listToRefresh.iterator(); 
		while(iter.hasNext()) {
			Entity entity = iter.next();
			int tempY= entity.coord.y;
			int tempX= entity.coord.x;
			//entities[entity.coord.y][entity.coord.x]=null;
			entities[tempY][tempX]=null;
			if(!(entity.remove)){
				entity.update();
			}
			
			if(entity.remove){
				iter.remove();
				listToRefresh.remove(entity);
			}
			else {
				entities[entity.coord.y][entity.coord.x]=entity;
			}
		}
		ArrayList<Entity> newList = new ArrayList<Entity>();
		for(int i=0;i<entities.length;i++){
			for(int j=0;j<entities[0].length;j++){
				if(newEntities.contains(entities[i][j]) && listToRefresh.contains(entities[i][j])){
					newEntities.remove(entities[i][j]);
				}
				if(newEntities.contains(entities[i][j]) && !entities[i][j].remove){
					newList.add(entities[i][j]);
				}
				else if(listToRefresh.contains(entities[i][j]) && !entities[i][j].remove){
					newList.add(entities[i][j]);
				}
				else {
					entities[i][j]=null;
				}
			}
		}
		listToRefresh = (ArrayList<Entity>) newList.clone();
		newEntities.clear();
	}
	public void render(){
		window.render();
	}	
	public void fertilize(int xPos, int yPos) {
		tiles[yPos][xPos].food=tiles[yPos][xPos].food+1;
	}
	public int numberOfEntity(){
		int numberOfEntity=listToRefresh.size();
		if(numberOfEntity>tiles.length*tiles.length){
			System.out.println("Error  number of entities: "+numberOfEntity);
			System.out.println("       max: "+tiles.length*tiles.length);
			System.exit(0);
		}
		return numberOfEntity;
	}
}