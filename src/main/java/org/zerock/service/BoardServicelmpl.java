package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServicelmpl implements BoardService {

	private final BoardMapper mapper;
	
	public List<BoardVO>getList(){
		return mapper.getList();
	}
	@Override
	public Long register(BoardVO board) {
		mapper.insertSelectKey(board);
		return board.getBno();
	}
	@Override
	public BoardVO get(Long bno) {

		return mapper.read(bno);
	}

	@Override
	public int modify(BoardVO board) {
		// TODO Auto-generated method stub
		return mapper.update(board);
	}

	@Override
	public int remove(Long bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno);
	}







	
	
	

}
