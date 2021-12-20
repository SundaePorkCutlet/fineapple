package kr.or.fineapple.mapper;

<<<<<<< HEAD
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.IntakeRecord;

@Mapper
@Repository
public interface DietMapper {

       int insertDietService(DietServ diet);
     
       DietServ getDietService(String userId);
       
       int insertIntakeRecord(IntakeRecord record);
       
       int updateBodyInfo(DietServ diet);
   }

=======
public interface DietMapper {

}
>>>>>>> refs/heads/KIM
