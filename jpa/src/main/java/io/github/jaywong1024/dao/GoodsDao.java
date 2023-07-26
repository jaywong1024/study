package io.github.jaywong1024.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.jaywong1024.entity.Goods;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Long> {

}
