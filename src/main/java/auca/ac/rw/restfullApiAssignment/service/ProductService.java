package auca.ac.rw.restfullApiAssignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.restfullApiAssignment.modal.ecommerce.Product;
import auca.ac.rw.restfullApiAssignment.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public String addNewProduct(Product product) {

    Optional<Product> existProduct = productRepository.findById(product.getId());

    if (existProduct.isPresent()) {
      return "Product with id " + product.getId() + " already exists";
    } else {
      productRepository.save(product);
      return "Product added successfully";
    }
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    Optional<Product> product = productRepository.findById(id);

    return product.orElse(null);
  }

  public String updateProduct(Long id, Product updatedProduct) {

    Optional<Product> existingProduct = productRepository.findById(id);

    if (existingProduct.isPresent()) {

      Product product = existingProduct.get();

      product.setName(updatedProduct.getName());
      product.setDescription(updatedProduct.getDescription());
      product.setPrice(updatedProduct.getPrice());
      product.setCategory(updatedProduct.getCategory());
      product.setBrand(updatedProduct.getBrand());
      product.setStockQuantity(updatedProduct.getStockQuantity());

      productRepository.save(product);

      return "Product updated successfully";

    } else {
      return "Product not found";
    }
  }

  public String deleteProduct(Long id) {

    Optional<Product> product = productRepository.findById(id);

    if (product.isPresent()) {
      productRepository.deleteById(id);
      return "Product deleted successfully";
    } else {
      return "Product not found";
    }
  }

  public List<Product> searchByCategory(String categoryName) {

    List<Product> products = productRepository.findByCategory(categoryName);

    if (products != null && !products.isEmpty()) {
      return products;
    } else {
      return null;
    }
  }

  public List<Product> searchByBrand(String brand) {

    List<Product> products = productRepository.findByBrand(brand);

    if (products != null && !products.isEmpty()) {
      return products;
    } else {
      return null;
    }
  }
}
