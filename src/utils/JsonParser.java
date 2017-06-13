package utils;

import beans.Client;
public class JsonParser {
	
	public static String[] getJsonObjects(String json) {
		return json.split("\\}, \\{");
	}

	public static String formate(String json) {
		String result = json.substring(4);
		result = result.substring(0, result.length() - 3);
		return result;
	}
	
	
	
	public static List<Client> getClients(String json) throws ParseException, JSONException {
		json = formate(json);
		String[] tabJsonObject = getJsonObjects(json);
		List<Client> listClients= createClients(tabJsonObject);
		return listClients;
		
	}

	private static List<Client> createClients(String[] tabJsonObject) throws JSONException {
		List<Client> listClients = new ArrayList<Client>();
		for(int i = 0; i < tabJsonObject.length; ++i) {
			listClients.add(createClient(tabJsonObject[i]));
		}
		return listClients;
	}

	private static Client createClient(String jsonObjectString) throws JSONException {
		jsonObjectString = "{" + jsonObjectString + "}";
		JSONObject jsonObject = new JSONObject(jsonObjectString);
		String email = jsonObject.getString("email");
		String lastName = jsonObject.getString("lastName");
		String firstName = jsonObject.getString("firstName");
		String password = jsonObject.getString("password");
		String birthDate = jsonObject.getString("birthDate");
		int cart = jsonObject.getInt("cart");
		
		Client client = new Client(email, lastName, firstName, password, birthDate/*, cart*/);
		
		return client;
	}



	
	
	
}
