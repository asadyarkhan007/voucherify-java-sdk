package io.voucherify.client.model.validationRules;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class Conditions<T> {

  private Map<Operator, List<T>> conditions = new HashMap<Operator, List<T>>();

  public Conditions<T> setCondition(Operator operator, List<T> values) {
    conditions.put(operator, values);
    return this;
  }

  public Map<Operator, List<T>> getConditions() {
    return conditions;
  }
}