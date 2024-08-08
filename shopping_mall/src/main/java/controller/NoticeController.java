package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.NoticeDto;
import service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService ns;
	
	//공지사항 리스트 페이지
	@GetMapping("/notice_list")
	public String notice_list(Integer page) {
		return "/notice/notice_list";
	}
	
	//공지사항 리스트 출력
	@GetMapping("/notice_list_ajax")
	public void notice_list_ajax(HttpServletResponse res, int page, int size) throws Exception {
		res.setContentType("aplication/json;charset=utf-8");
		page = size * (page-1);
		
		JSONArray ja = new JSONArray();
		ja.put(ns.getNotice(page,size));
		ja.put(ns.getNoticeCk());
		ja.put(ns.getNoticeFile(page, size));
		res.getWriter().print(ja.toString());
	}	
	
	
	//공지사항 등록 페이지
	@GetMapping("/notice_write")
	public String notice_write(Model m,HttpSession hs) {
		m.addAttribute("writer",hs.getAttribute("admin"));
		return "/notice/notice_write";
	}
	
	//공지사항 등록
	@ResponseBody
	@PostMapping("/notice_add")
	public String notice_add(NoticeDto dto, HttpServletRequest req) throws IOException {
		if(ns.addNotice(dto)) {
			dto.setNidx(ns.getNoticeIdx());
			if(ns.addNoticeFile(dto,req)) {
				return "ok";				
			}else {
				System.out.println("파일 저장실패");
				return "no";
			}
		}else {
			System.out.println("노티스 저장실패");
			return "no";
		}
	}
	
	//공지사항 세부사항
	@GetMapping("/notice_detail")
	public String notice_detail(Model m, int nidx) {
		ns.countNoticeView(nidx);
		m.addAttribute("notice",ns.getNoticeOne(nidx));
		return "notice/notice_detail";
	}
	
	//공지사항 삭제
	@PostMapping("notice_remove")
	public ResponseEntity<String> notice_remove(@RequestBody List<String> nidx){
		if(ns.deleteNoticeFile(nidx) && ns.deleteNotice(nidx)) {
			return ResponseEntity.ok("ok");			
		}else{
			return ResponseEntity.ok("no");
		}
	}
}
