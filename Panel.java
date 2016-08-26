import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panel extends JPanel {
	private World world;
	public Panel(World world) {
		this.world=world;
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(800, 0, 150, 830);
		g.setColor(Color.black);
		g.drawString("Day: "+world.day, 810, 20);
		g.drawString("Entities: "+String.valueOf(world.numberOfEntity()), 810, 35);
		
		int w = 800;
		int h = 800;
		int yN = world.tiles.length;
		int xN = world.tiles[0].length;
		int xSize = (int)(h/xN);
		int ySize = (int)(w/yN);
		for(int i=0;i<yN;i++){
			for(int j=0;j<xN;j++){
				if(world.tiles[i][j].type==0){
					if(world.tiles[i][j].food<=0){
						g.setColor(Color.lightGray);
					}
					else{
						g.setColor(Color.green);
					}
					
				}
				else if(world.tiles[i][j].type==1){
					if(world.tiles[i][j].food<=0){
						g.setColor(Color.cyan);
					}
					else{
						g.setColor(Color.blue);
					}
				}
				else {
					g.setColor(Color.black);
				}
				g.fillRect(j*ySize, i*xSize, ySize, xSize);
				//g.setColor(Color.black);
				//g.drawString(String.valueOf(world.tiles[i][j].food), j*ySize+(int)(ySize/2), i*xSize+(int)(xSize/2));
			}
		}
		for(int i=0;i<yN;i++){
			for(int j=0;j<xN;j++){
				if(world.entities[i][j]!=null){
					if(world.entities[i][j].type==0){
						Creature creature = ((Creature) world.entities[i][j]);
						
						if((creature.eatGrass || creature.eatWater) && (creature.eatCorpse || creature.eatCreature)){
							g.setColor(Color.yellow);
						}
						else if(creature.eatGrass || creature.eatWater){
							g.setColor(Color.magenta);
						}
						else if(creature.eatCorpse || creature.eatCreature){
							g.setColor(Color.red);
						}
						else {
							g.setColor(Color.white);
						}
					}
					else if(world.entities[i][j].type==1){
						g.setColor(Color.orange);
					}
					else {
						g.setColor(Color.black);
					}
					g.fillOval(j*ySize, i*xSize, ySize, xSize);
				}
				
			}
		}
	}
}