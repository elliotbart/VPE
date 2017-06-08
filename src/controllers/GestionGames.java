package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import beans.Game;
import utils.JsonParser;

/**
 * Servlet implementation class GestionGames
 */
@WebServlet("/GestionGames")
public class GestionGames extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_GAME = "http://localhost:8080/SR03_Bartholme_Bathellier_Vancon/rest/VideoGames/games";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionGames() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = "";
		try {
			result = getGames();
			System.out.println(result);
			List<Game>  listGame = JsonParser.getGames(result);
			// TODO : requestdispatcher --> vers jsp de pierre
			 response.getWriter().append(result);
			 request.setAttribute("listGame", listGame);
			 request.getRequestDispatcher("/homepage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getGames() throws Exception {
		String result = "";
		try {
			URL url = new URL(URL_GAME);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String temp;
			while ((temp = br.readLine()) != null) {
				result += temp;
			}
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
