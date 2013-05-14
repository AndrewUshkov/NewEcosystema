package ProjectInformaticaPackage;

public class LeoWish implements Comparable<LeoWish>{    //сейчас этот класс не используется
	private int numberOfLeoNeed;
	private float valueOfWish;
	
	public LeoWish(int number, float value) {
		this.numberOfLeoNeed=number;
		this.valueOfWish=value;
	}
	public int getNumberOfLeoNeed() {
		return numberOfLeoNeed;
	}
	public void setNumberOfLeoNeed(int numberOfLeoNeed) {
		this.numberOfLeoNeed = numberOfLeoNeed;
	}
	public float getValueOfWish() {
		return valueOfWish;
	}
	public void setValueOfWish(float valueOfWish) {
		this.valueOfWish = valueOfWish;
	}
	public int compareTo(LeoWish a) {
		if (a.valueOfWish>this.valueOfWish) return 1;
		if (a.valueOfWish<this.valueOfWish) return -1;
		return 0;
	}
	
}
