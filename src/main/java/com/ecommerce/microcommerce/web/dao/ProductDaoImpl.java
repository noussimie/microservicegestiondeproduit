//cette classe implemente l'interface et communique avec la base de donnée pour
//recuperer les produits ou en ajouter.

package com.ecommerce.microcommerce.web.dao;

import com.ecommerce.microcommerce.web.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // indique spring que cette classe gère les données.

public class ProductDaoImpl implements ProductDao{
// nous définissons ci dessous un tableau de Products  dans lequel nous ajoutons 4
// produits statiques
    public static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1, "Ordinateur portable", 350, 120));
        products.add(new Product(2, "Aspirateur Robot", 500,200));
        products.add(new Product(3, "Table de Ping Pong", 750,400));
        products.add(new Product(4, "téléphone tecno", 9000,10000));
    }

    @Override
    public List<Product> findAll(){
        return products;
    }
    @Override
    public Product findById(int id){
        for (Product product:products){
           if (product.getId()==id){
               return product;
           }
        }
        return null;

    }

    @Override
    public Product save(Product product){
        products.add(product);
        return product;
    }
}
