package com.tts.stock.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tts.stock.dto.ItemDto;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemId;
	private String itemName;
	private String itemCode;
	private int unitId;
	private int reorderLevel;
	private String remark;
	private Date created_at;
	private Date updated_at;
	
	public Item() {
		super();
	}

	public Item(ItemDto dto) {
		// TODO Auto-generated constructor stub
		this.itemId = dto.getItemId();
		this.itemName = dto.getItemName();
		this.itemCode = dto.getItemCode();
		this.unitId = dto.getUnitDto().getUnitId();
		this.reorderLevel = dto.getReorderLevel();
		this.remark = dto.getRemark();
		this.created_at = dto.getCreated_at();
		this.updated_at = dto.getUpdated_at();
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getUnitId() {
		return unitId;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public String getRemark() {
		return remark;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
