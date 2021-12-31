package kr.or.fineapple.service.diet;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;


public interface DietService {
	
	public int addDietService(DietServ diet)throws Exception;
	
	public int updateDietService(DietServ diet)throws Exception;
		
	public DietServ getDietService(String userId)throws Exception;
	
	public List getFoodList(Search search)throws Exception;
	
	public JSONArray getFoodAPIlist(Search search)throws Exception;
	
	public int addFavMeal(FavMeal favMeal)throws Exception;
	
	public int updateFavMealName(FavMeal favMeal)throws Exception;
	
	public int deleteFavMeal(int favMealNo)throws Exception;
	
	public String shoppingAPI(String searchKeyword)throws Exception;
	
	public Map<String,Object> getFavMealList(int dietServiceNo)throws Exception;
	
	public Food getFood(String foodCd)throws Exception;
	
	public int addFood(Food food)throws Exception;
	
	public int updateFood(Food food)throws Exception;
	
	public int deleteFood(String foodCd)throws Exception;
	
	public int addFavMealItem(FavMeal favMeal)throws Exception;
	
	public List getFavMealItemList(int favMealNo)throws Exception;	
	
	public int delteFavMealItem(int favMealInfoNo)throws Exception;
	
	public int updateFavMealItem(FavMeal favMeal)throws Exception;
	
	public int addIntakeRecord(IntakeRecord record)throws Exception;
	
	public int updateIntakeRecord(IntakeRecord record)throws Exception;
	
	public IntakeRecord getIntakeRecordList(int dietServiceNo)throws Exception;
	
	

}
