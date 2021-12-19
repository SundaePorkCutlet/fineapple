package kr.or.fineapple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.IntakeRecord;

@Mapper
@Repository
public interface DietMapper {

	    int insertDietService(IntakeRecord record);
	}

