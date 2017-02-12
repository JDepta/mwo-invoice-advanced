package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class TaxFreeProduct extends Product {
	public TaxFreeProduct(String name, BigDecimal price)  {
		super(name, price, BigDecimal.ZERO);
//		if (name == "")
//			throw new IllegalArgumentException();
//		else if (price.compareTo(BigDecimal.ZERO) < 0)
//			throw new IllegalArgumentException();
	}
}
