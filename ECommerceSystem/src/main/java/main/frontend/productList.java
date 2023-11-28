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
@WebServlet({ "/productList" })
public class productList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService service;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if((request.getSession().getAttribute("username")== null)||request.getSession().getAttribute("username").equals("")){
			response.sendRedirect("/Main/login");

		}
        ClassPathResource resource = new ClassPathResource("static/productList.html");
        

        byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String htmlContent = new String(contentBytes, "UTF-8");
        response.setContentType("text/html");
        
        ArrayList<product> ap = (ArrayList<product>) service.findAll();
        
        String s = "</tr>\n";
        for(product i:ap) {
        	s+="<tr>\n<td>" + i.getId()+"</td>\n<td>"+i.getName()+"</td>\n<td>"+i.getCategory()
        	+"</td>\n<td>"+i.getSeller()+"</td>\n<td>"+i.getNumberOfItems()+"</td>\n</tr>";
        }
      htmlContent = htmlContent.replace("</tr>", s);
        
        
//        String relativeCssPath = "/res/styles.css";
//        String realCssPath = context.getRealPath(relativeCssPath);
//        String cssContent = readHtmlFile(realCssPath);
//        
//        htmlContent = htmlContent.replace("</head>",
//                "<style type=\"text/css\">" + cssContent + "</style></head>");

        response.getWriter().write(htmlContent);
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
