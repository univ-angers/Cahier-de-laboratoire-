package com.groupe6.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.groupe6.beans.*;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Utilisateur> listUserByName(String name) {
		String sql = "select * from user where name like :name";

		List<Utilisateur> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(name), new UserMapper());

		return list;
	}

	private static final class UserMapper implements RowMapper<Utilisateur> {
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur user = new Utilisateur();
			user.setId((long) rs.getInt("id"));
			user.setNom(rs.getString("name"));
			return user;
		}
	}

	private SqlParameterSource getSqlParameterByModel(String name) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", "%"+name+"%");
		return paramSource;
	}
}
