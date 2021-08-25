package it.amora.webfluxtest.controller;

import it.amora.webfluxtest.model.Product;
import it.amora.webfluxtest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    private static final Logger log = Logger.getLogger(ProductController.class.getName());

    private final ProductRepository productRepository;

    @GetMapping("")
    public Flux<Product> getAll() {
        log.info("ProductController: getAll");
        return productRepository.findAll();
    }

    @GetMapping(value = "/getAllTimed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getAllTimed() {
        log.info("ProductController: getAllTimed");
        return productRepository.findAll().delayElements(Duration.ofMillis(500));
    }

    @PostMapping("/save")
    public void save(@RequestBody Product product) {
        log.info("ProductController: save");
        productRepository.save(product);
    }

    @PostMapping("/saveSub")
    public Mono<Product> saveSub(@RequestBody Product product) {
        log.info("ProductController: saveSub");
        return productRepository.save(product);
    }

    @PostMapping("/saveMassive")
    public Flux<Product> saveMassive(@RequestBody List<Product> productList) {
        return productRepository.saveAll(productList);
    }
}
