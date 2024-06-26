package com.spring.ex.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spring.ex.dao.MartDao;

public class MDeleteCommand implements MCommand{

	@Override
	public void execute(Model model) {
		MartDao mdao = MartDao.getInstance();

		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("req");

		int num = Integer.parseInt(request.getParameter("num"));

		mdao.delete(num);
	}

}
