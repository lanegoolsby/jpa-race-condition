package com.example.racecondition.engagement;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
public class Engagement implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ElementCollection(fetch = EAGER)
  private List<String> assignedUsers;

  @Version
  private Long version;

  private LocalDateTime updatedOn;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion(){return version;}
  public void setVersion(Long version){this.version = version;}

  public LocalDateTime getUpdatedOn(){
    return updatedOn;
  }
  public void setUpdatedOn(LocalDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public List<String> getAssignedUsers() {
    return assignedUsers;
  }
  public void setAssignedUsers(List<String> assignedUsers) {
    this.assignedUsers = assignedUsers;
  }

  public Engagement() {
  }
}

