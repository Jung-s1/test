package com.spring.ex.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spring.ex.dao.MartDao;
import com.spring.ex.dto.MartBean;

public class MUpdateFormCommand implements MCommand{

	@Override
	public void execute(Model model) {
		MartDao mdao = MartDao.getInstance();
		Map<String, Object> map = model.asMap();
		HttpServletRequest request =  (HttpServletRequest)map.get("req");
		MartBean mb = mdao.getMartByNum(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("mart", mb);
	}

}
