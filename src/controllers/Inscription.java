package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cart;
import beans.Client;
import dao.CartDao;
import dao.ClientDao;


/**
 * Servlet implementation class inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String ATT_INSCRIPTION = "inscription";
	private static final String ATT_CLIENT = "client";
	public static final String VUE = "/inscription.jsp";


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		GestionInscription gestionInscription = new GestionInscription();

        /* Traitement de la requête et récupération du bean en résultant */
		//encoding treatment
		request.setCharacterEncoding("UTF-8");
		
        Client client = null;
		client = gestionInscription.getClient(request);
		
		 if (gestionInscription.getErreurs().isEmpty()) { 
			 
			 //***********partie a mettre dans un webservice d'inscription ??**********
			 CartDao.insertSQL(new Cart());
			 int id_cart = CartDao.findLastCart();
			 client.setCart(id_cart);
			 if(ClientDao.insertSQL(client) > 0){
				 gestionInscription.setResultat("Succès de l'inscription.");
			 }
			 else{
				 CartDao.deleteCart(id_cart);
				 gestionInscription.setResultat("Echec de l'inscription.");
			 }	 			 
		 } 
		 //***********partie a mettre dans un webservice d'inscription ??**********
		 
		 request.setAttribute(ATT_INSCRIPTION, gestionInscription);
		 request.setAttribute(ATT_CLIENT, client);
		 
		 this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}

}