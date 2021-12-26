package kr.or.fineapple.service.diet;

import java.util.Map;

import org.json.simple.JSONArray;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;


public interface DietService {

	public int addIntakeRecord(IntakeRecord record)throws Exception;
	
	public int addDietService(DietServ diet)throws Exception;
	
	public int updateDietService(DietServ diet)throws Exception;
		
	public DietServ getDietService(String userId)throws Exception;
	
	public Map<String,Object> getFoodList(Search search)throws Exception;
	
	public int addFavMeal(FavMeal favMeal)throws Exception;
	
	public int updateFavMealName(FavMeal favMeal)throws Exception;
	
	public int deleteFavMeal(int favMealNo)throws Exception;
	
	public Map<String,Object> getFavMealList(int dietServiceNo)throws Exception;
	
	public String shoppingAPI(String searchKeyword)throws Exception;
	
	public JSONArray getFoodAPIlist(Search search)throws Exception;
	
	public Food getFood(String foodCd)throws Exception;
	

}
