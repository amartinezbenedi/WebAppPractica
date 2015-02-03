package controler;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.*;
import model.vo.*;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	
	AparcamientosPublicosDAO apDAO = new AparcamientosPublicosDAO();
	AparcamientosPublicosVO apVO = new AparcamientosPublicosVO();
	
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		PrintWriter out = response.getWriter();
		String aux = request.getParameter("id");
		int id = Integer.parseInt(aux);
		apVO = apDAO.detalleAparcamientos(id);
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1> este es el id que le paso: " + id + "</h1>");
		out.println("</body>");
		out.println("</hatml>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
