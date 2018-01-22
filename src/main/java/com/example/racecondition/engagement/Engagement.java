package com.example.racecondition.engagement;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.OptimisticLockType.DIRTY;

@Entity
@DynamicUpdate
@DynamicInsert
@JsonInclude(NON_EMPTY)
@ApiModel()
@OptimisticLocking(type = DIRTY)
public class Engagement implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "Generated engagements id")
  @Column(name = "id")
  private Long id;

  @ElementCollection(fetch = EAGER)
  private List<String> assignedUsers;

  @Version
  private Long version;

  private LocalDateTime updatedOn;

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

