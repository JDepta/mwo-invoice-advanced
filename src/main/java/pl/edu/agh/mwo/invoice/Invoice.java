package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	private int number;
	private static int nextNumber = 1;//jest static wiec pole nalezy nie do instancji ale do klasy, wiec xaxchowa swoja wartosc pomeidzy kolejnymi instancjami
	
	public Invoice() {
		this.number = nextNumber;
		nextNumber += 1;
	}
	
	public void addProduct(Product product) {
		int n;
		if (products.containsKey(product)) {
			n += 1;//pobrac jego ilossc i do niej dodac ta ilosc, ktora dodajemy
			addProduct(product, n);
		}
		else
			addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		products.put(product, quantity);
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}

	public Integer getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	public String printedVersion() {
		// TODO Auto-generated method stub
		String printed = String.valueOf(number);
		for (Product product : products.keySet()) {
			printed += "\n" + product.getName();
			printed += ", " + product.getClass().getName();
			printed += ", " + products.get(product);
		}
		printed += "\n" + "LICZBA PRODUKTOW: " + products.size();
		return printed;
	}
}
