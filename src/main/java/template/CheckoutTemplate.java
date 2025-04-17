package template;

import entity.Cart;
import entity.Order;

public abstract class CheckoutTemplate {

    public final Order checkout(Cart cart) {
        validateCart(cart);
        applyDiscounts(cart);
        calculateTotal(cart);
        Order order = createOrder(cart);
        updateStock(cart);
        return order;
    }
    protected abstract void validateCart(Cart cart);
    protected abstract void applyDiscounts(Cart cart);
    protected abstract void calculateTotal(Cart cart);
    protected abstract Order createOrder(Cart cart);
    protected abstract void updateStock(Cart cart);
}
