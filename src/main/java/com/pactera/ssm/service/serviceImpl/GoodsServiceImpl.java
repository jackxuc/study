package com.pactera.ssm.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pactera.ssm.entities.Goods;
import com.pactera.ssm.mapper.GoodsDao;
import com.pactera.ssm.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService{
	@Resource
	private GoodsDao dao; 
	public List<Goods> getGoodsPager(Goods goods) {
		int start=(goods.getPage()-1)*goods.getSize();
		goods.setStart(start);
		return dao.getGoodsPager(goods);
	}
	public Goods getGoodsById(Goods goods) {
		return dao.getGoodsById(goods);
	}
	public int deleteById(Goods goods) {
		return dao.deleteById(goods);
	}
	public int deletesGoods(int[] ids) {
		Goods goods=new Goods();
		int successnum=0;
		for(int i = 0; i < ids.length; i++) {
			goods.setId(ids[i]+"");
			successnum+=dao.deleteById(goods);
		}
		return successnum;
	}
	@Override
	public int saveGoods(Goods goods) {
		return dao.saveGoods(goods);
	}
	@Override
	public int addGoods(Goods goods) {
		return dao.addGoods(goods);
	}
	@Override
	public int getGoodsCount() {
		return dao.getGoodsCount();
	}
}
