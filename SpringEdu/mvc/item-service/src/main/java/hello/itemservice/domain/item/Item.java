package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Data // 롬복 어노테이션을 사용하기 위해서는 컴파일러 세팅에서 Annotation Processing을 활성화 해야 한다.
@Getter @Setter // 도메인 설계에서는 @Data 보다는 @Getter @Setter 따로따로 설정하는 것을 권장
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // price가 없을 때도 있다고 가정(Null)하여 Integer로 선언
    private Integer quantity; // int로 할 경우, 기본 값으로 0이 들어가기 때문

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
