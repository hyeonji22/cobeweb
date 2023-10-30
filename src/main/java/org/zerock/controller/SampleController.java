package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value = "/GetText" , produces = "text/plain; charset=UTF-8")
	public String getText() {
	
		log.info("MINE TYPE:" +MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	//xml과 json 직접 직정해주기
	@GetMapping(value = "/getSample" , 
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
	
		log.info("MINE TYPE:" +MediaType.TEXT_PLAIN_VALUE);
		
		return new SampleVO(112,"스타","로드");
	}
	//배열으로 
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"First" ,i + "Last"))
				.collect(Collectors.toList());
	}
	//맵으로
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111,"그루트","주니어"));
		
		return map;
	}
	//파라미터 받아서 처리 
	@GetMapping(value = "/check", params = {"height" , "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		
		SampleVO vo = new SampleVO(0,"" +height, ""+weight);
		ResponseEntity<SampleVO> result =null;
		
		if(height <150) {
			result =ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	@GetMapping("/product/{cat}/{pid}")//변수명을 지정해주면 들어감
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid
			) {
		
		return new String[] {"category :" +cat ,"productid:" + pid};
		
	}
	//json형태로 받아서 처리 
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert......ticket" +ticket);
		return ticket;
	}
	
	
	
	
}
