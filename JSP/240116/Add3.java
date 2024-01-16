package och01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add3
 */
public class Add3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Add3 Get Start...");
		
		// 목표: 1부터 누적값 전달
		int num = Integer.parseInt(request.getParameter("num"));
		String loc = request.getParameter("loc");
		
		System.out.println("Add3 num ->" + num);
		int sum = 0;
		for (int i = 1; i <= num; i++) {
			sum += i;
		}
		System.out.println("Add3 sum ->" + sum);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 사용자 Browser 화면에 보여주는 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		// %d = 숫자, %s = 문자를 받음 (파라미터 순서대로)
		out.printf("<h1>1부터 %d까지 합계 %s</h1>", num, loc);
		
		//out.printf("<4>LOC--> %s</h4>", loc);
		out.println(sum);
		out.println("</body></html>");
		out.close();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		System.out.println("Add3 Post Start...");
	}

}
