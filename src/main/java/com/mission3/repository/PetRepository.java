package com.mission3.repository;

import com.mission3.PetRowMapper;
import com.mission3.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Pet> selectAll() {
        return jdbcTemplate.query(
                "SELECT * FROM pet", new PetRowMapper());
    }

    public Pet select(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM pet WHERE id = ?", new Object[]{id}, new PetRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int insert(Pet pet) {
        return jdbcTemplate.update(
                "INSERT INTO pet(name, description, is_exotic, price) VALUES (?, ?, ?, ?)", pet.getName(), pet.getDescription(), pet.isExotic(), pet.getPrice());
    }

    public int update(Long id, Pet pet) {
        return jdbcTemplate.update(
                "UPDATE pet SET name = ?, description = ?, is_exotic = ?, price = ? WHERE id = ? ", pet.getName(), pet.getDescription(), pet.isExotic(), pet.getPrice(), id);
    }


    public int delete(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM pet WHERE id = ?", id);
    }
}
