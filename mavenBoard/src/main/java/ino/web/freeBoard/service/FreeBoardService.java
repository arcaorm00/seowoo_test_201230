package ino.web.freeBoard.service;

import ino.web.freeBoard.dto.FreeBoardDto;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeBoardService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<FreeBoardDto> freeBoardList(Map<String, String> search){
//		System.out.println("HERE IS SERVICE: " + search.get("keyword"));
		List<FreeBoardDto> list = sqlSessionTemplate.selectList("freeBoardGetList", search);
		return list;
	}
	
	
	public void freeBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("freeBoardInsertPro",dto);
	}
	
	public FreeBoardDto getDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDetailByNum", num);
	}
	
	public int getNewNum(){
		return sqlSessionTemplate.selectOne("freeBoardNewNum");
	}
	
	public void freeBoardModify(FreeBoardDto dto){
		sqlSessionTemplate.update("freeBoardModify", dto);
	}

	public int FreeBoardDelete (int num) {
		int re = sqlSessionTemplate.delete("freeBoardDelete", num);
		return re;
		
	}
	
}
