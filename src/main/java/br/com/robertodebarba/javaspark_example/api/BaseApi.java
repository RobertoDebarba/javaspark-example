package br.com.robertodebarba.javaspark_example.api;

import static spark.Spark.get;

public class BaseApi {

	public void bind() {
		get("/", (req, res) -> "Hello World");
	}

}
