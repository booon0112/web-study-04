package unit01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppContext")
public class AppContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
		doGet(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
		ServletContext application= getServletContext();
		String resourcePath= application.getInitParameter("noticePath");
		String str="";
		
		URL url=application.getResource(resourcePath);
		try(BufferedReader br= new BufferedReader(
				new InputStreamReader(url.openStream()        /*아래 주석문 대신 사용 같은 결과 값 */
						/* application.getResourceAsStream(resourcePath) */))){
			
					while( (str=br.readLine())!= null){
						System.out.println(str);		
					}
		}catch(IOException e){
			System.out.println("예외발생 " +e.getMessage());
		}
		
	}

}
