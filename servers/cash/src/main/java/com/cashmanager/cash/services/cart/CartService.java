package com.cashmanager.cash.services.cart;

import com.cashmanager.cash.models.*;
import com.cashmanager.cash.payload.request.article.UpdateArticleRequest;
import com.cashmanager.cash.payload.request.cart.*;
import com.cashmanager.cash.services.article.IArticleService;
import com.cashmanager.cash.services.clientaccount.IClientAccountService;
//import com.cashmanager.cash.services.order.IOrderRepository;
//import com.cashmanager.cash.services.payment.IPaymentRepository;
//import com.cashmanager.cash.services.client.IClientRepository;
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

//    @Autowired
//    private IOrderRepository orderRepository;
//    @Autowired
//    private IPaymentRepository paymentRepository;
//    @Autowired
//    private IClientRepository clientRepository;

    private IArticleService articleService;

    private IClientAccountService clientAccountService;


    @Override
    public Cart create() {
        Cart cart = new Cart();
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

        List <Article> articles = cart.getArticles();

        if (article == null) {
            return null;
        }

        articles.add(article);
        article.setQuantity(article.getQuantity() - 1);
        articleService.update(article.getId(), new UpdateArticleRequest(article.getName(), article.getPrice(), article.getQuantity()));
        cart.setArticles(articles);
//        client.setCart(cart);
//        clientRepository.save(client);
//        clientAccountService.update(clientAccount.getId(), new UpdateClientRequest());

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

        List <Article> articles = cart.getArticles();

//        articles.remove(article);
//        article.setQuantity(article.getQuantity() + 1);
//        articleService.update(article.getId(), new UpdateArticleRequest(article.getName(), article.getPrice(), article.getQuantity()));
//        cart.setArticles(articles);
//        client.setCart(cart);
//        clientAccount.setClient(client);
//        clientRepository.save(client);
//        clientAccountService.update(clientAccount.getId(), new UpdateClientRequest());
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

        Cart cart = client.getCart();

        List<Article> articles = cart.getArticles();

        if (articles.isEmpty()) {
            return null;
        }

        Long totalPrice = 0L;

        for (Article article : articles) {
            totalPrice += article.getPrice();
        }

        Order order = new Order(totalPrice);
//
//        order.setClient(client);
//        order.setArticles(articles);
//        TransactionType type = TransactionType.valueOf(data.getTypePayment());
//        Payment payment = new Payment(type, true);
//        payment.setOrder(order);
//
//        order.setPayment(payment);
//        client.getOrders().add(order);
//        orderRepository.save(order);
//        paymentRepository.save(payment);
//        clientAccount.setClient(client);
//        clientRepository.save(client);
//        clientAccountService.update(clientAccount.getId(), new UpdateClientRequest());
//
//        cart.setArticles(null);
//        cartRepository.save(cart);

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
