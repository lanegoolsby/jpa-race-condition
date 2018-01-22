package com.example.racecondition.users;

import com.example.racecondition.engagement.Engagement;

public interface UsersAuthService {
  Engagement addUserTo(Long engagement, String userid, String authorization, User assigningUser);
}