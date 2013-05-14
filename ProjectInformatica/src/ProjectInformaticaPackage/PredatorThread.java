package ProjectInformaticaPackage;

import java.util.Iterator;

public class PredatorThread implements Runnable {
	/*private Leo a;
	private Leo b;
	@Override
	public void run() {
	if (a!=null) {
		if (!a.makeDecision()) {
			synchronized (this) {
				Information.getLinkedListOfLeos().remove(a);
			}
		}
		} else a=this.findAnimal();
	if (b!=null) {
		if (!b.makeDecision()) {
			synchronized (this) {
				Information.getLinkedListOfLeos().remove(b);
			}
		}
		} else b=this.findAnimal();
	}
	private synchronized Leo findAnimal() {
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
			Leo currentAnimal=current.next();
		    if (currentAnimal.hasNoThread()==true) {
		    	currentAnimal.hasThread();
		        return currentAnimal;
		    }
		}
		return null;
	}
	private int getName() {
		return Integer.parseInt(Thread.currentThread().getName());
	}
	public PredatorThread(Leo a, Leo b) {
		this.a=a;
		this.b=b;
	}*/
	@Override
	public void run() {
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
	    	LifeForm currentAnimal = current.next();
	    	if (currentAnimal.makeDecision()==false) { //false появляется, если объект погибает
	        	current.remove();
	    	}
		}
		
		Information.checkIsLeoBorn();
	}
	
	
	
	
}
