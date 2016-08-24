import java.util.ArrayList;
import java.util.Random;


public class Creature extends Entity {
	protected DNA dna;
	protected int vitality;
	protected int energy;
	protected int maxEnergy;
	protected int hunger;
	protected int maxHunger;
	protected boolean eatWater;
	protected boolean eatGrass;
	protected boolean eatCorpse;
	protected boolean eatCreature;
	protected boolean canSwim;
	protected boolean canWalk;
	protected int ageToBreed;
	protected int oldAge;
	protected int energyToBreed;
	
	protected boolean canMove;
	protected boolean canEat;
	protected boolean canBreed;
	protected boolean[] movingOptions;
	protected boolean[] hasFood;
	protected boolean[] breedingOptions;
	protected int lastMove;
	
	public Creature(World world,int x,int y, DNA dna) {
		super(world,x,y);
		type=0;
		this.dna=dna;
		this.vitality=dna.getVitality();
		this.energy=dna.getEnergy();
		this.maxEnergy=dna.getEnergy();
		this.hunger=dna.getHunger();
		this.maxHunger=dna.getHunger();
		this.eatWater=dna.eatWater();
		this.eatGrass=dna.eatGrass();
		this.eatCorpse=dna.eatCorpse();
		this.eatCreature=dna.eatCreature();
		this.canSwim=dna.canSwim();
		this.canWalk=dna.canWalk();
		this.ageToBreed=dna.getBreedAge();
		this.oldAge=dna.getOldAge();
		this.energyToBreed=dna.getEnergyBreed();
		this.lastMove=-1;
	}
	public void update(){
		if(remove){
			return;
		}
		super.update();
		if(hunger<=0){
			vitality--;
			energy--;
		}
		else {
			hunger--;
		}
		if(age>=oldAge){
			vitality--;
		}
		if(coord.getTile(coord.x,coord.y).type==0 && !canWalk){
			die();
			return;
		}
		if(coord.getTile(coord.x,coord.y).type==1 && !canSwim){
			die();
			return;
		}
		if(vitality<=0){
			die();
			return;
		}
		if(energy<1){
			sleep();
			return;
		}
		//System.out.println(this.toString()+" V:"+vitality+" E:"+energy+" H:"+hunger);
		canMove = canIMove();
		canEat = canIEat();
		canBreed = canIBreed();
		//Decision engine
		if(hunger<5 && canEat){
			eat();
		}
		else if(canBreed){
			breed();
		}
		else{
			move();
		}
		
		
	}
	private boolean canIBreed() {
		if(!(energy>=energyToBreed && age>=ageToBreed && hunger>1)){
			return false;
		}
		breedingOptions=new boolean[4];
		
		breedingOptions[0]=false;
		breedingOptions[1]=false;
		breedingOptions[2]=false;
		breedingOptions[3]=false;
		
		if(((coord.getTile(coord.x,coord.y-1).type==0 && canWalk) || (coord.getTile(coord.x,coord.y-1).type==1 && canSwim)) && coord.getEntity(coord.x,coord.y-1)==null){
			breedingOptions[0]=true;
		}
		if(((coord.getTile(coord.x,coord.y+1).type==0 && canWalk) || (coord.getTile(coord.x,coord.y+1).type==1 && canSwim)) && coord.getEntity(coord.x,coord.y+1)==null){
			breedingOptions[1]=true;
		}
		if(((coord.getTile(coord.x-1,coord.y).type==0 && canWalk) || (coord.getTile(coord.x-1,coord.y).type==1 && canSwim)) && coord.getEntity(coord.x-1,coord.y)==null){
			breedingOptions[2]=true;
		}
		if(((coord.getTile(coord.x+1,coord.y).type==0 && canWalk) || (coord.getTile(coord.x+1,coord.y).type==1 && canSwim)) && coord.getEntity(coord.x+1,coord.y)==null){
			breedingOptions[3]=true;
		}
		
		if(breedingOptions[0] || breedingOptions[1] || breedingOptions[2] || breedingOptions[3]){
			return true;
		}
		else {
			return false;
		}
	}
	private boolean canIMove() {
		if(energy<1){
			return false;
		}
		movingOptions=new boolean[4];
		
		movingOptions[0]=false;
		movingOptions[1]=false;
		movingOptions[2]=false;
		movingOptions[3]=false;
		
		if(((coord.getTile(coord.x,coord.y-1).type==0 && canWalk) || (coord.getTile(coord.x,coord.y-1).type==1 && canSwim)) && coord.getEntity(coord.x,coord.y-1)==null){
			movingOptions[0]=true;
		}
		if(((coord.getTile(coord.x,coord.y+1).type==0 && canWalk) || (coord.getTile(coord.x,coord.y+1).type==1 && canSwim)) && coord.getEntity(coord.x,coord.y+1)==null){
			movingOptions[1]=true;
		}
		if(((coord.getTile(coord.x-1,coord.y).type==0 && canWalk) || (coord.getTile(coord.x-1,coord.y).type==1 && canSwim)) && coord.getEntity(coord.x-1,coord.y)==null){
			movingOptions[2]=true;
		}
		if(((coord.getTile(coord.x+1,coord.y).type==0 && canWalk) || (coord.getTile(coord.x+1,coord.y).type==1 && canSwim)) && coord.getEntity(coord.x+1,coord.y)==null){
			movingOptions[3]=true;
		}
		
		if(movingOptions[0] || movingOptions[1] || movingOptions[2] || movingOptions[3]){
			return true;
		}
		else {
			return false;
		}
	}
	private boolean canIEat(){
		if(energy<1){
			return false;
		}
		hasFood=new boolean[4];
		
		hasFood[0]=false;
		hasFood[1]=false;
		hasFood[2]=false;
		hasFood[3]=false;
		if((((coord.getTile(coord.x,coord.y-1).type==0 && canWalk && eatGrass && coord.getTile(coord.x,coord.y-1).food>0) || (coord.getTile(coord.x,coord.y-1).type==1 && canSwim && eatWater && coord.getTile(coord.x,coord.y-1).food>0)) && coord.getEntity(coord.x,coord.y-1)==null && coord.getTile(coord.x,coord.y-1).food>0) 
				|| (coord.getEntity(coord.x,coord.y-1)!=null && ((coord.getEntity(coord.x,coord.y-1).type==1 && eatCorpse) || (coord.getEntity(coord.x,coord.y-1).type==0 && eatCreature)))){
			hasFood[0]=true;
		}
		if((((coord.getTile(coord.x,coord.y+1).type==0 && canWalk && eatGrass && coord.getTile(coord.x,coord.y+1).food>0) || (coord.getTile(coord.x,coord.y+1).type==1 && canSwim && eatWater && coord.getTile(coord.x,coord.y+1).food>0)) && coord.getEntity(coord.x,coord.y+1)==null && coord.getTile(coord.x-1,coord.y+1).food>0) 
				|| (coord.getEntity(coord.x,coord.y+1)!=null && ((coord.getEntity(coord.x,coord.y+1).type==1 && eatCorpse) || (coord.getEntity(coord.x,coord.y+1).type==0 && eatCreature)))){
			hasFood[1]=true;
		}
		if((((coord.getTile(coord.x-1,coord.y).type==0 && canWalk && eatGrass && coord.getTile(coord.x-1,coord.y).food>0) || (coord.getTile(coord.x-1,coord.y).type==1 && canSwim && eatWater && coord.getTile(coord.x-1,coord.y).food>0)) && coord.getEntity(coord.x-1,coord.y)==null && coord.getTile(coord.x-1,coord.y).food>0) 
				|| (coord.getEntity(coord.x-1,coord.y)!=null && ((coord.getEntity(coord.x-1,coord.y).type==1 && eatCorpse) || (coord.getEntity(coord.x-1,coord.y).type==0 && eatCreature)))){
			hasFood[2]=true;
		}
		if((((coord.getTile(coord.x+1,coord.y).type==0 && canWalk && eatGrass && coord.getTile(coord.x+1,coord.y).food>0) || (coord.getTile(coord.x+1,coord.y).type==1 && canSwim && eatWater && coord.getTile(coord.x+1,coord.y).food>0)) && coord.getEntity(coord.x+1,coord.y)==null && coord.getTile(coord.x+1,coord.y).food>0) 
				|| (coord.getEntity(coord.x+1,coord.y)!=null && ((coord.getEntity(coord.x+1,coord.y).type==1 && eatCorpse) || (coord.getEntity(coord.x+1,coord.y).type==0 && eatCreature)))){
			hasFood[3]=true;
		}
		if(hasFood[0] || hasFood[1] || hasFood[2] || hasFood[3]){
			return true;
		}
		else {
			return false;
		}
	}
	private void move(){
		if(canMove){
			
			ArrayList<Integer> options = new ArrayList<Integer>();
			for(int i=0;i<4;i++){
				if(movingOptions[i] && lastMove!=i){
					options.add(i);
				}
			}
			if(options.size()<1){
				return;
			}
			energy--;
			Random rn = new Random();
			int decision = options.get(rn.nextInt(options.size()));
			lastMove=decision;
			switch(decision){
				case 0:
					coord.updateCoord(coord.x, coord.y-1);
					break;
				case 1:
					coord.updateCoord(coord.x, coord.y+1);
					break;
				case 2:
					coord.updateCoord(coord.x-1, coord.y);
					break;
				case 3:
					coord.updateCoord(coord.x+1, coord.y);
					break;
			}
		}
	}
	private void eat(){
		if(canEat){
			
			ArrayList<Integer> options = new ArrayList<Integer>();
			for(int i=0;i<4;i++){
				if(hasFood[i]){
					options.add(i);
				}
			}
			if(options.size()<1){
				return;
			}
			energy--;
			Random rn = new Random();
			int decision = options.get(rn.nextInt(options.size()));
			switch(decision){
				case 0:
					coord.updateCoord(coord.x, coord.y-1);
					break;
				case 1:
					coord.updateCoord(coord.x, coord.y+1);
					break;
				case 2:
					coord.updateCoord(coord.x-1, coord.y);
					break;
				case 3:
					coord.updateCoord(coord.x+1, coord.y);
					break;
			}
			if((eatCorpse || eatCreature) && coord.getEntity(coord.x, coord.y)!=null){
				Entity ent = coord.getEntity(coord.x, coord.y);
				ent.remove();
				hunger+=5;
				energy+=5;
			}
			else if(eatWater || eatGrass){
				Tile til = coord.getTile(coord.x, coord.y);
				til.food-=1;
				hunger+=3;
				energy+=3;
			}
		}
	}
	private void breed(){
		if(canBreed){
			
			ArrayList<Integer> options = new ArrayList<Integer>();
			for(int i=0;i<4;i++){
				if(movingOptions[i]){
					options.add(i);
				}
			}
			if(options.size()<1){
				return;
			}
			energy-=energyToBreed;
			Random rn = new Random();
			int decision = options.get(rn.nextInt(options.size()));
			lastMove=decision;
			switch(decision){
				case 0:
					new Creature(world,coord.getX(coord.x), coord.getY(coord.y-1), new DNA(dna.mutate()));
					break;
				case 1:
					new Creature(world,coord.getX(coord.x), coord.getY(coord.y+1), new DNA(dna.mutate()));
					break;
				case 2:
					new Creature(world,coord.getX(coord.x-1), coord.getY(coord.y), new DNA(dna.mutate()));
					break;
				case 3:
					new Creature(world,coord.getX(coord.x+1), coord.getY(coord.y), new DNA(dna.mutate()));
					break;
			}
		}
	}
	private void die() {
		remove();
		
		new Corpse(world,coord.x,coord.y);
	}
	private void sleep() {
		energy+=5;
	}
}
