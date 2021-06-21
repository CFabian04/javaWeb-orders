package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.model.Product;
import siit.sevices.OrderProductService;
import siit.sevices.OrderService;
import siit.sevices.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/customers/{customerId}/orders/{orderId}")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderProductService orderProductService;


    @GetMapping
    public Order getOrderBy(@PathVariable int customerId, @PathVariable int orderId) {
        return orderService.getOrderBy(customerId, orderId);

    }

    @GetMapping("/products")
    public List<OrderProduct> getOrderProducts(@PathVariable int customerId, @PathVariable int orderId) {
        return orderService.getOrderProductBy(customerId, orderId);

    }

    @PostMapping("/products")
    public OrderProduct addProduct(@RequestBody OrderProduct orderProduct, @PathVariable int customerId, @PathVariable int orderId) {
         orderProductService.addProductOrder(orderProduct,customerId,orderId);


        List<OrderProduct>products=orderService.getOrderProductBy(customerId, orderId);

        for(OrderProduct orderProduct1:products){
            System.out.println(orderProduct1.getProduct());
            System.out.println(orderProduct1.getValue());
            if(orderProduct1.getProduct().getId()==orderProduct.getProduct().getId()){
                System.out.println(orderProduct1.getProduct());

//                OrderProduct mockedOrderProduct = new OrderProduct();
//                     mockedOrderProduct.setId(orderId);
//                     mockedOrderProduct.setName(orderProduct1.getName());
//                        mockedOrderProduct.setQuantity(orderProduct1.getQuantity());
//                        mockedOrderProduct.setValue(BigDecimal.ONE);
//
//                        Product product = new Product();
//                        product.setId(orderProduct1.getProduct().getId());
//                        product.setName(orderProduct1.getName());
//                        product.setWeight(orderProduct1.getProduct().getWeight());
//                        product.setPrice(orderProduct1.getProduct().getPrice());
//
//                        mockedOrderProduct.setProduct(product);


                return orderProduct1;
            }
        }


//

        return orderProduct;

    }
    @RequestMapping ("/products/{orderProductId}")
    public void deleteOrderProduct(@PathVariable int orderProductId,@PathVariable int customerId,@PathVariable int orderId){
        orderProductService.deleteOrderBy(orderProductId);
    }
}
