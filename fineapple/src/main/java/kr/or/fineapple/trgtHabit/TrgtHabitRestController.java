package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@RestController
@RequestMapping("/trgtHabit/*")
public class TrgtHabitRestController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public TrgtHabitRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/getTrgtHabit", method=RequestMethod.POST)
	public TrgtHabit getTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
	
		System.out.println("/trgtHabit/json/getTrgtHabit : POST");
		////��ȸ�� ���� service ȣ��
		TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), trgtHabit.getViewDate(), trgtHabit.getTrgtHabitCateNo());
	
		//����� null�� �ƴҶ�
		if(returnTrgtHabit != null) {		
			////�����ϼ� ��� ���� ���� Logic
			//�������ڿ� ���������� ��
			LocalDateTime trgtHabitStartDate = returnTrgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			returnTrgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
			
			return returnTrgtHabit;		
			
		} else {
			
			TrgtHabit emptyTrgtHabit = new TrgtHabit();
			emptyTrgtHabit.setTrgtHabitCateNo(trgtHabit.getTrgtHabitCateNo());

			return emptyTrgtHabit;
		}
	}
		
	@RequestMapping(value="json/addTrgtHabit", method=RequestMethod.POST)
	public TrgtHabit addTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
		  
		System.out.println("/trgtHabit/json/addTrgtHabit : POST");

		//���� �ʱ�ȭ�� ������ ��� ���� �Ұ�
		LocalDate theLatestInitDate = trgtHabitService.getTheLatestInitDate(trgtHabit.getUserId(), trgtHabit.getTrgtHabitCateNo());
		
		if(theLatestInitDate == null || !theLatestInitDate.isEqual(LocalDate.now())) {
			//���� ���� �� ���� ���� ������ ���� Ȯ��(0�� ���ϰ�� ���� ����/1 ���ϰ�� �̹� ���������� ���ۺҰ�)
			int usingStt = trgtHabitService.getUsingTrgtHabit(trgtHabit.getUserId(), trgtHabit.getTrgtHabitCateNo());
			if(usingStt == 0) {
				trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
				//���۵� ��ǥ ���� ���� ��ȸ�� ���� service ȣ��
				TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), LocalDate.now(), trgtHabit.getTrgtHabitCateNo());
		
				////�����ϼ� ��� ���� ���� Logic
				//�������ڿ� ���������� ��
				LocalDateTime trgtHabitStartDate = returnTrgtHabit.getTrgtHabitStartDate().atStartOfDay();
				int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
				returnTrgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
				System.out.println(returnTrgtHabit);
				
				return returnTrgtHabit;	
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	 
	@RequestMapping(value="json/endTrgtHabit", method=RequestMethod.POST)
	public void endTrgtHabit(@RequestBody TrgtHabit trgthabit) {
		
		System.out.println("/trgtHabit/json/endTrgtHabit : POST");
		////��ǥ ���� �ϼ� �ʱ�ȭ�� ���� ���� ȣ��
		trgtHabitService.endTrgtHabit(trgthabit.getTrgtHabitServiceNo());
	}
	
	@RequestMapping(value="json/updateWtrIntake", method=RequestMethod.POST)
	public Double updateWtrIntake(@RequestBody TrgtHabit trgtHabit) {
		//REST �̿����� TrgtHabit �����ο� userWtrIntake �ʵ� �߰��Ͽ� �����(�Ϲ� ��Ʈ�ѷ� �̿��Ѵٸ� �ʿ�X)
		System.out.println("/trgt/json/updateWtrIntake");
		////����ڷκ��� �Է¹��� ���м��뷮 ����� ��ϵȰ� ��ȸ�Ͽ� ����
		Double returnUserWtrIntake = trgtHabitService.updateWtrIntake(trgtHabit.getUserId(), trgtHabit.getUserWtrIntake());
		
		return returnUserWtrIntake;
	}
}
