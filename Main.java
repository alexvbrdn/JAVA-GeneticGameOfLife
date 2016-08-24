
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int s = 50;
		Tile[][] tiles=new Tile[s][s];
		for(int i=0;i<s;i++){
			for(int j=0;j<s;j++){
				if(i<=25){
					tiles[j][i]=new Tile(i,j,1);
				}
				else{
					tiles[j][i]=new Tile(i,j,0);
				}
			}
		}
		World world = new World(tiles);
		new Creature(world,35,5,new DNA(DNA.dnaExample));
		new Creature(world,36,5,new DNA(DNA.dnaExample));
		new Creature(world,37,5,new DNA(DNA.dnaExample));
		
		while(true){
			world.update();
			world.render();
			//System.out.println("Day "+world.day);
			if(world.numberOfEntity()==0){
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
