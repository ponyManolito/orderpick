package security.orderpick.validation;

import org.parboiled.common.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import security.orderpick.datamodel.in.InOrder;
import security.orderpick.datamodel.in.InOrderType;
import security.orderpick.datamodel.in.InProductInOrder;

@Component(OrderValidator.name)
public class OrderValidator implements Validator {

	public static final String name = "orderValidator";

	public OrderValidator() {}

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		InOrder inOrder = (InOrder) arg0;
		if (inOrder.getIdTable() == 0) {
			errors.reject("idTable", "The table of the order must be defined");
		}

		if (inOrder.getTypes() == null || inOrder.getTypes().isEmpty()) {
			errors.reject("types", "The order must cointains at least one type");
		} else {
			for (InOrderType inOrderType : inOrder.getTypes()) {
				if (StringUtils.isNotEmpty(inOrderType.getStatus())) {
					errors.reject("types" + inOrderType.getId(), "The order type " + inOrderType.getId()
							+ " must have a status");
				}
				if (StringUtils.isNotEmpty(inOrderType.getType())) {
					errors.reject("types" + inOrderType.getId(), "The order type " + inOrderType.getId()
							+ " must have a type");
				}
				if (inOrderType.getProducts() == null || inOrderType.getProducts().isEmpty()) {
					errors.reject("products", "The order must cointains at least one prdouct in the type "
							+ inOrderType.getId());
				} else {
					for (InProductInOrder inProductInOrder : inOrderType.getProducts()) {
						if (inProductInOrder.getIdProduct() == 0) {
							errors.reject("product" + inProductInOrder.getId(),
									"The product type " + inProductInOrder.getId() + " must have known product");
						}
						if (inProductInOrder.getQuantity() == 0) {
							errors.reject("product" + inProductInOrder.getId(),
									"The product type " + inProductInOrder.getId() + " must have more than 0 products");
						}
					}
				}
			}
		}
	}
}
