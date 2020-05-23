import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.*;

@WebServlet(name="Checkout", value="/checkout")
public class Checkout extends HttpServlet {
    private String url = "jdbc:mysql://localhost:3306/ecrocs?serverTimezone=UTC";
    private String dbUsername = "root";
    private String dbPassword = "rxpost123";
    public void init() {
        // 1. Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected Shoe getShoe(String sid) {
        String query = "SELECT * FROM `shoes` WHERE id=" + sid;
        Shoe shoe = null;

        try {
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                shoe = new Shoe(rs, new String[]{"type", "name", "id", "price", "desc1", "desc2"});
            }

            System.out.println("CURRENT SHOE");
            System.out.println(shoe);

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return shoe;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

//        ArrayList<Item> cart = new ArrayList<Item>();
//        cart.add(new Item(getShoe("10001"), "Pool Blue", "3", 7,1));
//        cart.add(new Item(getShoe("205392"), "Black / White ", "2", 8,2));

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html><html lang='en'>");
            writer.println("<head>");
            writer.println("<title>eCrocs | Products</title>");
            writer.println("<meta name=\"description\" charset=\"UTF-8\" content=\"A site for INF 124 ecommerce project - selling eCrocs\" />");
            writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />");
            writer.println("<link href=\"http://db.onlinewebfonts.com/c/158a997f8a01e5bd6f96844ae5739add?family=AG+Book+Rounded\" rel=\"stylesheet\" type=\"text/css\"/>");
            writer.println("<link href=\"https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300;700&display=swap\" rel=\"stylesheet\"/>");
            writer.println("<link rel=\"stylesheet\" href=\"css/styles.css?v=1.0\" />");
            writer.println("</head>");


            writer.println("<body class=\"checkout\" style=\"margin: 0;\">");
            writer.println("<div class='confirmationheader'><div class=\"logo\"><a href=\"../inf124-ecommerce-p3/products\">ecrocs</a></div></div>");

            writer.println("<div class='confirmation'>");
            writer.println("<div class='confirmation-left'>");
            writer.println("<h3>Your Cart</h3>");

			HttpSession session = request.getSession(false);
			if(session != null) {
				ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
				if (cart == null) {
					session.setAttribute("cart", new ArrayList<Item>());
					cart = (ArrayList<Item>) session.getAttribute("cart");
				}
				double totalPrice = 0;
				double tax = 0;

				for (int i = 0;i<cart.size();i++) {
					writer.println("<div class='cartitem'>");
					writer.println("<h4>"+ cart.get(i).shoe.name +"</h4>");
					writer.println("<div class='cartrows'>");
					writer.println("<div class='cartleft'>");
					writer.println("<img src=\"./assets/"+ cart.get(i).shoe.id+"/product_"+cart.get(i).colorIndex+".jpg\" class='cartpic' />");
					writer.println("</div>");
					writer.println("<div class='cartright'>");
					writer.println("<br />");
					writer.println("<div><h5>Price: $" + cart.get(i).shoe.price +"</h5></div>");
					writer.println("<div><h5>Size:  " + cart.get(i).size +"</h5></div>");
					writer.println("<div><h5>Quantity: " + cart.get(i).quantity +"</h5></div>");
					writer.println("</div>");
					writer.println("</div>");
					writer.println("</div>");
					totalPrice += (cart.get(i).shoe.price * cart.get(i).quantity);
				}

				writer.println("<div><h5>Total Price: $" + totalPrice +"</h5></div>");
			}




//            writer.println("<div><h5>Tax: $" + tax +"</h5></div>");
//            writer.println("<div><h5>Total Price w/ Tax: $" + (totalPrice + tax) +"</h5></div>");


            writer.println("</div>");

            writer.println("<div class='confirmation-right'>");
            writer.println("<div class='order-form' id='odForm'>");

            writer.println("</div>");
            writer.println("</div>");
            writer.println("</div>");


            writer.println("<script src=\"./js/new-checkout.js\"></script>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
