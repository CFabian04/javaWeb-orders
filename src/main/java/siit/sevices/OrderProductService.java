package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderProductDao;
import siit.model.OrderProduct;

import java.util.List;

@Service
public class OrderProductService {
@Autowired
    OrderProductDao orderProductDao;

    public void deleteOrderBy(int orderProductId) {
        orderProductDao.deleteOrderProduct(orderProductId);
    }


    public void addProductOrder(OrderProduct orderProduct, int customerId, int orderId) {
        int count=0;
        List<OrderProduct> newList = orderProductDao.getOrderProductBy(orderId);
        for (OrderProduct list : newList) {
            if (list.getProduct().getId() == orderProduct.getProduct().getId()) {
                orderProductDao.updateDuplicateProduct(orderProduct, customerId, orderId);
                count++;
            }
        }
        if (count==0) {
            orderProductDao.addProductOrder(orderProduct, customerId, orderId);

        }
    }
}
