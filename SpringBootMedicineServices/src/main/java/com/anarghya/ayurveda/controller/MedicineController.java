package com.anarghya.ayurveda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anarghya.ayurveda.model.Medicine;
import com.anarghya.ayurveda.services.MedicineServicesImplementation;

/****************************
 * @CrossOrigin: enables cross-origin resource sharing only for this specific
 *               method
 ****************************/
@CrossOrigin(origins = "*")
/****************************
 * @RestController: to simplify the creation of RESTful web services
 ****************************/
@RestController
/****************************
 * @RequestMapping: which is used to map HTTP requests to handler methods of MVC
 *                  and REST controllers
 ****************************/
@RequestMapping("/api")
public class MedicineController {

	/****************************
	 * @Autowired: It allows Spring to resolve and inject collaborating beans into
	 *             our bean.
	 ****************************/
	@Autowired
	MedicineServicesImplementation servicesImplementation;

	/****************************
	 * Method: addToCart: It is used to add the medicine. But here it just return
	 * the message.
	 * 
	 * @PostMapping: It is used to map HTTP POST requests onto specific handler
	 *               methods.
	 * @PathVariable: it is used to retrieve data from the URL path.
	 ****************************/
	@PostMapping("/addToCart/{id}")
	public ResponseEntity<String> addToCart(@PathVariable Long id) {

		return ResponseEntity.ok("Medicine added to cart successfully");
	}

	/****************************
	 * Method: getAllMedicines: It is used to get all the medicines list
	 * 
	 * @returns It returns the list of medicines.
	 * @GetMapping: It is used for mapping HTTP GET requests onto specific handler
	 *              methods.
	 ****************************/
	@GetMapping("/medicines")
	public List<Medicine> getAllMedicines() {
		return servicesImplementation.getAllMedicines();
	}

	/****************************
	 * Method: getMedicineById: It is used to get the specific medicines by using
	 * id.
	 * 
	 * @returns It returns the specific medicine.
	 * @GetMapping: It is used for mapping HTTP GET requests onto specific handler
	 *              methods.
	 * 
	 * @PathVariable: it is used to retrieve data from the URL path.
	 ****************************/
	@GetMapping("/medicines/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
		Medicine medicine = servicesImplementation.getMedicineById(id);
		if (medicine != null) {
			return ResponseEntity.ok(medicine);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/****************************
	 * Method: addMedicine : It is used to do add the Medicine Details.
	 * 
	 * @returns It returns the medicine details.
	 * @PostMapping: It is used to map HTTP POST requests onto specific handler
	 *               methods.
	 * @RequestBody: It used to maps the HttpRequest body to a transfer or domain
	 *               object, enabling automatic deserialization of the inbound
	 *               HttpRequest body onto a Java object.
	 ****************************/
	@PostMapping("/medicines")
	public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine) {
		return servicesImplementation.addMedicine(medicine);
	}

	/****************************
	 * Method: updateMedicine: It is used to update the medicine details based on
	 * id. if email is already present it will return error message
	 * 
	 * @returns It returns the message like successful or not
	 * @PutMapping: It is used for mapping HTTP PUT requests onto specific handler
	 *              methods.
	 * @PathVariable: it is used to retrieve data from the URL path.
	 * 
	 * @RequestBody: It used to maps the HttpRequest body to a transfer or domain
	 *               object, enabling automatic deserialization of the inbound
	 *               HttpRequest body onto a Java object.
	 ****************************/
	@PutMapping("/medicines/{id}")
	public Medicine updateMedicine(@PathVariable Long id, @RequestBody Medicine updatedMedicine) {
		return servicesImplementation.updateMedicine(id, updatedMedicine);
	}

	/****************************
	 * Method: deleteMedicine: It is used to delete the specific medicine based on
	 * id.
	 * 
	 * @DeleteMapping: It is used to handle the HTTP POST requests matched with
	 *                 given URI expression.
	 * @PathVariable: it is used to retrieve data from the URL path.
	 ****************************/
	@DeleteMapping("/medicines/{id}")
	public void deleteMedicine(@PathVariable Long id) {
		servicesImplementation.deleteMedicine(id);
	}


}
