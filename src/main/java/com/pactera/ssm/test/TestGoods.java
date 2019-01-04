package com.pactera.ssm.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.pactera.ssm.entities.Goods;
import com.pactera.ssm.mapper.GoodsDao;

import junit.framework.Assert;

public class TestGoods {
	@Test
	public void testGetGoodsPager() {
		Goods goods=new Goods();
		goods.setStart(3);
		goods.setSize(3);
		SqlSession session=MyBatisUtil.getSession();
		try {
			GoodsDao dao=session.getMapper(GoodsDao.class);
			List<Goods> goodslist=dao.getGoodsPager(goods);
			Goods goods2=goodslist.get(0);
			Assert.assertEquals(goods2.getId(), "4");
		} finally {
			session.close();
		}
	}
}
