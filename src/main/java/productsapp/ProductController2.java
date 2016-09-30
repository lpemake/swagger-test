package productsapp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

import models.*;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController2 {

	private static final ProductRepository repository = new ProductRepositoryImpl();
	
	public ProductController2() {
				
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get all products", notes = "Returns a list of products")

    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Product.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not Found")}) 
	
	public Iterable<Product> get()
	{
		return repository.getAll();
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
	
	@ApiOperation(value = "Get product by id", notes = "Returns a product with given id if found",
			response = Product.class)

    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 404, message = "Not Found")}) 	
    public Product get(
    		@ApiParam(value = "id of the product", required = true) @PathVariable int id) {
		Product p = repository.getById(id);
		if (p == null)
		{
			throw new ProductNotFoundException("id = " + id);
		}
        return p;
    }	
	
	@RequestMapping(value = "/product", params = "name" ,method = RequestMethod.GET, produces = "application/json")
	
	@ApiOperation(value = "Get product by name", notes = "Returns a product with given name if found",
			response = Product.class)

    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 404, message = "Not Found")}) 	
    public Product get(
    		@ApiParam(value = "name of the product", required = true) @RequestParam(value="name") String name) {
		Product p = repository.getByName(name);
		if (p == null)
		{
			throw new ProductNotFoundException("name = " + name);
		}
        return p;
    }		
	
	
	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json")
	
	@ApiOperation(value = "Add product", notes = "Parameter type is body",
			response = Product.class)

    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK", response = Product.class),
            @ApiResponse(code = 404, message = "Not Found")}) 	
    public Product post(
    		@ApiParam(value = "product", required = true) Product product) {
		Product p = repository.add(product);
        return p;
    }		
	
	@RequestMapping(value = "/products", method = RequestMethod.DELETE, produces = "application/json")
	
	@ApiOperation(value = "Delete product of given index", notes = "Deletes by index, not id")

    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")}) 	 	
    public void delete(
    		@ApiParam(value = "index", required = true) @RequestParam(value="index") int index) {
		Product p = repository.removeAt(index);
		if (p == null)
		{
			throw new ProductNotFoundException("index = " + index);
		}
    }			
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String userId) {
		super("could not find '" + userId + "'.");
	}
}

