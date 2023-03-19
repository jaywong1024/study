package top.hanjie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.hanjie.entity.Goods;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Long> {

}
