package ProjectInformaticaPackage;

import java.util.Iterator;

public class HerbivoreThread implements Runnable {

	@Override
	public void run() {
		for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores().iterator(); current.hasNext(); ) {
	    	Herbivore currentAnimal = current.next();
	    	if (currentAnimal.makeDecision()==false) { //false появляется, если объект погибает или если исчезают его остатки
	    		if (currentAnimal.isAlive()) currentAnimal.kill();
	    		else
	    			current.remove();
	    	}
		}
		
		Information.checkIsHerbivoreBorn();
	}
	
	
	
	
}
