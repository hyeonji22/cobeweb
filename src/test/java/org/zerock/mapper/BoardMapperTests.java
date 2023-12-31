package org.zerock.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void getList() {
		log.info("-----------");
		boardMapper.getList();
	}
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("test테스트");
		vo.setContent("content 내용");
		vo.setWriter("tester");
		
		boardMapper.insert(vo);
	}
	@Test
	public void testread() {
		BoardVO vo = boardMapper.read(9L);
		log.info(vo);
	}
	@Test
	public void testDelete() {
		int count = boardMapper.delete(1L);
	}
	@Test
	public void testUpdate() {
		
		BoardVO vo = new BoardVO();
		vo.setBno(2L);
		vo.setTitle("updateTitle");
		vo.setContent("Updated content");
		vo.setWriter("user00");
		
		log.info("count:" +boardMapper.update(vo));
		
	}
	@Test
	public void testPaging() {
		//1 페이지 10개 
		Criteria cri = new Criteria();
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		list.forEach(b -> log.info(b));
	
	}
	@Test
	public void testPageDTO() {
		
		Criteria cri = new Criteria();
		cri.setPageNum(25);
		PageDTO pageDTO = new PageDTO(cri, 251);
		
		log.info(pageDTO);
		
	}
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<>();
	//	map.put("W", "WWW");
		//map.put("C", "CCC");
		//map.put("T", "TTT");
		
		Map<String, Map<String, String>> outer = new HashMap<>();
		outer.put("map",map);
		
		List<BoardVO> list = boardMapper.searchTest(outer);
		
		log.info(list);
	}
	@Test
	public void testserchPaging() {
		//1 페이지 10개 
		Criteria cri = new Criteria();
		//cri.setType("TCW");
		//cri.setKeyword("test");
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		list.forEach(b -> log.info(b));
	
	}
}
