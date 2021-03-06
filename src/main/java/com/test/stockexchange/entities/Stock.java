package com.test.stockexchange.entities;

import java.util.UUID;

/** represents a stock **/
public class Stock {

  private final UUID id;
  private final String name;

  public Stock( String name) {
    this.id =UUID.randomUUID();
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Stock other = (Stock) obj;
    return this.name.equals(other.getName());
  }
}
