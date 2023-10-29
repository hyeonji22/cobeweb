package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO>getList();
	
	void insert(BoardVO vo);
	
	void insertSelectKey(BoardVO board);
	
	BoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(BoardVO board);
	
	List<BoardVO> getListWithPaging(Criteria cri);
	
	int getTotalCount(Criteria cri);//검색할떄도 사용하기때문에 Criteria사용

	List<BoardVO> searchTest(Map<String,Map<String, String> > map);
}
