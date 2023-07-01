package hello.itemservice.domain.item;

public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    // Getter가 필요한 이유 : 타임리프에서 해당 description 변수에 접근하는 것은 프로퍼티 접근법이기 때문에 실제로 get 함수를 호출하기 때문이다. 따라서 getter가 정의되어 있지 않으면 오류 발생
    public String getDescription() {
        return description;
    }
}
