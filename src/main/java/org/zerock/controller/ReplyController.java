package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	//특정 게시물의 댓글 목록 확인
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = {
					MediaType.APPLICATION_XML_VALUE
					,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("page") int page,
			@PathVariable("bno") Long bno ){
		
		log.info("getList......................");

		Criteria cri = new Criteria(page,10);
		log.info(cri);
		
		return new ResponseEntity<>(service.getLit(cri, bno),HttpStatus.OK);
	}
	
	
	//댓글등록
	@PostMapping(value = "/new",
			consumes = "application/json", //json방식의 데이터만 처리
			produces = {MediaType.TEXT_PLAIN_VALUE}) //문자열 반환
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		
		log.info("replyvo:"+vo);
		
		int insertCount = service.register(vo);
		log.info("inserCount: "+insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK):
								  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500에러
		
	}
	//댓글 조회 
	@GetMapping(value ="/{rno}"
			,produces = {MediaType.APPLICATION_XML_VALUE
					    ,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get : "+rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK); 
		
	}
	//댓글 삭제
	@PostMapping(value = "/{rno}"
			,produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		log.info("get : "+rno);

		int removeCount = service.remove(rno);
		
		return removeCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500에러
	}
	//댓글 수정
	public ResponseEntity<String> modify (
							@RequestBody ReplyVO vo,
							@PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("rno"+rno);
		log.info("modify"+vo);
		
		int modifyCount =service.modify(vo);
		
		return modifyCount ==1 ? new ResponseEntity<>("success", HttpStatus.OK)
							   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500에러
	}
	
	
	
	
	
	
	
	

}
