package spring;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberDao { //Data Acces Object

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Member selectByEmail(String email){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?", // ? 부분에는 , 뒤에 가변인자로 여러 값 넣을 수 있음 지금은 email
                new MemberRowMapper<Member>(),
        email);
        return results.isEmpty()? null : results.get(0);
    }

    public List<Member> selectAll(){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER", // ? 부분에는 , 뒤에 가변인자로 여러 값 넣을 수 있음 지금은 email
                new MemberRowMapper<Member>());
        return results;
    }

    public class MemberRowMapper<Member> implements RowMapper{
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            spring.Member member = new spring.Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime());
            member.setId(rs.getLong("ID"));
            return member;
        }
    }

    public void update(Member member){
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail());
    }

    public int count(){
        Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class); //queryForObject() 결과 로우가 하나일 경우 사용가능.
        return count;
    }

    public void insert(Member member){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)" +
                                "values(?, ?, ?, ?)",
                        new String[] {"ID"});
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }
}
