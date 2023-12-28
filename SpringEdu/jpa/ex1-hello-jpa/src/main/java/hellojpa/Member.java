package hellojpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Member {
    @Id
    private Long id;
    @Column(name = "name", nullable = false) // 해당 컬럼이 NOT NULL이 됨 (자주 사용)
    private String name;
    private Integer age;

    // EnumType.ORDINAL(기본)은 사용하지 말것! 이는 Enum의 순서가 저장되기 때문에 운영에 좋지 않음
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * 구버전 용
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * 최신 버전의 경우, LocalDate(연,월) 또는 LocalDateTime(연,월,일)을 사용 - 하이버네이트에서 지원!
     */
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient // 테이블에 넣고 싶지 않고, 단순히 메모리에서만 사용할 필드에 사용하는 어노테이션
    private int temp;

    public Member() {
        // JPA를 사용하는 경우, 엔티티는 기본 생성자가 있어야 한다.
    }

}
