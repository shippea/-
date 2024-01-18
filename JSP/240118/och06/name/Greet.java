package och06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Greet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter log;
       
    public Greet() {
        super();
    }

    // init은 처음 서비스를 시작할 때만 한 번 실행됨
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init start...");
		try {
			log = new PrintWriter(new FileWriter("c:/log/log.txt",true));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("헐 !");
		}
	}

	public void destroy() {
		System.out.println("destroy start...");
		log.flush();
		if (log != null) log.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet start...");
		String name = request.getParameter("name");
		String msg = name + "님 반가워\r\n";
		GregorianCalendar gc = new GregorianCalendar();
		String date= String.format("%TF %TT\r\n",gc,gc);
		
		// File 출력 -> date + msg
		log.print(date + msg);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 화면출력 --> msg
		out.println("<html><body><h2>인사</h2>"+msg);
		out.println("</body></html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
