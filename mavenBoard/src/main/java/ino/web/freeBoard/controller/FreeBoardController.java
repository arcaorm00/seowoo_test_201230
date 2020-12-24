package ino.web.freeBoard.controller;

import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.service.FreeBoardService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@RequestMapping(value={"/main.ino", "/mainSearch.ino"}, method=RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public Object main(HttpServletRequest request, @RequestParam Map<String, String> searchJson){
		ModelAndView mav = new ModelAndView();
		System.out.println("searchJson:" + searchJson);

		List<FreeBoardDto> list = freeBoardService.freeBoardList(searchJson);
		
		System.out.println("LIST: "+list);
		System.out.println("request.getServletPath(): " + (request.getServletPath().equals("/main.ino")));
		
		if (request.getServletPath().equals("/main.ino")){
			mav.setViewName("boardMain");
			mav.addObject("freeBoardList", list);
			return mav;
		}
		return list;
	}
	
//	@RequestMapping(value="/main.ino", produces="application/json; charset=utf-8")
//	@ResponseBody
//	public Object main(HttpServletRequest request, @RequestParam Map<String, String> searchJson){
//		ModelAndView mav = new ModelAndView();
//		List<FreeBoardDto> list = freeBoardService.freeBoardList(searchJson);
//		System.out.println("LIST: "+list);
//		
//		mav.setViewName("boardMain");
//		mav.addObject("freeBoardList", list);
//		return mav;
//	}
	
	@RequestMapping(value="/mainSearch.ino", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<FreeBoardDto> mainSearch(HttpServletRequest request, @RequestParam Map<String, String> searchJson){
		System.out.println("searchJson:" + searchJson);
		List<FreeBoardDto> list = freeBoardService.freeBoardList(searchJson);
		return list;
	}
	
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert(){
		return "freeBoardInsert";
	}
	
	@RequestMapping("/freeBoardInsertPro.ino")
	@ResponseBody
	public int freeBoardInsertPro(HttpServletRequest request, FreeBoardDto dto){
		freeBoardService.freeBoardInsertPro(dto);
		System.out.println(dto);
		return dto.getNum();
	}
	
	@RequestMapping("/freeBoardDetail.ino")
	public ModelAndView freeBoardDetail(HttpServletRequest request, FreeBoardDto dto){
		ModelAndView mav = new ModelAndView("freeBoardDetail");
		FreeBoardDto fb = freeBoardService.getDetailByNum(dto.getNum());
		mav.addObject("freeBoardDto", fb);
		return mav;
	}
	
	@RequestMapping(value="/freeBoardModify.ino", method=RequestMethod.GET)
	public ModelAndView freeBoardModify(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("freeBoardModify");
		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto fb = freeBoardService.getDetailByNum(num);
		
		if(fb.getCodeType().equals("자유")){fb.setCodeType("01");}
		else if(fb.getCodeType().equals("익명")){fb.setCodeType("02");}
		else if(fb.getCodeType().equals("QnA")){fb.setCodeType("03");}
		System.out.println(fb);
		 
		mav.addObject("freeBoardDto", fb);
		return mav;
	}
	
	@RequestMapping(value="/freeBoardModifyPro.ino", method=RequestMethod.POST)
	@ResponseBody
	public int freeBoardModify(HttpServletRequest request, FreeBoardDto fb){
		int num = fb.getNum();
		System.out.println(fb);
		freeBoardService.freeBoardModify(fb);
		return num;
	}
	
		
	@RequestMapping("/freeBoardDelete.ino")
	@ResponseBody
	public String FreeBoardDelete(int num){
		int re = freeBoardService.FreeBoardDelete(num);
//		return "redirect:/main.ino";
		String msg = "게시물 삭제에 실패했습니다.";
		if (re > 0){
			msg = "게시물이 삭제되었습니다!";
		}
		return msg;
	}
}