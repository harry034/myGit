package com.testFW.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.testFW.service.DiaryService;

/**
 * 日志相关处理类 
 * @author kalor
 * @time 2013-1-24 at 下午04:11:17
 */
@Component
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DiaryServlet.class);
	private DiaryService diaryService;
	
	public void setDiaryService(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String fun = (String) req.getParameter("fun");
		if ("newdiary".equals(fun)) {
			newDiary(req, resp);
		}  
	}
	 
	/**
	 * 发布日志 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void newDiary(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		int result = diaryService.newDiary(req,resp);
		String msg = "";
		if(result<1) {
			msg = "fail";
		}else {
			msg = result+"";
		}		 
		out.print(msg);
		out.flush();
		out.close();
	}
	
	 
}