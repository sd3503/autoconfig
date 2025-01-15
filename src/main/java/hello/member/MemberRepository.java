package hello.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    public final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initTable() {
        jdbcTemplate.execute("CREATE TABLE MEMBER (member_id VARCHAR primary key , name VARCHAR)");
    }

    public void save(Member member) {
        jdbcTemplate.update("INSERT INTO MEMBER (member_id, name) VALUES (?, ?)",
                member.getMemberId(),
                member.getName());
    }

    public Member find(String memberId) {
        return jdbcTemplate.queryForObject("SELECT * FROM MEMBER WHERE member_id = ?",
                BeanPropertyRowMapper.newInstance(Member.class),
                memberId);
    }

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER",
                BeanPropertyRowMapper.newInstance(Member.class));
    }
}
