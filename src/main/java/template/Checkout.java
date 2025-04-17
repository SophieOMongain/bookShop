package template;

import org.springframework.stereotype.Component;
import entity.Cart;
import entity.Item;
import entity.Order;
import entity.OrderItem;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class Checkout extends CheckoutTemplate {

    @Override
    protected void validateCart(Cart cart) {
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
    }

    @Override
    protected void applyDiscounts(Cart cart) {
        for (Item item : cart.getItems()) {
        }
    }

    @Override
    protected void calculateTotal(Cart cart) {
        double total = 0.0;
        for (Item item : cart.getItems()) {
            total += item.getBook().getPrice() * item.getQuantity();
        }
        cart.setTotal(total); 
    }

    @Override
    protected Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setTotal(cart.getTotal());
        List<OrderItem> orderItems = new ArrayList<>();

        for (Item item : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setBook(item.getBook());
            oi.setQuantity(item.getQuantity());
            oi.setPrice(item.getBook().getPrice());
            oi.setOrder(order);
            orderItems.add(oi);
        }

        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    protected void updateStock(Cart cart) {
        for (Item item : cart.getItems()) {
            int currentStock = item.getBook().getStock();
            item.getBook().setStock(currentStock - item.getQuantity());
        }
    }
}
