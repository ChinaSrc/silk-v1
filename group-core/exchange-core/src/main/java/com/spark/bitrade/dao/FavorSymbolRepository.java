package com.spark.bitrade.dao;

import com.spark.bitrade.entity.FavorSymbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorSymbolRepository extends JpaRepository<FavorSymbol,Long>{
    List<FavorSymbol> findByMemberIdAndSymbol(Long memberId,String symbol);
    List<FavorSymbol> findAllByMemberId(Long memberId);
}
