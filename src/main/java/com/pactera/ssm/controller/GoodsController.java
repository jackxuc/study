package com.pactera.ssm.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pactera.ssm.entities.Goods;
import com.pactera.ssm.entities.UserInfo;
import com.pactera.ssm.service.GoodsService;
import com.pactera.ssm.service.UserService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	private GoodsService goodsService;

	@RequestMapping(value = "/goodslist")
	public String getGoodsList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "5") int pageSize) {
		Goods goods = new Goods();
		int count = goodsService.getGoodsCount();
		model.addAttribute("size", pageSize);
		model.addAttribute("pageNO", page);
		model.addAttribute("count", count);
		goods.setPage(page);
		goods.setSize(pageSize);
		model.addAttribute("goods", goodsService.getGoodsPager(goods));
		return "/goods/goods";
	}

	/*
	 * 鍒犻櫎鍗曚釜浜у搧瀵硅薄Action
	 */
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id,
			@RequestParam(required = false, defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes) {
		Goods goods = new Goods();
		goods.setId(id + "");
		if (goodsService.deleteById(goods) > 0) {
			redirectAttributes.addFlashAttribute("message", "鍒犻櫎鎴愬姛锛�");
		} else {
			redirectAttributes.addFlashAttribute("message", "鍒犻櫎澶辫触锛�");
		}
		return "redirect:/goods/goodslist?pageNO=" + pageNO;
	}

	/*
	 * 鍒犻櫎澶氫釜浜у搧瀵硅薄Action
	 */
	@RequestMapping("/deletes")
	public String deletes(Model model, @RequestParam int[] id,
			@RequestParam(required = false, defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes) {
		// 鎵ц鍒犻櫎
		int rows = goodsService.deletesGoods(id);
		if (rows > 0) {
			redirectAttributes.addFlashAttribute("message", "鍒犻櫎" + rows + "琛岃褰曟垚鍔燂紒");
		} else {
			redirectAttributes.addFlashAttribute("message", "鍒犻櫎澶辫触锛�");
		}
		return "redirect:/goods/goodslist?pageNO=" + pageNO;
	}

	@RequestMapping("/edit/{id}")
	public String editGoods(Model model, @PathVariable int id,
			@RequestParam(required = false, defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes) {
		Goods goods = new Goods();
		goods.setId(id + "");
		Goods goodsResult = goodsService.getGoodsById(goods);
		model.addAttribute("editGoods", goodsResult);
		return "/goods/editGoods";
	}

	@RequestMapping("/save")
	public String editSave(Model model, @ModelAttribute("editGoods") @Valid Goods editGoods,
			BindingResult bindingResult) {
		// 如果模型中存在错误
		if (!bindingResult.hasErrors()) {
			goodsService.saveGoods(editGoods);
			return "redirect:goodslist";
		}
		model.addAttribute("entity", editGoods);
		return "/goods/editGoods";
	}

	@RequestMapping("/openadd")
	public String openaddGoods(Model model, @ModelAttribute("addGoods") Goods addGoods) {
		return "/goods/addGoods";
	}

	@RequestMapping("/add")
	public String addGoods(Model model, @ModelAttribute("addGoods") @Valid Goods addGoods,
			BindingResult bindingResult) {
		// 如果模型中存在错误
		if (!bindingResult.hasErrors()) {
			goodsService.addGoods(addGoods);
			return "redirect:goodslist";
		}
		model.addAttribute("entity", addGoods);
		return "/goods/addGoods";
	}

	@RequestMapping("/uploadPicture/{id}")
	public String addGoods(Model model, @PathVariable int id) {
		Goods goods = new Goods();
		goods.setId(id + "");
		model.addAttribute("entity", goodsService.getGoodsById(goods));
		return "/goods/uploadGoodsPicture";
	}

	/*
	 * 上传图片保存
	 */
	@RequestMapping("/upPictureSave/{id}")
	public String upPictureSave(Model model, @PathVariable int id, MultipartFile picFile, HttpServletRequest request) {
		Goods goods = new Goods();
		goods.setId(id + "");
		Goods entity = goodsService.getGoodsById(goods);
		// 如果选择了文件
		if (picFile != null) {
			// 如果文件大小不为0
			if (picFile.getSize() > 0) {
				// 获得上传位置
				String path = request.getServletContext().getRealPath("/images");
				System.out.println(path);
				// 生成文件名
				String filename = UUID.randomUUID().toString()
						+ picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf("."));
				File tempFile = new File(path, filename);
				try {
					// 保存文件
					picFile.transferTo(tempFile);
					// 更新数据
					entity.setPicture(filename);
					goodsService.saveGoods(entity);
					// 转向列表页
					return "redirect:/goods/goodslist";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("entity", entity);
		return "goods/upfile";
	}
}