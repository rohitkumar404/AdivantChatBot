package com.agivantChatBot.demo.Repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CallDataRepository {

    private final JdbcTemplate jdbcTemplate;

    public CallDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String,Object>> runDynamicQuery(String dynamicQuery) {
        // Execute the dynamic query using JdbcTemplate
        return jdbcTemplate.queryForList(dynamicQuery);
    }
}
