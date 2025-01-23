package com.eric.larroque.tutorialcache.application;

import com.eric.larroque.tutorialcache.core.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
// Serve caso queira tornar cacheable no mesmo registro todos os métodos da classe
@CacheConfig(cacheNames = "cacheproductbyid")
public class ProductService {

    private static Map<Long, Product> productsMap = createProductsMap();


    /* É possível adicionar o atributo cacheNames e a key dentro da annotation
    * o cacheNames refere-se ao nome desse dado em cache, seria o mesmo que eu fizesse
    * @Cacheable(cacheNames="cacheproductbyid")
    *
    * Já o atributo key refere-se à chave que estará mapeada no meu cache. Por exemplo, poderia colocar como:
    * @Cacheable(cacheNames="cacheproductbyid", key = "#id")
    *
    * Caso eu retornasse um Dto do produto e quisesse mapear o id dele internamente,
    * poderia mapear da seguinte forma:
    *
    * @Cacheable(cacheNames="cacheproductbyid", key = "#ProductDto.id")
    *
    * ## Condition
    *
    * Posso também criar condições para que esse dado seja persistido em cache, tal como:
    *
    * @Cacheable(cacheNames="cacheproductbyid", key = "#id", condition = #id > 5)
    */
    @Cacheable("cacheproductbyid")
    public Product getById(Long id) {
        System.out.println("searching for products");
        simulateLantency();
        return productsMap.get(id);
    }

    private void simulateLantency(){
        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /*
    * Responsável por atualizar o cache persistido pelo Cacheable
    * */
    @CachePut(cacheNames = "cacheproductbyid", key = "#id")
    public Product update(Long id, Product newProduct) {
        productsMap.put(id, newProduct);
        return newProduct;
    }

    /*
    * Responsável por deletar a informação no cache
    *
    * O atributo allEntries serve para dizer se queremos deletar todas as informações
    * relacionadas a esse cache
    * */
    @CacheEvict(cacheNames = "cacheproductbyid", allEntries = true)
    public void deleteProduct(Long id) {
        productsMap.remove(id);
    }

    /*
    * Annotation que serve para especificar multiplas annotations, tais como o
    * @CachePut e o @CacheEvict vistos acima
    *
    * @ Caching(evict = {@CacheEvict(cacheNames = "cacheproductbyid", allEntries = true)})
     */

    private static Map<Long, Product> createProductsMap() {
        Map<Long, Product> products = new HashMap<>();
        products.put(1L, new Product(1L, "Notebook", "Macbook Pro"));
        products.put(2L, new Product(2L, "Notebook", "XPS"));
        products.put(3L, new Product(3L, "Notebook", "Alienware"));
        products.put(4L, new Product(4L, "Notebook", "Thinkpad"));
        products.put(5L, new Product(5L, "Notebook", "Zenbook"));
        return products;
    }

}
