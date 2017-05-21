package br.com.robertodebarba.javaspark_example.core;

import br.com.robertodebarba.javaspark_example.api.BaseApi;
import br.com.robertodebarba.javaspark_example.api.product.ProductApi;

public class MyApplication {

	public static void main(String[] args) {
		new BaseApi().bind();
		new ProductApi().bind();
	}

}
