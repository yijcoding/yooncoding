package org.exiting.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public List<Map<String, Object>> boardList(Map<String, Object> map);
	public List<Map<String, Object>> getComment(Map<String, Object> map);
	public int boardInsert(Map<String,Object> map);
	public int replyInsert(Map<String,Object> map);
	public Map<String, Object> boardView(Map<String,Object> map);

}
