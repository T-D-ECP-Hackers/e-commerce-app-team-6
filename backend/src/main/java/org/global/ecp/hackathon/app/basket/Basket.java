package org.global.ecp.hackathon.app.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Integer totalProducts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "basket_products", joinColumns = @JoinColumn(name = "basket_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<BasketProduct> basketProducts = new ArrayList<>();

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final Basket basket = (Basket) o;
        return id != null && Objects.equals(id, basket.id);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }
}
