package service;


/*
* 퍼사드 패턴의 특징 (복잡한 시스템이나 서브시스템을 단순화하여 외부에서 쉽게 접근할 수 있게 해주는 패턴입니다.)
*
1. 단순화된 인터페이스 제공: 여러 기능을 하나로 묶어서 단일 인터페이스만으로도 필요한 작업을 처리할 수 있습니다.
2. 결합도 감소: 외부에서 시스템의 구체적인 세부 사항을 알 필요 없이 퍼사드가 제공하는 인터페이스만 사용하므로 결합도가 낮아집니다.
3. 유지보수성 향상: 시스템 내부가 변경되더라도 퍼사드의 인터페이스만 유지된다면, 외부 코드에는 영향을 주지 않습니다.
*
*
*
* */

@Service
public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;

    public OrderFacade(InventoryService inventoryService, PaymentService paymentService, ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
    }

    public boolean placeOrder(String productId, String paymentInfo, String orderId) {
        // 재고 확인
        if (!inventoryService.checkStock(productId)) {
            return false;
        }

        // 결제 처리
        if (!paymentService.processPayment(paymentInfo)) {
            return false;
        }

        // 배송 처리
        shippingService.arrangeShipping(orderId);

        return true;
    }
}
