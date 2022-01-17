package kr.or.fineapple.service.diet;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.Recipe;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.common.ViewDuration;


public interface DietService {
	
	public int addDietService(DietServ diet)throws Exception;
	
	public int updateDietService(DietServ diet)throws Exception;
		
	public DietServ getDietService(String userId)throws Exception;
	
	public int updateDietServiceNo(DietServ diet)throws Exception;
	
	public List getFoodList(Search search)throws Exception;
	
	public JSONArray getFoodAPIlist(Search search)throws Exception;
	
	public int addFavMeal(FavMeal favMeal)throws Exception;
	
	public int updateFavMealName(FavMeal favMeal)throws Exception;
	
	public int deleteFavMeal(int favMealNo)throws Exception;
	
	public String shoppingAPI(String searchKeyword,int startNum,int endNum)throws Exception;
	
	public Map<String,Object> getFavMealList(int dietServiceNo)throws Exception;
	
	public Food getFood(String foodCd)throws Exception;
	
	public int addFood(Food food)throws Exception;
	
	public int updateFood(Food food)throws Exception;
	
	public int deleteFood(String foodCd)throws Exception;
	
	public int addFavMealItem(FavMeal favMeal)throws Exception;
	
	public List<FavMeal> getFavMealItemList(int favMealNo)throws Exception;	
	
	public int delteFavMealItem(int favMealInfoNo)throws Exception;
	
	public int updateFavMealItem(FavMeal favMeal)throws Exception;
	
	public int addIntakeRecord(IntakeRecord record)throws Exception;
	
	public int updateIntakeRecord(IntakeRecord record)throws Exception;
	
	public List<IntakeRecord> getIntakeRecordList(int dietServiceNo)throws Exception;
	
	public void deleteIntakeRecord(int IntakeRecordNo)throws Exception;
	
	public FavMeal getFavMealItem(int favMealInfoNo);
	
	public JSONArray getrcpList(Search search);
	
	public Recipe getRcp(String rcpCd);
	
	public List<IntakeRecord> FavIntake(String userId);
	////다이어리 진행자 하리니가 작성
	public List<IntakeRecord> getIntakeRecordListForDiary(LocalDate startDate, LocalDate endDate, int userServiceNo);
	
	////다이어리 진행자 하리니가 작성: 특정 일자의 영양소별 하루 총 섭취정보
    public TotalRecord getTotalDietRecord(ViewDuration viewDuration);
    
    ////다이어리 진행자 하리니가 작성: 기간 내 기록일의 수(테이블 내 기록이 존재하는 일자의 갯수)
    public int getDaysCount(ViewDuration viewDuration);
    
    ////다이어리 진행자 하리니가 작성: 기간 내 가장 섭취 칼로리가 큰 날과 그 날의 총 섭취 칼로리
    public TotalRecord getTheHighestIntakeKcalDay(ViewDuration viewDuration);

}
