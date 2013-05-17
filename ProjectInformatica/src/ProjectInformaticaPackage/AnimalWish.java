package ProjectInformaticaPackage;

public class AnimalWish implements Comparable<AnimalWish>{    //������ ���� ����� �� ������������
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
		if (a.valueOfWish>this.valueOfWish) return -1;   //��� ������ return, ����� ������ ������������
		if (a.valueOfWish<this.valueOfWish) return 1;    // � ������� ��������
		return 0;
	}
	
}
