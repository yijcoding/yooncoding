package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl,String search,String select,String b_type) {
		String pagingStr = "";
		System.out.println();

		// �떒怨� 3 : �쟾泥� �럹�씠吏� �닔 怨꾩궛
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

		// �떒怨� 4 : '�씠�쟾 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if (pageTemp != 1) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=1&search="+search+"&select="+select+"&b_type="+b_type+"&viewCnt="+pageSize+"'><<</a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + (pageTemp - 1)+ "&search="+search+"&select="+select+"&b_type="+b_type+"&viewCnt="+pageSize+"'><</a></span>";
		}

		// �떒怨� 5 : 媛� �럹�씠吏� 踰덊샇 異쒕젰 
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				// �쁽�옱 �럹�씠吏��뒗 留곹겕瑜� 嫄몄� �븡�쓬
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"&search="+search+"&select="+select+"&b_type="+b_type+"&viewCnt="+pageSize+"'><b style='color:black'>"+pageTemp+"</b></a></span>&nbsp;";
			} else  {
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"&search="+search+"&select="+select+"&b_type="+b_type+"&viewCnt="+pageSize+"'>"+pageTemp+"</a></span>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}

		// �떒怨� 6 : '�떎�쓬 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		if (pageTemp <= totalPages) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + pageTemp+"&search="+search+"&select="+select+"&b_type="+b_type+"&viewCnt="+pageSize+"'>></a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + totalPages+"&search="+search+"&select="+select+"&b_type="+b_type+"&viewCnt="+pageSize+"'>>></a></span>";
		}

		return pagingStr;
	}
	
	public static String customerstr(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl) {
		String pagingStr = "";

		// �떒怨� 3 : �쟾泥� �럹�씠吏� �닔 怨꾩궛
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

		// �떒怨� 4 : '�씠�쟾 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if (pageTemp != 1) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=1'><<</a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + (pageTemp - 1)+ "'><</a></span>";
		}

		// �떒怨� 5 : 媛� �럹�씠吏� 踰덊샇 異쒕젰 
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				// �쁽�옱 �럹�씠吏��뒗 留곹겕瑜� 嫄몄� �븡�쓬
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"'><b style='color:black'>"+pageTemp+"</b></a></span>&nbsp;";
			} else  {
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"'>"+pageTemp+"</a></span>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}

		// �떒怨� 6 : '�떎�쓬 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		if (pageTemp <= totalPages) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + pageTemp+"'>></a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + totalPages+"'>>></a></span>";
		}

		return pagingStr;
	}
	
	
	public static String customerfaq(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl,String f_type) {
		String pagingStr = "";
		
		// �떒怨� 3 : �쟾泥� �럹�씠吏� �닔 怨꾩궛
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
		System.out.println("paging++++++++++++++++++++++++++++++++++++++++"+totalPages);
		// �떒怨� 4 : '�씠�쟾 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if (pageTemp != 1) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=1&f_type="+f_type+"'><<</a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + (pageTemp - 1)+ "&f_type="+f_type+"'><</a></span>";
		}
		
		// �떒怨� 5 : 媛� �럹�씠吏� 踰덊샇 異쒕젰 
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				// �쁽�옱 �럹�씠吏��뒗 留곹겕瑜� 嫄몄� �븡�쓬
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"&f_type="+f_type+"'><b style='color:black'>"+pageTemp+"</b></a></span>&nbsp;";
			} else  {
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"&f_type="+f_type+"'>"+pageTemp+"</a></span>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		
		// �떒怨� 6 : '�떎�쓬 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		if (pageTemp <= totalPages) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + pageTemp+"&f_type="+f_type+"'>></a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + totalPages+"&f_type="+f_type+"'>>></a></span>";
		}
		
		return pagingStr;
	}
	
	public static String adminMemberPagingStr(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl,String search) {
		String pagingStr = "";
		System.out.println();

		// �떒怨� 3 : �쟾泥� �럹�씠吏� �닔 怨꾩궛
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

		// �떒怨� 4 : '�씠�쟾 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if (pageTemp != 1) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=1&search="+search+"'><<</a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + (pageTemp - 1)+ "&search="+search+"'><</a></span>";
		}

		// �떒怨� 5 : 媛� �럹�씠吏� 踰덊샇 異쒕젰 
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				// �쁽�옱 �럹�씠吏��뒗 留곹겕瑜� 嫄몄� �븡�쓬
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"&search="+search+"'><b style='color:black'>"+pageTemp+"</b></a></span>&nbsp;";
			} else  {
				pagingStr += "&nbsp;<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum="+pageTemp+"&search="+search+"'>"+pageTemp+"</a></span>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}

		// �떒怨� 6 : '�떎�쓬 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		if (pageTemp <= totalPages) {
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + pageTemp+"&search="+search+"'>></a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link href='" + reqUrl + "?pageNum=" + totalPages+"&search="+search+"'>>></a></span>";
		}

		return pagingStr;
	}
	
	public static String AjaxCommentPaging(int totalCount, int pageSize, int blockPage,
			int pageNum) {
		String pagingStr = "";
		System.out.println();

		// �떒怨� 3 : �쟾泥� �럹�씠吏� �닔 怨꾩궛
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

		// �떒怨� 4 : '�씠�쟾 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if (pageTemp != 1) {
			pagingStr += "<span class=page-item><a class=page-link  onclick='commentList("+pageTemp+")'><<</a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link  onclick='commentList("+pageTemp+")'><</a></span>";
		}

		// �떒怨� 5 : 媛� �럹�씠吏� 踰덊샇 異쒕젰 
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				// �쁽�옱 �럹�씠吏��뒗 留곹겕瑜� 嫄몄� �븡�쓬
				pagingStr += "&nbsp;<span class=page-item><a class=page-link  onclick='commentList("+pageTemp+")''><b style='color:black'>"+pageTemp+"</b></a></span>&nbsp;";
			} else  {
				pagingStr += "&nbsp;<span class=page-item><a class=page-link  onclick='commentList("+pageTemp+")''>"+pageTemp+"</a></span>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}

		// �떒怨� 6 : '�떎�쓬 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
		if (pageTemp <= totalPages) {
			pagingStr += "<span class=page-item><a class=page-link  onclick='commentList("+pageTemp+")''>></a></span>";
			pagingStr += "&nbsp;";
			pagingStr += "<span class=page-item><a class=page-link  onclick='commentList("+pageTemp+")'>>></a></span>";
		}

		return pagingStr;
	}

}