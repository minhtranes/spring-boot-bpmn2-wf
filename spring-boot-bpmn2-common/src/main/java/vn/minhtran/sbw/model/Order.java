package vn.minhtran.sbw.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private String customerName;

	private List<Dish> dishes = new ArrayList<>();

	private Long id;

	private String orderId;

	private boolean payed;

	public String getCustomerName() {
		return this.customerName;
	}

	public List<Dish> getDishes() {
		return this.dishes;
	}

	public Long getId() {
		return this.id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public boolean isPayed() {
		return this.payed;
	}

	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	public void setDishes(final List<Dish> dishes) {
		this.dishes = dishes;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}

	public void setPayed(final boolean payed) {
		this.payed = payed;
	}

}
