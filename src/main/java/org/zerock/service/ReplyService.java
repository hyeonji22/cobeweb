package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	//등록
	public int register(ReplyVO vo);
	//조회
	public ReplyVO get(Long bno);
	//삭제
	public int remove(Long rno);
	//수정
	public int modify(ReplyVO reply);
	//목록 +페이징
	public List<ReplyVO> getLit(Criteria cri, Long bno);

}
