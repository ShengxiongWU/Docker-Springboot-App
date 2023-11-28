package main.frontend;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.Service.ProductService;
import main.database.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

/**
 * Servlet implementation class FirstApplication
 */
@WebServlet({ "/MainPage" })
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService service;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if((request.getSession().getAttribute("username")== null)){
			response.sendRedirect("/Main/login");

		}else{
			ClassPathResource resource = new ClassPathResource("static/MainPage.html");


			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");



//        String relativeCssPath = "/res/styles.css";
//        String realCssPath = context.getRealPath(relativeCssPath);
//        String cssContent = readHtmlFile(realCssPath);
//
//        htmlContent = htmlContent.replace("</head>",
//                "<style type=\"text/css\">" + cssContent + "</style></head>");

			response.getWriter().write(htmlContent);
		}

		

	}

	private String readHtmlFile(String filePath) throws IOException {
	    StringBuilder content = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            content.append(line);
	            content.append("\n");
	        }
	    }
	    return content.toString();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);


	}

}
