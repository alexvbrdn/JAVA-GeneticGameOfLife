import java.util.Random;


public class DNA {
	public float[] dna;
	/* 0 vitality (20 max)
	 * 1 energy (20 max)
	 * 2 hunger (20 max)
	 * 3 al water
	 * 4 al grass
	 * 5 al corpse
	 * 6 al creature
	 * 7 mob land
	 * 8 mob water
	 * 9 breed age (10 max)
	 * 10 old age (10 max)
	 * 11 energy to breed (10 max)
	 */
	public static float[] dnaExample={5f,5f,10f,0f,1f,0f,0f,1f,0f,5f,5f,5f};
	
	public DNA(float[] dna) {
		this.dna = dna;
	}
	public float[] mutate(){
		Random random = new Random();
		float[] dnaMutated = new float[dna.length];
		System.arraycopy(dna, 0, dnaMutated, 0, dna.length);
		float mutationRate = 0.1f;
		
		dnaMutated[random.nextInt(dnaMutated.length)]+=mutationRate;
		//printArray(dnaMutated);
		return dnaMutated;
	}
	public void printArray(float[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	public int getVitality(){
		return Math.abs((int) dna[0]) % 20;
	}
	public int getEnergy(){
		return Math.abs((int) dna[1]) % 20;
	}
	public int getHunger(){
		return Math.abs((int) dna[2]) % 20;
	}
	public boolean eatWater(){
		return ((Math.abs((int) dna[3]) % 2)==1);
	}
	public boolean eatGrass(){
		return ((Math.abs((int) dna[4]) % 2)==1);
	}
	public boolean eatCorpse(){
		return ((Math.abs((int) dna[5]) % 2)==1);
	}
	public boolean eatCreature(){
		return ((Math.abs((int) dna[6]) % 2)==1);
	}
	public boolean canWalk(){
		return ((Math.abs((int) dna[7]) % 2)==1);
	}
	public boolean canSwim(){
		return ((Math.abs((int) dna[8]) % 2)==1);
	}
	public int getBreedAge(){
		return Math.abs((int) dna[9]) % 10;
	}
	public int getOldAge(){
		return (Math.abs((int) dna[10]) % 10)+getBreedAge();
	}
	public int getEnergyBreed(){
		return Math.abs((int) dna[11]) % 10;
	}
}
