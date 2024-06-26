package com.spring.ex.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.spring.ex.dao.MartDao;
import com.spring.ex.dto.MartBean;

public class MUpdateCommand implements MCommand{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		MartBean mb = (MartBean)map.get("mart");

		MartDao mdao = MartDao.getInstance();
		mdao.update(mb);
	}

}
