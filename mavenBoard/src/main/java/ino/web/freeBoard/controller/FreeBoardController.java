package ino.web.freeBoard.controller;

import ino.web.freeBoard.common.util.PaginationUtil;
import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.service.FreeBoardService;

import java.util.HashMap;
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
	
	@RequestMapping(value="/main.ino")
	@ResponseBody
	public ModelAndView main(HttpServletRequest request, 
			@RequestParam(defaultValue="") String searchField, 
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int page){
		
		Map<String, Object> totalCntMap = new HashMap<String, Object>();
		totalCntMap.put("searchField", searchField);
		totalCntMap.put("keyword", keyword);
		int totalCnt = freeBoardService.freeBoardGetTotalCnt(totalCntMap);
		PaginationUtil pageUtil = new PaginationUtil(page, totalCnt);
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchField", searchField);
		map.put("keyword", keyword);
		map.put("start", pageUtil.getStartPage());
		map.put("end", pageUtil.getEndPage());
		
		List<FreeBoardDto> list = freeBoardService.freeBoardList(map);
		
		mav.setViewName("boardMain");
		mav.addObject("freeBoardList", list);
		mav.addObject("pagination", pageUtil);
		
		mav.addObject("searchField", searchField);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
	
	@RequestMapping(value="/mainSearch.ino")
	@ResponseBody
	public Map<String, Object> mainSearch(HttpServletRequest request, 
			@RequestParam(defaultValue="") String searchField,
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int page){
		
		Map<String, Object> totalCntMap = new HashMap<String, Object>();
		totalCntMap.put("searchField", searchField);
		totalCntMap.put("keyword", keyword);
		int totalCnt = freeBoardService.freeBoardGetTotalCnt(totalCntMap);
		PaginationUtil pageUtil = new PaginationUtil(page, totalCnt);
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchField", searchField);
		map.put("keyword", keyword);
		map.put("start", pageUtil.getStartPage());
		map.put("end", pageUtil.getEndPage());
		
		List<FreeBoardDto> list = freeBoardService.freeBoardList(map);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		returnMap.put("page", pageUtil);
		returnMap.put("searchField", searchField);
		returnMap.put("keyword", keyword);
		
		return returnMap;
	}
	
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert(){
		return "freeBoardInsert";
	}
	
	@RequestMapping("/freeBoardInsertPro.ino")
	@ResponseBody
	public Map<String, Object> freeBoardInsertPro(HttpServletRequest request, FreeBoardDto dto){
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("dto", dto);
		int re = freeBoardService.freeBoardInsertPro(insertMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("re", re);
		map.put("num", dto.getNum());
		
		return map;
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
	public Map<String, Object> freeBoardModify(HttpServletRequest request, FreeBoardDto fb){
		int num = fb.getNum();
		System.out.println(fb);
		
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("dto", fb);
		int re = freeBoardService.freeBoardModify(updateMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("re", re);
		map.put("num", num);
		
		return map;
	}
	
		
	@RequestMapping("/freeBoardDelete.ino")
	@ResponseBody
	public Map<String, Object> FreeBoardDelete(HttpServletRequest request, int num){
		int re = freeBoardService.FreeBoardDelete(num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("re", re);
		
		return map;
	}
}