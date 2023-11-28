package main.frontend;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.Service.ProductService;
import main.Service.UserService;
import main.backend.Auction.Auction;
import main.backend.Auction.AuctionService;
import main.database.product;
import main.database.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@RestController
@RequestMapping("/Main")
public class frontendController {
	@Autowired
	private UserService service;
	@Autowired
	private ProductService productService;
	@Autowired
	private AuctionService auctionService;
	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if((request.getSession().getAttribute("username")== null)){
			return true;
		}
		return false;

	}

	@GetMapping("/MainPage")
	public void MainPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			ClassPathResource resource = new ClassPathResource("static/MainPage.html");


			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(htmlContent);

		}

	}

	@GetMapping("/AuctionList")
	public void AuctionList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{


			ClassPathResource resource = new ClassPathResource("static/AuctionList.html");


			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");

			ArrayList<Auction> ap = (ArrayList<Auction>) auctionService.findAll();

			String s = "</tr>\n";
			for(Auction a:ap) {
				a.checkIfStart();
				a.checkIfEnd();
				auctionService.saveAuction(a);
				s+="<tr>\n<td>" + a.getAuction_id()+"</td>\n<td>"+a.getCreateTime()+"</td>\n<td>"+a.getStartTime()
						+"</td>\n<td>"+a.getEndTime()+"</td>\n<td>"+a.getSeller().getUserId()+"</td>\n<td>"+a.getStartingPrice()+
						"</td>\n<td>"+a.getCurrentHighestPrice()+"</td>\n<td>"+a.getCurrentHighestUser().getUserId()+"</td>\n<td>"+a.getState()+
						"</td>\n<td>"+a.getAuction_type()+"</td>\n<td>"+"<button onclick=\"AttendAuction("+a.getAuction_id()+")\">Attend Auction</button>"+"</td>\n</tr>";

			}
			htmlContent = htmlContent.replace("</tr>", s);

			response.getWriter().write(htmlContent);

		}

	}

	@GetMapping("/InventoryManagement")
	public void InventoryManagement(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			ClassPathResource resource = new ClassPathResource("static/InventoryManagement.html");
			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(htmlContent);

		}

	}

	@GetMapping("/AuctionManagement")
	public void AuctionManagement(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			ClassPathResource resource = new ClassPathResource("static/AuctionManagement.html");
			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(htmlContent);

		}

	}

	@GetMapping("/editAuction")
	public void AuctionEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			ClassPathResource resource = new ClassPathResource("static/editAuction.html");
			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(htmlContent);

		}

	}

	@GetMapping("/AuctionSetStartTime")
	public void AuctionSetStartTime(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			String datetimeLocalValue = request.getParameter("auction_start_time");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime localDateTime = null;
			try {
				localDateTime = LocalDateTime.parse(datetimeLocalValue, formatter);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Integer id = Integer.parseInt(request.getParameter("auction_id"));
			Auction p = ((ArrayList<Auction>)auctionService.findById(id)).get(0);


			p.setStartTime(localDateTime);
			auctionService.saveAuction(p);



			response.sendRedirect("/Main/editAuction");

		}

	}

	@GetMapping("/AuctionSetEndTime")
	public void AuctionSetEndTime(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			String datetimeLocalValue = request.getParameter("auction_end_time");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime localDateTime = null;
			try {
				localDateTime = LocalDateTime.parse(datetimeLocalValue, formatter);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Integer id = Integer.parseInt(request.getParameter("auction_id"));
			Auction p = ((ArrayList<Auction>)auctionService.findById(id)).get(0);


			p.setEndTime(localDateTime);
			auctionService.saveAuction(p);



			response.sendRedirect("/Main/editAuction");

		}

	}

	@GetMapping("/AuctionSetStartPrice")
	public void AuctionSetStartPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			String Price = request.getParameter("auction_start_price");
			Integer startPrice = Integer.parseInt(Price);

			Integer id = Integer.parseInt(request.getParameter("auction_id"));
			Auction p = ((ArrayList<Auction>)auctionService.findById(id)).get(0);


			p.setStartingPrice(startPrice);
			auctionService.saveAuction(p);



			response.sendRedirect("/Main/editAuction");

		}

	}

	@GetMapping("/AuctionSetMinIncrePrice")
	public void AuctionSetMinIncrePrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			String Price = request.getParameter("auction_min_price");
			Integer startPrice = Integer.parseInt(Price);

			Integer id = Integer.parseInt(request.getParameter("auction_id"));
			Auction p = ((ArrayList<Auction>)auctionService.findById(id)).get(0);


			p.setMinimum_bid_increment(startPrice);
			auctionService.saveAuction(p);



			response.sendRedirect("/Main/editAuction");

		}

	}




	@GetMapping("/CreateAuction")
	public void CreateAuction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			user u = null;
			for(user i:service.findByUsernameLike(username)) {
				if(i.getUsername().equals(username)){
					u = i;
				}
			}

			Integer seller = u.getUserId();
			String auction_type = request.getParameter("auction_type");


			Auction a = new Auction();
			a.setAuction_type(auction_type);
			a.setSeller(u);

			auctionService.saveAuction(a);

			response.sendRedirect("/Main/AuctionManagement");
		}




	}



	@GetMapping("/CreateProduct")
	public void CreateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{
			String name = request.getParameter("name");
			String category = request.getParameter("category");

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			user u = null;
			for(user i:service.findByUsernameLike(username)) {
				if(i.getUsername().equals(username)){
					u = i;
				}
			}

			Integer seller = u.getUserId();
			Integer NumberOfItem = Integer.parseInt(request.getParameter("numberofitem"));


			product p = new product(null, name, category, seller, NumberOfItem);

			productService.saveProduct(p);

			response.sendRedirect("/Main/InventoryManagement");
		}




	}

	@GetMapping("/DeleteAuction")
	public void DeleteAuction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{


			Integer id = Integer.parseInt(request.getParameter("id"));

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			user u = null;
			for(user i:service.findByUsernameLike(username)) {
				if(i.getUsername().equals(username)){
					u = i;
				}
			}


			Integer seller = u.getUserId();




			Auction p = ((ArrayList<Auction>)auctionService.findById(id)).get(0);

			if(p.getSeller().getUserId().equals(seller) ) {
				auctionService.deleteById(id);
			}


			response.sendRedirect("/Main/AuctionManagement");
		}


	}

	@GetMapping("/Auction")
	public void Auction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{


			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer bid = Integer.parseInt(request.getParameter("bid"));

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			user u = null;
			for(user i:service.findByUsernameLike(username)) {
				if(i.getUsername().equals(username)){
					u = i;
				}
			}





			Auction p = ((ArrayList<Auction>)auctionService.findById(id)).get(0);

			if(p.getCurrentHighestPrice()+p.getMinimum_bid_increment()<= bid&&p.getState().equals("Started")){
				p.setCurrentHighestPrice(bid);
				p.setCurrentHighestUser(u);
				auctionService.saveAuction(p);
			}


			response.sendRedirect("/Main/AuctionList");
		}


	}

	@GetMapping("/DeleteProduct")
	public void DeleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");

		}else{


			Integer id = Integer.parseInt(request.getParameter("id"));

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			user u = null;
			for(user i:service.findByUsernameLike(username)) {
				if(i.getUsername().equals(username)){
					u = i;
				}
			}


			Integer seller = u.getUserId();




			product p = ((ArrayList<product>)productService.findById(id)).get(0);

			if(p.getSeller().equals(seller) ) {
				productService.deleteById(id);
			}


			response.sendRedirect("/Main/InventoryManagement");
		}


	}
	@GetMapping("/profile")
	public void profile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(checkLogin(request,response)) {
			response.sendRedirect("/Main/login");
		}else{
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			user u = null;
			for(user i:service.findByUsernameLike(username)) {
				if(i.getUsername().equals(username)){
					u = i;
				}
			}



			ClassPathResource resource = new ClassPathResource("static/profile.html");


			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");

			htmlContent = htmlContent.replace("<ul>", "<ul>\n<li>Username: "+u.getUsername()+"</li>\n<li>First Name: "+u.getFirstName()+"</li>\n<li>Last Name: "+u.getLastName()+"</li>\n<li>Email: "+u.getEmail()+"</li>");

//        String relativeCssPath = "/res/styles.css";
//        String realCssPath = context.getRealPath(relativeCssPath);
//        String cssContent = readHtmlFile(realCssPath);
//
//        htmlContent = htmlContent.replace("</head>",
//                "<style type=\"text/css\">" + cssContent + "</style></head>");

			response.getWriter().write(htmlContent);
		}

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		user u = null;
		for(user i:service.findByUsernameLike(username)) {
			if(i.getUsername().equals(username)){
				u = i;
			}
		}

	}


	
	@GetMapping("/SignUpResult")
	public void SignUpResult(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");




		if(service.findByUsernameLike(username)==null||service.findByUsernameLike(username).isEmpty()) {
			user u = new user(null,firstName,lastName,"client",username,password,email);
			service.saveUser(u);

			ClassPathResource resource = new ClassPathResource("static/login.html");


			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");
			htmlContent = htmlContent.replace("</form>", "</form>\n<p>Sign up success. You can login now</p>");


			response.getWriter().write(htmlContent);
		}else {
			boolean flag = false;
			for(user u:service.findByUsernameLike(username)) {
				if(u.getUsername().equals(username)){
					flag = true;
				}
			}
			if(flag) {
				ClassPathResource resource = new ClassPathResource("static/signUp.html");


				byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
				String htmlContent = new String(contentBytes, "UTF-8");
				response.setContentType("text/html");

				htmlContent = htmlContent.replace("</form>", "</form>\n<p>Sign up failed. Username already exists</p>");



//        String relativeCssPath = "/res/styles.css";
//        String realCssPath = context.getRealPath(relativeCssPath);
//        String cssContent = readHtmlFile(realCssPath);
//
//        htmlContent = htmlContent.replace("</head>",
//                "<style type=\"text/css\">" + cssContent + "</style></head>");

				response.getWriter().write(htmlContent);
			}else{
				user u = new user(null,firstName,lastName,"client",username,password,email);
				service.saveUser(u);
				ClassPathResource resource = new ClassPathResource("static/login.html");


				byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
				String htmlContent = new String(contentBytes, "UTF-8");
				response.setContentType("text/html");

				htmlContent = htmlContent.replace("</form>", "</form>\n<p>Sign up success. You can login now.</p>");

//        String relativeCssPath = "/res/styles.css";
//        String realCssPath = context.getRealPath(relativeCssPath);
//        String cssContent = readHtmlFile(realCssPath);
//
//        htmlContent = htmlContent.replace("</head>",
//                "<style type=\"text/css\">" + cssContent + "</style></head>");

				response.getWriter().write(htmlContent);
			}

		}
	}

	@GetMapping("/LoginResult")
	public void LoginResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean flag = false;
		for(user u:service.findByUsernameLike(username)) {
			if(u.getUsername().equals(username)&&u.getPassword().equals(password)){
				flag = true;
			}
		}
		if(flag) {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			response.sendRedirect("/Main/MainPage");
		}else{
			ClassPathResource resource = new ClassPathResource("static/login.html");


			byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String htmlContent = new String(contentBytes, "UTF-8");
			response.setContentType("text/html");

			htmlContent = htmlContent.replace("</form>", "</form>\n<p>Login failed. Incorrect username or password.</p>");
			response.getWriter().write(htmlContent);
		}


	}


	@GetMapping("/login")
	public void getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClassPathResource resource = new ClassPathResource("static/login.html");


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
