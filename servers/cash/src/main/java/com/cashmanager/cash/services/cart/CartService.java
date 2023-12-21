package com.cashmanager.cash.services.cart;

import com.cashmanager.cash.models.*;
import com.cashmanager.cash.models.enums.TransactionType;
import com.cashmanager.cash.payload.request.article.UpdateArticleRequest;
import com.cashmanager.cash.payload.request.cart.*;
import com.cashmanager.cash.services.article.IArticleService;
import com.cashmanager.cash.services.clientaccount.IClientAccountService;
import com.cashmanager.cash.services.order.IOrderService;
import com.cashmanager.cash.services.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
class CartService implements ICartService {

    private ICartRepository cartRepository;

    private IOrderService orderService;

    private IArticleService articleService;

    private IClientAccountService clientAccountService;

    private IPaymentService paymentService;


    @Override
    public Cart create() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public Cart setClient(Long id, Cart cart) {
        ClientAccount clientAccount = clientAccountService.findById(id).orElse(null);
        if (clientAccount == null) {
            return null;
        }
        Client client = clientAccount.getClient();
        cart.setClient(client);
        return cartRepository.save(cart);
    }

    @Override
    public Cart add(Long id, AddItemCartRequest data) {

        ClientAccount clientAccount = clientAccountService.findById(id).orElse(null);
        if (clientAccount == null) {
            return null;
        }
        Client client = clientAccount.getClient();

        Cart cart = client.getCart();

        Article article = articleService.findById(data.getArticle_id()).orElse(null);

        List <Article> articles = this.articleService.getCartArticles(id);

        if (article == null) {
            return null;
        }

        articles.add(article);
        articleService.setInCart(article.getId(), cart);
        cart.setArticles(articles);


        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id, DeleteItemCartRequest data) {

        ClientAccount clientAccount = clientAccountService.findById(id).orElse(null);
        if (clientAccount == null) {
            return;
        }
        Client client = clientAccount.getClient();

        Cart cart = client.getCart();
        Article article = articleService.findById(data.getArticle_id()).orElse(null);

        if (cart == null || article == null) {
            return;
        }

        List <Article> articles = articleService.getCartArticles(id);

        if (articles.isEmpty()) {
            return;
        }

        articles.remove(article);
        articleService.removeFromCart(article.getId(), cart);
        cart.setArticles(articles);
        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Order validateCart(Long id, ValidateCartRequest data) {
        ClientAccount clientAccount = clientAccountService.findById(id).orElse(null);
        if (clientAccount == null) {
            return null;
        }
        Client client = clientAccount.getClient();
        Long cartId = client.getCart().getId();
        List <Article> articles = articleService.getCartArticles(cartId);

        if (articles.isEmpty()) {
            return null;
        }

        Order order = orderService.add(articles);

        Payment payment = paymentService.add(data);

        if (payment == null || order == null) {
            return null;
        }

        order = orderService.setClient(order.getId(), client);
        order = orderService.validateOrder(order.getId(), payment);
        paymentService.setOrder(payment.getId(), order);

        for (Article article : articles) {
            articleService.setInOrder(article.getId(), order);
        }

        return order;
    }

    @Override
    public Long count() {
        return cartRepository.count();
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
