package ProjectInformaticaPackage;

public class AnimalWish implements Comparable<AnimalWish>{    //сейчас этот класс не используется
	private int numberOfLeoNeed;
	private float valueOfWish;
	
	public AnimalWish(int number, float value) {
		this.numberOfLeoNeed=number;
		this.valueOfWish=value;
	}
	public int getNumberOfAnimalNeed() {
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
	public int compareTo(AnimalWish a) {
		if (a.valueOfWish>this.valueOfWish) return -1;   //так сделал return, чтобы массив сортировался
		if (a.valueOfWish<this.valueOfWish) return 1;    // в порядке убывания
		return 0;
	}
	
}
