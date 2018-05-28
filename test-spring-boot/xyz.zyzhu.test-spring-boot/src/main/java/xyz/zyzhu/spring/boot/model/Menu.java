package xyz.zyzhu.spring.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 标题： TODO
 * </p>
 * <p>
 * 功能：
 * </p>
 * <p>
 * 所属模块： TODO
 * </p>
 * <p>
 * 版权： Copyright © 2017 SNSOFT
 * </p>
 * <p>
 * 公司: 赵玉柱练习
 * </p>
 * <p>
 * 创建日期：2017年12月3日 上午12:04:12
 * </p>
 * <p>
 * 类全名：xyz.zyzhu.spring.boot.model.Menu
 * </p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
@Entity
@Table(name = "menu")
public class Menu extends BasModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer mid;
	String mname;
	Integer offlags;
	String url;
	String limits;

	/**
	 * @param mname
	 * @param offlags
	 * @param url
	 * @param limits
	 */
	public Menu(String mname, Integer offlags, String url, String limits) {
		super();
		this.mname = mname;
		this.offlags = offlags;
		this.url = url;
		this.limits = limits;
	}

	/**
	 * 
	 */
	public Menu() {
		super();
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getOfflags() {
		return offlags;
	}

	public void setOfflags(Integer offlags) {
		this.offlags = offlags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLimits() {
		return limits;
	}

	public void setLimits(String limits) {
		this.limits = limits;
	}

}
