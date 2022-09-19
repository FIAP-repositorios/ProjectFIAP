package br.com.projectgro.config;

import br.com.projectgro.model.*;
import br.com.projectgro.model.enums.OrderStatus;
import br.com.projectgro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        Buyer b1 = new Buyer(null,  "Maria Brown", "mariabronwn123", "maria@gmail.com", "988888888", "123456", "0222222221", "Rua Dias", "01113");
        Buyer b2 = new Buyer(null, "Alex Green", "alexgrenn123", "alex@gmail.com", "977777777", "123456",  "019230921", "Rua Carlos", "09883");

        Seller s1 = new Seller(null,  "Brown Maria", "bronwzinha", "bronwwwn@gmail.com", "945627450", "1459864", "4894864865", "Rua Vinte", "02330001");
        Seller s2 = new Seller(null, "Green Alex", "grenn123alerx", "grenn@gmail.com", "925634567", "ftyikrfi7yt6kgf",  "4894864869", "Rua quatrp", "02334123");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, b1, s1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, b2, s2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELED, b1, s1);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        buyerRepository.saveAll(Arrays.asList(b1, b2));
        sellerRepository.saveAll(Arrays.asList(s1, s2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);
        Payment pay2 = new Payment(null, Instant.parse("2020-06-20T21:53:07Z"), o2);
        o2.setPayment(pay2);
        Payment pay3 = new Payment(null, Instant.parse("2022-06-20T21:53:07Z"), o3);
        o3.setPayment(pay3);
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
