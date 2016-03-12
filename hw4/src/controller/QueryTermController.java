package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webInterface.IndexConstant;
import webInterface.LuceneTester;

/**
 * Servlet implementation class QueryTermController
 */
@WebServlet("/QueryTermController")
public class QueryTermController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTermController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		QueryTermModel queryTermModel = new QueryTermModel();
		
		String arryTerm[];
		String queryTerm = request.getParameter("queryTerm");
		
		int maxSearch = Integer.parseInt(request.getParameter("maxSearch"));
		queryTermModel.setMaxSearch(maxSearch);
		IndexConstant.MAX_SEARCH = maxSearch;
		if(queryTerm.toLowerCase().contains("or"))
			{
				System.out.println("or ::>> ");
				arryTerm =queryTerm.split("or");
				
			}
		else if(queryTerm.toLowerCase().contains("and"))
			{
				System.out.println("and ::>> ");
				arryTerm =queryTerm.split("and");
				queryTermModel.setQueryTerm0(arryTerm[0]);
				queryTermModel.setQueryTerm0(arryTerm[1]);
			}
		else 
			{
				System.out.println("else ");
				queryTermModel.setQueryTerm0(queryTerm);
			}
		
		List ls = LuceneTester.start(queryTermModel);
		System.out.println("controller : >> "+ls.size());
		
		session.setAttribute("resultList", ls);
		response.sendRedirect("getResult.jsp");
		
	}

}
