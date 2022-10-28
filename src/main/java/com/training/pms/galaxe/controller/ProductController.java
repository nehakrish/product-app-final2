package com.training.pms.galaxe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;
import com.training.pms.galaxe.service.ProductService;
import com.training.pms.galaxe.service.ProductServiceImpl;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	Product product;
	@Autowired
	ProductServiceImpl productServiceImpl;
	public ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping()					//http://localhost:9090/product/		-POST		-BODY (product) 102
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;
		int pId = product.getProductId();
		if(productService.isProductExists(pId)) {
			responseEntity = new ResponseEntity<String>("Product with product id :"+pId+" already exists", HttpStatus.CONFLICT);
		}
		else
		{
			String message = productService.saveProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
		
	
	
	@GetMapping					//http://localhost:9090/product
	
	public ResponseEntity<List<Product>> getProducts() {
		
		List<Product> products = productService.getProduct();
		System.out.println("Get the id");
		ResponseEntity<List<Product>> responseEntity;

		if(products.isEmpty()) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	
	@PutMapping()
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;
		int pId = product.getProductId();
		if(!productServiceImpl.isProductExists(pId)) {
			responseEntity = new ResponseEntity<String>("Product with product id :"+pId+" Doesn't Exists", HttpStatus.NOT_FOUND);
		}
		else
		{
			String message = productServiceImpl.updateProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;

      
		
	}

@DeleteMapping("{productId}") // localhost:9090/product/5
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) {
        ResponseEntity<String> responseEntity;
        if (productService.isProductExists(productId)) {
            String message = productService.deleteProduct(productId);
            responseEntity = new ResponseEntity<String>(message,
                    HttpStatus.OK);
        } else {
            String errorMessage = "No Such Product ID Exists";
            responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }


@GetMapping("{productId}") // localhost:9090/product/5
public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {
    ResponseEntity<Product> responseEntity;
    if (productService.isProductExists(productId)) {
        product = productService.getProduct(productId);
        responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK);
    } else {
        responseEntity = new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    return responseEntity;
}

@GetMapping("searchByProductName/{productName}") // url - localhost:9090/product/2435678
public ResponseEntity<List<Product>> getProductByName(@PathVariable("productName") String productName) {

	ResponseEntity<List<Product>> responseEntity;
	List<Product> products  = productService.searchProduct(productName);
	if(products.isEmpty()) {
		responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
	}
	else
	{
		responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	return responseEntity;
}

//
@GetMapping("searchByPriceRange/{min}/{max}") // url - localhost:9090/product/2435678
public ResponseEntity<List<Product>> getProductByPriceRange(@PathVariable("min") Integer min,@PathVariable("max") Integer max) {

	ResponseEntity<List<Product>> responseEntity;
	List<Product> products  = productService.searchProduct( min, max);
	if(products.isEmpty()) {
		responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
	}
	else
	{
		responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	return responseEntity;
}

//	@GetMapping			//https://    localhost:9090/product
//	public  List<Product> getProducts() {  
////		return (List<Product>) productDAO.findAll();
//		return null;
////		return "Getting all products";
//	}
//	@GetMapping("{productId}")    		//https://   localhost:9090/product/192
//	public String getProducts(@PathVariable("productId")Integer productId) {
//		return "Getting a single product with productId : "+productId;
//	}
	
	
//	@DeleteMapping("{productId}")    		//https://   localhost:9090/product/192 -DELETE
//	public String deleteProducts(@PathVariable("productId")Integer productId) {
//		return "deleting a single product with productId : "+productId;
//	}
	
//	@PostMapping()    		//https://   localhost:9090/product/192 -DELETE
//	public String saveProduct(@RequestBody Product product){
//		
//		return productService.saveProduct(product);
////		return "saving a single product with product details : "+product;
//	}
	
//	@PutMapping()    		//https://   localhost:9090/product/192 -DELETE
//	public String updateProduct(@RequestBody Product product){
//		return "updating a single product with product details : "+product;
//	}
	

}
