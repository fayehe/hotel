package com.faye.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faye.entity.FoodType;
import com.faye.factory.BeanFactory;
import com.faye.service.IFoodTypeService;
import com.faye.service.impl.FoodTypeService;

@WebServlet("/FoodTypeServlet")
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IFoodTypeService foodTypeService = BeanFactory.GetInstance("foodTypeService", FoodTypeService.class);
	private String url;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if (methodName.equals("list")) { getList(request, response);}
		else if (methodName.equals("save")) { save(request,response);}
		else if (methodName.equals("updatePage")) {updatePage(request,response);}
		else if (methodName.equals("delete")) {delete(request,response);}
		else if (methodName.equals("update")) {update(request, response);}
		else if (methodName.equals("findByTypeName")) {getListByTypeName(request, response);}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<FoodType> list = foodTypeService.getAll();
			request.setAttribute("foodTypeList", list);
			url = "/sys/foodtype/cuisineList.jsp";
			
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void getListByTypeName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String fuzzyTypeName = request.getParameter("keyword");
			List<FoodType> list = foodTypeService.getAll(fuzzyTypeName);
			request.setAttribute("foodTypeList", list);
			url = "/sys/foodtype/cuisineList.jsp";
			
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeName = request.getParameter("typeName");
		FoodType foodType = new FoodType();
		foodType.setTypeName(typeName);
		try {
			foodTypeService.save(foodType);
			url = "/FoodTypeServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void updatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			FoodType foodType = foodTypeService.findById(id);
			request.setAttribute("foodType", foodType);
			url = "/sys/foodtype/updateCuisine.jsp";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String typeName = request.getParameter("typeName");
		FoodType foodType = new FoodType();
		foodType.setTypeName(typeName);
		foodType.setId(id);
		try {
			foodTypeService.update(foodType);
			url = "/FoodTypeServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			foodTypeService.delete(id);
			url = "/FoodTypeServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
