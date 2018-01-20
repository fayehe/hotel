package com.faye.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.faye.entity.Food;
import com.faye.entity.FoodType;
import com.faye.factory.BeanFactory;
import com.faye.service.IFoodService;
import com.faye.service.IFoodTypeService;
import com.faye.service.impl.FoodService;
import com.faye.service.impl.FoodTypeService;
import com.faye.utils.PageBean;

@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IFoodService foodService = BeanFactory.GetInstance("foodService", FoodService.class);
	private IFoodTypeService foodTypeService = BeanFactory.GetInstance("foodTypeService", FoodTypeService.class);
	private String url;
	private List<FoodType> foodTypes;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		foodTypes = (List<FoodType>) session.getAttribute("foodTypes");
		if (foodTypes == null) {
			try {
				foodTypes = foodTypeService.getAll();
				session.setAttribute("foodTypes", foodTypes);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		String methodName = request.getParameter("method");
		if (methodName.equals("list")) { getList(request, response);}
		else if (methodName.equals("save")) { save(request,response);}
		else if (methodName.equals("updatePage")) {updatePage(request,response);}
		else if (methodName.equals("delete")) {delete(request,response);}
		else if (methodName.equals("update")) {update(request, response);}
		else if (methodName.equals("findFoodName")) {getListByFoodName(request, response);}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String currentPage = request.getParameter("currPage");
			List<Food> list  = (List<Food>) request.getSession().getAttribute("foodList");
			if (list == null) {
				list = foodService.getAll();
				request.getSession().setAttribute("foodList", list);
			}
			PageBean<Food> pageBean = new PageBean<>();
			if (currentPage !=null && !"".equals(currentPage.trim())) {
				pageBean.setCurrentPage(Integer.parseInt(currentPage));
			}
			pageBean.setTotalCount(list.size());
			pageBean.setPageData(list);
			request.setAttribute("pageBean", pageBean);
			url = "/sys/food/foodList.jsp";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void getListByFoodName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String fuzzyTypeName = request.getParameter("keyword");
			List<Food> list = foodService.getAll(fuzzyTypeName);
			request.setAttribute("foodList", list);
			url = "/sys/food/foodList.jsp";
			
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			Food food = new Food();
			FileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
			upload.setHeaderEncoding("UTF-8");
			if (upload.isMultipartContent(request)) {
				List<FileItem> parseRequest = upload.parseRequest(request);
				for (FileItem fileItem : parseRequest) {
					if (fileItem.isFormField()) {
							 if(fileItem.getFieldName().equals("foodName")) food.setFoodName(fileItem.getString("UTF-8"));
						else if(fileItem.getFieldName().equals("foodType_id")) food.setFoodType_id(Integer.parseInt(fileItem.getString()));
						else if(fileItem.getFieldName().equals("mprice")) food.setMprice(Double.parseDouble(fileItem.getString()));
						else if(fileItem.getFieldName().equals("price")) food.setPrice(Double.parseDouble(fileItem.getString()));
						else if(fileItem.getFieldName().equals("remark")) food.setRemark(fileItem.getString("UTF-8"));
					}else {
						String imgName = UUID.randomUUID().toString() + "_" + fileItem.getName();
						
						String filePath = getServletContext().getRealPath("/sys/upload/food");
						File file = new File(filePath, imgName);
						fileItem.write(file);
						fileItem.delete();
						food.setImg(imgName);
					}
				}
			}
			
			foodService.save(food);
			url = "/FoodServlet?method=list";
		} catch (Exception e) {
			url = "/error/error.jsp";
			e.getStackTrace();
		} 
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void updatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Food food = foodService.findById(id);
			request.setAttribute("food", food);
			request.setAttribute("realPath", getServletContext().getRealPath("/sys/upload/food"));
			url = "/sys/food/updateFood.jsp";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			Food food = new Food();
			food.setId(id);
			FileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
			upload.setHeaderEncoding("UTF-8");
			if (upload.isMultipartContent(request)) {
				List<FileItem> parseRequest = upload.parseRequest(request);
				for (FileItem fileItem : parseRequest) {
					if (fileItem.isFormField()) {
							 if(fileItem.getFieldName().equals("foodName")) food.setFoodName(fileItem.getString("UTF-8"));
						else if(fileItem.getFieldName().equals("foodType_id")) food.setFoodType_id(Integer.parseInt(fileItem.getString()));
						else if(fileItem.getFieldName().equals("mprice")) food.setMprice(Double.parseDouble(fileItem.getString()));
						else if(fileItem.getFieldName().equals("price")) food.setPrice(Double.parseDouble(fileItem.getString()));
						else if(fileItem.getFieldName().equals("remark")) food.setRemark(fileItem.getString("UTF-8"));
					}else {
						String imgName = UUID.randomUUID().toString() + "_" + fileItem.getName();
						String filePath = getServletContext().getRealPath("/sys/upload/food");
						File file = new File(filePath, imgName);
						fileItem.write(file);
						fileItem.delete();
						food.setImg(imgName);
					}
				}
			}
			
			foodService.update(food);
			url = "/FoodServlet?method=list";
		} catch (Exception e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			foodService.delete(id);
			url = "/FoodServlet?method=list";
		} catch (SQLException e) {
			url = "/error/error.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
