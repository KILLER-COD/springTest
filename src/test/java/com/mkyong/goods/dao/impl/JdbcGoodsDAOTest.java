package com.mkyong.goods.dao.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcGoodsDAOTest {

    private JdbcGoodsDAO jdbcGoodsDAO = new JdbcGoodsDAO();

    @Test
    void deleteSoft() {
        int result = jdbcGoodsDAO.plus(1, 3);
        assertTrue(result == 4);
    }

    @Test
    void deleteSoftWhenNumbersAreBig() {
        int result = jdbcGoodsDAO.plus(1000, 3000);
        assertTrue(result == 4000);
    }

    @Test
    void deleteSoftWhenBothAreNegative() {
        int result = jdbcGoodsDAO.plus(-1, -3);
        assertTrue(result == -4);
    }


}