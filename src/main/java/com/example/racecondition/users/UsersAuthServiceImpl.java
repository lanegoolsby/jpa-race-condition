package com.example.racecondition.users;

import com.example.racecondition.engagement.Engagement;
import com.example.racecondition.engagement.EngagementsRepository;
import com.example.racecondition.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

public class UsersAuthServiceImpl implements UsersAuthService{
  private final UsersAuthService      users;
  private final EngagementsRepository engagements;

  public UsersAuthServiceImpl(UsersAuthService users, EngagementsRepository engagements) {
    this.users = users;
    this.engagements = engagements;
  }

  @Override
  @Transactional(isolation = SERIALIZABLE)
  public Engagement addUserTo(Long engagementId, String userId, String authorization, User assigningUser) {
    Engagement engagement = engagements.findById(engagementId);

    engagement.getAssignedUsers().add(userId);
    engagement.setUpdatedOn(LocalDateTime.now());

    return engagements.save(engagement);
  }}
