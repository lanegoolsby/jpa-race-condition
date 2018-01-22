package com.example.racecondition.users;

import com.example.racecondition.engagement.Engagement;
import com.example.racecondition.User;

public interface UsersAuthService {
  Engagement addUserTo(Long engagement, String userid, String authorization, User assigningUser);
}