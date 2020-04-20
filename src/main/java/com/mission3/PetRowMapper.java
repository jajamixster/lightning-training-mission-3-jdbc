package com.mission3;

import com.mission3.model.Pet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetRowMapper implements RowMapper<Pet> {
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
