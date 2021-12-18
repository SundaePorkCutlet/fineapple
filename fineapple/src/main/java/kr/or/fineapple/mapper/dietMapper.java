package kr.or.fineapple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface dietMapper {

	 @Select("select password from users")
	    public String password();
	}

