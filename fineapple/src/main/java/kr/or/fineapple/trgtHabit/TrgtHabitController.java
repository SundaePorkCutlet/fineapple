package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@Controller
@RequestMapping("/trgtHabit/*")
public class TrgtHabitController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public TrgtHabitController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="getTrgtHabit", method=RequestMethod.GET)
	public ModelAndView getTrgtHabit(HttpSession session) {
	
		System.out.println("/trgtHabit/getTrgtHabit : GET");
		
		////����ڿ��� �������� ù ȭ�鿡 �ʿ��� ���� ��ȸ(��ǥ �� Ŀ��, ���� ���м��뷮 ����, ��������� ��ǥ �������� ��� �̸�)
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate date = LocalDate.now();
		
		////������ ModelAndView ����
		ModelAndView mav = new ModelAndView();
		
		for(int i=1; i<=4; i++) {
			//��ȸ�� ���� service ȣ��
			int trgtHabitCateNo = i;
			TrgtHabit trgtHabit = trgtHabitService.getTrgtHabit(userId, date, trgtHabitCateNo);
			
			if(trgtHabit != null) {		
				////�����ϼ� ��� ���� ���� Logic
				//�������ڿ� ���������� ��
				LocalDateTime trgtHabitStartDate = trgtHabit.getTrgtHabitStartDate().atStartOfDay();
				int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
				//����� trgtHabit �����ο� ����
				trgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
				
				//model�� ���� keyName ����
				if(trgtHabit.getTrgtHabitCateNo()==1) {
					mav.addObject("coffee", trgtHabit);
				}else if(trgtHabit.getTrgtHabitCateNo()==2) {
					mav.addObject("alcohol", trgtHabit);
				}else if(trgtHabit.getTrgtHabitCateNo()==3) {
					mav.addObject("smoking", trgtHabit);
				}else if(trgtHabit.getTrgtHabitCateNo()==4) {
					mav.addObject("userHabit", trgtHabit);
				}
			} else {
				//����ڰ� ���� �������� �ʾ� null�� ������ ��� �� trgtHabit ��ü ����
				TrgtHabit emptyTrgtHabit = new TrgtHabit();
				emptyTrgtHabit.setTrgtHabitCateNo(i);
				emptyTrgtHabit.setTrgtHabitServiceNo(0);
				emptyTrgtHabit.setTrgtHabitSuccDayCount(0);
				
				//model�� ���� keyName ����
				if(emptyTrgtHabit.getTrgtHabitCateNo()==1) {
					mav.addObject("coffee", emptyTrgtHabit);
				}else if(emptyTrgtHabit.getTrgtHabitCateNo()==2) {
					mav.addObject("alcohol", emptyTrgtHabit);
				}else if(emptyTrgtHabit.getTrgtHabitCateNo()==3) {
					mav.addObject("smoking", emptyTrgtHabit);
				}else if(emptyTrgtHabit.getTrgtHabitCateNo()==4) {
					emptyTrgtHabit.setTrgtHabitCateName("���������");
					mav.addObject("userHabit", emptyTrgtHabit);
				}
			}
		}

		double userWtrIntake = trgtHabitService.getWtrIntake(userId, date);
		mav.addObject("userWtrIntake", userWtrIntake);
		mav.addObject("NavName1", "��������");
		mav.setViewName("trgtHabit/getTrgtHabit.html");
		
		return mav;
	}
	
}
