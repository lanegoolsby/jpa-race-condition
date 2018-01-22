package com.example.racecondition.users;

import com.example.racecondition.engagement.Engagement;

import java.beans.ConstructorProperties;
import java.util.List;

public final class User {
  private final String           name;
  private final String           email;
  private final String           userId;
  private final List<Engagement> engagements;

  @ConstructorProperties({"roles", "name", "email", "userId", "engagements"})
  User(String name, String email, String userId, List<Engagement> engagements) {
    this.name = name;
    this.email = email;
    this.userId = userId;
    this.engagements = engagements;
  }

  public static User.UserBuilder builder() {
    return new User.UserBuilder();
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getUserId() {
    return this.userId;
  }

  public List<Engagement> getEngagements() {
    return this.engagements;
  }

  public static final class UserBuilder {
    private String           name;
    private String           email;
    private String           userId;
    private List<Engagement> engagements;

    UserBuilder() {
    }

    public User.UserBuilder name(String name) {
      this.name = name;
      return this;
    }

    public User.UserBuilder email(String email) {
      this.email = email;
      return this;
    }

    public User.UserBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public User.UserBuilder engagements(List<Engagement> engagements) {
      this.engagements = engagements;
      return this;
    }

    public User build() {
      return new User(this.name, this.email, this.userId, this.engagements);
    }

    public String toString() {
      return "User.UserBuilder(name=" + this.name + ", email=" + this.email + ", userId=" + this.userId + ", engagements=" + this.engagements + ")";
    }
  }
}