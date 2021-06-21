package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderDao;
import siit.db.OrderProductDao;
import siit.model.Order;
import siit.model.OrderProduct;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderProductDao orderProductDao;
    @Autowired
    OrderDao orderDao;

    public void deleteOrderBy(int orderId) {
        orderDao.deleteOrderProduct(orderId);
        orderDao.deleteOrderBy(orderId);
    }

    public List<OrderProduct> getOrderProductBy(int customerId, int orderId) {
        return orderProductDao.getOrderProductBy(orderId);
    }

    public Order getOrderBy(int customerId, int orderId) {
        for (Order order : orderDao.getOrdersBy(customerId)) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void insertProduct(OrderProduct orderProduct, int customerId) {

        orderProductDao.insertIntoOrderProducts(orderProduct.getOrderId(), orderProduct.getProduct().getId(), orderProduct.getQuantity());
    }



//    public OrderProduct getProductOrder(OrderProduct orderProduct, int customerId, int orderId) {
//
//        return (OrderProduct) orderDao.getProductOrder(orderProduct,  customerId,  orderId);
//    }
}



