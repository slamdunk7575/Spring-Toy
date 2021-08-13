package me.study.core.order.application;

import me.study.core.order.domain.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
