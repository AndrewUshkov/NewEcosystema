package ProjectInformaticaPackage;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Herbivore implements LifeForm {
	private boolean male;
	private float age;
	private float passion;
	private float starvation;
	private float exhaustion;
	private int xPosition;
	private int yPosition;
	private float starvationCoefficient;
	private float passionCoefficient;
	private float exhaustionCoefficient;
	private float legacyStarvationCoefficient;
	private float legacyPassionCoefficient;
	private float legacyExhaustionCoefficient;
	private float decision;
	private int timeOfPregnant = -1;
	private boolean wantToBorn = false;
	private int meat = 40;
	private Herbivore fromWhom = null;
	private boolean isChild = true;
	private char previousAction = 0;
	private Herbivore badFemale = null; // самка, несогласна€ на спаривание
	public int timeOfInertion = this.isChild ? 30 : 0;
	public AnimalWish wish[] = { new AnimalWish(0, 0), new AnimalWish(0, 0),
			new AnimalWish(0, 0) };
	private boolean hasNoThread = true;
	private boolean isAlive = true;
	private boolean bigCursor = false;

	public float getExhaustion() {
		return exhaustion;
	}

	public void setExhaustion(float exhaustion) {
		this.exhaustion = exhaustion;
	}

	public float getExhaustionCoefficient() {
		return exhaustionCoefficient;
	}

	public void setExhaustionCoefficient(float exhaustionCoefficient) {
		this.exhaustionCoefficient = exhaustionCoefficient;
	}

	public boolean isChild() {
		return this.isChild;
	}

	public float getLegacyExhaustionCoefficient() {
		return legacyExhaustionCoefficient;
	}

	public void setLegacyExhaustionCoefficient(float legacyExhaustionCoefficient) {
		this.legacyExhaustionCoefficient = legacyExhaustionCoefficient;
	}

	ArrayList<Herbivore> listOfHerbivores = Information.getListOfHerbivores();

	// конструктор
	public Herbivore(boolean IfMale, int startXPosition, int startYPosition,
			float startAge, float startStarvation, float startPassion,
			float startExhaustion, float startStarvationCoefficient,
			float startPassionCoefficient, float startExhaustionCoefficient,
			float legacyStarvationCoefficient, float legacyPassionCoefficient,
			float legacyExhaustionCoefficient) {
		this.male = IfMale;
		this.xPosition = startXPosition;
		this.yPosition = startYPosition;
		this.age = startAge;
		this.starvation = startStarvation;
		this.passion = startPassion;
		this.exhaustion = startExhaustion;
		this.legacyPassionCoefficient = legacyPassionCoefficient;
		this.legacyStarvationCoefficient = legacyStarvationCoefficient;
		this.legacyExhaustionCoefficient = legacyExhaustionCoefficient;
		this.passionCoefficient = startPassionCoefficient;
		this.starvationCoefficient = startStarvationCoefficient;
		this.exhaustionCoefficient = startExhaustionCoefficient;

	}

	public float getAge() {
		return age;
	}

	public void setAge(float newAge) {
		this.age = newAge;
	}

	public float getPassion() {
		return passion;
	}

	public int getPreviousAction() {
		return this.previousAction;
	}

	public void setPassion(float passion) {
		this.passion = passion;
	}

	public float getStarvation() {
		return starvation;
	}

	public void setStarvation(float starvation) {
		this.starvation = starvation;
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isMale() {
		return this.male;
	}

	public float getStarvationCoefficient() {
		return this.starvationCoefficient;
	}

	public void setPassionCoefficient(float newPCoef) {
		this.passionCoefficient = newPCoef;
	}

	public float getLegacyStarvationCoefficient() {
		return this.legacyStarvationCoefficient;
	}

	public void setLegacyStarvationCoefficient(float newLSCoef) {
		this.starvationCoefficient = newLSCoef;
	}

	public float getLegacyPassionCoefficient() {
		return this.legacyPassionCoefficient;
	}

	public void setLegacyPassionCoefficient(float newLPCoef) {
		legacyPassionCoefficient = newLPCoef;
	}

	public Image getAnimalImage() {
		if (this.isAlive) {
			if (this.isMale()) {
				if (this.previousAction == 3) {
					return Information.getImageSleepingHerbivore();
				}
				if (this.isChild) {
					return Information.getImageHerbivoreChild();
				}
				if (this.previousAction == 7) {
					return Information.getImageHerbivoreRun();
				}
				return Information.getImageHerbivore();
			} else {
				if (this.previousAction == 3) {
					return Information.getImageSleepingHerbivoreFemale();
				}
				if (this.isChild) {
					return Information.getImageHerbivoreFemaleChild();
				}
				if (this.previousAction == 7) {
					return Information.getImageHerbivoreFemaleRun();
				}
				return Information.getImageHerbivoreFemale();
			}
		} else {
			if (this.meat >= 80) {
				return Information.getImageLotOfMeat();
			}
			if ((this.meat >= 40) && (this.meat < 80)) {
				return Information.getImageMiddleMeat();
			} else
				Information.getImageFewMeat();
		}
		return Information.getImageFewMeat();
	}

	public boolean femaleAgree() {
		if ((passion <= 80) && (this.previousAction != 3))
			return true;
		return false;
	}

	public CoordinatesXY goToNearestPredator() {
		CoordinatesXY Coords = new CoordinatesXY();
		Iterator<Herbivore> currentLifeForm = Information.getLinkedListOfHerbivores().iterator();
		if (currentLifeForm.hasNext()) {
			currentLifeForm.next();
		}
		Herbivore nearestHerbivore = Information.getLinkedListOfHerbivores().getFirst();
		while (currentLifeForm.hasNext()) {
			Herbivore element = currentLifeForm.next();
			if ((element.getXPosition() + element.getYPosition())
					* (element.getXPosition() + element.getYPosition()) < (nearestHerbivore
					.getXPosition() + nearestHerbivore.getYPosition())
					* (nearestHerbivore.getXPosition() + nearestHerbivore
							.getYPosition())) {
				nearestHerbivore = element;
			}

		}

		Coords.setXCoord(nearestHerbivore.getXPosition());
		Coords.setYCoord(nearestHerbivore.getYPosition());
		if (Coords.getXCoord() >= this.xPosition) {
			this.xPosition += 4;
		} else
			this.xPosition -= 4;
		if (Coords.getYCoord() >= this.yPosition) {
			this.yPosition += 4;
		} else
			this.yPosition -= 4;
		if (this.xPosition >= Information.getDefaultWeight()) {
			this.xPosition -= Information.getDefaultWeight();
		}
		if (this.xPosition <= 0) {
			this.xPosition += Information.getDefaultWeight();
		}
		if (this.yPosition >= Information.getDefaultHeight()) {
			this.yPosition -= Information.getDefaultHeight();
		}
		if (this.yPosition <= 0) {
			this.yPosition += Information.getDefaultHeight();
		}
		float x = Coords.getXCoord();
		float y = Coords.getYCoord();
		if ((this.starvation <= 100 - 2 * this.starvationCoefficient)
				&& ((xPosition - x) * (xPosition - x) + (yPosition - y)
						* (yPosition - y) < Information.getSizeOfCell()
						* Information.getSizeOfCell()))
			this.starvation += 2 * this.starvationCoefficient;
		return Coords;

	}

	public void setFromWhom(Herbivore FromWhom) {
		this.fromWhom = FromWhom;  //от кого беременна
	}

	public boolean feelHungry() {
		int size=Information.getSizeOfCell();
		//ищем ближайшую траву
		if (!Information.getLinkedListOfGrass().isEmpty()) {
			Grass nearestGrass = Information.getLinkedListOfGrass().getFirst();
			int nearestDistance = (nearestGrass.getXPosition()*size - this.xPosition)
					* (nearestGrass.getXPosition()*size - this.xPosition)
					+ (nearestGrass.getYPosition()*size - this.yPosition)
					* (nearestGrass.getYPosition()*size - this.yPosition);
			int currentDistance;
			Grass currentGrass;
			for (Iterator<Grass> current = Information.getLinkedListOfGrass().iterator(); current.hasNext();) {
				currentGrass = current.next();
				currentDistance = (currentGrass.getXPosition()*size - this.xPosition)
						* (currentGrass.getXPosition()*size - this.xPosition)
						+ (currentGrass.getYPosition()*size - this.yPosition)
						* (currentGrass.getYPosition()*size - this.yPosition);

				if (currentDistance < nearestDistance) {
					nearestGrass = currentGrass;
					nearestDistance = currentDistance;
				}
			}
			
			//теперь мы знаем ближайшую траву, и движемс€ к ней на шажок

			if (nearestGrass.getXPosition()*size >= this.xPosition) {
				this.xPosition += 4;
			} else
				this.xPosition -= 4;
			if (nearestGrass.getYPosition()*size >= this.yPosition) {
				this.yPosition += 4;
			} else
				this.yPosition -= 4;
			if (this.xPosition >= Information.getDefaultWeight()) {
				this.xPosition -= Information.getDefaultWeight();
			}
			if (this.xPosition <= 0) {
				this.xPosition += Information.getDefaultWeight();
			}
			if (this.yPosition >= Information.getDefaultHeight()) {
				this.yPosition -= Information.getDefaultHeight();
			}
			if (this.yPosition <= 0) {
				this.yPosition += Information.getDefaultHeight();
			}
			
			//если подобрались близко к траве, чтобы ее есть, то мы ее едим
			if (nearestDistance <= Information.getSizeOfCell()) {

				this.starvation += nearestGrass.eat();
				if (this.starvation > 100)
					this.starvation = 100;
			}
			return true;

		} else
			return false;
	}

	public boolean wantToBorn() {
		return this.wantToBorn;
	}

	public void setTimeOfPregnant(int time) {
		this.timeOfPregnant = time;
	}

	public Herbivore getFromWhom() {
		return this.fromWhom;
	}

	private void becomePregnant() {
		this.timeOfPregnant = 30;
	}

	private void tryMakeChildren(Herbivore female) {
		if (female.femaleAgree()) {
			this.setXPosition(female.getXPosition());
			this.setYPosition(female.getYPosition());
			this.timeOfInertion = 10;
			female.timeOfInertion = 10;
			this.previousAction = 8;
			female.previousAction = 8;
			this.passion = 100;
			female.setPassion(100);
			this.badFemale = null;
			female.setFromWhom(this);
			female.becomePregnant();
		} else {
			if (female.previousAction != 3) {
				this.badFemale = female;
				this.timeOfInertion = 0;
			}
		}

	}

	private boolean feelPassion(Herbivore badFemale) 
{
		if (this.isMale()) {     
			int numberMaleChildren = 0;           
			if (Information.getLinkedListOfHerbivores().size() > 1) {
				Herbivore nearestHerbivore = null;
				Herbivore currentHerbivore;
				int currentDistance = -1;
				int nearestDistance = -1;
				for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores()
						.iterator(); current.hasNext();) {
					currentHerbivore = current.next();
					if ((currentHerbivore.isChild())
							&& (currentHerbivore.isMale())) {
						numberMaleChildren++;
					}    
					if ((nearestHerbivore == null)   
							&& (currentHerbivore.isMale())
							&& (!currentHerbivore.isChild())
							&& (currentHerbivore.timeOfPregnant == -1)
							&& (!currentHerbivore.equals(badFemale))) {    
						nearestHerbivore = currentHerbivore;
						nearestDistance = (nearestHerbivore.getXPosition() - this.xPosition)
								* (nearestHerbivore.getXPosition() - this.xPosition)
								+ (nearestHerbivore.getYPosition() - this.yPosition)
								* (nearestHerbivore.getYPosition() - this.yPosition);
					}
					if ((nearestHerbivore != null)
							&& (nearestHerbivore != currentHerbivore)
							&& (currentHerbivore.isMale())
							&& (!currentHerbivore.isChild())
							&& (currentHerbivore.timeOfPregnant == -1)
							&& (!currentHerbivore.equals(badFemale))) {
						currentDistance = (currentHerbivore.getXPosition() - this.xPosition)
								* (currentHerbivore.getXPosition() - this.xPosition)
								+ (currentHerbivore.getYPosition() - this.yPosition)
								* (currentHerbivore.getYPosition() - this.yPosition);
						if (currentDistance < nearestDistance) {
							nearestDistance = currentDistance;
							nearestHerbivore = currentHerbivore;
						}

					}

				}

				if ((nearestHerbivore == null) && (numberMaleChildren == 0)) {  
					this.timeOfInertion = 0;
					return false;
				} else {   
					if (nearestHerbivore != null) {
						if (nearestHerbivore.getXPosition() >= this.xPosition) {
							this.xPosition += 4;
						} else
							this.xPosition -= 4;
						if (nearestHerbivore.getYPosition() >= this.yPosition) {
							this.yPosition += 4;
						} else
							this.yPosition -= 4;
						if (this.xPosition >= Information.getDefaultWeight()) {
							this.xPosition -= Information.getDefaultWeight();
						}
						if (this.xPosition <= 0) {
							this.xPosition += Information.getDefaultWeight();
						}
						if (this.yPosition >= Information.getDefaultHeight()) {
							this.yPosition -= Information.getDefaultHeight();
						}
						if (this.yPosition <= 0) {
							this.yPosition += Information.getDefaultHeight();
						}
						if ((nearestDistance <= Information.getSizeOfCell())
								&& (this.isMale())) {
							this.tryMakeChildren(nearestHerbivore);
						}
						return true;

					}
					return true;
				}
			} else
				return false;
		} 
else
{
	int numberFemaleChildren = 0;           

	if (Information.getLinkedListOfHerbivores().size() > 1) {
		Herbivore nearestHerbivore = null;
		Herbivore currentHerbivore;
		int currentDistance = -1;
		int nearestDistance = -1;
		for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores()
				.iterator(); current.hasNext();) {
			currentHerbivore = current.next();
			if ((currentHerbivore.isChild())
					&& (!currentHerbivore.isMale())) {
				numberFemaleChildren++;
			}                         
			if ((nearestHerbivore == null)   
					&& (!currentHerbivore.isMale())
					&& (!currentHerbivore.isChild())
					&& (currentHerbivore.timeOfPregnant == -1)
					&& (!currentHerbivore.equals(badFemale))) {    
				nearestHerbivore = currentHerbivore;
				nearestDistance = (nearestHerbivore.getXPosition() - this.xPosition)
						* (nearestHerbivore.getXPosition() - this.xPosition)
						+ (nearestHerbivore.getYPosition() - this.yPosition)
						* (nearestHerbivore.getYPosition() - this.yPosition);
			}
			if ((nearestHerbivore != null)
					&& (nearestHerbivore != currentHerbivore)
					&& (!currentHerbivore.isMale())
					&& (!currentHerbivore.isChild())
					&& (currentHerbivore.timeOfPregnant == -1)
					&& (!currentHerbivore.equals(badFemale))) {
				currentDistance = (currentHerbivore.getXPosition() - this.xPosition)
						* (currentHerbivore.getXPosition() - this.xPosition)
						+ (currentHerbivore.getYPosition() - this.yPosition)
						* (currentHerbivore.getYPosition() - this.yPosition);
				if (currentDistance < nearestDistance) {
					nearestDistance = currentDistance;
					nearestHerbivore = currentHerbivore;
				}

			}

		}

		if ((nearestHerbivore == null) && (numberFemaleChildren == 0)) {  //самок нет и нет подрастающих самок
			this.timeOfInertion = 0;
			return false;
		} else {       
			if (nearestHerbivore != null) {
				if (nearestHerbivore.getXPosition() >= this.xPosition) {
					this.xPosition += 4;
				} else
					this.xPosition -= 4;
				if (nearestHerbivore.getYPosition() >= this.yPosition) {
					this.yPosition += 4;
				} else
					this.yPosition -= 4;
				if (this.xPosition >= Information.getDefaultWeight()) {
					this.xPosition -= Information.getDefaultWeight();
				}
				if (this.xPosition <= 0) {
					this.xPosition += Information.getDefaultWeight();
				}
				if (this.yPosition >= Information.getDefaultHeight()) {
					this.yPosition -= Information.getDefaultHeight();
				}
				if (this.yPosition <= 0) {
					this.yPosition += Information.getDefaultHeight();
				}
				if ((nearestDistance <= Information.getSizeOfCell())
						&& (!this.isMale())) {
					this.tryMakeChildren(nearestHerbivore);
				}
				return true;

			}
			return true;
		}
	} else
		return false;
}
			
	}

	private void bornNewHerbivore() {
		this.exhaustion -= 10;
		this.starvation -= 10;
		this.wantToBorn = true;
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void kill() {
		this.isAlive = false;
		this.timeOfInertion = 100;
	}

	public void eatMeat(int meat) {
		this.meat -= meat;
		if (this.meat <= 0) {
			this.timeOfInertion = 0;
		}
	}

	private boolean feelSleepy() {
		if (this.exhaustion < 2 * this.exhaustionCoefficient) {
			this.exhaustion += 2 * this.exhaustionCoefficient;
		} else
			this.exhaustion = 100;
		return true;
	}

	private int getDecision() {

		if (this.RunAwayFromPredator()) {
			this.age -= 0.5; // установка новых значений полей
			this.starvation -= this.starvationCoefficient;

			if (passion > 20) {
				this.passion -= this.passionCoefficient;
			}
			if (passion <= 20)
				this.badFemale = null;

			this.exhaustion -= this.exhaustionCoefficient;

			if (this.age <= 0) {
				System.out.println("Age");
				return 0;
			}
			if (this.starvation <= 0) {
				System.out.println("Starvation");
				return 0;
			}
			if (this.exhaustion <= 0)
				return 3;

			if (this.isAlive)
				this.meat = (int) (this.starvation + this.age) / 2;

			return 7;

		} else {
			if (this.timeOfInertion == 0) {
				if (!this.isAlive)
					return 0;
				if (this.isChild)
					this.isChild = false;
				this.age -= 0.5; // установка новых значений полей
				this.starvation -= this.starvationCoefficient;

				if (passion > 20) {
					this.passion -= this.passionCoefficient;
				}
				if (passion <= 20)
					this.badFemale = null;

				this.exhaustion -= this.exhaustionCoefficient;
				if (this.timeOfPregnant > 0)
					this.timeOfPregnant--;

				if (this.age <= 0) {
					System.out.println("Age");
					return 0;
				}
				if (this.starvation <= 0) {
					System.out.println("Starvation");
					return 0;
				}
				if (this.exhaustion <= 0)
					return 3;

				if (this.timeOfPregnant == 0) {
					return 5;
				}

				if (this.isAlive)
					this.meat = (int) (this.starvation + this.age) / 2;

				this.wish[0] = new AnimalWish(1, this.starvation);
				this.wish[1] = new AnimalWish(2, this.passion);
				this.wish[2] = new AnimalWish(3, this.exhaustion);

				Arrays.sort(wish); // сортируем желани€ траво€дного по степени
									// важности
				this.previousAction = (char) wish[0].getNumberOfAnimalNeed();

				if (wish[0].getNumberOfAnimalNeed() == 1)
					this.timeOfInertion = 10;
				if (wish[0].getNumberOfAnimalNeed() == 2)
					this.timeOfInertion = 5;
				if (wish[0].getNumberOfAnimalNeed() == 3)
					this.timeOfInertion = 20;
				return wish[0].getNumberOfAnimalNeed();

			} else {
				this.timeOfInertion--;
				if ((!this.isChild) && (this.isAlive)) {
					this.age -= 0.5; // установка новых значений полей
					this.starvation -= this.starvationCoefficient;

					if (passion > 20) {
						this.passion -= this.passionCoefficient;
					}
					if (passion <= 20)
						this.badFemale = null;

					this.exhaustion -= this.exhaustionCoefficient;
					if (this.timeOfPregnant > 0)
						this.timeOfPregnant--;

					if (this.age <= 0) {
						System.out.println("Age");
						return 0;
					}
					if (this.starvation <= 0) {
						System.out.println("Starvation");
						return 0;
					}
					if (this.exhaustion <= 0) {
						this.timeOfInertion = 0;
						return 3;
					}

					if (this.timeOfPregnant == 0) {
						this.timeOfInertion = 0;
						return 5;
					}

					if (this.isAlive)
						this.meat = (int) (this.starvation + this.age) / 2;

				}
				return 6;
			}

		}
	}

	private boolean doActionWithNumber(int number) {
		if (number == 1)
			return this.feelHungry();
		if (number == 2)
			return this.feelPassion(this.badFemale);
		if (number == 3)
			return this.feelSleepy();
		return false;
	}

	public void setWantToBorn(boolean a) {
		this.wantToBorn = a;
	}

	public boolean makeDecision() {
		decision = this.getDecision();
		if (decision == 7) {
			this.previousAction = 7;
			this.timeOfInertion = 0;
		}
		if (decision == 0) {
			return false;
		}
		if (decision == 5) {
			this.bornNewHerbivore();
		}
		if ((decision == 6) && (this.isAlive)) {
			switch (this.previousAction) {
			case 0:
				break;
			case 1:
				 this.feelHungry();
				break;
			case 2:
				this.feelPassion(this.badFemale);
				break;
			case 3:
				this.feelSleepy();
				break;
			case 8:
				break;
			}
		}

		if ((decision == 1) || (decision == 2) || (decision == 3)) {

			if (!this.doActionWithNumber(wish[0].getNumberOfAnimalNeed())) {
				if (!this.doActionWithNumber(wish[1].getNumberOfAnimalNeed())) {
					this.doActionWithNumber(wish[2].getNumberOfAnimalNeed()); // здесь
																				// можно
																				// оставить
																				// последнее
																				// действие
																				// без
																				// проверок
																				// каких-либо, потому что сон всегда возвращает true.
					this.previousAction = (char) wish[2]                    
							.getNumberOfAnimalNeed();
				} else
					this.previousAction = (char) wish[1]
							.getNumberOfAnimalNeed();           //тут ооочень глубокий смысл, то есть животное может хотеть одно, но по невозможности
			}                                                   // приходитс€ делать другое

		}

		return true;
	}



	/*
	 * ќсуществл€етс€ проверка каждый раз, когда принимаетс€ решение. ≈сли р€дом
	 * есть хищники, траво€дный убегает от них. ћетод возвращает true если нужно
	 * убегать и false если не нужно.
	 */
	private boolean RunAwayFromPredator() {
		// —корость бегства
		int speed = 3;
		// ƒистанци€ опасности.
		int DangerDistance = 100;

		DangerDistance = DangerDistance * DangerDistance;
		int min = DangerDistance + 1;
		int currentDistance;
		Leo currentLeo;

		// ћертвые не убегают.
		if (!this.isAlive) {
			return false;
		}
		// ƒети не убегают
		if (this.isChild) {
			return false;
		}

		// –ассто€ние до ближайшего хищника
		for (Iterator<Leo> current = Information.getLinkedListOfLeos()
				.iterator(); current.hasNext();) {
			currentLeo = current.next();
			currentDistance = (currentLeo.getXPosition() - this.xPosition)
					* (currentLeo.getXPosition() - this.xPosition)
					+ (currentLeo.getYPosition() - this.yPosition)
					* (currentLeo.getYPosition() - this.yPosition);
			if (currentDistance < min) {
				min = currentDistance;
			}
		}

		// ≈сли хищники далеко, нужно жить обычной жизнью.
		if (min > DangerDistance) {
			return false;
		}

		// ≈сли хищники близко, решаем, куда бежать.

		// ¬арианты отступлени€ по 8 направлени€м. Speed - скорость бегства
		int speed45 = (int) Math.floor(((double) speed) / 1.41);

		int[][] way = new int[8][2];

		way[0][0] = speed;
		way[0][1] = 0;

		way[1][0] = -speed;
		way[1][1] = 0;

		way[2][0] = 0;
		way[2][1] = speed;

		way[3][0] = 0;
		way[3][1] = -speed;

		way[4][0] = speed45;
		way[4][1] = speed45;

		way[5][0] = speed45;
		way[5][1] = -speed45;

		way[6][0] = -speed45;
		way[6][1] = speed45;

		way[7][0] = -speed45;
		way[7][1] = -speed45;

		int bestdir = 0;
		int max = -1;

		// ѕосле какого шага львы будут дальше всего
		for (int i = 0; i <= 7; i++) {
			min = 20000;
			for (Iterator<Leo> current = Information.getLinkedListOfLeos()
					.iterator(); current.hasNext();) {
				currentLeo = current.next();
				currentDistance = (currentLeo.getXPosition() - way[i][0] - this.xPosition)
						* (currentLeo.getXPosition() - way[i][0] - this.xPosition)
						+ (currentLeo.getYPosition() - way[i][1] - this.yPosition)
						* (currentLeo.getYPosition() - way[i][1] - this.yPosition);
				if (currentDistance < min) {
					min = currentDistance;
				}
			}
			if (min > max) {
				max = min;
				bestdir = i;
			}
		}

		// “акой шаг и делаем
		this.setXPosition(this.xPosition + way[bestdir][0]);
		this.setYPosition(this.yPosition + way[bestdir][1]);
		return true;
	}

	public void setBigCursor(boolean a) {
		this.bigCursor = a;
	}

	public boolean haveBigCursor() {
		return this.bigCursor;
	}

}
