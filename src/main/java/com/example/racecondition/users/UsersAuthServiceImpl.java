package com.example.racecondition.users;

import com.example.racecondition.engagement.Engagement;
import com.example.racecondition.engagement.EngagementsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
public class UsersAuthServiceImpl implements UsersAuthService{
  @PersistenceContext
  private EntityManager entityManager;

  private final EngagementsRepository engagements;

  public UsersAuthServiceImpl(EngagementsRepository engagements) {
    this.engagements = engagements;
  }

  @Override
  @Transactional(isolation = SERIALIZABLE)
  public Engagement addUserTo(Long engagementId, String userId, String authorization, User assigningUser) {
    Engagement engagement = engagements.findById(engagementId);

    engagement.getAssignedUsers().add(userId);
    engagement.setUpdatedOn(LocalDateTime.now());

    entityManager.persist(engagement);
    engagement = engagements.findById(engagementId);
    return engagement;
  }}
