package prototype;

import prototype.annonymous.MessageBox;
import prototype.framework.Manager;
import prototype.framework.Product;

public class PrototypeMain {
	
	public static void main(String[] args) {
		
		Product product = new MessageBox("=====");
		Manager manager = new Manager(product);
		Product cloneProduct = null;
		
		try {
			cloneProduct = (Product)manager.create();
			cloneProduct.use();
			
		} catch (CloneNotSupportedException cnse) {
			cnse.printStackTrace();
		}
				
	} // main

} // class
