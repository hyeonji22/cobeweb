package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {

	public List<BoardVO>getList();
	
	void insert(BoardVO vo);
	
	void insertSelectKey(BoardVO board);
	
	BoardVO get(Long bno);
	
	BoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(BoardVO board);
}
