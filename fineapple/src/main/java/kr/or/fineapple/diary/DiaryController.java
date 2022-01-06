package kr.or.fineapple.diary;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.diary.DiaryService;

@Controller
@RequestMapping("/diary/*")
public class DiaryController {
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	@RequestMapping(value="getDiary")
	public String getDiary() {
		
		 LocalDate startDate = YearMonth.now().atDay(1);
		 LocalDate endDate = YearMonth.now().atEndOfMonth();
		
		return "diary/getDiary.html";
	}
	
	@RequestMapping(value="getUserBodyInfo")
	public ModelAndView getUserBodyInfo(HttpSession session) {
		
		System.out.println("diary/getUserBodyInfo");
		
		////사용자 신체 변화 정보 입력 출력를 위한 parameter
		//session 내 userId와 오늘 일자 기준 주간 기록 조회
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDateTime endDate = LocalDate.now().atStartOfDay();
		LocalDateTime startDate = endDate.minus(Duration.ofDays(6));
		//viewDuration 내 userId, startDate, endDate, date 셋팅
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setEndDate(LocalDate.now());
		viewDuration.setStartDate(startDate.toLocalDate());
		System.out.println(viewDuration.getStartDate());
		
		List<Object> list = diaryService.getUserBodyInfoList(viewDuration);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("diary/getUserBodyInfo.html");
		mav.addObject("userBodyInfo", list);
		System.out.println(list);
		return mav;
	}

}
