//traitement de requete et methode defini

package com.ecommerce.microcommerce.web.controller;


import com.ecommerce.microcommerce.web.dao.ProductDao;
import com.ecommerce.microcommerce.web.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController

public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao=productDao;
    }

    /*@GetMapping("/Produits")
    public List<Product> listeProduits(){
        return productDao.findAll();

    }*/

//afficher la liste des produits par filtre dynamique.
    @GetMapping("/Produits")
    public MappingJacksonValue listeProduits(){
        List<Product> products = productDao.findAll();
        SimpleBeanPropertyFilter monFiltre =
                SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listDeNosFiltres = new
                SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue produitsFitres = new MappingJacksonValue(products);
        produitsFitres.setFilters(listDeNosFiltres);
        return produitsFitres;

    }

    @GetMapping("/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id){

        return productDao.findById(id);
    }
//ceci envoie un code 200ok une fois executée
   /* @PostMapping("/Produits")
    public void ajouterProduit(@RequestBody Product product){
        productDao.save(product);
    }*/

    //afficher le bon code http 201 created

    @PostMapping("/Produits")
    public ResponseEntity<Product> ajouterProduit(@RequestBody Product product){
        Product productAdded = productDao.save(product);
        if (Objects.isNull(productAdded)){
           return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
