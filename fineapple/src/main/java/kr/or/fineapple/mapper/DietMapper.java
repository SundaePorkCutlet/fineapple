package kr.or.fineapple.mapper;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.common.ViewDuration;

@Mapper
@Repository
public interface DietMapper {

       int insertDietService(DietServ diet);
       
       int updateDietServiceNo(DietServ diet);
       
       DietServ getDietService(String userId);
       
       int insertIntakeRecord(IntakeRecord record);
       
       int updateBodyInfo(DietServ diet);
       
       int updateServiceTrgt(DietServ diet);
       
       List<Food> getFoodList(Search search);
       
       int updateDietService(DietServ diet);
       
       int insertFavMeal(FavMeal favMeal);
       
       int updateFavMealName(FavMeal favMeal);
       
       int deleteFavMeal(int favMealNo);
       
       List<FavMeal> getFavMealList(int dietServiceNo);
       
       int insertFavMealItem(FavMeal favMeal);
       
       List<FavMeal> getFavMealItemList(int favMealNo);
       
       FavMeal getFavMealItem(int favMealInfoNo);
       
       int deleteFavMealItem(int favMealinfoNo);
       
       int updateFavMealItem(FavMeal favMeal);
       
       List<IntakeRecord> getIntakeRecordList(int dietServiceNo);
       
       int updateIntakeRecord(IntakeRecord record);
       
       int addFood(Food food);
       
       int updateFood(Food food);
       
       int deleteFood(String foodCd);
       
       Food getFood(String foodCd);
       
       void deleteIntakeRecord(int IntakeRecordNo);
       
       List<IntakeRecord> FavIntake(String userId);     
       ////다이어리 진행자 하리니가 작성: 특정 일자의 일일 식단 정보 조회
       List<IntakeRecord> getIntakeRecordListForDiary(Map map);
       
       ////다이어리 진행자 하리니가 작성: 특정 일자의 영양소별 하루 총 섭취정보
       TotalRecord getTotalDietRecord(ViewDuration viewDuration);
}

