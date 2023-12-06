package com.api.quarkus.social.repository;

import com.api.quarkus.social.entity.Follower;
import com.api.quarkus.social.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
    public boolean follows(User follower, User user) {
        var params = Parameters.with("follower", follower).and("user", user).map();
        var query = find("follower=:follower and user=:user", params);
        var result = query.firstResultOptional();
        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId) {
        var query = find("user.id", userId);
        return query.list();
    }
}
