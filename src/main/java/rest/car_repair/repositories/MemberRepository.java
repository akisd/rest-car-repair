package rest.car_repair.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.car_repair.domain.Member;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findAll();

    Member findOne(long id);

    Member save(Member member);

    void deleteByUserId(long id);


}