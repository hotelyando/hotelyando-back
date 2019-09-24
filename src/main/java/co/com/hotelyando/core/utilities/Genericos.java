package co.com.hotelyando.core.utilities;

import com.google.gson.Gson;

public class Genericos<T> {

	public String convertirObjetoAJson(T object) {

		Gson gson = new Gson();
		String json = "";

		json = gson.toJson(object);

		return json;

	}

	@SuppressWarnings("unchecked")
	public T convertirJsonAObjeto(String json) {
		
		Gson gson = new Gson();
		T object = null;
		
		object = (T) gson.fromJson(json, Object.class);
		
		return object;
		
		
	}

}
