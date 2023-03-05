package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Member> memberRowMapper =
            new RowMapper<Member>() {
                @Override
                public Member mapRow(ResultSet rs, int i) throws SQLException {
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    return member;
                }
            };

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER WHERE EMAIL = ?",
                memberRowMapper,
                email
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("SELECT * FROM MEMBER",
                memberRowMapper);
        return results;
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM MEMBER", Integer.class);
        return count;
    }

    public void insert(Member member) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder(); // 자동 생성된 키 값을 구해주는 클래스
        jdbcTemplate.update((Connection con)-> {
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)", new String[] {"ID"}
                );
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                return pstmt;
        },keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());

    }

    public void update(Member member) {
        jdbcTemplate.update(
                "UPDATE MEMBER set NAME = ?, PASSWORD = ? WHERE EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail()
        );
    }

    public List<Member> selectByRegDate(
            LocalDateTime from, LocalDateTime to
    ) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where REGDATE between ? and ? " +
                        "order by REGDATE desc",
                memberRowMapper, from, to
        );
        return results;
    }

    public Member selectById(Long memId) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER WHERE ID = ?",
                memberRowMapper, memId
        );
        return results.isEmpty() ? null : results.get(0);
    }
}
