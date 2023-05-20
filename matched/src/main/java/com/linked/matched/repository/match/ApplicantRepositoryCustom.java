package com.linked.matched.repository.match;

import com.linked.matched.entity.Post;
import com.linked.matched.response.user.SelectUser;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ApplicantRepositoryCustom {
    List<SelectUser> getUserList(Post post);
}
