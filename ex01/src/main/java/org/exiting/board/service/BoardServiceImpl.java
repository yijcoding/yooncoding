package org.exiting.board.service;

import java.util.List;
import java.util.Map;

import org.exiting.board.domain.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;

	@Override
	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		return dao.boardList(map);
	}

	@Override
	public int boardInsert(Map<String, Object> map) {
		return dao.boardInsert(map);
	}

	@Override
	public Map<String, Object> boardView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.boardView(map);
	}

	@Override
	public int replyInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.replyInsert(map);
	}

	@Override
	public List<Map<String, Object>> getComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getComment(map);
	}

}
