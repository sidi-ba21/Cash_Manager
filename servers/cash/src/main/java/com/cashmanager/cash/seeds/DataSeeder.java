package com.cashmanager.cash.seeds;

import com.cashmanager.cash.models.ClientAccount;
import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.payload.request.client.*;
import com.cashmanager.cash.payload.request.cart.*;
import com.cashmanager.cash.payload.request.article.*;
import com.cashmanager.cash.services.clientaccount.IClientAccountService;
import com.cashmanager.cash.services.cart.ICartService;
import com.cashmanager.cash.services.article.IArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

	private final IClientAccountService clientService;
	private final ICartService cartService;
	private final IArticleService articleService;

	@Override
	public void run(String... args) throws Exception {
		if (clientService.count() > 0) {
			return;
		}
		AddClientRequest addClientRequest = new AddClientRequest("John", "DOE", "john.doe@gmail.com", "password");
		ClientAccount clientAccount1 = this.clientService.add(addClientRequest);

		Cart myCart = this.cartService.create();
		this.cartService.setClient(clientAccount1.getId(), myCart);
		this.clientService.setCart(clientAccount1.getId(), myCart);

		AddArticleRequest addArticleRequest = new AddArticleRequest("Article 1", 10L, 10L);
		Article article1 = this.articleService.add(addArticleRequest);

		AddArticleRequest addArticleRequest2 = new AddArticleRequest("Article 2", 20L, 20L);
		Article article2 = this.articleService.add(addArticleRequest2);



		AddItemCartRequest addItemCartRequest = new AddItemCartRequest(article1.getId());
		this.cartService.add(clientAccount1.getId(), addItemCartRequest);

		AddItemCartRequest addItemCartRequest2 = new AddItemCartRequest(article2.getId());
		this.cartService.add(clientAccount1.getId(), addItemCartRequest2);

		// print all articles in the cart
		Cart actualCart = this.cartService.findById(clientAccount1.getId()).orElse(null);
		if (actualCart != null) {
			 List<Article> articles = articleService.getCartArticles(clientAccount1.getId());
			if (articles != null) {
				log.info("Cart articles : " + articles.size());
				//log.info("cart articles : {}", articles);
			}
			else {
				log.info("Cart articles : null");
			}
		}
		else {
			log.info("Cart is : null");
		}

		UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest("Article 1", 15L, 15L);
		Article article3 = this.articleService.update(article1.getId(), updateArticleRequest);

		UpdateClientRequest updateClientRequest = new UpdateClientRequest("John", "DOE", "doe.john@gmail.com", "123456");
		ClientAccount clientAccount2 = this.clientService.update(clientAccount1.getId(), updateClientRequest);

		DeleteItemCartRequest deleteItemCartRequest = new DeleteItemCartRequest(article1.getId());
		this.cartService.delete(clientAccount1.getId(), deleteItemCartRequest);

		ValidateCartRequest validateCartRequest = new ValidateCartRequest("CARD", true);
		this.cartService.validateCart(clientAccount1.getId(), validateCartRequest);


	}

}
