package com.spring.ex.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.command.MCommand;
import com.spring.ex.command.MDeleteAllCommand;
import com.spring.ex.command.MDeleteCommand;
import com.spring.ex.command.MInsertCommand;
import com.spring.ex.command.MListCommand;
import com.spring.ex.command.MUpdateCommand;
import com.spring.ex.command.MUpdateFormCommand;
import com.spring.ex.dto.MartBean;

@Controller
public class MartController {

	MCommand command = null;

	@RequestMapping("form")
	public String form() {
		return "insertForm";
	}

	@RequestMapping("insert")
	public String result(@ModelAttribute("mart") @Valid MartBean mart, BindingResult result,HttpServletRequest request, Model model) {
		
		if(result.hasErrors()) {
			return "insertForm";
		}
		command= new MInsertCommand();
		command.execute(model);

		return "redirect:/list";
	}

	@RequestMapping("list")
	public String list(Model model) {
		command = new MListCommand();
		command.execute(model); 
		return "list"; 
	}

	@RequestMapping("delete")
	public String delete(HttpServletRequest request ,Model model) {

		//String num = request.getParameter("num");
		model.addAttribute("req", request);
		command = new MDeleteCommand();
		command.execute(model);

		return "redirect:/list";
	}

	@RequestMapping("deleteAll")
	public String deleteAll(HttpServletRequest request, Model model) {

		String[] rc = request.getParameterValues("rowcheck");
		
		model.addAttribute("req", request); 

		command = new MDeleteAllCommand(); 
		command.execute(model);

		return "redirect:/list";
	}

	@RequestMapping("updateForm")
	public String updateForm(HttpServletRequest request, Model model) {
		
		model.addAttribute("req", request); 
		
		command = new MUpdateFormCommand();
		command.execute(model);
		
		return "updateForm";
	}
	
	@RequestMapping(value="update")
	public String update(@ModelAttribute("mart") @Valid MartBean mb, BindingResult result,  HttpServletRequest request, Model model) {	
		
		if(result.hasErrors()) {
			return "updateForm";
		}
		command = new MUpdateCommand(); //update~
		command.execute(model);
		return "redirect:/list";
		
	}
}








