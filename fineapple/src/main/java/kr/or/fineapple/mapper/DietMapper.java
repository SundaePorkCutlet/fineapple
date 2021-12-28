package kr.or.fineapple.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;

@Mapper
@Repository
public interface DietMapper {

       int insertDietService(DietServ diet);
       
       DietServ getDietService(String userId);
       
       int insertIntakeRecord(IntakeRecord record);
       
       int updateBodyInfo(DietServ diet);
       
       int updateServiceTrgt(DietServ diet);
       
       List<Food> getFoodList(Search search);
       
       int updateDietService(DietServ diet);
       
       int insertFavMeal(FavMeal favMeal);
       
       int updateFavMealName(FavMeal favMeal);
       
       int deleteFavMeal(int favMealNo);
       
       List<FavMeal> getFavMealList(int dierServiceNo);
       
       int insertFavMealItem(FavMeal favMeal);
       
       List<FavMeal> getFavMealItemList(int favMealNo);
       
       int deleteFavMealItem(int favMealinfoNo);
       
       int updateFavMealItem(FavMeal favMeal);
}

