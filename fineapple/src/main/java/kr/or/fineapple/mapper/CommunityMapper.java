package kr.or.fineapple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommunityMapper {
	
	@Select("SELECT * FROM alarm")
	public String getAlarmList();
}
