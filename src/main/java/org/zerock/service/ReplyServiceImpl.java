package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	

	@Setter(onMethod_= @Autowired )
	private ReplyMapper mapper;

	
	@Override
	public int register(ReplyVO vo) {
		log.info("register..............."+vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long bno) {
		log.info("register..............."+bno);

		return mapper.read(bno);
	}

	@Override
	public int remove(Long rno) {
		log.info("register..............."+rno);

		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("register..............."+reply);

		return mapper.update(reply);
	}

	@Override
	public List<ReplyVO> getLit(Criteria cri, Long bno) {
		log.info("register..............."+bno);

		return mapper.getListWithPageing(cri, bno);
	}
}
