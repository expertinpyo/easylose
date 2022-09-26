package com.easylose.backend.api.v1.repository.specification;

import com.easylose.backend.api.v1.domain.Food;
import com.easylose.backend.api.v1.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class FoodSpecification {
  public static Specification<Food> containName(String name, User user) {
    return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
  }

  public static Specification<Food> myFoodUser(User user) {
    return (root, query, builder) ->
        builder.or(builder.equal(root.get("user"), user), builder.isNull(root.get("user")));
  }

  public static Specification<Food> equalBarcode(String code) {
    return (root, query, builder) -> builder.equal(root.get("barcode"), code);
  }
}
