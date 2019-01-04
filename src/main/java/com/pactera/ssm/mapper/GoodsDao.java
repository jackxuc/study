package com.pactera.ssm.mapper;

import java.util.List;

import com.pactera.ssm.entities.Goods;

public interface GoodsDao {
	public List<Goods> getGoodsPager(Goods goods);
	public Goods getGoodsById(Goods goods);
	public int deleteById(Goods goods);
	public int saveGoods(Goods goods);
	public int addGoods(Goods goods);
	public int getGoodsCount();
}
