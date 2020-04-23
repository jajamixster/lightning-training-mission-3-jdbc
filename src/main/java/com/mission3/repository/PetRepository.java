package com.mission3.repository;

import com.mission3.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PetRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //RowMapper
    class PetRowMapper implements RowMapper<Pet> {
        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pet pet = new Pet();
            pet.setId(rs.getLong("id"));
            pet.setName(rs.getString("name"));
            pet.setDescription(rs.getString("description"));
            pet.setExotic(rs.getBoolean("is_exotic"));
            pet.setPrice(rs.getLong("price"));
            return pet;
        }
    }

    //GET all
    public List<Pet> selectAll() {
        return jdbcTemplate.query(
                "SELECT * FROM pet", new PetRowMapper());
    }

    //GET by ID
    public Pet select(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM pet WHERE id = ?", new Object[]{id}, new PetRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //POST to return generated ID using SimpleJdbcInsert
    public Pet insert(Pet pet) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("pet")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> petMapper = new HashMap<String, Object>();

        //MySQL Column name mapping with values
        petMapper.put("name", pet.getName());
        petMapper.put("description", pet.getDescription());
        petMapper.put("is_exotic", pet.isExotic());
        petMapper.put("price", pet.getPrice());

        //Execute and retrieve auto incremental ID from SQL -> object
        Long id = simpleJdbcInsert.executeAndReturnKey(petMapper).longValue();
        pet.setId(id);

        return pet;
    }

    //PUT
    public int update(Long id, Pet pet) {
        return jdbcTemplate.update(
                "UPDATE pet SET name = ?, description = ?, is_exotic = ?, price = ? WHERE id = ? ", pet.getName(), pet.getDescription(), pet.isExotic(), pet.getPrice(), id);
    }
    //DELETE
    public int delete(Long id) {
        return jdbcTemplate.update( "DELETE FROM pet WHERE id = ?", id);
    }
}
