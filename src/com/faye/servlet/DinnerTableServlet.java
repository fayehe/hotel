package com.faye.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faye.factory.BeanFactory;
import com.faye.service.IDinnerTableService;
import com.faye.service.impl.DinnerTableService;
import com.faye.entity.DinnerTable;

@WebServlet("/DinnerTableServlet")
public class DinnerTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDinnerTableService dinnerTableService = BeanFactory.GetInstance("dinnerTableService", DinnerTableService.class);
	private String url;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if (methodName.equals("list")) { getList(request, response);}
		else if (methodName.equals("save")) { save(request,response);}
		else if (methodName.equals("delete")) {delete(request,response);}
		else if (methodName.equals("update")) {update(request, response);}
		else if (methodName.equals("findByTableName")) {getListByTableName(request, response);}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<DinnerTable> list = dinnerTableService.getAll();
			request.setAttribute("dinnerTableList", list);
			url = "/sys/dinnertable/boardList.jsp";
			
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void getListByTableName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String fuzzyTableName = request.getParameter("keyword");
			List<DinnerTable> list = dinnerTableService.getAll(fuzzyTableName);
			request.setAttribute("dinnerTableList", list);
			url = "/sys/dinnertable/boardList.jsp";
			
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		DinnerTable dinnerTable = new DinnerTable();
		dinnerTable.setTableName(tableName);
		try {
			dinnerTableService.save(dinnerTable);
			url = "/DinnerTableServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int tableStatus = Integer.valueOf(request.getParameter("tableStatus"));
		DinnerTable dinnerTable = new DinnerTable();
		dinnerTable.setTableStatus(tableStatus);
		dinnerTable.setId(id);
		if(tableStatus == 0) {dinnerTable.setOrderDate(null);}
		else if(tableStatus == 1) {dinnerTable.setOrderDate(new Date());}
		try {
			dinnerTableService.update(dinnerTable);
			url = "/DinnerTableServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			dinnerTableService.delete(id);
			url = "/DinnerTableServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
