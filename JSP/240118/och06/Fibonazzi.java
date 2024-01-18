package och06;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Fibonazzi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BigInteger[] arr = new BigInteger[100];
       
    public Fibonazzi() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Fibonazzi init...");
		arr[0] = new BigInteger("1");
		arr[1] = new BigInteger("1");
		for (int i=2; i<arr.length; i++) {
			arr[i] = arr[i-2].add(arr[i-1]);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Fibonazzi doGet...");
		int num = Integer.parseInt(request.getParameter("num"));
		if(num > 100)  num=100;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h2>피보나찌수열</h2>");
		for (int i=0; i<num; i++) {
			out.println(arr[i]+"<br>");
		}
		out.println("</body></html>");
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
