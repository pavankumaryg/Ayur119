package com.anarghya.aayurvedaapp.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anarghya.aayurvedaapp.client.FeignClientCustomer;
import com.anarghya.aayurvedaapp.client.FeignClientMedicine;
import com.anarghya.aayurvedaapp.entity.CartModuleEntity;
import com.anarghya.aayurvedaapp.entity.CustomerModule;
import com.anarghya.aayurvedaapp.entity.MedicineModule;
import com.anarghya.aayurvedaapp.exception.CartNotFoundException;
import com.anarghya.aayurvedaapp.repository.CartModuleRepository;
import com.anarghya.aayurvedaapp.repository.MedicineModuleRepository;

@Service
public class CartModuleServiceImpl implements CartModuleService {

	@Autowired
	private CartModuleRepository cartRepo;
	@Autowired
	private MedicineModuleRepository medicineRepo;
	@Autowired
	private FeignClientMedicine feignMedicine;
	@Autowired
	private FeignClientCustomer feignCustomer;

	@Override
	public CartModuleEntity createCart(Long customerId) {
		System.out.println("Customer Id in service "+customerId);

		CartModuleEntity status = cartRepo.findByCustomerId(customerId);
		if (status != null) {
			System.out.println(status.toString());

			System.out.println("Enter into if Condition and customer is Present in DataBase");
			CartModuleEntity cart = cartRepo.findByCustomerId(customerId);
			return cart;
		} else {
			System.out.println("Entered into else Condition in createCart Method");
			
			CustomerModule customerReq = feignCustomer.viewCustomer(customerId);
			System.out.println("Customer Data"+customerReq.getCustomerId());
//			cartEntity.setCustomerId(customerReq.getCustomerId());
			CustomerModule customerModule = new CustomerModule();
			CartModuleEntity cart = new CartModuleEntity();
			customerModule.setCustomerId(customerReq.getCustomerId());
			customerModule.setCustomerName(customerReq.getCustomerName());
			customerModule.setEmailId(customerReq.getEmailId());
			customerModule.setMobileNumber(customerReq.getMobileNumber());
			cart.setCustomer(customerModule);
			cartRepo.save(cart);
			System.out.println("Data saved into cart Table");
			return cart;
		}

	}

	@Override
	public List<CartModuleEntity> showAll() {
		List<CartModuleEntity> cartList = new ArrayList<CartModuleEntity>();
		List<CartModuleEntity> cartEntity = cartRepo.findAll();
		if (!cartEntity.isEmpty()) {
			cartEntity.forEach(c -> cartList.add(c));
			return cartList;
		}
		return null;
	}

	@Override
	public CartModuleEntity deleteMedicine(Integer cartId, Long medicineId) throws CartNotFoundException {
		Optional<CartModuleEntity> cartStatus = cartRepo.findById(cartId);
		if (cartStatus.isPresent()) {
			Optional<MedicineModule> medicineStatus = medicineRepo.findById(medicineId);

			if (medicineStatus.isPresent()) {
				medicineRepo.deleteById(medicineId);
				CartModuleEntity cartModuleEntity = cartRepo.findById(cartId).get();
				return cartModuleEntity;
			} else {
				return cartStatus.orElseThrow(() -> new CartNotFoundException("Medicine  Not Found"));
			}
		} else {

			return cartStatus.orElseThrow(() -> new CartNotFoundException("Cart Details Not Found"));
		}
	}

	@Override
	public CartModuleEntity deleteMedicines(Integer cartId) throws CartNotFoundException {
		Double totalCost = 0.0;
		Integer totalQuantity = 0;
		Optional<CartModuleEntity> cartStatus = cartRepo.findById(cartId);
		if (cartStatus.isPresent()) {
			medicineRepo.deleteBycartId(cartId);
			CartModuleEntity cartModuleEntity = cartStatus.get();
			cartModuleEntity.setCost(totalCost);
			cartModuleEntity.setQuantity(totalQuantity);
			cartRepo.save(cartModuleEntity);
			return cartStatus
					.orElseThrow(() -> new CartNotFoundException("All medicines from your cart has been deleted."));
		} else {
			return cartStatus
					.orElseThrow(() -> new CartNotFoundException("cartId Which you are trying to delete is not found"));
		}
	}

	@Override
	public CartModuleEntity addToCart(Long customerId, Long medicineId) throws CartNotFoundException {
		Double totalCost = 0.0;
		Double cost = 0.0;
		Integer totalQuantity = 0;
		Integer quantity1 = 0;
		Double doubleValue = 0.0;
		Integer quantity=1;
		CartModuleEntity cartEntity = new CartModuleEntity();
		List<MedicineModule> medicines = new ArrayList<>();
		MedicineModule medicineEntity = new MedicineModule();

		CartModuleEntity cartDetails = cartRepo.findByCustomerId(customerId);

		MedicineModule medicine = feignMedicine.getMedicineById(medicineId).getBody();

		Integer cartId = cartDetails.getCartId();
		Optional<CartModuleEntity> cartStatus = cartRepo.findById(cartId);
		CartModuleEntity cartModuleEntity = cartStatus.get();
		medicineEntity.setId(medicine.getId());
		medicineEntity.setName(medicine.getName());
		medicineEntity.setCompany(medicine.getCompany());
		medicineEntity.setCost(medicine.getCost());
		medicineEntity.setMfdDate(medicine.getMfdDate());
		medicineEntity.setExpiryDate(medicine.getExpiryDate());
		medicineEntity.setQuantity(quantity);
		medicineEntity.setBatchCode(medicine.getBatchCode());
		medicineEntity.setCategory(medicine.getCategory());
		medicineEntity.setDescription(medicine.getDescription());
		medicineEntity.setFormula(medicine.getFormula());
			System.out.println("Entering into add to cart ");
			medicines.add(medicineEntity);
			cartEntity.setMedicines(medicines);
//			cartEntity.getCartId();
			medicineEntity.setCart(cartModuleEntity);
//			cartRepo.save(cartEntity);
			medicineRepo.save(medicineEntity);
			List<MedicineModule> medicines2 = cartModuleEntity.getMedicines();
			for (MedicineModule medicineData : medicines2) {
				cost = (double) medicineData.getCost();
				quantity1 = medicineData.getQuantity();
				System.out.println(cost);
				doubleValue = (double) quantity1;
				totalQuantity += medicineData.getQuantity();
				System.out.println();
				totalCost += cost * doubleValue;
				System.out.println("Inside For Loop " + totalCost);
			}
			cartModuleEntity.setCost(totalCost);
			cartModuleEntity.setQuantity(totalQuantity);
			cartRepo.save(cartModuleEntity);
			
			return cartModuleEntity;
//		} else {
//
//			return cartStatus.orElseThrow(() -> new CartNotFoundException("cart Details  Not Found"));
//		}
		
			}

	@Override
	public List<MedicineModule> viewMedicines(Integer cartId) {

		List<MedicineModule> medicines = new ArrayList<MedicineModule>();

		Optional<CartModuleEntity> findById = cartRepo.findById(cartId);

		if (findById.isPresent()) {
			CartModuleEntity cartModuleEntity = findById.get();
			List<MedicineModule> allMedicines = cartModuleEntity.getMedicines();
			allMedicines.forEach(m -> medicines.add(m));
			return medicines;

		}
		// findById.orElseThrow(() -> new MedicineIdNotFoundException("cart Details Not
		// Found"));;
		return null;
	}

	@Override
	public CartModuleEntity viewCartInfo(Integer cartId) throws CartNotFoundException {
		Double totalCost = 0.0;
		Double cost = 0.0;
		Integer totalQuantity = 0;
		Integer quantity = 0;
		Double doubleValue = 0.0;
		Optional<CartModuleEntity> cartInfo = cartRepo.findById(cartId);
//		MedicineModule medicineEntity=new MedicineModule();
//
		if (cartInfo.isPresent()) {
			CartModuleEntity cartModuleEntity = cartInfo.get();
			List<MedicineModule> medicines = cartModuleEntity.getMedicines();
			for (MedicineModule medicine : medicines) {
				cost = (double) medicine.getCost();
				quantity = medicine.getQuantity();
				System.out.println(cost);
				doubleValue = (double) quantity;
				totalQuantity += medicine.getQuantity();
				System.out.println();
				totalCost += cost * doubleValue;
				System.out.println("Inside For Loop " + totalCost);
			}

			System.out.println("Inside View Cart Cost " + totalCost);
			cartModuleEntity.setCost(totalCost);
			cartModuleEntity.setQuantity(totalQuantity);
			cartRepo.save(cartModuleEntity);

			return cartModuleEntity;

		} else {
			return cartInfo.orElseThrow(() -> new CartNotFoundException("cart Details  Not Found"));
		}

	}

	@Override
	public CartModuleEntity addMedicine(Integer cartId, Long medicineId, Integer quantity)
			throws CartNotFoundException {
		Double medicineCost1 = 0.0;
		Double totalCost = 0.0;
//		Double totalQuantity = 0.0;
		List<MedicineModule> medicines = new ArrayList<>();
		Optional<CartModuleEntity> cartStatus = cartRepo.findById(cartId);
		if (cartStatus.isPresent()) {
			Optional<MedicineModule> medicineStatus = medicineRepo.findById(medicineId);
			if (medicineStatus.isPresent()) {
				MedicineModule medicineModule = medicineStatus.get();
				medicineModule.setQuantity(quantity);
				medicines.add(medicineModule);
				CartModuleEntity cartEntity = cartStatus.get();
				Double cost = cartEntity.getCost();
				  float medicineCost = medicineModule.getCost();
				Double doubleValue = (double) quantity;
				medicineCost1 = medicineCost * doubleValue;
				totalCost = cost + medicineCost1;
				System.out.println("Total cost of Add Medicine " + medicineCost1);
				System.out.println("Total cost of cart " + totalCost);
				cartEntity.setMedicines(medicines);

				cartRepo.save(cartEntity);
				CartModuleEntity cartModuleEntity = cartRepo.findById(cartId).get();
				return cartModuleEntity;
			} else {
				return cartStatus.orElseThrow(() -> new CartNotFoundException("Medicine Details  Not Found"));
			}

		} else {
			return cartStatus.orElseThrow(() -> new CartNotFoundException("cart Details  Not Found"));
		}

	}

}
