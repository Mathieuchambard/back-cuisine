package blockchain.entrepreneur.cuisine.model.menus;

import java.util.ArrayList;
import java.util.List;

public class WeekMenus {
	private List<Menus> allMenus;
	
	public WeekMenus(RestrictionStructureMenus res) {
		this.allMenus = new ArrayList<Menus>();
		for (int i = 0;i< 14;i++) {
			switch (res) {
				case ALL :
					this.allMenus.add(new Menus(true,true,true));
					break;
				case PLATETENTREE :
					this.allMenus.add(new Menus(true,true,false));
					break;
				case PLATETDESSERT :
					this.allMenus.add(new Menus(false,true,true));
					break;
				case PLATENTREEOUDESSERT :
					if (((i+1) /2) %2 == 0) this.allMenus.add(new Menus(true,true,false));
					else this.allMenus.add(new Menus(false,true,true));
					break;
				case PLAT :
					this.allMenus.add(new Menus(false,true,false));
					break;
					 
			}
		}
	}
	
	
	public void fillPlat(List<RecipeMenus> list,boolean noTime) {
		for (int i = 0;i<this.allMenus.size();i++) {
			if (noTime) this.allMenus.get(i).setPlatRecipe(list.get((int) ((i+1)/2)).getRecipe());
			else this.allMenus.get(i).setPlatRecipe(list.get(i).getRecipe());
		}
	}
	public void fillDessert(List<RecipeMenus> list) {
		int n = 0;
		for (int i = 0;i<this.allMenus.size();i++) {
			if (this.allMenus.get(i).isDessert()) {
				this.allMenus.get(i).setDessertRecipe(list.get((int) (n/2)).getRecipe());
				n ++;
			}
		}
	}
	
	public void fillEntree(List<RecipeMenus> list) {
		int n = 0;
		for (int i = 0;i<this.allMenus.size();i++) {
			if (this.allMenus.get(i).isDessert()) {
				this.allMenus.get(i).setEntreeRecipe(list.get((int) (n/2)).getRecipe());
				n ++;
			}
		}
	}
	
}
