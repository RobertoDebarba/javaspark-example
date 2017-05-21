package br.com.robertodebarba.javaspark_example.api.product;

import static br.com.robertodebarba.javaspark_example.core.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Optional;

import com.google.gson.Gson;

public class ProductApi {

	private final ProductDAO productDAO = new ProductDAO();

	public void bind() {
		get("product", (req, res) -> {
			res.type("application/json");

			final String search = req.queryParams("$search");

			Product[] products = new Product[0];
			if (search != null && !search.isEmpty()) {
				products = this.productDAO.getProducts(search);
			} else {
				products = this.productDAO.getProducts();
			}

			if (products.length < 1) {
				res.status(400);
				return "";
			}

			return products;
		}, json());

		get("product/:productId", (req, res) -> {
			res.type("application/json");

			final int productId = Integer.parseInt(req.params("productId"));

			final Optional<Product> product = this.productDAO.getProduct(productId);

			if (product.isPresent()) {
				return product;
			}

			res.status(400);
			return "";
		}, json());

		put("product", (req, res) -> {
			final Product product = new Gson().fromJson(req.body(), Product.class);

			if (this.productDAO.insertProduct(product)) {
				return "";
			}

			res.status(400);
			return "";
		});

		post("product", (req, res) -> {
			final Product product = new Gson().fromJson(req.body(), Product.class);

			if (this.productDAO.updateProduct(product)) {
				return "";
			}

			res.status(400);
			return "";
		}, json());

	}

}
