package com.pactera.ssm.service;

import java.util.List;

import com.pactera.ssm.entities.Goods;

public interface GoodsService {
	public List<Goods> getGoodsPager(Goods goods);
	public Goods getGoodsById(Goods goods);
	public int deleteById(Goods goods);
	public int deletesGoods(int[] ids);
	public int saveGoods(Goods goods);
	public int addGoods(Goods goods);
	public int getGoodsCount();
}
