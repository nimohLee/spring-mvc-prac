package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "SELECT * FROM MEMBER WHERE EMAIL = ?",
                (rs, rowNum) -> {
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member.setId(rs.getLong("ID"));
                    return member;
                },
                email
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("SELECT * FROM MEMBER",
                new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
                Member member = new Member(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getTimestamp("REGDATE").toLocalDateTime()
                );
                return member;
            }

                });
        return results;
    }

    public void insert(Member member) {
    }

    public void update(Member member) {

    }
}
