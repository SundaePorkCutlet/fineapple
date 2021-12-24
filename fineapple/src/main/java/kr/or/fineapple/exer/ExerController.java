package kr.or.fineapple.exer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.exer.ExerService;


@Controller
@RequestMapping("/exer/*")
public class ExerController {
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;

	public ExerController() {
	}

	
@RequestMapping(value="addUserService")
public String addUserService() {
	
    System.out.println("addUserService");

	return "exer/addUserService.html";
	
	
}


@RequestMapping("getExerList")
public String getExerList( @ModelAttribute("search") Search search, Model model) throws Exception {
	
	  System.out.println("getExerList");
 
	  
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
			  
		
	return "exer/getExerList.html";	
	
}



@GetMapping("getExer")
public String getExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception{
	
	
	exer = exerService.getExer(exer.getExerNo());
	
	System.out.println("getExer");
	
	if(exer.getExerVideoName() == null) {
		exer.setExerVideoName("null");
		
		System.out.println("exerVideoName이 null체크" + exer);
		
		model.addAttribute("exer", exer);
		
	}else {
	
	System.out.println("exerVideoName이 null체크" + exer);
		
	model.addAttribute("exer", exer);
	
	}
	
	return "exer/getExer.html";
	
	
}



@GetMapping("getUpdateExer")
public String getUpdateExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	exer= exerService.getExer(exer.getExerNo());
	
	model.addAttribute("exer", exer);
	
	System.out.println("GetUpdateExer");
	
	
	return "exer/updateExer.html";
	
}

private static final String FILE_SERVER_PATH = "C:\\Users\\82105\\git\\fineapple\\fineapple\\src\\main\\resources\\templates\\assets\\video";

@PostMapping("postUpdateExer")
public String postUpdateExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {
	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("운동영상 업로드 성공~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	System.out.println("PostUpdateExer");
	
	exerService.postUpdateExer(exer);
	

	model.addAttribute("exer", exer);
	
	
	return "exer/getExer";
	
}

@RequestMapping("deleteExer")
public String deleteExer(@ModelAttribute("exer") Exer exer , Model model) throws Exception {
	
	System.out.println("deleteExer");
	
	exerService.deleteExer(exer.getExerNo());
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
	
	
	
	model.addAttribute("exer", exer);
	
	return "exer/getExerList";
}

}
