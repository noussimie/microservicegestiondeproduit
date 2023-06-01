//declaration des operations à implémenter (interface)
package com.ecommerce.microcommerce.web.dao;

import com.ecommerce.microcommerce.web.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();//affiche la liste complète de tous les produits
    Product findById(int id);//affiche un produit par son id
    Product save(Product product); //ajoute un produit
}
