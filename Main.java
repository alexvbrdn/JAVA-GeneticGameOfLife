import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main {
	public static int refreshTime=100;
	public static String mapPath="map/meadow";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
        
		World world = loadMap(mapPath);
		new Creature(world,2,2,new DNA(DNA.dnaWalking));
		
		while(true){
			world.update();
			world.render();
			//System.out.println("Day "+world.day);
			if(world.numberOfEntity()==0){
				System.out.println("Extinction day:  "+world.day);
				break;
			}
			try {
				Thread.sleep(refreshTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static World loadMap(String file){
		int h=0;
		int w=0;
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			while ((line=br.readLine())!=null){
				w=0;
				String[] parts = line.split("");
				for(int i=0;i<parts.length;i++){
					w++;
				}
				h++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		Tile[][] tiles = new Tile[h][w];
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			int y=0;
			while ((line=br.readLine())!=null){
				String[] parts = line.split("");
				int x=0;
				for(int i=0;i<parts.length;i++){
					tiles[y][x]=new Tile(x,y,Integer.parseInt(parts[i]));
					x++;
				}
				y++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		World world = new World(tiles);
		return world;
	}
	

}
