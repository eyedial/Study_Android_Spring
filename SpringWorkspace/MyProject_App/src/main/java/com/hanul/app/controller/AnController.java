package com.hanul.app.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.app.command.AnCommand;
import com.hanul.app.command.AnJoinCommand;

@Controller
public class AnController {
	
	AnCommand command;

	@RequestMapping(value="/anJoin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anJoin(HttpServletRequest req, Model model){
		System.out.println("anJoin()");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 	
		
		//1. 안드로이드에서 보낸 값을 req로 받아서 변수에 저장
		String id = (String) req.getParameter("id");
		String passwd = (String) req.getParameter("passwd");
		String name = (String) req.getParameter("name");
		String phonenumber = (String) req.getParameter("phonenumber");
		String address = (String) req.getParameter("address");
		
		//2. 한번 찍어본다
		System.out.println(id);
		System.out.println(passwd);
		System.out.println(name);
		System.out.println(phonenumber);
		System.out.println(address);
		
		//3. command 에서 사용하기 위해 model에 저장
		model.addAttribute("id", id);
		model.addAttribute("passwd", passwd);
		model.addAttribute("name", name);
		model.addAttribute("phonenumber", phonenumber);
		model.addAttribute("address", address);
		
		//4. AnJoinCommand를 생성해서 모델에 담긴 데이터를 넘겨준다
		command = new AnJoinCommand();
		command.execute(model);
		
		//11. 응답을 위해 views에서 anJoin.jsp를 찾아 실행한다.
		return "anJoin";
	}
	
	
}
