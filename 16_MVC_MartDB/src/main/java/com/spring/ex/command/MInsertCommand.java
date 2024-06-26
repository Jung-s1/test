package com.spring.ex.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.spring.ex.dao.MartDao;
import com.spring.ex.dto.MartBean;

public class MInsertCommand implements MCommand{

	@Override
	public void execute(Model model) {
		Map<String ,Object> map = model.asMap();
		
		MartDao mdao = MartDao.getInstance();
		MartBean mb = (MartBean)map.get("mart");
		
		mdao.insertMart(mb);
	}

}
