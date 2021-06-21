package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getOrdersBy(int customerId) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE customer_id = ?",
                this::getOrder, customerId);
    }

    public void deleteOrderBy(int orderId) {
        jdbcTemplate.update("DELETE FROM orders WHERE id = ?", orderId);
    }

    public void deleteOrderProduct(int orderId) {
        jdbcTemplate.update("DELETE FROM orders_products WHERE order_id = ?", orderId);
    }

    private Order getOrder(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setNumber(resultSet.getString("number"));
        order.setPlaced(resultSet.getTimestamp("placed").toLocalDateTime());
        return order;
    }



//    public List<Object> getProductOrder(OrderProduct orderProduct, int customerId, int orderId) {
////
//        return  jdbcTemplate.query(""
//                + "SELECT  op.order_id,op.id, op.quantity, p.name, "
//                + "        op.quantity * p.price AS value, p.id as product_id, p.weight, p.price AS price "
//                + "FROM ORDERS_PRODUCTS op "
//                + "JOIN products p on p.id = op.product_id "
//                + "WHERE PRODUCT_ID=? AND ORDER_ID=?", this::getProduct, orderProduct.getProduct().getId(),orderId);
//
//    }
//
//    private Object getProduct(ResultSet resultSet, int rowNum) throws SQLException {
//        OrderProduct orderProduct = new OrderProduct();
//        orderProduct.setId(resultSet.getInt("product_id"));
//        orderProduct.setQuantity(resultSet.getBigDecimal("quantity"));
//        orderProduct.setName(resultSet.getString("name"));
//        orderProduct.setValue(resultSet.getBigDecimal("value"));
//        orderProduct.setOrder_id(resultSet.getInt("order_id"));
//
//        return orderProduct;
//    }


}
