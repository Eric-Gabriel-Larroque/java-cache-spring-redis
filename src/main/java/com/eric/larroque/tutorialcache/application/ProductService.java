package com.eric.larroque.tutorialcache.application;

import com.eric.larroque.tutorialcache.core.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    @Cacheable("cacheproductbyid")
    public Product getById(Long id) {
        Map<Long, Product> productsMap = createProductsMap();
        System.out.println("searching for products");
        simulateLantency();
        return productsMap.get(id);
    }

    private Map<Long, Product> createProductsMap() {
        Map<Long, Product> products = new HashMap<>();
        products.put(1L, new Product(1L, "Notebook", "Macbook Pro"));
        products.put(2L, new Product(2L, "Notebook", "XPS"));
        products.put(3L, new Product(3L, "Notebook", "Alienware"));
        products.put(4L, new Product(4L, "Notebook", "Thinkpad"));
        products.put(5L, new Product(5L, "Notebook", "Zenbook"));
        return products;
    }

    private void simulateLantency(){
        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
