package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {229L, 228L, 227L, 226L, 225L};

	@Setter(onMethod_= @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		
		log.info(mapper);
	}
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1,10).forEach(i->{
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글테스트"+i);
			vo.setReplyer("replyer"+i);
			
			mapper.insert(vo);
		});
		
	}
	@Test
	public void testRead() {
		
		Long tagetBno = 5L;
		
		ReplyVO vo = mapper.read(tagetBno);
		
		log.info(vo);
		
	}
	@Test
	public void testDelete() {
		
		Long tagetRno = 2L;
		
		mapper.delete(tagetRno);
		
	}
	@Test
	public void testUpdate() {
		Long tagetRno = 4L;
		
		ReplyVO vo = mapper.read(tagetRno);
		vo.setReply("수정 테스트 ~~~~");
		
		int count = mapper.update(vo);
		log.info("update~~~~~~~~~~~~~~:"+count);
	}
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		//229
		List<ReplyVO> replies = mapper.getListWithPageing(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
		
	}

	
}
