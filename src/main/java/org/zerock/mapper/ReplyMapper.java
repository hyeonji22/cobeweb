package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	//댓글등록
	public int insert(ReplyVO vo);
	
	//댓글조회
	public ReplyVO read(Long bno);
	
	//댓글삭제
	public int delete(Long rno);
	
	//댓글수정
	public int update(ReplyVO reply);
	
	//댓글목록+페이징
	public List<ReplyVO> getListWithPageing(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
}
