package com.coupangmall.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.coupangmall.www.web_controller.MyData;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class web_controller {
	
	//ajax통신 CORS 해제
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ajaxok.do")
	@ResponseBody
	public MyData ajaxok(@RequestParam String alldata) {
		System.out.println(alldata);
		MyData data = new MyData();
		data.setMessage(alldata);
		data.setStatus("success");
		return data;
	}
	
	public static class MyData{
		private String message;
		private String status;
		
		public String getMessage() {return message;}
		public void setMessage(String message) {this.message = message;}
		public String getStatus() {return status;}
		public void setStatus(String status) {this.status = status;}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public ResponseEntity<JsonNode> ajaxkok2(@RequestBody JsonNode alldata){
		System.out.println(alldata.toString());
		return ResponseEntity.ok(alldata);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ajaxok3.do")
	@ResponseBody
	public String ajaxkok3(@RequestParam String data){
		//JSONObject
		JSONObject jo = new JSONObject(data);
		System.out.println(jo);
		return jo.toString();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok4.do")
	@ResponseBody
	public String ajaxok4(@RequestBody String arr) {
		System.out.println(arr);
		JSONArray ja = new JSONArray(arr);
		System.out.println(ja.get(0));
		System.out.println(ja.get(1));
		return "ok";
	}
	
	/*@PostMapping("/loginok.do")
	public String loginok(@RequestParam String mid, HttpServletRequest req) {
		HttpSession hs = req.getSession();
		hs.setAttribute("mid", mid);
		hs.setMaxInactiveInterval(1800);
		return "/index3";
	}*/
	
	//HttpSession : 세션
	@PostMapping("/loginok.do")
	public String loginok(String mid, HttpSession session) {
		if(mid != null) {
			session.setAttribute("mid", mid);
			session.setMaxInactiveInterval(1800);
		}
		return "/index3";
	}
	
	@GetMapping("/restapi.do")
	//@SessionAttribute: 등록되어 있는 session 값을 가져옴
	public String restapi(@SessionAttribute(name="mid", required = false) String mid) {
		if(mid==null) {
			System.out.println("로그인 하세요");
		}else {
			System.out.println(mid+"님 환영");			
		}
		return "home";
	}
}
