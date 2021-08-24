package it.amora.webfluxtest.controller;

import it.amora.webfluxtest.model.Product;
import it.amora.webfluxtest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("")
    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Product product) {
        productRepository.save(product);
    }

    @PostMapping("/saveSub")
    public Mono<Product> saveSub(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
