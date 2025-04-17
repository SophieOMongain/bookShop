package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.Book;
import entity.Cart;
import entity.Item;
import entity.User;
import repository.BookRepository;
import repository.CartRepository;
import repository.ItemRepository;
import repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository,
                       UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Cart getCartByUserId(Long userId) {
    	
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if(optionalCart.isPresent()){
            return optionalCart.get();
        } else {
            return createCartForUser(userId);
        }
    }

    public Cart createCartForUser(Long userId) {
    	
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
        	
            Cart cart = new Cart();
            cart.setUser(userOptional.get());
            cart.setItems(new ArrayList<>());
            return cartRepository.save(cart);
        }
        return null;
    }

    public Cart addItemToCart(Long userId, Long bookId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        
        if(bookOptional.isPresent()){
        	
            Book book = bookOptional.get();
            List<Item> items = cart.getItems();
            for(Item item : items){
            	
                if(item.getBook().getId().equals(bookId)){
                	
                    item.setQuantity(item.getQuantity() + quantity);
                    itemRepository.save(item);
                    return cartRepository.save(cart);
                }
            }
            Item newItem = new Item();
            newItem.setCart(cart);
            newItem.setBook(book);
            newItem.setQuantity(quantity);
            itemRepository.save(newItem);
            items.add(newItem);
            cart.setItems(items);
            return cartRepository.save(cart);
        }
        return cart;
    }

    public Cart removeItemFromCart(Long userId, Long itemId) {
        Cart cart = getCartByUserId(userId);
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        
        if(itemOptional.isPresent()){
        	
            Item item = itemOptional.get();
            if(item.getCart().getId().equals(cart.getId())){
            	
                cart.getItems().remove(item);
                itemRepository.delete(item);
                return cartRepository.save(cart);
            }
        }
        return cart;
    }

    public Cart updateItemQuantity(Long userId, Long itemId, Integer newQuantity) {
        Cart cart = getCartByUserId(userId);
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        
        if(itemOptional.isPresent()){
        	
            Item item = itemOptional.get();
            
            if(item.getCart().getId().equals(cart.getId())){
                item.setQuantity(newQuantity);
                itemRepository.save(item);
                return cartRepository.save(cart);
            }
        }
        return cart;
    }

    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        List<Item> items = cart.getItems();
        
        if(items != null){
        	
            for(Item item : items){
                itemRepository.delete(item);
            }
            items.clear();
            cart.setItems(items);
            cartRepository.save(cart);
        }
    }
}
