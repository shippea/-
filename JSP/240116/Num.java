package och02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Num
 */
public class Num extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Num() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		PrintWriter out = response.getWriter();
		
		out.printf("<html><body>");
		out.println("<h1>연산결과</h1>");
		out.printf("덧셈: %d + %d = %d<br>",num1,num2,(num1+num2));
		out.printf("뺄셈: %d - %d = %d<br>",num1,num2,(num1-num2));
		out.printf("곱셈: %d * %d = %d<br>",num1,num2,(num1*num2));
		out.printf("나눗셈: %d / %d = %f<br>",num1,num2,((float)num1/num2));
		
		out.printf("</body><html>");
		out.close();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
