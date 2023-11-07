package com.anarghya.aayurvedaapp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anarghya.aayurvedaapp.entity.CartModuleEntity;
import com.anarghya.aayurvedaapp.entity.MedicineModule;
import com.anarghya.aayurvedaapp.exception.CartNotFoundException;
import com.anarghya.aayurvedaapp.service.CartModuleService;

/*Rest Controller Class for Cart_Module Controller
Author : Pramod.Goskula
*/

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1" )
@RestController
public class CartModuleController {

	@Autowired
	private CartModuleService cartService;

	/****************************
	 * Method: createCart Description: It is used to create cart for new user into
	 * the CART_MODULE table
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with cartModuleEntity after
	 *          processing the cart creation.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URL expression. annotation in Spring MVC is used to capture and
	 *               bind a value from a URL path segment to a method parameter in a
	 *               controller method.
	 * @PathVariable: an annotation in Spring Framework that is used to bind a URI
	 *                template variable to a method parameter in a RESTful APIs,
	 *                it's common to have parts of the URL path that act as
	 *                placeholder for dynamic values.
	 * 
	 *                Author : Pramod.Goskula
	 * 
	 ****************************/

	@PostMapping("/cart/{customerId}")
	public ResponseEntity<CartModuleEntity> createCart(@PathVariable Long customerId) {
		System.out.println("Customer Id in Controller"+customerId);
		System.out.println("CustomerId " + customerId);
		System.out.println("Entering into service class");
		CartModuleEntity cart = cartService.createCart(customerId);
		System.out.println("out from service");

		System.out.println("Service return cart Object" + cart.toString());
		return new ResponseEntity<CartModuleEntity>(cart, HttpStatus.CREATED);
	}

	/****************************
	 * Method: showAll Description: It is used to fetch all cart details for
	 * existing user from the CART_MODULE table
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with cartModuleEntity.
	 * @GetMapping: It is used to handle the HTTP get requests matched with given
	 *              URI expression.
	 * 
	 * 
	 *              Author : Pramod.Goskula
	 * 
	 ****************************/

	@GetMapping("/cart")
	public ResponseEntity<List<CartModuleEntity>> showAll() {

		List<CartModuleEntity> CartEntity = cartService.showAll();
		return new ResponseEntity<List<CartModuleEntity>>(CartEntity, HttpStatus.ACCEPTED);
	}

	/****************************
	 * Method: deleteMedicine Description: It is used to delete specific medicine
	 * record for existing cart user from the CART_MODULE table
	 * @throws CartNotFoundException 
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with cartModuleEntity.
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with
	 *                 given URI expression.
	 * @PathVariable: an annotation in Spring Framework that is used to bind a URI
	 *                template variable to a method parameter in a RESTful APIs,
	 *                it's common to have parts of the URL path that act as
	 *                placeholder for dynamic values.
	 * 
	 *                Author : Pramod.Goskula
	 * 
	 ****************************/

	@DeleteMapping("/cart/{cartId}/{medicineId}")
	public ResponseEntity<CartModuleEntity> deleteMedicine(@PathVariable Integer cartId,
			@PathVariable Long medicineId) throws CartNotFoundException {
		CartModuleEntity deleteMedicine = cartService.deleteMedicine(cartId, medicineId);
		return new ResponseEntity<CartModuleEntity>(deleteMedicine, HttpStatus.OK);
//		if (deleteMedicine != null) {
//			return new ResponseEntity<CartModuleEntity>(deleteMedicine, HttpStatus.OK);
//
//		} else {
//			return new ResponseEntity<CartModuleEntity>(HttpStatus.NOT_FOUND);
//		}
	}

	/****************************
	 * Method: deleteMedicines Description: It is used to delete entire cart
	 * medicines record for existing cart user from the CART_MODULE table
	 * @throws CartNotFoundException 
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with String.
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with
	 *                 given URI expression.
	 * @PathVariable: an annotation in Spring Framework that is used to bind a URI
	 *                template variable to a method parameter in a RESTful APIs,
	 *                it's common to have parts of the URL path that act as
	 *                placeholder for dynamic values.
	 * 
	 *                Author : Pramod.Goskula
	 * 
	 ****************************/
	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<CartModuleEntity> deleteMedicines(@PathVariable Integer cartId) throws CartNotFoundException {
		 CartModuleEntity cart = cartService.deleteMedicines(cartId);
	
			return new ResponseEntity<CartModuleEntity>(cart, HttpStatus.OK);
		

	}

	/****************************
	 * Method: addToCart Description: It is used to add medicine into cart for new
	 * or existing user into the CART_MODULE table
	 * @throws CartNotFoundException 
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with cartModuleEntity
	 * 
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URL expression. annotation in Spring MVC is used to capture and
	 *               bind a value from a URL path segment to a method parameter in a
	 *               controller method.
	 * @PathVariable: an annotation in Spring Framework that is used to bind a URI
	 *                template variable to a method parameter in a RESTful APIs,
	 *                it's common to have parts of the URL path that act as
	 *                placeholder for dynamic values.
	 * 
	 *                Author : Pramod.Goskula
	 * 
	 ****************************/

	@PostMapping("/cart/addtocart/{customerId}/{medicineId}")
	public ResponseEntity<CartModuleEntity> addToCart(@PathVariable Long customerId,
			@PathVariable Long medicineId) throws CartNotFoundException {
		CartModuleEntity addToCart = cartService.addToCart(customerId, medicineId);
		return new ResponseEntity<CartModuleEntity>(addToCart,HttpStatus.OK);
//		if (addToCart.equals(null)) {
//			return new ResponseEntity<CartModuleEntity>(HttpStatus.NOT_FOUND);
//		} else {
//			return new ResponseEntity<CartModuleEntity>(addToCart, HttpStatus.OK);
//		}

	}

	/****************************
	 * Method: viewMedicines Description: It is used to fetch all medicines details for
	 * existing user from the CART_MODULE table
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with MedicineModule.
	 *          
	 * @GetMapping: It is used to handle the HTTP get requests matched with given
	 *              URI expression.
	 * 
	 * 
	 *              Author : Pramod.Goskula
	 * 
	 ****************************/
	
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<List<MedicineModule>> viewMedicines(@PathVariable Integer cartId) {
		List<MedicineModule> medicines = cartService.viewMedicines(cartId);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
//		if (medicines != null) {
//			return new ResponseEntity<>(medicines, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(medicines, HttpStatus.NO_CONTENT);
//		}
	}
	

	/****************************
	 * Method: viewCartInfo Description: It is used to fetch cart details for
	 * existing user from the CART_MODULE table
	 * @throws CartNotFoundException 
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with MedicineModule.
	 *          
	 * @GetMapping: It is used to handle the HTTP get requests matched with given
	 *              URI expression.
	 * 
	 * 
	 *              Author : Pramod.Goskula
	 * 
	 ****************************/

	@GetMapping("/cart/cartInfo/{cartId}")
	public ResponseEntity<CartModuleEntity> viewCartInfo(@PathVariable Integer cartId) throws CartNotFoundException {

		CartModuleEntity viewCartInfo = cartService.viewCartInfo(cartId);
		return new ResponseEntity<CartModuleEntity>(viewCartInfo, HttpStatus.OK);
	}
	
	/****************************
	 * Method: addMedicine Description: It is used to add medicine quantity into cart for new
	 * or existing user into the CART_MODULE table
	 * @throws CartNotFoundException 
	 * 
	 * @returns return statement here is used to send an HTTP response with a
	 *          specific status code and response body with cartModuleEntity
	 * 
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URL expression. annotation in Spring MVC is used to capture and
	 *               bind a value from a URL path segment to a method parameter in a
	 *               controller method.
	 * @PathVariable: an annotation in Spring Framework that is used to bind a URI
	 *                template variable to a method parameter in a RESTful APIs,
	 *                it's common to have parts of the URL path that act as
	 *                placeholder for dynamic values.
	 * 
	 *                Author : Pramod.Goskula
	 * 
	 ****************************/

	@PostMapping("/cart/{cartId}/{medicineId}/{quantity}")
	public ResponseEntity<CartModuleEntity> addMedicine(@PathVariable Integer cartId, @PathVariable Long medicineId,
			@PathVariable Integer quantity) throws CartNotFoundException {

		System.out.println("Enter Into add Medicine Controller Method cartId: "+cartId+"medicineId: "+medicineId+"quantity : "+quantity );
		
		CartModuleEntity addMedicine = cartService.addMedicine(cartId, medicineId, quantity);
		return new ResponseEntity<>(addMedicine, HttpStatus.ACCEPTED);
//		if (addMedicine != null) {
//			return new ResponseEntity<>(addMedicine, HttpStatus.ACCEPTED);
//		} else {
//			return new ResponseEntity<>(addMedicine, HttpStatus.BAD_REQUEST);
//		}

	}

	
}
